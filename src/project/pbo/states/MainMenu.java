package project.pbo.states;

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

    private final Rectangle overlayTop = new Rectangle(0, 0, width,70);
    private final Rectangle playBtn = new Rectangle(360, 0, 70, 70);
    private final Rectangle shopBtn = new Rectangle(480, 0, 70, 70);
    private final Rectangle settingBtn = new Rectangle(605, 0, 80, 70);
    private final Rectangle creditBtn = new Rectangle(755, 0, 70, 70);
    private final Rectangle logoutBtn = new Rectangle(1020, 12, 40, 40);


    public MainMenu(Handler handler, User user) {
        super(handler);
        this.user = user;
        this.mouseManager = handler.getMouseManager();


        System.out.println("Masuk sebagai: " + user.getUsername());
    }

    public void playMusic(){
        clip = Assets.audioMenu;
        clip.start();
        handler.setVol(clip);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void tick() {
        if((mouseManager.isLeftPressed()) || mouseManager.isRightPressed()){
            if(logoutBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK LOGOUT
                clip.stop();
                setCurrentState(new LoadingState(handler, new LoginState(handler)));
            } else if(playBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK PLAY
                clip.stop();
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

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG, 0, 30, width, height, null);
        g.drawImage(Assets.mainLogo, 296, 445, 450, 300, null);

        // OVERLAY TOP
        g.setColor(new Color(0x000000));
        ((Graphics2D) g).fill(overlayTop);

        // ICON DAN NAMA PLAYER
        g.drawImage(Assets.avatar, 16, 31, 30, 30, null);
        Text.drawString(g, "Logged in as :", 13, 23, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, user.getUsername(), 54, 56, false, Color.WHITE, Assets.regulerFont);

//        g.setColor(new Color(0xE1AD01));
//        ((Graphics2D) g).fill(playBtn);
//        ((Graphics2D) g).fill(shopBtn);
//        ((Graphics2D) g).fill(settingBtn);
//        ((Graphics2D) g).fill(creditBtn);

        // BUTTON PLAY
        g.drawImage(Assets.playIcon, 325, -9, 135, 100, null);
        Text.drawString(g, "Play", 365, 63, false, Color.WHITE, Assets.regulerFont);

        // BUTTON SHOP
        g.drawImage(Assets.shopIcon, 478, 2, 75, 60, null);
        Text.drawString(g, "Shop", 485, 63, false, Color.WHITE, Assets.regulerFont);

        //BUTTON SETTINGS
        g.drawImage(Assets.settingIcon, 610, 2, 69, 61, null);
        Text.drawString(g, "Settings", 590, 63, false, Color.WHITE, Assets.regulerFont);

        // BUTTON CREDIT
        g.drawImage(Assets.creditIcon, 760, 2, 60, 60, null);
        Text.drawString(g, "Credit", 748, 63, false, Color.WHITE, Assets.regulerFont);

        // BUTTON LOGOUT
        g.drawImage(Assets.logout, 1020, 12, 40, 40, null);
    }

}
