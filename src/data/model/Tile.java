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

    private GameElement standingObject;

    private final int locationX;
    private final int locationY;

    public Tile(int positionX, int positionY) {
        this.locationX = positionX;
        this.locationY = positionY;
        this.standingObject = null;
    }

    /**
     * @return the image of the player or gameElement that is standing on the
     * tile.
     */
    public BufferedImage getImage() {
        if (this.standingObject != null) {
            return this.standingObject.getImage();
        } /*else if (getCharacter() != null) {
            return this.getCharacter().getImage();
        }*/ else if (this instanceof EndTile) {
            return this.getImage();
        }
        return null;
    }

//    /**
//     * @return the character
//     */
//    public Player getCharacter() {
//        return character;
//    }
//
//    /**
//     * @param character the character to set
//     */
//    public void setCharacter(Player character) {
//        this.character = character;
//    }

    /**
     * @return the locationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * @return the locationY
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     * @return the standingObject
     */
    public GameElement getStandingObject() {
        return standingObject;
    }

    /**
     * @param standingObject the standingObject to set
     */
    public void setStandingObject(GameElement standingObject) {
        this.standingObject = standingObject;
    }
}
