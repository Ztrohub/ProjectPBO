package project.pbo;

import project.pbo.account.DB;
import project.pbo.input.MouseManager;

import java.io.*;

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

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public void saveFile(){
        try {
            FileOutputStream fos = new FileOutputStream("res/save.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(db);
            oos.close();
            fos.close();
        } catch (IOException e){
            System.out.println("There's Problem on Save!" + e);
        }
    }
}
