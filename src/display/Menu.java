/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author hwpva
 */
public class Menu extends JPanel {
    
    JButton start = new JButton("Start");
    JButton levels = new JButton("Levels");
    JButton quit = new JButton("Quit");
    
    public Menu(){
        this.initComponents();
    }
    
    private void initComponents() {
        this.setLayout(new GridLayout(3, 1));
        this.add(start);
        this.add(levels);
        this.add(quit);
    }
}
