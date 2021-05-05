package project.pbo.game.helper;

import project.pbo.game.Card;

import java.util.Random;

public class Shield extends Card {
    private final int defend;

    public Shield() {
        super("Sh+");
        defend = new Random().nextInt(6)+2;
    }

    public int getDefend() {
        return defend;
    }
}
