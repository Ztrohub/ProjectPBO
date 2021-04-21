package project.pbo.states;

import project.pbo.util.KeyHandler;
import project.pbo.util.MouseHandler;

import java.awt.*;

public abstract class GameState {

    private GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);

}
