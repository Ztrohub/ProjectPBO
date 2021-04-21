package project.pbo.states;

import project.pbo.util.KeyHandler;
import project.pbo.util.MouseHandler;

import java.awt.*;

public class PlayState extends GameState{

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, 64, 64);
    }
}
