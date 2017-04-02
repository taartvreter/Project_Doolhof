/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

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
}
