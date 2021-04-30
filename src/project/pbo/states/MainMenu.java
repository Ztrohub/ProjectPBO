package project.pbo.states;

import project.pbo.gfx.Assets;

import java.awt.*;

public class MainMenu extends State{

    public MainMenu() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG, 0, 0, null);
    }
}
