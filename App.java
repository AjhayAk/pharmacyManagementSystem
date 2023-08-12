/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy_management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

/**
 *
 * @author chess
 */
public class App implements ActionListener{
    JFrame universalFrame = new JFrame();
    Header headFrame = new Header();
    Login  loginPage = new Login();
    Purchase purchasePage = new Purchase();
    ManagerDashBoard  ManagerDashBoardPage = new ManagerDashBoard();
    Sales  SalesPage = new Sales();
    NewEmployee  NewEmployeePage = new NewEmployee();
    
    App(){
        //Login Page
        loginPage.loginPanel.setVisible(true);
        universalFrame.setSize(600,700);
        universalFrame.add(loginPage.loginPanel);
        universalFrame.add(headFrame.headPanel);
        universalFrame.add(purchasePage.purchasePanel);
        universalFrame.add(ManagerDashBoardPage.ManagerDashBoardPanel);
        universalFrame.add(SalesPage.SalesPanel);
        universalFrame.add(NewEmployeePage.NewEmployeePanel);
        loginPage.loginBtn.addActionListener(this);
        purchasePage.backBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                       purchasePage.purchasePanel.setVisible(false);  
                       loginPage.loginPanel.setVisible(true);  
                       loginPage.clear();
                       loginPage.msgLbl.setText("");
            }
        });
        SalesPage.backBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                       SalesPage.SalesPanel.setVisible(false); 
                       ManagerDashBoardPage.ManagerDashBoardPanel.setVisible(true);  
                       SalesPage.clear();
            }
        });
        ManagerDashBoardPage.backBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                       ManagerDashBoardPage.ManagerDashBoardPanel.setVisible(false);  
                       loginPage.loginPanel.setVisible(true); 
                       loginPage.clear();
                       loginPage.msgLbl.setText("");
            }
        });
       ManagerDashBoardPage.salesBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                       ManagerDashBoardPage.ManagerDashBoardPanel.setVisible(false);  
                       SalesPage.SalesPanel.setVisible(true);
            }
        });
       ManagerDashBoardPage.addEmployeeBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                       ManagerDashBoardPage.ManagerDashBoardPanel.setVisible(false);  
                       NewEmployeePage.NewEmployeePanel.setVisible(true);
            }
        });
        universalFrame.setLayout(null);
        universalFrame.setVisible(true);
        headFrame.headPanel.setVisible(true);
        universalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try
        {  
			//step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
                        
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			  
			//step4 execute query  
			ResultSet rs=stmt.executeQuery("SELECT EMP_ID, BRANCH_ID, PASSWORD FROM EMPLOYEE"); 			
			while(rs.next())  
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			  
			//step5 close the connection object  
			con.close();  
		  
	}
        catch(ClassNotFoundException | SQLException e){ System.out.println(e);}  
    }
    
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if( s == "Login")
        {
            if(loginPage.authenticate())
            {
                loginPage.loginPanel.setVisible(false);
                if(loginPage.level == 1)
                {   
                    purchasePage.purchasePanel.setVisible(true);
                    purchasePage.currEmpId = loginPage.currEmpId;
                }
                if(loginPage.level == 2)
                {
                    System.out.println("Manage Login");
                    ManagerDashBoardPage.ManagerDashBoardPanel.setVisible(true);
                }
            }
            else
            {
                loginPage.clear();
            }
        }
    }
}
