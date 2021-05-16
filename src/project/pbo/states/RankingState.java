package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import java.awt.*;

public class RankingState extends State implements SIZE {
    private float alhpa = 0.1f;
    private boolean animated;
    private String jenisAnimasi;
    private final MouseManager mouseManager;
    private final MainMenu currMain;

    private final Rectangle exitBtn = new Rectangle(962, 26, 89, 36);

    public RankingState(Handler handler, MainMenu currMain) {
        super(handler);
        this.currMain = currMain;
        this.mouseManager = handler.getMouseManager();

        this.animated = true;
        this.jenisAnimasi = "muncul";
    }

    @Override
    public void tick() {
        if((mouseManager.isLeftPressed() || mouseManager.isRightPressed()) && exitBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
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

        g.drawImage(Assets.rankingBG, 0, 0, width, height, null);

        // BUTTON EXIT
        Text.drawString(g, "EXIT", 1019, 45, true, Color.WHITE, Assets.smallFont);
        g.drawRect(962, 26, 89, 36);
        g.drawImage(Assets.xIcon, 971, 38, 12, 12, null);

        Text.drawString(g, "RANKING", 540, 155, true, Color.WHITE, Assets.superMegaBigFont);
        g.drawImage(Assets.textArea, 170, 220, 740, 530, null);


        for (int i = 0; i < 5; i++) {
            Text.drawString(g, (i+1) + "", 196, 274+(63*i), true, Color.WHITE, Assets.mediumFont);

            if(i == 0) g.drawImage(Assets.medalIcon, 240, 220, 75, 75, null);
            else g.drawImage(Assets.avatar, 256, 316+(63*(i-1)), 40, 40, null);

            // NAMA PLAYER
            Text.drawString(g, "EMPTY", 400, 274+(63*i), true, Color.WHITE, Assets.mediumFont);

            // SCORE PLAYER
            if(i < 1) Text.drawString(g, "0", 868, 274+(63*i), true, Color.WHITE, Assets.mediumFont);
            else if(i < 3) Text.drawString(g, "50", 868, 274+(63*i), true, Color.WHITE, Assets.mediumFont);
            else Text.drawString(g, "100", 868, 274+(63*i), true, Color.WHITE, Assets.mediumFont);
        }
    }

    @Override
    public void playMusic() {

    }
}
