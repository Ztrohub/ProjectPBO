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
    private final String[] quotes = {"Use WASD or Arrow keys to move your character."
            , "You can gain attack, damage, and max health from shop."
            , "\"Fear is the path to the dark side.\""
            , "There are monster bosses waiting in every 10 stages."
            , "Reflector only can be gain from the grass card."
            , "When using reflector, user immune to all damage !"};

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
        Text.drawString(g, quotes[rand], 350 , 300, true, Color.WHITE, Assets.dungeonFont);
        Text.drawString(g, "LOADING...", 950 , 80, true, Color.WHITE, Assets.dungeonFont);
    }

    @Override
    public void playMusic() {

    }
}
