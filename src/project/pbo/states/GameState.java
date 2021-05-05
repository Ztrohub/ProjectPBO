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
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.Random;

public class GameState extends State implements SIZE {

    private final User user;
    private int gold = 0;
    private final Rectangle exitBtn = new Rectangle(950,7, 100, 35 );
    private Clip clip;
    private int health;
    private final int maxHealth;
    private int damage;
    private int defend;

    private final Card[][] cards = new Card[3][3];

    private int x = 0, y = 0;
    private boolean running = true;
    private int counter = 0;
    private boolean delay = false;

    private final KeyManager keyManager;

    public GameState(Handler handler, User user){
        super(handler);
        this.user = user;
        this.keyManager = handler.getKeyManager();

        health = user.getPlayer().getHealth();
        maxHealth = user.getPlayer().getHealth();
        damage = user.getPlayer().getDamage();
        defend = user.getPlayer().getDefend();
        cards[x][y] = new PlayerCard(health);
        randomCard();
    }

    @Override
    public void tick() {
        if (!running){
            clip.stop();
            setCurrentState(new LoadingState(handler, new MainMenu(handler, user)));
        }

        if (delay){
            counter++;
            if (counter > 50){
                counter = 0;
                delay = false;
            }
        } else {
            if (keyManager.up) { moveKartu(0, -1); }
            if (keyManager.down) moveKartu(0, 1);
            if (keyManager.left) moveKartu(-1, 0);
            if (keyManager.right) { moveKartu(1, 0); }
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
                    cards[this.y][this.x] = null;
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
                cards[this.y][this.x] = null;
                this.x = tempx; this.y = tempy;
            }

            randomCard();
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
                    Text.drawString(g, c.getSymbol(), (j * 184) + 132, (i * 177) + 168, true, Color.WHITE, Assets.regulerFont);
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
    }

    @Override
    public void playMusic() {
        clip = Assets.audioGame;
        clip.start();
        handler.setVol(clip);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
