package project.pbo.game.enemy;

import project.pbo.gfx.Assets;

import java.awt.*;

public class Shaman extends Enemy{
    private int ctrEnemy;
    private int timer;

    public Shaman(int stage) {
        super("Shaman", stage, 1, 5);
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.shamanEnemy[ctrEnemy], (x*184)+95+this.x, (y*177)+120+this.y, 90, 90, null);

        if(timer == 0) {
            ctrEnemy = (ctrEnemy == Assets.shamanEnemy.length - 1) ? 0 : ++ctrEnemy;
            this.timer++;
        } else {
            if(timer == 7) this.timer = 0;
            else this.timer++;
        }
    }
}
