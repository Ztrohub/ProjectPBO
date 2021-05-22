package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.window.SIZE;

import java.awt.*;
import java.util.Random;

public class LoadingState extends State implements SIZE {

    private final State nextState;
    private final Thread thread;
    private int count = 0;
    private final int rand;
    private final String[] quotes = {"Use WASD or Arrow keys to move your character."
            , "You can gain attack, damage, and max health from shop."
            , "\"Fear is the path to the dark side.\""
            , "There are monster bosses waiting in every 10 stages."
            , "Reflector only can be gain from the grass card."
            , "When using reflector, user immune to all damage !"};

    public LoadingState(Handler handler, State nextState) {
        super(handler);
        this.nextState = nextState;
        thread = new Thread(new LoadThread(nextState));
        thread.start();
        rand = new Random().nextInt(6);
    }

    @Override
    public void tick() {
        if (!thread.isAlive() && count > 300){
            nextState.playMusic();
            setCurrentState(nextState);
        }
        count++;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.loadingBG,0, 0, width, height, null);
        Text.drawString(g, quotes[rand], 350 , 300, true, Color.WHITE, Assets.dungeonFont);
        Text.drawString(g, "LOADING...", 950 , 80, true, Color.WHITE, Assets.dungeonFont);
    }

    @Override
    public void playMusic() {

    }

    @Override
    public void loadFile() {

    }
}
