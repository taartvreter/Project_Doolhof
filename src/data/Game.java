/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.model.*;
import display.Display;
import gfx.Asset;
import gfx.LevelGetter;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        init();
    }

    private void init() {
        //Add the keyboard listener
        KeyAdapter keyWhisperer = new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }

            @Override
            public void keyReleased(KeyEvent e) {
                tryPlayerMove(e);
            }
        };

        display = new Display(title, width, height, keyWhisperer);

        Asset.init();

        this.loadLevel();
        
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

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(mazeMap[j][i].getImage(), TILEDRAWINGWIDTH * (mazeMap[j][i].getLocationX() - 1), TILEDRAWINGHEIGHT * (mazeMap[j][i].getLocationY() - 1), this);
            }
        }
        //draw character
        g.drawImage(player1.getImage(), TILEDRAWINGWIDTH * (player1.getLocationX() - 1), TILEDRAWINGHEIGHT * (player1.getLocationY() - 1), this);

        //End Drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (isRunning) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            render();
            if (delta >= 1) {
                delta--;
            }

            if (timer >= 1000000000) {
                //System.out.println("Ticks and Frames: " + ticks);
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
        LevelGetter levelLoader = new LevelGetter();

        System.out.println(levelLoader.getNumberOfLevels());
        this.mazeMap = levelLoader.loadMapToArray(1);
        this.player1 = levelLoader.loadPlayer();
    }

    public void tryPlayerMove(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        int locationX = this.player1.getLocationX();
        int locationY = this.player1.getLocationY();

        int nextItemLocationX = 0;
        int nextItemLocationY = 0;
        String moveDirection = "";
        boolean canWalk = false;

        switch (keyPressed) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (locationY > 1) {
                    nextItemLocationY = locationY - 2;
                    nextItemLocationX = locationX - 1;
                    moveDirection = "up";
                    canWalk = true;
                }
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (locationY < 10) {
                    nextItemLocationY = locationY;
                    nextItemLocationX = locationX - 1;
                    moveDirection = "down";
                    canWalk = true;
                }
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (locationX > 1) {
                    nextItemLocationY = locationY - 1;
                    nextItemLocationX = locationX - 2;
                    moveDirection = "left";
                    canWalk = true;
                }
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (locationX < 10) {
                    nextItemLocationY = locationY - 1;
                    nextItemLocationX = locationX;
                    moveDirection = "right";
                    canWalk = true;
                }
                break;

        }
        if (canWalk) {
            GameElement standingObject = this.mazeMap[nextItemLocationY][nextItemLocationX].getStandingObject();

            if (standingObject instanceof Barricade) {
                Barricade walkingAgainstBarricade = player1.putKeyInBarricade((Barricade) standingObject);
                if (walkingAgainstBarricade == null) {
                    this.mazeMap[nextItemLocationY][nextItemLocationX].setStandingObject(walkingAgainstBarricade);
                    this.player1.move(moveDirection);
                }
            } else if (standingObject instanceof Key) {
                this.display.setCurrentKeyCode(((Key) standingObject).getKeyPinCode());
                Key walkingAgainstKey = player1.pickUpKey((Key) standingObject);
                this.mazeMap[nextItemLocationY][nextItemLocationX].setStandingObject(walkingAgainstKey);
                this.player1.move(moveDirection);
            } else if (standingObject instanceof Wall) {
                Wall.walkAgainstWall();
            } else {
                this.player1.move(moveDirection);
            }
        }
    }
}
