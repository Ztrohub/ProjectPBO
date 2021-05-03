package project.pbo.game;

public class Character {
    String symbol;
    int health;
    int x, y;

    public Character(String symbol, int health, int x, int y) {
        this.symbol = symbol;
        this.health = health;
        this.x = x;
        this.y = y;
    }
}
