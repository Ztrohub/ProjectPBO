package project.pbo.game.enemy;

import project.pbo.gfx.Assets;

import java.awt.*;

public class Skeleton extends Enemy {
    private int ctrMummy;
    private int timer;

    public Skeleton( int stage) {
        super("Skeleton", stage, 1, 5);
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.mummyEnemy[ctrMummy], (x*184)+57+this.x, (y*177)+90+this.y, 140, 130, null);

        if(timer == 0) {
            ctrMummy = (ctrMummy == 12) ? 0 : ++ctrMummy;
            this.timer++;
        } else {
            if(timer == 7) this.timer = 0;
            else this.timer++;
        }

    }
}
