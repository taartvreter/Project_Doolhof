/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import State.GameState;
import State.State;
import data.model.Player;
import data.model.Tile;
import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.LevelGetter;
import gfx.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

/**
 *
 * @author Frenky
 */
public class Game extends JPanel implements Runnable {

    //Game Models
    private Player player1;
    private Tile[][] mazeMap;

    //Display Variables
    private Display display;
    public int width, height;
    public String title;

    private static final int TILEDRAWINGWIDTH = 70, TILEDRAWINGHEIGHT = 70;

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
        //Add the keyboard listener
        KeyAdapter keyWhisperer = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                doPlayerMove(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                doPlayerMove(e);
            }
        };

        display = new Display(title, width, height, keyWhisperer);
        Assets.init();
//soutce key bindings java swing: http://stackoverflow.com/questions/22741215/how-to-use-key-bindings-instead-of-key-listeners
        //keyListener 
        gameState = new GameState();
        State.setState(gameState);

        this.loadLevel();
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
        for (int i = 0; i <= 10; i++) {
            g.drawLine((i * 70), 0, (i * 70), 700);
        }
        for (int i = 0; i <= 10; i++) {
            g.drawLine(0, (i * 70), 700, (i * 70));
        }

        for (Tile[] tileOnMap : this.mazeMap) {
            for (int i = 0; i < tileOnMap.length; i++) {
                g.drawImage(tileOnMap[i].getImage(), TILEDRAWINGWIDTH * (tileOnMap[i].getLocationX() - 1), TILEDRAWINGHEIGHT * (tileOnMap[i].getLocationY() - 1), this);
            }

        }
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

    private void loadLevel() {
        this.mazeMap = new LevelGetter().loadMapToArray();
    }

    public void doPlayerMove(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }
}
