package project.pbo;

import project.pbo.gfx.ImageLoader;
import project.pbo.window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Thread thread;
    private Window window;

    private BufferStrategy bs;
    private Graphics g;

    private boolean running = false;

    public Game(){
        start();
    }

    private void init(){
        window = new Window();
    }

    private void tick(){

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

        bs.show();
        g.dispose();

    }

    @Override
    public void run() {
        init();

        while (running){
            tick();
            render();
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
}
