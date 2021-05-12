package project.pbo.game.helper;

import project.pbo.game.Card;

import java.awt.*;
import java.util.Random;

public class Healing extends Card {
    private final int heal;

    public Healing() {
        super("Heal Potion");
        heal = new Random().nextInt(8)+3;
    }

    @Override
    protected void cetak(Graphics g) {

    }

    public int getHeal() {
        return heal;
    }




}
