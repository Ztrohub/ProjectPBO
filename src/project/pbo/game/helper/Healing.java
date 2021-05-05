package project.pbo.game.helper;

import project.pbo.game.Card;

import java.util.Random;

public class Healing extends Card {
    private final int heal;

    public Healing() {
        super("H+");
        heal = new Random().nextInt(8)+3;
    }

    public int getHeal() {
        return heal;
    }
}
