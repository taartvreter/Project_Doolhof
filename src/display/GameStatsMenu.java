/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author hwpva
 */
public class GameStatsMenu extends JPanel {

    private final JLabel keyPinCodeLabel;

    public GameStatsMenu() {
        this.keyPinCodeLabel = new JLabel(" ");
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(new FlowLayout());
        this.add(keyPinCodeLabel);
    }

    public void changeKeyPinCode(int keyPinCode) {
        this.keyPinCodeLabel.setText("U heeft een sleutel vast met de volgende waarde:  " + keyPinCode);
    }
}
