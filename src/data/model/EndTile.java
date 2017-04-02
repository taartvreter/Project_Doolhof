/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data.model;

import gfx.Assets;
import java.awt.image.BufferedImage;


/**
 *
 * @author hwpva
 */
public class EndTile extends Tile{

    public EndTile(int positionX, int positionY) {
        super(positionX, positionY);
    }
    
  /*  public BufferedImage getImage(){

    }*/
    public void showWinningMessage(){
        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(),"Winnaar die je bent ;)!");
    }
    
    @Override
    public BufferedImage getImage(){
        return Assets.endTile;
    }
}
