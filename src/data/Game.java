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
import data.model.EndTile;
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

    private static int nextLevel = 1;


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
                checkEndTile();
            }
        };

        display = new Display(title, width, height, keyWhisperer);

        Asset.init();

        this.loadLevel(nextLevel);
    }

    private void checkEndTile() {
        int playerLocationX = this.player1.getLocationX();
        int playerLocationY = this.player1.getLocationY();
        
        int endTileX = EndTile.getEndTilePositionX();
        int endTileY = EndTile.getEndTilePositionY();  
        if(playerLocationX == endTileX && playerLocationY == endTileY){
            if(nextLevel == 4){
                EndTile.showEndmessage();
                nextLevel -= LevelGetter.getNumberOfLevels() - 1;
                this.loadLevel(nextLevel);
            } else {
                EndTile.showWinningMessage();
                nextLevel++;
                this.loadLevel(nextLevel);
            }
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

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(mazeMap[j][i].getImage(), TILEDRAWINGWIDTH * (mazeMap[j][i].getLocationX() - 1), TILEDRAWINGHEIGHT * (mazeMap[j][i].getLocationY() - 1), this);
            }
        }
        //draw character
        g.drawImage(getPlayer1().getImage(), TILEDRAWINGWIDTH * (getPlayer1().getLocationX() - 1), TILEDRAWINGHEIGHT * (getPlayer1().getLocationY() - 1), this);

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

    public static int getLevel() {
        return nextLevel;
    }

    public void loadLevel(int levelUp) {
        LevelGetter levelLoader = new LevelGetter();
//        System.out.println(levelLoader.getNumberOfLevels());
        this.mazeMap = levelLoader.loadMapToArray(levelUp);
        this.player1 = levelLoader.loadPlayer();
    }

    public void tryPlayerMove(KeyEvent e) {
        System.out.println(e.paramString());
        System.out.println(e.toString());
        System.out.println(e.getWhen());
        int keyPressed = e.getKeyCode();
        int locationX = this.getPlayer1().getLocationX();
        int locationY = this.getPlayer1().getLocationY();

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
                } else {
                    System.out.println("You can't walk of board.");
                }
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (locationY < 10) {
                    nextItemLocationY = locationY;
                    nextItemLocationX = locationX - 1;
                    moveDirection = "down";
                    canWalk = true;
                } else {
                    System.out.println("You can't walk of board.");
                }
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (locationX > 1) {
                    nextItemLocationY = locationY - 1;
                    nextItemLocationX = locationX - 2;
                    moveDirection = "left";
                    canWalk = true;
                } else {
                    System.out.println("You can't walk of board.");
                }
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (locationX < 10) {
                    nextItemLocationY = locationY - 1;
                    nextItemLocationX = locationX;
                    moveDirection = "right";
                    canWalk = true;
                } else {
                    System.out.println("You can't walk of board.");
                }
                break;
                
            case KeyEvent.VK_R:
                 int lvl = Game.getLevel();
                this.loadLevel(lvl);
                //canvas.requestFocusInWindow();
             break;

        }
        if (canWalk) {
            GameElement standingObject = this.mazeMap[nextItemLocationY][nextItemLocationX].getStandingObject();

            if (standingObject instanceof Barricade) {
                Barricade walkingAgainstBarricade = getPlayer1().putKeyInBarricade((Barricade) standingObject);
                if (walkingAgainstBarricade == null) {
                    this.mazeMap[nextItemLocationY][nextItemLocationX].setStandingObject(walkingAgainstBarricade);
                    this.getPlayer1().move(moveDirection);
                }
            } else if (standingObject instanceof Key) {
                this.display.setCurrentKeyCode(((Key) standingObject).getKeyPinCode());
                Key walkingAgainstKey = getPlayer1().pickUpKey((Key) standingObject);
                this.mazeMap[nextItemLocationY][nextItemLocationX].setStandingObject(walkingAgainstKey);
                this.getPlayer1().move(moveDirection);
            } else if (standingObject instanceof Wall) {
                Wall.walkAgainstWall();
            } else {
                this.getPlayer1().move(moveDirection);
            }
        }
    }

    /**
     * @return the player1 added player for testing purposes
     */
    public Player getPlayer1() {
        return player1;
    }
}
