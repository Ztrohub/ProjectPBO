package project.pbo.game.helper;

import project.pbo.game.Card;

import java.awt.*;
import java.util.Random;

public class Shield extends Card {
    private final int defend;

    public Shield() {
        super("Shield");
        defend = new Random().nextInt(6)+2;
    }

    @Override
    protected void cetak(Graphics g) {

    }

    public int getDefend() {
        return defend;
    }

}
