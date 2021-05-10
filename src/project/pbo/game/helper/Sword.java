package project.pbo.game.helper;

import project.pbo.game.Card;
import java.util.Random;

public class Sword extends Card {
    private final int damage;
    public Sword() {
        super("Sword");
        damage = new Random().nextInt(8)+3;
    }

    public int getDamage() {
        return damage;
    }

}
