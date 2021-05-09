package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.User;
import project.pbo.game.Card;
import project.pbo.game.PlayerCard;
import project.pbo.game.enemy.Enemy;
import project.pbo.game.enemy.MonsterTree;
import project.pbo.game.enemy.Skeleton;
import project.pbo.game.enemy.Slime;
import project.pbo.game.helper.Healing;
import project.pbo.game.helper.Poison;
import project.pbo.game.helper.Shield;
import project.pbo.game.helper.Sword;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.KeyManager;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.Random;

public class GameState extends State implements SIZE {

    private final User user;
    private int gold = 0;
    private final Rectangle exitBtn = new Rectangle(950,7, 100, 35 );
    private final Rectangle replayBtn = new Rectangle(300,350, 150, 35 );
    private final Rectangle continueBtn = new Rectangle(300,350, 150, 35 );
    private final Rectangle loseBtn = new Rectangle(650,350, 150, 35 );
    private Clip clip;
    private int health;
    private int maxHealth;
    private int damage;
    private int defend;

    private Card[][] cards;

//    Player
    private int x, y;

    private int counter = 0, timer = 0;
    private int ctrMummy = 0;
    private boolean delay = false;

    private boolean running = true;
    private boolean kalah = false;
    private boolean exit = false;

    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    public GameState(Handler handler, User user){
        super(handler);
        this.user = user;
        this.keyManager = handler.getKeyManager();
        this.mouseManager = handler.getMouseManager();

        init();
    }

    void init(){
        this.x = 0; this.y = 0;
        cards = new Card[3][3];
        health = user.getPlayer().getHealth();
        maxHealth = user.getPlayer().getHealth();
        damage = user.getPlayer().getDamage();
        defend = user.getPlayer().getDefend();
        cards[x][y] = new PlayerCard();
        randomCard();
    }

    @Override
    public void tick() {

        keyManager.tick();
        if (!running){
            clip.stop();
            clip.setFramePosition(0);
            setCurrentState(new LoadingState(handler, new MainMenu(handler, user)));
        }

        if ((kalah || exit) && loseBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) running = false;
        if (kalah && replayBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) {
            init();
            kalah = false;
        }

        if (exit && continueBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) exit = false;

        if (exitBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) exit = true;

        if (health == 0){
            kalah = true;
        }


        if (delay){
            counter++;
            if (counter > 10){
                counter = 0;
                delay = false;
            }
        } else {
            if (keyManager.up) { moveKartu(0, -1); }
            if (keyManager.down) moveKartu(0, 1);
            if (keyManager.left) moveKartu(-1, 0);
            if (keyManager.right) { moveKartu(1, 0);}
            if (keyManager.up || keyManager.down || keyManager.left || keyManager.right) delay = true;
        }
    }

    void moveKartu(int x, int y){
        int tempx = this.x + x, tempy = this.y + y;
        if (tempx >= 0  && tempx <= 2 && tempy >= 0 && tempy <= 2){
            Card card = cards[tempy][tempx];
            if (card instanceof Enemy){
                Enemy enemy = (Enemy) card;
                if (damage > 0) {
                    int tempH = enemy.getHealth();
                    enemy.setHealth(Math.max(enemy.getHealth() - damage, 0));
                    damage = Math.max(damage - tempH, 0);
                } else if (defend > 0) {
                    int tempH = enemy.getHealth();
                    enemy.setHealth(Math.max(enemy.getHealth() - defend, 0));
                    defend = Math.max(defend - tempH, 0);
                } else {
                    int tempH = enemy.getHealth();
                    enemy.setHealth(Math.max(enemy.getHealth() - health, 0));
                    health = Math.max(health - tempH, 0);
                }

                if (enemy.getHealth() == 0){
                    cards[tempy][tempx] = cards[this.y][this.x];
                    cardPindah();
                    this.x = tempx; this.y = tempy;
                }
            } else {
                if (card instanceof Sword){
                    Sword sword = (Sword) card;
                    damage = Math.max(user.getPlayer().getDamage() + sword.getDamage(), damage);
                }
                if (card instanceof Healing){
                    Healing healing = (Healing) card;
                    health = Math.min(health + healing.getHeal(), maxHealth);
                }
                if (card instanceof Poison){
                    Poison poison = (Poison) card;
                    health = Math.max(health + poison.getPoison(), 0);
                }
                if (card instanceof Shield){
                    Shield shield = (Shield) card;
                    defend = Math.max(user.getPlayer().getDefend() + shield.getDefend(), defend);
                }

                cards[tempy][tempx] = cards[this.y][this.x];
                cardPindah();
                this.x = tempx; this.y = tempy;

            }

            randomCard();
        }
    }

    void cardPindah(){
        if (keyManager.up){
            if (y != 2) cards[1][this.x] = cards[2][this.x];
            cards[2][this.x] = null;
        }
        if (keyManager.down){
            if (y != 0) cards[1][this.x] = cards[0][this.x];
            cards[0][this.x] = null;
        }
        if (keyManager.left){
            if (x != 2) cards[this.y][1] = cards[this.y][2];
            cards[this.y][2] = null;
        }
        if (keyManager.right){
            if (x != 0) cards[this.y][1] = cards[this.y][0];
            cards[this.y][0] = null;
        }
    }

    void randomCard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (cards[i][j] == null){
                    Random random = new Random();
                    int rand = random.nextInt(7);
                    int stage = user.getPlayer().getStage();
                    if (rand == 0) cards[i][j] = new MonsterTree(stage);
                    if (rand == 1) cards[i][j] = new Skeleton(stage);
                    if (rand == 2) cards[i][j] = new Slime(stage);
                    if (rand == 3) cards[i][j] = new Healing();
                    if (rand == 4) cards[i][j] = new Poison();
                    if (rand == 5) cards[i][j] = new Shield();
                    if (rand == 6) cards[i][j] = new Sword();
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
//        Background
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);

//        Tab bar
        g.setColor(Color.gray);
        g.fillRect(0,0,width,50);
        Text.drawString(g, "Stage: " + user.getPlayer().getStage(), 10, 30, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Gold: " + gold, 180, 30, false, Color.WHITE, Assets.regulerFont);
        g.setColor(Color.red);
        ((Graphics2D) g).fill(exitBtn);
        Text.drawString(g, "Exit", 1000, 25, true, Color.WHITE, Assets.regulerFont);

//        Hero Memu
        g.setColor(Color.red);
        g.drawRect(700, 80, 330, 90);
        g.drawRect(700, 170, 330, 290);
        g.drawRect(700, 460, 331, 150);
        Text.drawString(g, user.getUsername(), 870, 125, true, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Health: " + health + " / " + maxHealth, 720, 495, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Damage: " + damage, 720, 540, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Defend: " + defend, 720, 585, false, Color.WHITE, Assets.regulerFont);

//        Game
        cetakKartu(g);

        if (kalah){
            g.setColor(Color.black);
            g.fillRect(190, 150, 700, 300);
            g.setColor(Color.white);
            g.drawRect(190, 150, 700, 300);
            Text.drawString(g, "YOU DIED", 540, 250, true, Color.RED, Assets.regulerFont);
            g.setColor(Color.RED);
            ((Graphics2D) g).fill(replayBtn);
            ((Graphics2D) g).fill(loseBtn);
            Text.drawString(g, "Restart", 375, 368, true, Color.white, Assets.regulerFont);
            Text.drawString(g, "Exit", 725, 368, true, Color.white, Assets.regulerFont);
        }

        if (exit){
            g.setColor(Color.black);
            g.fillRect(190, 150, 700, 300);
            g.setColor(Color.white);
            g.drawRect(190, 150, 700, 300);
            Text.drawString(g, "Are you sure want to exit ?", 540, 250, true, Color.RED, Assets.regulerFont);
            Text.drawString(g, "You will not keep gold on this stage", 540, 300, true, Color.WHITE, Assets.regulerFont);
            g.setColor(Color.red);
            ((Graphics2D) g).fill(continueBtn);
            ((Graphics2D) g).fill(loseBtn);
            Text.drawString(g, "Continue", 375, 368, true, Color.white, Assets.regulerFont);
            Text.drawString(g, "Exit", 725, 368, true, Color.white, Assets.regulerFont);
        }

    }

    void cetakKartu(Graphics g){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Card c = cards[i][j];
                g.setColor(Color.red);
                g.drawRect((j*184)+40, (i*177)+80, 184, 177);
                if (c instanceof PlayerCard){
                    Text.drawString(g, ""+damage, (j*184)+190, (i*177)+230, true, Color.WHITE, Assets.regulerFont);
                    Text.drawString(g, ""+defend, (j*184)+70, (i*177)+230, true, Color.WHITE, Assets.regulerFont);
                    Text.drawString(g, ""+health, (j*184)+190, (i*177)+105, true, Color.WHITE, Assets.regulerFont);
                    Text.drawString(g, c.getSymbol(), (j*184)+132, (i*177)+168, true, Color.WHITE, Assets.regulerFont);
                }
                else {


                    if(c.getSymbol().equalsIgnoreCase("s")){
                        g.drawImage(Assets.mummyEnemy[ctrMummy], (j*184)+38, (i*177)+82, 179, 172, null);
                    } else {
                        Text.drawString(g, c.getSymbol(), (j * 184) + 132, (i * 177) + 168, true, Color.WHITE, Assets.regulerFont);
                    }


                    if (c instanceof Enemy){
                        Enemy enemy = (Enemy) c;
                        Text.drawString(g, "" + enemy.getHealth(), (j * 184) + 190, (i * 177) + 105, true, Color.WHITE, Assets.regulerFont);
                    }
                    if (c instanceof Sword){
                        Sword sword = (Sword) c;
                        Text.drawString(g, ""+ sword.getDamage(), (j*184)+190, (i*177)+230, true, Color.WHITE, Assets.regulerFont);
                    }
                    if (c instanceof Healing){
                        Healing healing = (Healing) c;
                        Text.drawString(g, ""+healing.getHeal(), (j*184)+190, (i*177)+105, true, Color.WHITE, Assets.regulerFont);
                    }
                    if (c instanceof Poison){
                        Poison poison = (Poison) c;
                        Text.drawString(g, ""+poison.getPoison(), (j*184)+190, (i*177)+105, true, Color.WHITE, Assets.regulerFont);
                    }
                    if (c instanceof Shield){
                        Shield shield = (Shield) c;
                        Text.drawString(g, ""+shield.getDefend(), (j*184)+70, (i*177)+230, true, Color.WHITE, Assets.regulerFont);
                    }

                }
            }
        }

        if(timer == 0) {
            ctrMummy = (ctrMummy != 12) ? ++ctrMummy : 0;
        }
        this.timer = (this.timer == 6) ? 0 : ++timer;
    }

    @Override
    public void playMusic() {
        clip = Assets.audioGame;
        clip.start();

        //TODO Suara game tak buat 0 dulu
        handler.setVol(clip, 0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
