package project.pbo.game;

import project.pbo.game.enemy.Enemy;
import project.pbo.game.helper.Healing;
import project.pbo.game.helper.Poison;
import project.pbo.game.helper.Shield;
import project.pbo.game.helper.Sword;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;

import java.awt.*;

public abstract class Card {
    private String symbol;
    private float alhpa = 1f;

    private boolean animating = false, fade = false;
    private int move = 0;
    protected int x = 0, y = 0;

    public Card(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void fading(){
        this.fade = true;
    }

    public void tick(){
        if (fade){
            animating = true;
            alhpa -= 0.05f;
            if (alhpa < 0f){
                alhpa = 0;
                fade = false;
                animating = false;
            }
        } else if (move != 0){
            animating = true;
//            UP
            if (move == 1){
                y-=6;
                if (y <= -177){
                    y = -177;
                    move = 0;
                }
            }
//            Right
            if (move == 2){
                x+=6;
                if (x >= 184){
                    x = 184;
                    move = 0;
                }
            }
//            Down
            if (move == 3){
                y+=6;
                if (y >= 177){
                    y = 177;
                    move = 0;
                }
            }
//            Left
            if (move == 4){
                x-=6;
                if (x <= -184){
                    x = -184;
                    move = 0;
                }
            }
        } else {
            animating = false;
        }
    }

    public void render(Graphics g, int x, int y){
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alhpa));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(Assets.cardBG, (x*184)+40+this.x, (y*177)+80+this.y, 184, 177, null);
        g.drawImage(Assets.card, (x*184)+40+this.x, (y*177)+80+this.y, 184, 177, null);
        Text.drawString(g, this.getSymbol(), (x * 184) + 132+this.x, (y * 177) + 225+this.y, true,
                this instanceof PlayerCard ? new Color(217, 199, 51) : Color.WHITE, Assets.smallerFont);

        cetak(g);

        if (this instanceof PlayerCard){
            PlayerCard pc = (PlayerCard) this;
            Text.drawString(g, ""+pc.getDamage(), (x*184)+190+this.x-10, (y*177)+230+this.y-5, true, new Color(74, 116, 186), Assets.regulerFont);
            Text.drawString(g, ""+pc.getDefend(), (x*184)+70+this.x+10, (y*177)+230+this.y-5, true, new Color(74, 116, 186), Assets.regulerFont);
            Text.drawString(g, ""+pc.getHealth(), (x*184)+190+this.x-10, (y*177)+105+this.y+10, true, new Color(166, 121, 89), Assets.regulerFont);
        }
        if (this instanceof Enemy){
            Enemy enemy = (Enemy) this;
            Text.drawString(g, "" + enemy.getHealth(), (x * 184) + 190+this.x-10, (y * 177) + 105+this.y+10, true, new Color(166, 121, 89), Assets.regulerFont);
        }
        if (this instanceof Sword){
            Sword sword = (Sword) this;
            Text.drawString(g, ""+ sword.getDamage(), (x * 184) + 190+this.x-10, (y * 177) + 105+this.y+10, true, new Color(74, 116, 186), Assets.regulerFont);
        }
        if (this instanceof Healing){
            Healing healing = (Healing) this;
            Text.drawString(g, ""+healing.getHeal(), (x * 184) + 190+this.x-10, (y * 177) + 105+this.y+10, true, new Color(206, 34, 50), Assets.regulerFont);
        }
        if (this instanceof Poison){
            Poison poison = (Poison) this;
            Text.drawString(g, ""+poison.getPoison(), (x * 184) + 190+this.x-10, (y * 177) + 105+this.y+10, true, new Color(206, 34, 50), Assets.regulerFont);
        }
        if (this instanceof Shield){
            Shield shield = (Shield) this;
            Text.drawString(g, ""+shield.getDefend(), (x * 184) + 190+this.x-10, (y * 177) + 105+this.y+10, true, new Color(74, 116, 186), Assets.regulerFont);
        }

    }

    protected abstract void cetak(Graphics g);

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public boolean isAnimating() {
        return animating;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
