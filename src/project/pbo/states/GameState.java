package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.Player;
import project.pbo.account.User;
import project.pbo.game.Card;
import project.pbo.game.Character;
import project.pbo.game.PlayerCard;
import project.pbo.game.enemy.Enemy;
import project.pbo.game.enemy.MonsterTree;
import project.pbo.game.enemy.Skeleton;
import project.pbo.game.enemy.Slime;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.KeyManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends State implements SIZE {

    private final User user;
    private int gold = 0;
    private final Rectangle exitBtn = new Rectangle(950,7, 100, 35 );
    private Clip clip;
    private int health;
    private int maxHealth;
    private int damage;
    private int defend;
    private final Character[][] cards = new Character[3][3];
    private int x = 0, y = 0;
    private boolean running = true;

    private KeyManager keyManager;

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

        }
        keyManager.tick();
        if (keyManager.up) moveKartu(0, -1);
        if (keyManager.down) moveKartu(0, 1);
        if (keyManager.left) moveKartu(-1, 0);
        if (keyManager.right) moveKartu(1, 0);
        keyManager.keyReset();
    }

    void moveKartu(int x, int y){
        int tempx = this.x + x, tempy = this.y + y;
        if (tempx >= 0  && tempx <= 2 && tempy >= 0 && tempy <= 2){
            Character card = cards[tempy][tempx];
            System.out.println(card.getSymbol());
            if (card instanceof Enemy){
                if (damage > 0) {
                    int tempH = card.getHealth();
                    card.setHealth(Math.max(card.getHealth() - damage, 0));
                    damage = Math.max(damage - tempH, 0);
                } else if (defend > 0) {
                    int tempH = card.getHealth();
                    card.setHealth(Math.max(card.getHealth() - defend, 0));
                    defend = Math.max(damage - tempH, 0);
                } else {
                    int tempH = card.getHealth();
                    card.setHealth(Math.max(card.getHealth() - health, 0));
                    health = Math.max(health - tempH, 0);
                }
            }

            if (card.getHealth() == 0){
                cards[tempy][tempx] = new PlayerCard(health);
                cards[this.y][this.x] = null;
                this.x = tempx; this.y = tempy;
            }
            keyManager.keyReset();
            randomCard();
        }
    }


    void randomCard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (cards[i][j] == null){
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    int stage = user.getPlayer().getStage();
                    if (rand == 0) cards[i][j] = new MonsterTree(stage);
                    if (rand == 1) cards[i][j] = new Skeleton(stage);
                    if (rand == 2) cards[i][j] = new Slime(stage);
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
                Character c = cards[i][j];
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
                    Text.drawString(g, "" + c.getHealth(), (j * 184) + 190, (i * 177) + 105, true, Color.WHITE, Assets.regulerFont);
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
