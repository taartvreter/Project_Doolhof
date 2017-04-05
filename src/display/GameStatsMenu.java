/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import data.model.Key;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author hwpva
 */
public class GameStatsMenu extends JPanel {

    private final JLabel keyPinCodeLabel;
    private final JLabel numberBarricadesOpend;

    public GameStatsMenu() {
        this.keyPinCodeLabel = new JLabel("KeyCode:");
        this.numberBarricadesOpend = new JLabel("Number of barricades opened:");
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(new FlowLayout());
        this.add(keyPinCodeLabel);
        this.add(numberBarricadesOpend);
    }

    public void changeKeyPinCode(int keyPinCode){
         this.keyPinCodeLabel.setText("U heeft een sleutel vast met de volgende waarde:  " + keyPinCode);
    }
}
