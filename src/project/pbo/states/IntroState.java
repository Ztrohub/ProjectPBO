package project.pbo.states;

import project.pbo.gfx.Assets;

import java.awt.*;

public class IntroState extends State{

    Graphics2D g;
    float alhpa = 0.1f;
    int count = 0;

    @Override
    public void tick() {
        if (count < 100)
            alhpa += 0.01f;

        if (alhpa >= 1.0f){
            alhpa = 1.0f;
            if (count < 100)
            count++;
        }

        if (count >= 100)
            alhpa -= 0.01f;
        if (alhpa <= 0.0f){
            State.setCurrentState(new MainMenu());
        }
    }

    @Override
    public void render(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alhpa));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(Assets.logoSTTS, 400, 180, 250, 250, null);
    }
}
