package project.pbo.game.helper;

import project.pbo.game.Card;
import project.pbo.gfx.Assets;

import java.awt.*;
import java.util.Random;

public class Gold extends Card {

    private final int gold;

    public Gold() {
        super("Gold");
        gold = new Random().nextInt(5)+1;
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.coin, (x*184)+100+this.x, (y*177)+130+this.y, 60, 60, null);
    }

    public int getGold() {
        return gold;
    }
}
