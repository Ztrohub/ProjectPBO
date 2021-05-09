package project.pbo.states;

import javafx.scene.effect.BoxBlur;
import project.pbo.Handler;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;

public class MainMenu extends State implements SIZE {

    private final MouseManager mouseManager;
    private final User user;
    private Clip clip;
    private int ctrBg, ctrCoin, ctrMummy, timer;

    private final Rectangle overlayTop = new Rectangle(0, 0, width,72);
    private final Rectangle playBtn = new Rectangle(301, 0, 74, 70);
    private final Rectangle shopBtn = new Rectangle(431, 0, 76, 70);
    private final Rectangle settingBtn = new Rectangle(566, 0, 100, 70);
    private final Rectangle creditBtn = new Rectangle(725, 0, 90, 70);
    private final Rectangle logoutBtn = new Rectangle(1020, 12, 44, 50);
    private final Rectangle overlayBot = new Rectangle(0, 544, width, 100);

    private final Rectangle continueBtn = new Rectangle(597,340, 175, 35 );
    private final Rectangle exitBtn = new Rectangle(292,340, 200, 35 );

    private boolean popUpLogout = false;


    public MainMenu(Handler handler, User user) {
        super(handler);
        this.user = user;
        this.mouseManager = handler.getMouseManager();


        System.out.println("Masuk sebagai: " + user.getUsername());
    }

    public void playMusic(){
        clip = Assets.audioMenu;
        clip.start();
        handler.setVol(clip, 0.7f);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void tick() {
        if(popUpLogout){
            if((mouseManager.isLeftPressed()) || mouseManager.isRightPressed()){
                if(exitBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
                    clip.stop();
                    clip.setFramePosition(0);
                    setCurrentState(new LoadingState(handler, new LoginState(handler)));
                } else if(continueBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
                    popUpLogout = false;
                }
            }
        } else {
            if((mouseManager.isLeftPressed()) || mouseManager.isRightPressed()){
                if(logoutBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK LOGOUT
                    popUpLogout = true;
                } else if(playBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK PLAY
                    clip.stop();
                    clip.setFramePosition(0);
                    setCurrentState(new LoadingState(handler, new GameState(handler, user)));
                } else if(shopBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK SHOP
                    setCurrentState(new ShopState(handler));
                } else if(settingBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK SETTINGS
                    setCurrentState(new SettingState(handler));
                } else if(creditBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK CREDIT
                    setCurrentState(new CreditState(handler));
                }
                mouseManager.setLeftPressed(false);
                mouseManager.setRightPressed(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG[ctrBg], 0, 51, width, height, null);
        g.setColor(new Color(0x000100));
        ((Graphics2D) g).fill(overlayTop);
        ((Graphics2D) g).fill(overlayBot);

        g.drawImage(Assets.mainLogo, 310, 478, 428, 246, null);

        // ICON DAN NAMA PLAYER
        g.drawImage(Assets.avatar, 10, 5, 60, 60, null);
        g.drawImage(Assets.coinsIcon[ctrCoin], 79, 41, 15, 15, null);
        Text.drawString(g, user.getUsername(), 78, 30, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, user.getPlayer().getGold() + "", 102, 55, false, Color.WHITE, Assets.regulerFont);

//        g.setColor(new Color(0xE1AD01));
//        ((Graphics2D) g).fill(playBtn);
//        ((Graphics2D) g).fill(shopBtn);
//        ((Graphics2D) g).fill(settingBtn);
//        ((Graphics2D) g).fill(creditBtn);
//        ((Graphics2D) g).fill(logoutBtn);

        // BUTTON PLAY
        g.drawImage(Assets.playIcon, 273, -9, 135, 100, null);
        Text.drawString(g, "Play", 308, 63, false, Color.WHITE, Assets.regulerFont);

        // BUTTON SHOP
        g.drawImage(Assets.shopIcon, 432, 2, 75, 60, null);
        Text.drawString(g, "Shop", 440, 63, false, Color.WHITE, Assets.regulerFont);

        //BUTTON SETTINGS
        g.drawImage(Assets.settingIcon, 580, 2, 69, 61, null);
        Text.drawString(g, "Settings", 568, 63, false, Color.WHITE, Assets.smallFont);

        // BUTTON CREDIT
        g.drawImage(Assets.creditIcon, 740, 2, 60, 60, null);
        Text.drawString(g, "Credit", 728, 63, false, Color.WHITE, Assets.regulerFont);

        // BUTTON LOGOUT
        g.drawImage(Assets.logout, 1020, 15, 44, 44, null);

        if(popUpLogout){
            g.drawImage(Assets.popUp, 237, 155, 600, 240, null);

            Text.drawString(g, "WARNING!", 537, 201, true, new Color(0xe28743), Assets.warningFont);
            Text.drawString(g, "Are you sure want to log out ?", 537, 275, true, Color.WHITE, Assets.warningFont);

//            g.setColor(Color.red);
//            ((Graphics2D) g).fill(continueBtn);
//            ((Graphics2D) g).fill(exitBtn);

            Text.drawString(g, "Continue", 392, 357, true, Color.white, Assets.smallFont);
            Text.drawString(g, "Cancel", 692, 357, true, Color.white, Assets.smallFont);
        }

        if(timer == 0) {
            ctrBg = (ctrBg != 19) ? ++ctrBg : 0;
            ctrCoin = (ctrCoin != 6) ? ++ctrCoin : 0;
            ctrMummy = (ctrMummy != 12) ? ++ctrMummy : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;



    }

}
