package project.pbo;

import project.pbo.account.DB;

public class Handler {
    private Game game;
    private DB db = new DB();

    public Handler(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }
}
