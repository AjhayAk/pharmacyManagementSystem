/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy_management;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagerDashBoard {

    
      Color myBlue = new Color (35,99,215);
    JPanel ManagerDashBoardPanel = new JPanel();
    JButton salesBtn = new JButton("Purchase Entry");
    JButton addEmployeeBtn = new JButton("Add Employee");
    
    JButton backBtn = new JButton("Back");
    ManagerDashBoard(){
        ManagerDashBoardPanel.setBounds(0,100,600,600);
        ManagerDashBoardPanel.setBackground(Color.DARK_GRAY);
        salesBtn.setBounds(200,200,200,30);
        salesBtn.setBackground(myBlue);
        salesBtn.setForeground(Color.white);
        addEmployeeBtn.setBounds(200,250,200,30);
        addEmployeeBtn.setBackground(myBlue);
        addEmployeeBtn.setForeground(Color.white);
        backBtn.setBounds(50, 500,100,30);
        backBtn.setBackground(myBlue);
        backBtn.setForeground(Color.WHITE);
        
        
        ManagerDashBoardPanel.add(salesBtn);
        //ManagerDashBoardPanel.add(addEmployeeBtn);
        ManagerDashBoardPanel.add(backBtn);
        
        
        ManagerDashBoardPanel.setVisible(false);
        ManagerDashBoardPanel.setLayout(null);
    }
}
