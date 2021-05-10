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
    private Card[][] cards;

//    Player
    private int x, y;

    private boolean running = true;
    private boolean kalah = false;
    private boolean exit = false;

    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    private int tempy, tempx, move;
    private boolean pause = false;
    private boolean doneAnim = true;

    private PlayerCard pc;

    public GameState(Handler handler, User user){
        super(handler);
        this.user = user;
        this.keyManager = handler.getKeyManager();
        this.mouseManager = handler.getMouseManager();

        init();
    }

    void init(){
        this.x = 1; this.y = 1;
        cards = new Card[3][3];
        pc = new PlayerCard(user.getPlayer().getHealth(), user.getPlayer().getDefend(), user.getPlayer().getDamage(), user.getPlayer().getHealth());
        cards[x][y] = pc;
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

        if (pc.getHealth() == 0){
            kalah = true;
        }

        if (doneAnim && !kalah && !exit){
            if (keyManager.up) {
                moveKartu(0, -1);
            }
            else if (keyManager.down) {
                moveKartu(0, 1);
            }
            else if (keyManager.left) {
                moveKartu(-1, 0);
            }
            else if (keyManager.right) {
                moveKartu(1, 0);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cards[i][j].tick();
                if (cards[i][j].isAnimating()){
                    pause = true;
                    doneAnim = false;
                }
            }
        }


        if (!pause && move != 0){
            reset();
            moving();
            doneAnim = true;
        }
        pause = false;
    }

    void reset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cards[i][j].setX(0);
                cards[i][j].setY(0);
            }
        }
    }

    void moveKartu(int x, int y){
        int tempx = this.x + x, tempy = this.y + y;
        if (tempx >= 0  && tempx <= 2 && tempy >= 0 && tempy <= 2){
            Card card = cards[tempy][tempx];
            if (card instanceof Enemy){
                Enemy enemy = (Enemy) card;
                if (pc.getDamage() > 0) {
                    int tempH = enemy.getHealth();
                    enemy.setHealth(Math.max(enemy.getHealth() - pc.getDamage(), 0));
                    pc.setDamage(Math.max(pc.getDamage() - tempH, 0));
                } else if (pc.getDefend() > 0) {
                    int tempH = enemy.getHealth();
                    enemy.setHealth(Math.max(enemy.getHealth() - pc.getDefend(), 0));
                    pc.setDefend(Math.max(pc.getDefend() - tempH, 0));
                } else {
                    int tempH = enemy.getHealth();
                    enemy.setHealth(Math.max(enemy.getHealth() - pc.getHealth(), 0));
                    pc.setHealth(Math.max(pc.getHealth() - tempH, 0));
                }

                if (enemy.getHealth() == 0){

                    cardPindah(tempy, tempx);
                }
            } else {
                if (card instanceof Sword){
                    Sword sword = (Sword) card;
                    pc.setDamage(Math.max(user.getPlayer().getDamage() + sword.getDamage(), pc.getDamage()));
                }
                if (card instanceof Healing){
                    Healing healing = (Healing) card;
                    pc.setHealth(Math.min(pc.getHealth() + healing.getHeal(), pc.getMaxHealth()));
                }
                if (card instanceof Poison){
                    Poison poison = (Poison) card;
                    pc.setHealth(Math.max(pc.getHealth() + poison.getPoison(), 0));
                }
                if (card instanceof Shield){
                    Shield shield = (Shield) card;
                    pc.setDefend(Math.max(user.getPlayer().getDefend() + shield.getDefend(), pc.getDefend()));
                }
                cardPindah(tempy, tempx);

            }
        }
    }

    void moving(){
        cards[tempy][tempx] = cards[this.y][this.x];
        if (move == 1){
            if (y != 2){
                cards[1][this.x] = cards[2][this.x];
            }
            cards[2][this.x] = null;
        }
        if (move == 3){
            if (y != 0) {
                cards[1][this.x] = cards[0][this.x];
            }
            cards[0][this.x] = null;
        }
        if (move == 4){
            if (x != 2){
                cards[this.y][1] = cards[this.y][2];
            }
            cards[this.y][2] = null;
        }
        if (move == 2){
            if (x != 0){
                cards[this.y][1] = cards[this.y][0];
            }
            cards[this.y][0] = null;
        }
        this.x = tempx; this.y = tempy;

        move = 0;

        randomCard();
    }

    void cardPindah(int tempy, int tempx){
        this.tempy = tempy; this.tempx = tempx;
        cards[tempy][tempx].fading();
        if (keyManager.up){
            this.move = 1;
            cards[this.y][this.x].setMove(1);
            if (y != 2){
                cards[2][this.x].setMove(1);
            }
        }
        if (keyManager.down){
            this.move = 3;
            cards[this.y][this.x].setMove(3);
            if (y != 0) {
                cards[0][this.x].setMove(3);
            }
        }
        if (keyManager.left){
            this.move = 4;
            cards[this.y][this.x].setMove(4);
            if (x != 2){
                cards[this.y][2].setMove(4);
            }
        }
        if (keyManager.right){
            this.move = 2;
            cards[this.y][this.x].setMove(2);
            if (x != 0){
                cards[this.y][0].setMove(2);
            }
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
        g.setColor(new Color(36,39,44));
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
        Text.drawString(g, "Health: " + pc.getHealth() + " / " + pc.getMaxHealth(), 720, 495, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Damage: " + pc.getDamage(), 720, 540, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Defend: " + pc.getDefend(), 720, 585, false, Color.WHITE, Assets.regulerFont);

//        Game
        cetakKartu(g);

        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
                c.render(g, j, i);
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
