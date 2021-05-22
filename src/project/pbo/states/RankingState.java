package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;

public class RankingState extends State implements SIZE {
    private final ArrayList <User> rank = handler.getDb().getUsers();
    private final MouseManager mouseManager;
    private final MainMenu currMain;
    private final Clip click;

    private int ctrBg, timer;
    private float alhpa = 0.1f;
    private boolean animated;
    private String jenisAnimasi;

    private final Rectangle exitBtn = new Rectangle(962, 26, 89, 36);

    public RankingState(Handler handler, MainMenu currMain) {
        super(handler);
        this.currMain = currMain;
        this.mouseManager = handler.getMouseManager();
        click = Assets.audioClick;
        handler.setVol(click, 0.1);

        if(rank.size() > 0){
            for (int i = 1; i < rank.size(); i++) {
                if(rank.get(i-1).getPlayer().getHighestStep() < rank.get(i).getPlayer().getHighestStep()){
                    User temp = rank.get(i-1);
                    rank.set(i-1, rank.get(i));
                    rank.set(i, temp);
                }
            }
        }

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
        }

        if(animated){
            if(this.jenisAnimasi.equalsIgnoreCase("muncul")){
                if(alhpa < 1.0f){
                    alhpa += 0.04f;
                    if(alhpa >= 1.0f){
                        alhpa = 1.0f;
                        this.jenisAnimasi = "";
                        animated = false;
                    }
                }
            } else if(this.jenisAnimasi.equalsIgnoreCase("hilang")){
                if(alhpa > 0.0f){
                    alhpa -= 0.04f;
                    if(alhpa <= 0.1f){
                        animated = false;
                        this.jenisAnimasi = "";
                        setCurrentState(currMain);
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alhpa));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(Assets.blurBG[ctrBg], 0, 0, width, height, null);

        // BUTTON EXIT
        Text.drawString(g, "EXIT", 1019, 45, true, Color.WHITE, Assets.smallFont);
        g.drawRect(962, 26, 89, 36);
        g.drawImage(Assets.xIcon, 971, 38, 12, 12, null);

        Text.drawString(g, "RANKING", 538, 145, true, Color.WHITE, Assets.superMegaBigFont);
        g.drawImage(Assets.textArea, 170, 205, 730, 530, null);


        for (int i = 0; i < 5; i++) {
            Text.drawString(g, (i+1) + "", 206, 250+(69*i), true, Color.WHITE, Assets.mediumFont);

            // NAMA PLAYER
            if(i < rank.size()){
                if(rank.get(i).getPlayer().getHighestStep() > 0){
                    if(i == 0) g.drawImage(Assets.medalIcon, 253, 197, 77, 77, null);
                    else g.drawImage(Assets.avatar, 271, 297+(68*(i-1)), 42, 42, null);

                    Text.drawString(g, rank.get(i).getUsername(), 360, 257+(68*i), false, Color.WHITE, Assets.mediumFont);
                    Text.drawString(g, String.valueOf(rank.get(i).getPlayer().getHighestStep()), 851, 250+(69*i), true, Color.WHITE, Assets.mediumFont);
                } else {
                    Text.drawString(g, "EMPTY", 528, 251+(69*i), true, Color.WHITE, Assets.mediumFont);
                    Text.drawString(g, "-", 850, 250+(69*i), true, Color.WHITE, Assets.mediumFont);
                }
            } else {
                Text.drawString(g, "EMPTY", 528, 251+(69*i), true, Color.WHITE, Assets.mediumFont);
                Text.drawString(g, "-", 850, 250+(69*i), true, Color.WHITE, Assets.mediumFont);
            }
        }

        if(timer == 0)
            ctrBg = (ctrBg != Assets.blurBG.length - 1) ? ++ctrBg : 0;

        this.timer = (this.timer == 4) ? 0 : ++timer;
    }

    @Override
    public void playMusic() {

    }

    @Override
    public void loadFile() {

    }
}
