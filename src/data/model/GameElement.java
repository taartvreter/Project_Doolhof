/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author hwpva
 */
public abstract class GameElement {
    private BufferedImage image;
    public void setImage(){}
    abstract public boolean canWalkThrough();
    //abstract public void draw();
}
