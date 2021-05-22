package project.pbo.states;

import project.pbo.gfx.Assets;

public class LoadThread implements Runnable{

    private final State nextstate;

    public LoadThread(State nextstate) {
        this.nextstate = nextstate;
    }

    @Override
    public void run() {
        Assets.nullThisYeah();
        nextstate.loadFile();
    }
}
