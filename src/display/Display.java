/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Frenky
 */
public class Display {

    private GameStatsMenu statsMenu;
    private JFrame frame;
    private Canvas canvas;
    private JPanel menu;

    private String title;
    private int width, height;

    private final KeyAdapter keyAdapter;

    public Display(String title, int width, int height, KeyAdapter kAdapter) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.keyAdapter = kAdapter;
        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        
        ////Inventory
        this.statsMenu = new GameStatsMenu();
        frame.add(this.statsMenu, BorderLayout.NORTH);
        
        ///Menu
        menu = new Menu();
        menu.addKeyListener(this.keyAdapter);
        menu.setPreferredSize(new Dimension(width, height));
        menu.requestFocusInWindow();
        frame.add(menu, BorderLayout.CENTER);
        
        ////Game
//        canvas = new Canvas();
//        canvas.addKeyListener(this.keyAdapter);
//        canvas.setPreferredSize(new Dimension(width, height));
//        canvas.requestFocusInWindow();
//        frame.add(canvas, BorderLayout.CENTER);
        
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCurrentKeyCode(int pinCode) {
        statsMenu.changeKeyPinCode(pinCode);
    }
}
