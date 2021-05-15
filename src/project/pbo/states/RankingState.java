package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import java.awt.*;

public class RankingState extends State implements SIZE {
    private final MouseManager mouseManager;
    private final MainMenu currMain;

    private final Rectangle exitBtn = new Rectangle(962, 26, 89, 36);

    public RankingState(Handler handler, MainMenu currMain) {
        super(handler);
        this.currMain = currMain;
        this.mouseManager = handler.getMouseManager();
    }

    @Override
    public void tick() {
        if((mouseManager.isLeftPressed() || mouseManager.isRightPressed()) && exitBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
            setCurrentState(currMain);
            mouseManager.setLeftPressed(false);
            mouseManager.setRightPressed(false);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rankingBG, 0, 0, width, height, null);

        // BUTTON EXIT
        Text.drawString(g, "EXIT", 1019, 45, true, Color.WHITE, Assets.smallFont);
        g.drawRect(962, 26, 89, 36);
        g.drawImage(Assets.xIcon, 971, 38, 12, 12, null);

        Text.drawString(g, "RANKING", 540, 155, true, Color.WHITE, Assets.superMegaBigFont);
        g.drawImage(Assets.textArea, 162, 105, 750, 500, null);
    }

    @Override
    public void playMusic() {

    }
}
