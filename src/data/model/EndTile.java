/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import gfx.Asset;
import java.awt.image.BufferedImage;

/**
 *
 * @author hwpva
 */
public class EndTile extends Tile {

    private static int positionX;
    private static int positionY;
    
    public EndTile(int positionX, int positionY) {

        super(positionX, positionY);
        this.positionX = positionX;
        this.positionY = positionY;

    }
  
    /*  public BufferedImage getImage(){

     }*/
    public static void showWinningMessage() {
        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Winnaar die je bent!");
    }
    
    public static void showEndmessage(){
        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Je hebt het spel uitgespeeld. Je gaat nu terug naar Level 1!");
    }

    /**
     * @return the image of a end tile.
     */
    @Override
    public BufferedImage getImage() {
        return Asset.endTile;
    }

    public static int getEndTilePositionX() {
        return positionX;
    }
    
    public static int getEndTilePositionY(){
        return positionY;
    }
}
