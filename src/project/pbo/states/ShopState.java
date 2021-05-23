package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.Player;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import java.awt.*;

public class ShopState extends State implements SIZE {
    private MouseManager mouseManager;
    private Player Play;
    private float alhpa = 0.1f ;
    private int ctrBg,timer;
    private Rectangle dmgbtn = new Rectangle(145,520,206,93);
    private Rectangle defbtn = new Rectangle(445,520,206,93);
    private Rectangle hltbtn = new Rectangle(745,520,206,93);
    public ShopState(Handler handler, User user) { super(handler);
        this.Play = user.getPlayer();
        this.mouseManager = handler.getMouseManager();

    }

    @Override
    public void tick() {

        if (dmgbtn.contains(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.isLeftPressed()){
            if (Play.getGold() >= Play.getDmglvl()*5){
                Play.setGold(Play.getGold()- (Play.getDmglvl()*5));
                Play.setMaxdmg(Play.getMaxdmg()+2);
            }
            mouseManager.setLeftPressed(false);
        }
        if (defbtn.contains(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.isLeftPressed()){
            if (Play.getGold() >= Play.getDefendlvl()*5){
                Play.setGold(Play.getGold()- (Play.getDefendlvl()*5));
                Play.setMaxdef(Play.getMaxdef()+2);
            }
            mouseManager.setLeftPressed(false);
        }
        if (hltbtn.contains(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.isLeftPressed()){
            if (Play.getGold() >= Play.getHealthlvl()*10){
                Play.setGold(Play.getGold()- (Play.getHealthlvl()*10));
                Play.setHealth(Play.getHealth()+5);
            }
            mouseManager.setLeftPressed(false);
        }

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.blurBG[ctrBg], 0, 0, width, height, null);
        Text.drawString(g, "SHOP", 538, 80, true, Color.WHITE, Assets.superMegaBigFont);
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

        g.drawImage(Assets.bybuttonlight,145,520,206,93,null);
        g.drawImage(Assets.bybuttonlight,445,520,206,93,null);
        g.drawImage(Assets.bybuttonlight,745,520,206,93,null);

        Text.drawString(g, "BUY", 245, 560, true, Color.WHITE, Assets.biggerFont);
        Text.drawString(g, "BUY", 545, 560, true, Color.WHITE, Assets.biggerFont);
        Text.drawString(g, "BUY", 845, 560, true, Color.WHITE, Assets.biggerFont);





        if(timer == 0) {
            ctrBg = (ctrBg != Assets.blurBG.length - 1) ? ++ctrBg : 0;
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
