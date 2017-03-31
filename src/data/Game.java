/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import State.GameState;
import State.State;
import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Frenky
 */
public class Game extends JPanel implements Runnable {

    private Display display;
    public int width, height;
    public String title;
    
    private int breedte = 70, hoogte = 70;
    
    

    private Thread thread;
    private boolean isRunning = false;

    private BufferStrategy bs;
    private Graphics g;

    //State
    private State gameState;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        display = new Display(title, width, height);
        Assets.init();

        this.setFocusable(true);
        gameState = new GameState();
        State.setState(gameState);
    }

    private void tick() {
        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear Screen

        g.clearRect(0, 0, width, height);

        // Draw Here!
        
        
        for(int i = 0; i <= 10; i++){
            g.drawLine((i*70), 0, (i*70), 700); 
        }
        for(int i = 0; i <= 10; i++){
            g.drawLine(0, (i*70), 700, (i*70)); 
        }
        
        
        g.drawImage(Assets.player, 0,0, this);

        //g.drawImage(, WIDTH, WIDTH, this);
        //End Drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
