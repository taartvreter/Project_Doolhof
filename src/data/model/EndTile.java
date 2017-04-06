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

    public EndTile(int positionX, int positionY) {
        super(positionX, positionY);
    }

    /*  public BufferedImage getImage(){

    }*/
    public static void showWinningMessage() {
        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Winnaar die je bent ;)!");
    }

    /**
     * @return the image of a end tile.
     */
    @Override
    public BufferedImage getImage() {
        return Asset.endTile;
    }
}
