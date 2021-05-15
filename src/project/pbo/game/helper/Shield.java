package project.pbo.game.helper;

import project.pbo.game.Card;
import project.pbo.gfx.Assets;

import java.awt.*;
import java.util.Random;

public class Shield extends Card {
    private final int defend;

    public Shield() {
        super("Shield");
        defend = new Random().nextInt(6)+2;
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.shield, (x*184)+90+this.x, (y*177)+120+this.y, 80, 80, null);
    }

    public int getDefend() {
        return defend;
    }

}
