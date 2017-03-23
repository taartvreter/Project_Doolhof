/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import display.Display;
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
public class Game extends JPanel implements Runnable 
{

    private Display display;
    public int width, height;
    public String title;

    private Thread thread;
    private boolean isRunning = false;
    
    private BufferStrategy bs;
    private Graphics g;
    
    private BufferedImage testImage;
    private SpriteSheet sheet;
    
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        display = new Display(title, width, height);
        testImage = ImageLoader.LoadImage("/textures/shee1t.png");
        sheet = new SpriteSheet(testImage);
    }

    private void tick() {

    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        //clear Screen
        g.clearRect(0, 0, width, height);
        // Draw Here!
        
        g.drawImage(testImage, 200, 60, null);
        g.drawImage(sheet.crop(60, 20, 80, 80), 20, 20, null);
        
        //End Drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {

        init();

        while (isRunning) {
            tick();
            render();
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
