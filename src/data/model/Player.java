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
public class Player {

    private String name = "Hope Solo";
    private Key keyPocket;

    public Player() {
    }

    public void move() {
    }

    public void openBarricade() {
    }

    public void checkKeyPocket() {
    }

    public void pickUpKey() {
    }

    public int getKeyPinCode() {
        return this.keyPocket.getKeyPinCode();
    }

    public String getName() {
        return this.getName();
    }
}
