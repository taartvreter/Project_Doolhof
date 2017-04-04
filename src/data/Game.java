/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.model.*;
import display.Display;
import gfx.Assets;
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
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                tryPlayerMove(e);
            }
        };

        display = new Display(title, width, height, keyWhisperer);
        Assets.init();
//soutce key bindings java swing: http://stackoverflow.com/questions/22741215/how-to-use-key-bindings-instead-of-key-listeners
        //keyListener 

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

        for (Tile[] tileOnMap : this.mazeMap) {
            for (int i = 0; i < tileOnMap.length; i++) {
                g.drawImage(tileOnMap[i].getImage(), TILEDRAWINGWIDTH * (tileOnMap[i].getLocationX() - 1), TILEDRAWINGHEIGHT * (tileOnMap[i].getLocationY() - 1), this);
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
        int ticks = 0;

        while (isRunning) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
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
        LevelGetter levelLoader = new LevelGetter();
        this.mazeMap = levelLoader.loadMapToArray();
        this.player1 = levelLoader.loadPlayer();
    }

    public void tryPlayerMove(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        int locationX = this.player1.getLocationX();
        int locationY = this.player1.getLocationY();

        String moveType = "";
        switch (keyPressed) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                //up
                if (locationY > 1) {
                    moveType = "up";
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                //down
                if (locationY < 10) {
                    GameElement standingObject = this.mazeMap[locationY][locationX - 1].getStandingObject();
                    //System.out.println(standingObject);
                    if (standingObject instanceof Barricade) {
                        Barricade walkingAgainstBarricade = player1.putKeyInBarricade((Barricade) standingObject);
                        if (walkingAgainstBarricade == null) {
                            this.mazeMap[locationY][locationX - 1].setStandingObject(walkingAgainstBarricade);
                        }
                    } else if (standingObject instanceof Key) {
                        this.display.setCurrentKeyCode(((Key) standingObject).getKeyPinCode());
                        Key walkingAgainstKey = player1.pickUpKey((Key) standingObject);
                        this.mazeMap[locationY][locationX - 1].setStandingObject(walkingAgainstKey);
                    }
                    standingObject = this.mazeMap[locationY][locationX - 1].getStandingObject();
                    if (standingObject != null) {
                        if (standingObject.canWalkThrough()) {
                            this.player1.move("down");
                        }
                    } else {
                        this.player1.move("down");
                    }

                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                //left
                if (locationX > 1) {
                    this.player1.move("left");
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                //right
                if (locationX < 10) {
                    this.player1.move("right");
                }
                break;
            default:
                moveType = "";
                break;
        }

        this.player1.move(moveType);
    }
}
