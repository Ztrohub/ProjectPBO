package project.pbo.states;

import project.pbo.Handler;

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
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void playMusic();

}
