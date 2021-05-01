package project.pbo;

import project.pbo.account.DB;
import project.pbo.gfx.Assets;
import project.pbo.window.Window;
import project.pbo.states.IntroState;
import project.pbo.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Game implements Runnable {

    private Thread thread;
    private Window window;

    private BufferStrategy bs;
    private Graphics g;

    private DB db = new DB();

//    Handler
    private Handler handler;

    private boolean running = false;

    public Game(){
        start();
    }

    private void init(){
        window = new Window();
        Assets.init();
        handler = new Handler(this);
        loadFile();

        State.setCurrentState(new IntroState(handler));
    }

    private void tick(){
        if (State.getCurrentState() != null)
            State.getCurrentState().tick();
    }

    private void loadFile(){
        File file = new File("save.txt");
        if (file.exists()){
            try {
                FileInputStream fis = new FileInputStream("save.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                handler.setDb((DB) ois.readObject());
                ois.close();
                fis.close();
            } catch (IOException e){
                System.out.println("There's Problem on load!" + e);
            } catch (ClassNotFoundException ex){
                System.out.println("Class Not Found!");
            }
        }
    }

    private void render(){
        bs = window.getCanvas().getBufferStrategy();
        if(bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

//        Clear Screen
        g.clearRect(0,0, window.getWidth(), window.getHeight());

//        Draw Here
        if (State.getCurrentState() != null)
            State.getCurrentState().render(g);

        bs.show();
        g.dispose();

    }

    @Override
    public void run() {
        init();

        double fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
//        long timer = 0;
//        int ticks = 0;

        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
//            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
//                ticks++;
                delta--;
            }

//            UNCOMMENT SEMUA UNTUK LIHAT FPS
//            if (timer >= 1000000000){
//                System.out.println("FPS: " + ticks);
//                ticks = 0;
//                timer = 0;
//            }
        }

        stop();
    }

    public synchronized void start(){
        if (running) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running) return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Window getWindow() {
        return window;
    }
}