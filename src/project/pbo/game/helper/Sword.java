package project.pbo.game.helper;

import project.pbo.game.Card;

import java.awt.*;
import java.util.Random;

public class Sword extends Card {
    private final int damage;
    public Sword() {
        super("Sword");
        damage = new Random().nextInt(8)+3;
    }

    @Override
    protected void cetak(Graphics g) {

    }

    public int getDamage() {
        return damage;
    }

}
