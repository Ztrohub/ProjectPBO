package project.pbo;

import project.pbo.account.DB;
import project.pbo.input.KeyManager;
import project.pbo.input.MouseManager;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.*;

public class Handler {
    private Game game;
    private DB db = new DB();
    private double vol = 0.5;

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

    public void setVol(Clip clip){
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(this.vol) / Math.log(10) * 20);
        gain.setValue(dB);
    }

    public void setVol(Clip clip, double vol){
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(this.vol + vol) / Math.log(10) * 20);
        gain.setValue(dB);
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
}
