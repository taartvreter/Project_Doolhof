/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import javax.swing.JPanel;

/**
 *
 * @author Jim
 */
public class gameDisplay extends Canvas{

    private final KeyAdapter keyAdapter;
    private Canvas canvas;
    private int height;
    private int width;
    
    
    public gameDisplay(int width, int height, KeyAdapter kAdapter) {
        this.width = width;
        this.height = height;
        this.keyAdapter = kAdapter;
        this.initComponents();
    }

    private void initComponents() {
        canvas = new Canvas();
        canvas.addKeyListener(this.keyAdapter);
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.requestFocusInWindow();
    }
}
