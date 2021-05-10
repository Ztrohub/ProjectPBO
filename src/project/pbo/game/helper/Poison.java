package project.pbo.game.helper;

import project.pbo.game.Card;
import java.util.Random;

public class Poison extends Card {
    private final int poison;

    public Poison() {
        super("Poison");
        poison = (new Random().nextInt(5)+1) * -1;
    }

    public int getPoison() {
        return poison;
    }

}
