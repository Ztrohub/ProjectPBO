package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.Player;
import project.pbo.account.User;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(Handler handler, User user){
        super(handler);
        this.player = user.getPlayer();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void playMusic() {

    }
}
