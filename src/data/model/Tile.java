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
public class Tile {

    private Player character;

    private GameElement standingObject;

    private final int locationX;
    private final int locationY;

    public Tile(int positionX, int positionY) {
        this.locationX = positionX;
        this.locationY = positionY;
        this.standingObject = null;
    }

    public int getLocationX() {
        return this.locationX;
    }

    public int getLocationY() {
        return this.locationY;
    }

    public void setStandingObject(GameElement newStandingObject) {
        this.standingObject = newStandingObject;
    }

    public GameElement getStandingObject() {
        return this.standingObject;
    }

    public BufferedImage getImage() {
        if (this.standingObject != null) {
            return this.standingObject.getImage();
        } else if (character != null) {
            return this.character.getImage();
        } else if (this instanceof EndTile) {
            return this.getImage();
        }
        return null;
    }
}
