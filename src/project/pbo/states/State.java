package project.pbo.states;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    public static State getCurrentState() {
        return currentState;
    }

    //    CLASS
    public abstract void tick();

    public abstract void render(Graphics g);

}
