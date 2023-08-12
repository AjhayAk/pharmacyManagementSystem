/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy_management;

import java.awt.Color;
import javax.swing.*;

public class Header {
    JPanel headPanel = new JPanel();
    JLabel titleLbl = new JLabel("Apollo Pharmacy");
    JLabel addressLbl = new JLabel("");
    Header()
    {
        headPanel.setBackground(Color.BLACK);
        headPanel.setForeground(Color.white);
        titleLbl.setForeground(Color.white);
        
        headPanel.setBounds(0,0,600,100);
        titleLbl.setBounds(150,30,400,40);
        addressLbl.setForeground(Color.white);
        titleLbl.setFont (titleLbl.getFont().deriveFont (32.0f));
        
        headPanel.add(titleLbl);  
        headPanel.add(addressLbl);  
        
        headPanel.setLayout(null);
    }   
}