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
public class Wall extends GameElement {

    private final BufferedImage image = Asset.wall;;

    @Override
    public boolean canWalkThrough() {
        return false;
    }

    /**
     * @return the image
     */
    @Override
    public BufferedImage getImage() {
        return image;
    }
    public static void walkAgainstWall(){
        System.out.println("You can't walk through a wall..");
    }
}
