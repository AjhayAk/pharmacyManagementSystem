/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy_management;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author chess
 */
public class NewEmployee  implements ActionListener {
    
      Color myBlue = new Color (35,99,215);
    JPanel NewEmployeePanel = new JPanel();
    JLabel empNameLbl = new JLabel("Name : ");
    JLabel empPassLbl = new JLabel("Password : ");
    JLabel empDescLbl = new JLabel("Designation : ");
    JLabel empPhoneLbl = new JLabel("Phone No : ");
    JLabel empAddressLbl = new JLabel("Address : ");
    JLabel empBranchLbl= new JLabel("Branch : ");    
    JLabel manPassLbl= new JLabel("Manager Authentication :");

    
    JTextField empNameTxt = new JTextField();
    JPasswordField empPassTxt = new JPasswordField();
    JTextField empDescTxt = new JTextField();
    JTextField empPhoneTxt = new JTextField();
    JTextField empAddressTxt = new JTextField();
    JTextField empBranchTxt = new JTextField();
    JPasswordField manPassTxt = new JPasswordField();
    JButton addBtn = new JButton("Add Employee");
    JButton backBtn = new JButton("Back");
    NewEmployee(){
        NewEmployeePanel.setBounds(0,100,600,600);
        NewEmployeePanel.setBackground(Color.DARK_GRAY);
        
        backBtn.setBounds(50, 500,100,30);
        backBtn.setBackground(myBlue);
        backBtn.setForeground(Color.WHITE);
        addBtn.setBounds(410, 500,140,30);
        addBtn.setBackground(myBlue);
        addBtn.setForeground(Color.WHITE);
        empNameTxt.setBounds(250,100,200,30);
        empNameTxt.setForeground(Color.black);
        empPassTxt.setBounds(250,150,200,30);
        empPassTxt.setForeground(Color.black);
        empDescTxt.setBounds(250,200,200,30);
        empDescTxt.setForeground(Color.black);
        empPhoneTxt.setBounds(250,250,200,30);
        empPhoneTxt.setForeground(Color.black);
        empAddressTxt.setBounds(250,300,200,30);
        empAddressTxt.setForeground(Color.black);
        empBranchTxt.setBounds(250,350,200,30);
        empBranchTxt.setForeground(Color.black);
        manPassTxt.setBounds(250,400,200,30);
        manPassTxt.setForeground(Color.black);
        empNameLbl.setBounds(50,100,100,40);
        empNameLbl.setForeground(Color.white);
        empPassLbl.setBounds(50,150,100,40);
        empPassLbl.setForeground(Color.white);
        empDescLbl.setBounds(50,200,100,40);
        empDescLbl.setForeground(Color.white);
        empPhoneLbl.setBounds(50,250,100,40);
        empPhoneLbl.setForeground(Color.white);
        empAddressLbl.setBounds(50,300,100,40);
        empAddressLbl.setForeground(Color.white);
        empBranchLbl.setBounds(50,350,100,40);
        empBranchLbl.setForeground(Color.white);
        manPassLbl.setBounds(50,400,150,40);
        manPassLbl.setForeground(Color.white);
        //"INSERT INTO EMPLOYEE "
        NewEmployeePanel.add(addBtn);
        NewEmployeePanel.add(backBtn);
        NewEmployeePanel.add(empNameTxt);
        NewEmployeePanel.add(empPassTxt);
        NewEmployeePanel.add(empDescTxt);
        NewEmployeePanel.add(empPhoneTxt);
        NewEmployeePanel.add(empAddressTxt);
        NewEmployeePanel.add(empBranchTxt);
        NewEmployeePanel.add(manPassTxt);
        NewEmployeePanel.add(empNameLbl);
        NewEmployeePanel.add(empPassLbl);
        NewEmployeePanel.add(empDescLbl);
        NewEmployeePanel.add(empPhoneLbl);
        NewEmployeePanel.add(empAddressLbl);
        NewEmployeePanel.add(empBranchLbl);
        NewEmployeePanel.add(manPassLbl);
        
        
        NewEmployeePanel.setVisible(false);
        NewEmployeePanel.setLayout(null);
    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if( s.equals("Add Employee"))
        {
            try
            { 
                            Class.forName("oracle.jdbc.driver.OracleDriver");
                            Connection con=DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
                            Statement stmt=con.createStatement();
                            ResultSet rs=stmt.executeQuery("INSERT INTO EMPLOYEE VALUES('"   + "')"); 			
                            while(rs.next())  
                            {
                                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +"  "+ rs.getString(4)+"|");  
                            }
            }
            catch(ClassNotFoundException | SQLException ex)
            { 
                System.out.println(ex);
            }
        }
    }
}
