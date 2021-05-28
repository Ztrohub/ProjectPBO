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

public class ShopState extends State implements SIZE {
    private final MouseManager mouseManager;
    private final Player Play;
    private int ctrBg, timer;
    private final Rectangle dmgbtn = new Rectangle(145,520,206,93);
    private final Rectangle defbtn = new Rectangle(445,520,206,93);
    private final Rectangle hltbtn = new Rectangle(745,520,206,93);
    private int ctrCoin;
    private final Clip click;

    private final Rectangle exitBtn = new Rectangle(962, 26, 89, 36);
    private final MainMenu mainMenu;

    private float alpha = 0.1f;
    private boolean animated;
    private String jenisAnimasi;

    public ShopState(Handler handler, User user, MainMenu mainMenu) {
        super(handler);
        this.mainMenu = mainMenu;
        this.Play = user.getPlayer();
        this.mouseManager = handler.getMouseManager();
        click = Assets.audioClick;
        handler.setVol(click, 0.1);

        this.animated = true;
        this.jenisAnimasi = "muncul";
    }

    @Override
    public void tick() {

        if((mouseManager.isLeftPressed() || mouseManager.isRightPressed()) && exitBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
            click.stop();
            click.flush();
            click.setFramePosition(0);
            click.start();

            this.animated = true;
            this.jenisAnimasi = "hilang";

            mouseManager.setLeftPressed(false);
            mouseManager.setRightPressed(false);
            handler.saveFile();
        }

        // ANIMASI FADE
        if(animated){
            if(this.jenisAnimasi.equalsIgnoreCase("muncul")){
                if(alpha < 1.0f){
                    alpha += 0.04f;
                    if(alpha >= 1.0f){
                        alpha = 1.0f;
                        this.jenisAnimasi = "";
                        animated = false;
                    }
                }
            } else if(this.jenisAnimasi.equalsIgnoreCase("hilang")){
                if(alpha > 0.0f){
                    alpha -= 0.04f;
                    if(alpha <= 0.1f){
                        animated = false;
                        this.jenisAnimasi = "";
                        setCurrentState(mainMenu);
                    }
                }
            }
        }

        if (dmgbtn.contains(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.isLeftPressed()){
            if (Play.getGold() >= Play.getDmglvl()*5){
                Play.setGold(Play.getGold()- (Play.getDmglvl()*5));
                Play.setMaxdmg(Play.getMaxdmg()+2);
                Play.setDmglvl(Play.getDmglvl() + 1);
                click.stop();
                click.flush();
                click.setFramePosition(0);
                click.start();
            }
            mouseManager.setLeftPressed(false);
        }
        if (defbtn.contains(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.isLeftPressed()){
            if (Play.getGold() >= Play.getDefendlvl()*5){
                Play.setGold(Play.getGold()- (Play.getDefendlvl()*5));
                Play.setMaxdef(Play.getMaxdef()+2);
                Play.setDefendlvl(Play.getDefendlvl() + 1);
                click.stop();
                click.flush();
                click.setFramePosition(0);
                click.start();
            }
            mouseManager.setLeftPressed(false);
        }
        if (hltbtn.contains(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.isLeftPressed()){
            if (Play.getGold() >= Play.getHealthlvl()*10){
                Play.setGold(Play.getGold()- (Play.getHealthlvl()*10));
                Play.setHealth(Play.getHealth()+5);
                Play.setHealthlvl(Play.getHealthlvl() + 1);
                click.stop();
                click.flush();
                click.setFramePosition(0);
                click.start();
            }
            mouseManager.setLeftPressed(false);
        }

    }

    @Override
    public void render(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(Assets.blurBG[ctrBg], 0, 0, width, height, null);

        Text.drawString(g, "EXIT", 1019, 45, true, Color.WHITE, Assets.smallFont);
        g.drawRect(962, 26, 89, 36);
        g.drawImage(Assets.xIcon, 971, 38, 12, 12, null);

        g.drawImage(Assets.coinsIcon[ctrCoin], 15, 10, 35, 35, null);
        Text.drawString(g, "" + Play.getGold(), 70, 37, false, new Color(255,191,64), Assets.biggerFont);

        Text.drawString(g, "STORE", 538, 80, true, Color.WHITE, Assets.superMegaBigFont);
        g.drawImage(Assets.shop,80,135,306,306,null);
        g.drawImage(Assets.shop,380,135,306,306,null);
        g.drawImage(Assets.shop,680,135,306,306,null);

        g.drawImage(Assets.sword,170,215,125,125,null);
        g.drawImage(Assets.shield,473,217,120,120,null);
        g.drawImage(Assets.heal,773,215,125,125,null);

        Text.drawString(g, "MAX DAMAGE +2", 235, 450, true, Color.WHITE, Assets.mediumFont);
        Text.drawString(g, "MAX DEFEND +2", 535, 450, true, Color.WHITE, Assets.mediumFont);
        Text.drawString(g, "MAX HEALTH +5", 835, 450, true, Color.WHITE, Assets.mediumFont);

        Text.drawString(g, (Play.getDmglvl()*5)+"G", 240, 480, true, new Color(255,191,64), Assets.mediumFont);
        Text.drawString(g, (Play.getDefendlvl()*5)+"G", 540, 480, true, new Color(255,191,64), Assets.mediumFont);
        Text.drawString(g, (Play.getHealthlvl()*10)+"G", 840, 480, true, new Color(255,191,64), Assets.mediumFont);

        g.drawImage(Play.getGold() >= Play.getDmglvl()*5 ? Assets.bybuttonlight : Assets.bybuttondrk,145,520,206,93,null);
        g.drawImage(Play.getGold() >= Play.getDefendlvl()*5 ? Assets.bybuttonlight : Assets.bybuttondrk,445,520,206,93,null);
        g.drawImage(Play.getGold() >= Play.getHealthlvl()*10 ? Assets.bybuttonlight : Assets.bybuttondrk,745,520,206,93,null);

        Text.drawString(g, "BUY", 245, 560, true, Play.getGold() >= Play.getDmglvl()*5 ? Color.WHITE : Color.gray, Assets.biggerFont);
        Text.drawString(g, "BUY", 545, 560, true, Play.getGold() >= Play.getDefendlvl()*5 ? Color.WHITE : Color.gray, Assets.biggerFont);
        Text.drawString(g, "BUY", 845, 560, true, Play.getGold() >= Play.getHealthlvl()*10 ? Color.WHITE : Color.gray, Assets.biggerFont);





        if(timer == 0) {
            ctrBg = (ctrBg != Assets.blurBG.length - 1) ? ++ctrBg : 0;
            ctrCoin = (ctrCoin != 6) ? ++ctrCoin : 0;
        }
        this.timer = (this.timer == 4) ? 0 : ++timer;


    }

    @Override
    public void playMusic() {

    }

    @Override
    public void loadFile() {

    }
}
