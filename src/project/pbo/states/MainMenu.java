package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;

import java.awt.*;

public class MainMenu extends State{

    public MainMenu(Handler handler) {
        super(handler);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG, 0, 0, null);
    }
}
