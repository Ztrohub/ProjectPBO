package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.Player;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;

public class GameState extends State implements SIZE {

    private User user;
    private int gold = 0;
    private final Rectangle exitBtn = new Rectangle(950,7, 100, 35 );
    private Clip clip;
    private int health, maxHealth, damage, defend;

    public GameState(Handler handler, User user){
        super(handler);
        this.user = user;
        health = user.getPlayer().getHealth();
        maxHealth = user.getPlayer().getHealth();
        damage = user.getPlayer().getDamage();
        defend = user.getPlayer().getDefend();
    }

    @Override
    public void tick() {

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
        g.drawRect(40, 80, 552, 531);


    }

    @Override
    public void playMusic() {
        clip = Assets.audioGame;
        clip.start();
        handler.setVol(clip);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
