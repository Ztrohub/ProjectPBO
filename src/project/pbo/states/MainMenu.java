package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;
import project.pbo.window.SIZE;

import java.awt.*;

public class MainMenu extends State implements SIZE {

    public MainMenu(Handler handler) {
        super(handler);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG, 0, 0, width, height, null);


    }
}
