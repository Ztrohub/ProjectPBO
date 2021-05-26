package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.Player;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;


public class SettingState extends State implements SIZE {
    private MouseManager mouseManager;
    private float alpha = 0.1f ;
    private int ctrBg,timer;
    private final Rectangle slider = new Rectangle(250,298,80,78);
    private final Rectangle exitBtn = new Rectangle(962, 26, 89, 36);
    private final MainMenu mainMenu;
    private final Clip Click;


    public SettingState(Handler handler,MainMenu mainMenu) { super(handler);
        this.mainMenu = mainMenu ;
        this.mouseManager = handler.getMouseManager();
        this.Click = Assets.audioClick;
    }

    @Override
    public void playMusic() {

    }

    @Override
    public void tick() {
        if((mouseManager.isLeftPressed() || mouseManager.isRightPressed()) && exitBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
            Click.stop();
            Click.flush();
            Click.setFramePosition(0);
            Click.start();
            setCurrentState(mainMenu);
            mouseManager.setLeftPressed(false);
            mouseManager.setRightPressed(false);
            handler.saveFile();
        }


    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blurBG[ctrBg], 0, 0, width, height, null);
        Text.drawString(g, "SETTING", 538, 80, true, Color.WHITE, Assets.superMegaBigFont);
        // SOUND ICON
        Text.drawString(g,"SOUND :",260, 200, false, Color.WHITE, Assets.biggerFont);
        g.drawImage(Assets.soundsilent,410,145,98,85,null);
        g.drawImage(Assets.soundon,705,145,98,85,null);
//
//        // SLIDER
        g.drawImage(Assets.sliderbar,460,60,255,255,null);
        g.drawImage(Assets.slidericon,470,168,40,39,null);
//        //EXIT
        Text.drawString(g, "EXIT", 1019, 45, true, Color.WHITE, Assets.smallFont);
        g.drawRect(962, 26, 89, 36);
        g.drawImage(Assets.xIcon, 971, 38, 12, 12, null);
//        // RESET
        Text.drawString(g,"RESET :",260, 300, false, Color.WHITE, Assets.biggerFont);
        Text.drawString(g, "RESET", 440, 293, true, Color.RED, Assets.smallFont);
        g.drawRect(395, 275, 89, 36);



        if(timer == 0) {
            ctrBg = (ctrBg != Assets.blurBG.length - 1) ? ++ctrBg : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;

    }

    @Override
    public void loadFile() {

    }
}
