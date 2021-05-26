package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.DB;
import project.pbo.account.Player;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;


public class SettingState extends State implements SIZE {
    private int xsound;
    private MouseManager mouseManager;
    private float alpha = 0.1f ;
    private int ctrBg,timer;
    private final Rectangle exitBtn = new Rectangle(962, 26, 89, 36);
    private final Rectangle soundicon;
    private final Rectangle reset = new Rectangle(415, 345, 89, 36);
    private final MainMenu mainMenu;
    private final Clip Click;
    private final Rectangle soundoff = new Rectangle(410,215,98,85);
    private final Rectangle soundon = new Rectangle(705,215,98,85);


    public SettingState(Handler handler,MainMenu mainMenu) { super(handler);
        this.xsound = handler.getVol();
        System.out.println(xsound);
        this.mainMenu = mainMenu ;
        this.mouseManager = handler.getMouseManager();
        this.Click = Assets.audioClick;
        soundicon = new Rectangle(470+xsound,238,40,39);
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

        if ((mouseManager.isLeftPressed()|| mouseManager.isRightPressed())&& soundicon.contains(mouseManager.getMouseX(),mouseManager.getMouseY())){
            if(mouseManager.getMouseX()>= 470 && mouseManager.getMouseX()<=690) {
                this.xsound = mouseManager.getMouseX()-470;
                System.out.println("di tekan");
            }
        }

        if ((mouseManager.isLeftPressed()|| mouseManager.isRightPressed())&& reset.contains(mouseManager.getMouseX(),mouseManager.getMouseY())){
            handler.setDb(new DB());
            handler.saveFile();
            handler.getGame().loadFile();
            setCurrentState(new IntroState(handler));

        }
        if  ((mouseManager.isLeftPressed()|| mouseManager.isRightPressed())&& soundoff.contains(mouseManager.getMouseX(),mouseManager.getMouseY())){
            handler.setVol(0.0);
            handler.setVol(mainMenu.getClip());
            handler.setVol(mainMenu.getClick());

        }
        if  ((mouseManager.isLeftPressed()|| mouseManager.isRightPressed())&& soundon.contains(mouseManager.getMouseX(),mouseManager.getMouseY())){
            handler.setVol(0.2);
            handler.setVol(mainMenu.getClip());
            handler.setVol(mainMenu.getClick());

        }




    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blurBG[ctrBg], 0, 0, width, height, null);
        Text.drawString(g, "SETTING", 538, 80, true, Color.WHITE, Assets.superMegaBigFont);
        // SOUND ICON
        Text.drawString(g,"SOUND :",260, 270, false, Color.WHITE, Assets.biggerFont);
        g.drawImage(Assets.soundsilent,410,215,98,85,null);
        g.drawImage(Assets.soundon,705,215,98,85,null);

//        // SLIDER
        g.drawImage(Assets.sliderbar,460,130,240,255,null);
        g.drawImage(Assets.slidericon,470+xsound,238,40,39,null);
//        //EXIT
        Text.drawString(g, "EXIT", 1019, 45, true, Color.WHITE, Assets.smallFont);
        g.drawRect(962, 26, 89, 36);
        g.drawImage(Assets.xIcon, 971, 38, 12, 12, null);
//        // RESET
        Text.drawString(g,"RESET :",260, 370, false, Color.WHITE, Assets.biggerFont);
        Text.drawString(g, "RESET", 460, 363, true, Color.RED, Assets.smallFont);
        g.drawRect(415, 345, 89, 36);



        if(timer == 0) {
            ctrBg = (ctrBg != Assets.blurBG.length - 1) ? ++ctrBg : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;

    }

    @Override
    public void loadFile() {

    }
}
