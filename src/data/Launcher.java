/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Frenky
 */
public class Launcher {
    
    public static void main(String[] args) throws Exception {
        Game game = new Game("Project", 800, 800);
        game.start();
    }
}
