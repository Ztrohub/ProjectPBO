package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.User;
import project.pbo.game.Card;
import project.pbo.game.PlayerCard;
import project.pbo.game.enemy.Enemy;
import project.pbo.game.enemy.Mummy;
import project.pbo.game.enemy.Shaman;
import project.pbo.game.enemy.Slime;
import project.pbo.game.helper.*;
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
    private final Rectangle exitBtn = new Rectangle(1000, 13, 35, 35);
    private final Rectangle replayBtn = new Rectangle(597,340, 175, 35 );

    private final Rectangle continueBtn = new Rectangle(597,340, 175, 35 );
    private final Rectangle loseBtn = new Rectangle(292,340, 200, 35 );

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
    private boolean saved = true;

    private int ctrCoin, timer;

    private PlayerCard pc;

    private int step = 1;

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
            user.getPlayer().setHighestStep(Math.max(user.getPlayer().getHighestStep(), step-1));
            clip.stop();
            clip.setFramePosition(0);
            setCurrentState(new LoadingState(handler, new MainMenu(handler, user)));
        }

        if ((kalah || exit) && loseBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) running = false;
        if (kalah && replayBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) {
            gold = 0;
            step = 1;
            saved = true;
            init();
            kalah = false;
        }

        if (exit && continueBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) exit = false;

        if (exitBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY()) &&
                (mouseManager.isRightPressed() || mouseManager.isLeftPressed())) exit = true;

        if (pc.getHealth() == 0){
            kalah = true;
            if (saved){
                user.getPlayer().setHighestStep(Math.max(user.getPlayer().getHighestStep(), step-1));
                user.getPlayer().setGold(user.getPlayer().getGold() + gold);
                saved = false;
                handler.saveFile();
            }
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
            step++;
        }
        pause = false;
    }

    @Override
    public void render(Graphics g) {
//        Background
        g.drawImage(Assets.gameBG, 0, 0, width, height, null);

//        Tab bar
        g.setColor(Color.black);
        g.fillRect(0,0,width,60);
        g.drawImage(Assets.coinsIcon[ctrCoin], 15, 10, 35, 35, null);
        Text.drawString(g, "" + gold, 70, 37, false, new Color(255,191,64), Assets.biggerFont);
        Text.drawString(g, "Step " + (step-1), 540, 30, true, Color.gray, Assets.biggerFont);
        g.setColor(Color.red);
        g.drawImage(Assets.logout, 1000, 13, 35, 35, null);

//        Hero Memu
        g.drawImage(Assets.heroMenu, 650, 60, 400, 555, null);
//        Text.drawString(g, user.getUsername(), 870, 125, true, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, pc.getHealth() + " / " + pc.getMaxHealth(), 730, 140, false, Color.WHITE, Assets.mediumFont);
        Text.drawString(g, pc.getDamage()+"", 730, 190, false, Color.WHITE, Assets.mediumFont);
        Text.drawString(g, pc.getDefend()+"", 730, 245, false, Color.WHITE, Assets.mediumFont);

//        Game
        cetakKartu(g);

        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (kalah){
            g.drawImage(Assets.popUp, 237, 155, 600, 240, null);

            Text.drawString(g, "YOU DIED!", 537, 201, true, new Color(0xe28743), Assets.warningFont);
            Text.drawString(g, "You got " + gold + " gold", 537, 260, true, Color.WHITE, Assets.smallFont);
            Text.drawString(g, "Want to restart?", 537, 300, true, Color.WHITE, Assets.warningFont);

            Text.drawString(g, "Exit", 392, 357, true, Color.white, Assets.smallFont);
            Text.drawString(g, "Restart", 692, 357, true, Color.white, Assets.smallFont);
        }

        if (exit){
            g.drawImage(Assets.popUp, 237, 155, 600, 240, null);

            Text.drawString(g, "WARNING!", 537, 201, true, new Color(0xe28743), Assets.warningFont);
            Text.drawString(g, "Are you sure want to Exit?", 537, 265, true, Color.WHITE, Assets.warningFont);
            Text.drawString(g, "Gold and step will not be saved!", 537, 300, true, Color.WHITE, Assets.warningFont);

            Text.drawString(g, "Yes", 392, 357, true, Color.white, Assets.smallFont);
            Text.drawString(g, "No", 692, 357, true, Color.white, Assets.smallFont);

        }

        if(timer == 0) {
            ctrCoin = (ctrCoin != 6) ? ++ctrCoin : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;

    }

    void cetakKartu(Graphics g){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Card c = cards[i][j];
                c.render(g, j, i);
            }
        }
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
                if (card instanceof Gold){
                    Gold gold = (Gold) card;
                    this.gold += gold.getGold();
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
                    int enemyorhelp = random.nextInt(100);
                    int rand = random.nextInt(100);
                    if (enemyorhelp > (Math.min(step * 5, 50))){
                        if (rand < 30) cards[i][j] = new Shield();
                        else if (rand < 60) cards[i][j] = new Sword();
                        else if (rand < 80) cards[i][j] = new Gold();
                        else if (rand < 90) cards[i][j] = new Healing();
                        else cards[i][j] = new Poison();
                    } else {
                        int stage = step/5 + 1;
                        if (rand < 40) cards[i][j] = new Slime(stage);
                        if (rand < 80) cards[i][j] = new Mummy(stage);
                        else cards[i][j] = new Shaman(stage);
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
