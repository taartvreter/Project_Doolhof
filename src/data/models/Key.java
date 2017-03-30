/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.models;

/**
 *
 * @author hwpva
 */
public class Key {

    private int pinCode = 0;

    public Key() {
        java.util.Random rand = new java.util.Random(100);
    }

    public int getKeyCode() {
        return this.pinCode;
    }

    public boolean canWalkThrough() {
        return true;
    }
}
