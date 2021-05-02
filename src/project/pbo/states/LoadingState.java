package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.window.SIZE;

import java.awt.*;
import java.util.Random;

public class LoadingState extends State implements SIZE {

    private final State nextState;
    private int count = 0;
    private final int rand;
    private final String[] quotes = {"\"Every creature has a weakness. The wise hero trains for what he will face.\""
            , "\"The important thing is not how long you live. It's what you accomplish with your life.\""
            , "\"Overconfidence is a slow and insidious killer.\""
            , "\"Even in dark times, we cannot relinquish the things that make us human\""
            , "\"Fear is the path to the dark side.\""
            , "\"Sometimes dungeon is scary and dark. That is why we must find the light.\""
            , "\"We never lose our demons. We only learn to live above them.\""};

    public LoadingState(Handler handler, State nextState) {
        super(handler);
        this.nextState = nextState;
        rand = new Random().nextInt(6);
    }

    @Override
    public void tick() {
        int time = new Random().nextInt(400)+500;
        if (count < time){
            count++;
        }
        if (count >= time){
            nextState.playMusic();
            setCurrentState(nextState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.loadingBG,0, 0, width, height, null);
        Text.drawString(g, quotes[rand], 540 , 300, true, Color.WHITE, Assets.dungeonFont);
        Text.drawString(g, "LOADING...", 950 , 500, true, Color.WHITE, Assets.dungeonFont);
    }

    @Override
    public void playMusic() {

    }
}
