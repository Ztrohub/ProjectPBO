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

    private int ctrBg, ctrCoin, timer;
    private boolean isLogout = false;
    private final MouseManager mouseManager;
    private final User user;
    private Clip clip;
    private Clip click;

    private final Rectangle overlayTop = new Rectangle(0, 0, width,73);
    private final Rectangle playBtn = new Rectangle(302, 0, 75, 70);
    private final Rectangle rankingBtn = new Rectangle(442, 0, 95, 70);
    private final Rectangle storeBtn = new Rectangle(595, 0, 81, 70);
    private final Rectangle settingsBtn = new Rectangle(737, 0, 95, 70);
    private final Rectangle logoutBtn = new Rectangle(1020, 12, 44, 50);
    private final Rectangle overlayBot = new Rectangle(0, 544, width, 100);

    // LOGOUT BUTTON YES / NO
    private final Rectangle popBtnNo = new Rectangle(597, 356, 185, 35 );
    private final Rectangle popBtnYes = new Rectangle(292, 356, 200, 35 );

    // ATRIBUT POP UP MENU
    private boolean animated = false;
    private String jenisAnimasi;

    // GAMBAR 175
    // CONTINUE BTN 360
    // EXIT BTN 360
    // WARNING 221
    // MESSAGE 295
    // YES 377
    // NO 377

    int yGambar = -225;
    int yWarning = -179;
    int yMessage = -105;
    int yYes = -23;
    int yNo= -23;


    public MainMenu(Handler handler, User user) {
        super(handler);
        this.user = user;
        this.mouseManager = handler.getMouseManager();
    }

    public void playMusic(){
        clip = Assets.audioMenu;
        clip.start();
        click = Assets.audioClick;
        handler.setVol(click, 0.1);
        handler.setVol(clip,0.5);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void tick() {
        if(isLogout){
            if((mouseManager.isLeftPressed()) || mouseManager.isRightPressed() && !animated){
                if(popBtnYes.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
                    click.stop();
                    click.flush();
                    click.setFramePosition(0);
                    click.start();
                    clip.stop();
                    clip.setFramePosition(0);
                    setCurrentState(new LoadingState(handler, new LoginState(handler)));
                } else if(popBtnNo.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
                    click.stop();
                    click.flush();
                    click.setFramePosition(0);
                    click.start();
                    animated = true;
                    jenisAnimasi = "naik";
                }
                mouseManager.setLeftPressed(false);
                mouseManager.setRightPressed(false);
            }
        } else {
            if((mouseManager.isLeftPressed()) || mouseManager.isRightPressed()){
                if(logoutBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){// USER CLICK LOGOUT
                    click.stop();
                    click.flush();
                    click.setFramePosition(0);
                    click.start();
                    animated = true;
                    isLogout = true;
                    jenisAnimasi = "turun";
                } else if(playBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK PLAY
                    click.stop();
                    click.flush();
                    click.setFramePosition(0);
                    click.start();
                    clip.stop();
                    clip.setFramePosition(0);
                    setCurrentState(new LoadingState(handler, new GameState(handler, user)));
                } else if(rankingBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK LEADERBOARD
                    click.stop();
                    click.flush();
                    click.setFramePosition(0);
                    click.start();
                    setCurrentState(new RankingState(handler, this));
                } else if(storeBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK SHOP
                    click.stop();
                    click.flush();
                    click.setFramePosition(0);
                    click.start();
                    setCurrentState(new ShopState(handler,user));
                } else if(settingsBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){ // USER CLICK SETTINGS
                    click.stop();
                    click.flush();
                    click.setFramePosition(0);
                    click.start();
                    setCurrentState(new SettingState(handler));
                }
                mouseManager.setLeftPressed(false);
                mouseManager.setRightPressed(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG[ctrBg], 0, 47, width, height, null);
        g.setColor(new Color(0x000100));
        ((Graphics2D) g).fill(overlayTop);
        ((Graphics2D) g).fill(overlayBot);

        g.drawImage(Assets.mainLogo, 310, 476, 435, 253, null);

        // ICON DAN NAMA PLAYER
        g.drawImage(Assets.avatar, 10, 6, 60, 60, null);
        g.drawImage(Assets.coinsIcon[ctrCoin], 79, 42, 15, 15, null);
        Text.drawString(g, user.getUsername(), 78, 31, false, Color.WHITE, Assets.menuRegulerFont);
        Text.drawString(g, user.getPlayer().getGold() + "", 103, 55, false, Color.WHITE, Assets.regulerFont);

//        g.setColor(new Color(0xE1AD01));
//        ((Graphics2D) g).fill(playBtn);
//        ((Graphics2D) g).fill(rankingBtn);
//        ((Graphics2D) g).fill(storeBtn);
//        ((Graphics2D) g).fill(settingsBtn);
//        ((Graphics2D) g).fill(logoutBtn);

        // BUTTON PLAY
        g.drawImage(Assets.playIcon, 302, 6, 75, 56, null);
        Text.drawString(g, "Play", 314, 64, false, Color.WHITE, Assets.menuRegulerFont);

        // BUTTON RANKING
        g.drawImage(Assets.rankingIcon, 459, 6, 60, 55, null);
        Text.drawString(g, "Ranking", 444, 64, false, Color.WHITE, Assets.menuRegulerFont);

        // BUTTON STORE
        g.drawImage(Assets.storeIcon, 599, 2, 75, 60, null);
        Text.drawString(g, "Store", 603, 64, false, Color.WHITE, Assets.menuRegulerFont);

        // BUTTON SETTINGS
        g.drawImage(Assets.settingsIcon, 751, 5, 68, 59, null);
        Text.drawString(g, "Settings", 741, 64, false, Color.WHITE, Assets.menuSmallFont);

        // BUTTON LOGOUT
        g.drawImage(Assets.logout, 1020, 15, 44, 44, null);

        if(isLogout){
            if(animated) popAnimated();

            g.drawImage(Assets.popUp, 237, yGambar, 600, 240, null);

            Text.drawString(g, "WARNING!", 537, yWarning, true, new Color(0xe28743), Assets.menuRegulerFont);
            Text.drawString(g, "Are you sure want to log out ?", 537, yMessage, true, Color.WHITE, Assets.menuRegulerFont);

            Text.drawString(g, "Yes", 392, yYes, true, Color.WHITE, Assets.menuSmallFont);
            Text.drawString(g, "No", 692, yNo, true, Color.WHITE, Assets.menuSmallFont);
        }

        if(timer == 0) {
            ctrBg = (ctrBg != Assets.menuBG.length - 1) ? ++ctrBg : 0;
            ctrCoin = (ctrCoin != Assets.coinsIcon.length - 1) ? ++ctrCoin : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;
    }

    private void popAnimated(){
        if(jenisAnimasi.equalsIgnoreCase("turun")){
            this.yGambar += 22;
            this.yWarning += 22;
            this.yMessage += 22;
            this.yYes += 22;
            this.yNo += 22;

            if(yGambar == 171) {
                animated = false;
                jenisAnimasi = "";
            }
        } else if(jenisAnimasi.equalsIgnoreCase("naik")){
            this.yGambar -= 22;
            this.yWarning -= 22;
            this.yMessage -= 22;
            this.yYes -= 22;
            this.yNo -= 22;

            if(yGambar == -225) {
                animated = false;
                isLogout = false;
                jenisAnimasi = "";
            }
        }
    }

    @Override
    public void loadFile() {
        Assets.initMenu();
    }
}
