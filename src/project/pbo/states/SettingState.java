package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.DB;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SettingState extends State implements SIZE {
    private int xsound;
    private final MouseManager mouseManager;
    private float alpha = 0.1f ;
    private int ctrBg,timer;
    private final Rectangle exitBtn = new Rectangle(962, 26, 89, 36);
    private final Rectangle soundicon;
    private final Rectangle reset = new Rectangle(415, 345, 89, 36);
    private final MainMenu mainMenu;
    private final Clip Click;
    private final Rectangle soundoff = new Rectangle(410,215,60,85);
    private final Rectangle soundon = new Rectangle(715,215,70,85);

    private final MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
    private int preX;
    boolean pressedOut = false;
    boolean ditekan = false;


    public SettingState(Handler handler,MainMenu mainMenu) { super(handler);

        handler.getGame().getWindow().getFrame().addMouseListener(myMouseAdapter);
        handler.getGame().getWindow().getFrame().addMouseMotionListener(myMouseAdapter);
        handler.getGame().getWindow().getCanvas().addMouseListener(myMouseAdapter);
        handler.getGame().getWindow().getCanvas().addMouseMotionListener(myMouseAdapter);

        this.mainMenu = mainMenu ;
        this.mouseManager = handler.getMouseManager();
        this.Click = Assets.audioClick;
        this.xsound = handler.getVol();
        soundicon = new Rectangle(477+xsound,248,20,20);
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

            handler.getGame().getWindow().getFrame().removeMouseListener(myMouseAdapter);
            handler.getGame().getWindow().getFrame().removeMouseMotionListener(myMouseAdapter);
            handler.getGame().getWindow().getCanvas().removeMouseListener(myMouseAdapter);
            handler.getGame().getWindow().getCanvas().removeMouseMotionListener(myMouseAdapter);

            setCurrentState(mainMenu);
            mouseManager.setLeftPressed(false);
            mouseManager.setRightPressed(false);
            handler.saveFile();
        }

        if ((mouseManager.isLeftPressed()|| mouseManager.isRightPressed())&& reset.contains(mouseManager.getMouseX(),mouseManager.getMouseY())){
            Click.stop();
            Click.flush();
            Click.setFramePosition(0);
            Click.start();
            handler.getGame().getWindow().getFrame().removeMouseListener(myMouseAdapter);
            handler.getGame().getWindow().getFrame().removeMouseMotionListener(myMouseAdapter);
            handler.getGame().getWindow().getCanvas().removeMouseListener(myMouseAdapter);
            handler.getGame().getWindow().getCanvas().removeMouseMotionListener(myMouseAdapter);
            handler.setDb(new DB());
            handler.getDb().setVol(handler.myVol());
            handler.saveFile();
            handler.getGame().loadFile();
            mainMenu.getClip().stop();
            setCurrentState(new LoadingState(handler, new IntroState(handler)));

        }
        if  ((mouseManager.isLeftPressed()|| mouseManager.isRightPressed())&& soundoff.contains(mouseManager.getMouseX(),mouseManager.getMouseY())){
            Click.stop();
            Click.flush();
            Click.setFramePosition(0);
            Click.start();
            handler.setVol(0.0);
            handler.getDb().setVol(handler.myVol());
            soundicon.setLocation(477+xsound,248);
            handler.setVol(Click);
            handler.setVol(mainMenu.getClip(), 0.5);
            handler.setVol(mainMenu.getClick());
            this.xsound = handler.getVol();
        }
        if  ((mouseManager.isLeftPressed()|| mouseManager.isRightPressed())&& soundon.contains(mouseManager.getMouseX(),mouseManager.getMouseY())){
            Click.stop();
            Click.flush();
            Click.setFramePosition(0);
            Click.start();
            handler.setVol(0.5175);
            handler.getDb().setVol(handler.myVol());
            soundicon.setLocation(477+xsound,248);
            handler.setVol(Click);
            handler.setVol(mainMenu.getClip(), 0.5);
            handler.setVol(mainMenu.getClick());
            this.xsound = handler.getVol();
        }

    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blurBG[ctrBg], 0, 0, width, height, null);
        Text.drawString(g, "SETTING", 538, 80, true, Color.WHITE, Assets.superMegaBigFont);
        // SOUND ICON
        Text.drawString(g,"SOUND :",260, 270, false, Color.WHITE, Assets.biggerFont);
        g.drawImage(Assets.soundsilent,410,215,98,85,null);
        g.drawImage(Assets.soundon,715,215,98,85,null);

//        // SLIDER
        g.drawImage(Assets.sliderbar,460,130,260,255,null);
        g.drawImage(Assets.slidericon,soundicon.x-10,238,40,39,null);
//        //EXIT
        Text.drawString(g, "EXIT", 1019, 45, true, Color.WHITE, Assets.smallFont);
        g.drawRect(962, 26, 89, 36);
        g.drawImage(Assets.xIcon, 971, 38, 12, 12, null);
//        // RESET
        Text.drawString(g,"RESET :",260, 370, false, Color.WHITE, Assets.biggerFont);
        g.setColor(Color.red);
        g.fillRect(415, 345, 89, 36);
        Text.drawString(g, "RESET", 460, 363, true, Color.WHITE, Assets.smallFont);

        if(timer == 0) {
            ctrBg = (ctrBg != Assets.blurBG.length - 1) ? ++ctrBg : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;

    }



    @Override
    public void loadFile() {

    }

    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            preX = soundicon.x - e.getX();

            if (soundicon.contains(e.getX(), e.getY())) updateLocation(e);
            else {
                pressedOut = true;
                ditekan = false;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (soundicon.contains(e.getX(), e.getY())) {
                updateLocation(e);
            } else {
                pressedOut = false;
                ditekan = false;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!pressedOut){
                updateLocation(e);
            } else {
                ditekan = false;
            }
        }

        public void updateLocation(MouseEvent e){
            ditekan = true;
            if (preX + e.getX() >= 477 && preX + e.getX() <= 684 ) {
                int tempx = preX + e.getX();
                soundicon.setLocation(tempx, 248);
                handler.setVol((tempx-477) * 1.0/200);
                handler.getDb().setVol(handler.myVol());
                handler.setVol(Click);
                handler.setVol(mainMenu.getClip(), 0.5);
                handler.setVol(mainMenu.getClick());
            }
        }
    }
}
