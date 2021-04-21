package project.pbo.states;

import com.sun.media.jfxmedia.events.PlayerStateEvent;
import project.pbo.util.KeyHandler;
import project.pbo.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;

    public GameStateManager() {
        states = new ArrayList<>();

        states.add(new PlayState(this));
    }

    public void update() {
        for (GameState gameState : states){
            gameState.update();
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for (GameState gameState : states){
            gameState.input(mouse, key);
        }
    }

    public void render(Graphics2D g) {
        for (GameState gameState : states){
            gameState.render(g);
        }
    }

}
