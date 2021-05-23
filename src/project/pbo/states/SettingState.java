package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.Player;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import java.awt.*;


public class SettingState extends State implements SIZE {
    private MouseManager mouseManager;
    private Player Play;
    private float alpha = 0.1f ;
    private int ctrBg,timer;

    public SettingState(Handler handler, User user) { super(handler);
        this.Play = user.getPlayer();
        this.mouseManager = handler.getMouseManager();
    }

    @Override
    public void playMusic() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blurBG[ctrBg], 0, 0, width, height, null);
        Text.drawString(g, "SETTING", 538, 80, true, Color.WHITE, Assets.superMegaBigFont);
        // SOUND ICON
        g.drawImage(Assets.soundicon,80,215,236,209,null);
        // SLIDER
        g.drawImage(Assets.sliderbar,150,215,208,55,null);
        g.drawImage(Assets.slidericon,150,215,53,40,null);



        if(timer == 0) {
            ctrBg = (ctrBg != Assets.blurBG.length - 1) ? ++ctrBg : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;

    }

    @Override
    public void loadFile() {

    }
}
