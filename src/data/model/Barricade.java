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
public class Barricade extends GameElement{

    private int pinCode;

    public Barricade() {
    }

    public boolean canWalkThrough() {
        return false;
    }

    public int getPinCode() {
        return this.pinCode;
    }
}
