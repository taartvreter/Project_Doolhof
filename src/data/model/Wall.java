/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import data.GameElement;

/**
 *
 * @author hwpva
 */
public class Wall extends GameElement {
    
    @Override
    public boolean canWalkThrough() {
        return false;
    }
}
