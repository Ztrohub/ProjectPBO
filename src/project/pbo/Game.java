package project.pbo;

import project.pbo.gfx.Assets;
import project.pbo.window.Window;
import states.IntroState;
import states.MainMenu;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Thread thread;
    private Window window;

    private BufferStrategy bs;
    private Graphics g;

//    States
    private State mainMenu, intro;

    private boolean running = false;

    public Game(){
        start();
    }

    private void init(){
        window = new Window();
        Assets.init();

//        mainMenu = new MainMenu();
//        State.setCurrentState(mainMenu);

        intro = new IntroState();
        State.setCurrentState(intro);
    }

    private void tick(){
        if (State.getCurrentState() != null)
            State.getCurrentState().tick();
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
}
