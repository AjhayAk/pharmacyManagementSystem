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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author chess
 */
public class Sales implements ActionListener {
    String query="";
    Color myBlue = new Color (35,99,215);
    JPanel SalesPanel = new JPanel();
    JTextField BranchSearch = new JTextField();
    JTextField EmpSearch = new JTextField();
    JTextField DateSearch = new JTextField();
    JLabel BranchSearchLbl = new JLabel("Branch Id");
    JLabel EmpSearchLbl = new JLabel("Employee Id");
    JLabel DateSearchLbl = new JLabel("Date ");
    JButton searchBtn = new JButton("Search");
    JTextArea resultArea = new JTextArea();
    JButton backBtn = new JButton("Back");
    Sales(){
        
        searchBtn.setBounds(450,100,100,30);
        searchBtn.setBackground(myBlue);
        searchBtn.setForeground(Color.white);
        searchBtn.addActionListener(this);
        SalesPanel.setBounds(0,100,600,600);
        SalesPanel.setBackground(Color.DARK_GRAY);
        
        BranchSearch.setBounds(50,110,110,30);
        EmpSearch.setBounds(180,110,110,30);
        DateSearch.setBounds(310,110,110,30);
        BranchSearchLbl.setBounds(50,80,110,30);
        BranchSearchLbl.setForeground(Color.white);
        EmpSearchLbl.setBounds(180,80,110,30);
        EmpSearchLbl.setForeground(Color.white);
        DateSearchLbl.setBounds(310,80,110,30);
        DateSearchLbl.setForeground(Color.white);
        resultArea.setBounds(50,170,500,300);
        backBtn.setBounds(50, 500,100,30);
        backBtn.setBackground(myBlue);
        backBtn.setForeground(Color.WHITE);
        
        SalesPanel.add(searchBtn);
        SalesPanel.add(BranchSearch);
        SalesPanel.add(EmpSearch);
        SalesPanel.add(DateSearch);
        SalesPanel.add(BranchSearchLbl);
        SalesPanel.add(EmpSearchLbl);
        SalesPanel.add(DateSearchLbl);
        SalesPanel.add(resultArea);
        SalesPanel.add(backBtn);
        
        
        SalesPanel.setVisible(false);
        SalesPanel.setLayout(null);
        
    }
    
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if( s.equals("Search"))
        {
            if(!BranchSearch.getText().equals("") && !EmpSearch.getText().equals("") && !DateSearch.getText().equals(""))
            {
                query = "SELECT * FROM SALES WHERE BRANCH_ID = '" + BranchSearch.getText() + "' AND EMP_ID = '" + EmpSearch.getText() + "' AND TRUNC(PURCHASE_DATE, 'DD') = '" + DateSearch.getText() + "'";
            }
            else if(!BranchSearch.getText().equals("") && !EmpSearch.getText().equals("") && DateSearch.getText().equals(""))
            {
                query = "SELECT * FROM SALES WHERE BRANCH_ID = '" + BranchSearch.getText() + "' AND EMP_ID = '" + EmpSearch.getText() + "'";
            }
            else if(!BranchSearch.getText().equals("") && EmpSearch.getText().equals("") && !DateSearch.getText().equals(""))
            {
                query = "SELECT * FROM SALES WHERE BRANCH_ID = '" + BranchSearch.getText()  + "' AND TRUNC(PURCHASE_DATE, 'DD') = '" + DateSearch.getText() + "'";
            }
            else if(BranchSearch.getText().equals("") && !EmpSearch.getText().equals("") && !DateSearch.getText().equals(""))
            {
                query = "SELECT * FROM SALES WHERE EMP_ID = '" + EmpSearch.getText() + "' AND TRUNC(PURCHASE_DATE, 'DD') = '" + DateSearch.getText() + "'";
            }
            else if(!BranchSearch.getText().equals("") && EmpSearch.getText().equals("") && DateSearch.getText().equals(""))
            {
                query = "SELECT * FROM SALES WHERE BRANCH_ID = '" + BranchSearch.getText() + "'";
            }
            else if(BranchSearch.getText().equals("") && !EmpSearch.getText().equals("") && DateSearch.getText().equals(""))
            {
                query = "SELECT * FROM SALES WHERE  EMP_ID = '" + EmpSearch.getText() +  "'";
            }
            else if(BranchSearch.getText().equals("") && EmpSearch.getText().equals("") && !DateSearch.getText().equals(""))
            {
                
                query = "SELECT * FROM SALES WHERE TRUNC(PURCHASE_DATE, 'DD') =  '" + DateSearch.getText() + "'";
            }
            else
            {
                query = "SELECT * FROM SALES";
            }
            resultArea.setText("");
            getResult(query);
        }
    }
    
    void getResult(String query)
    {
        try
        { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query); 			
			while(rs.next())  
                        {
                            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +"  "+ rs.getString(4)+"|");  
                            resultArea.append(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +"  "+ rs.getString(4)+"  "+ rs.getString(5)+"  "  + rs.getString(6)+"\n");
                        }
	}
        catch(ClassNotFoundException | SQLException ex)
        { 
            System.out.println(ex);
        }
    }    
    void clear()
    {
        EmpSearch.setText("");
        BranchSearch.setText("");
        DateSearch.setText("");
        resultArea.setText("");
    }
    
}
