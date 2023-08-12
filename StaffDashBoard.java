/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy_management;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author chess
 */
public class StaffDashBoard {
        Color myBlue = new Color (35,99,215);
    JPanel StaffDashBoardPanel = new JPanel();
    JButton purchaseBtn = new JButton("Purchase Entry");
    StaffDashBoard(){
        
        purchaseBtn.setBounds(200,300,200,30);
        purchaseBtn.setBackground(myBlue);
        purchaseBtn.setForeground(myBlue);
        StaffDashBoardPanel.setBounds(0,100,600,600);
        StaffDashBoardPanel.setBackground(Color.DARK_GRAY);
    }
}
