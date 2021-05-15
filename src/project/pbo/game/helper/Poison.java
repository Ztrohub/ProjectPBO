package project.pbo.game.helper;

import project.pbo.game.Card;
import project.pbo.gfx.Assets;

import java.awt.*;
import java.util.Random;

public class Poison extends Card {
    private final int poison;

    public Poison() {
        super("Poison");
        poison = (new Random().nextInt(5)+1) * -1;
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.poison, (x*184)+80+this.x, (y*177)+110+this.y, 100, 100, null);
    }

    public int getPoison() {
        return poison;
    }

}
