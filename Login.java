package pharmacy_management;
import javax.swing.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;

public class Login{
    String currEmpId = "", readEmpId = "", currEmpPass = "", readEmpPass = "";
    int level = 0;
    JPanel loginPanel = new JPanel();
    JLabel usernameLbl = new JLabel("Username :");
    JTextField usernameTxt = new JTextField();
    JLabel passwordLbl = new JLabel("Password :");
    JPasswordField passwordTxt = new JPasswordField();
    JLabel msgLbl = new JLabel("");
    JButton loginBtn = new JButton("Login");
    boolean Authentication = false;
    
    Login() {
        Color myBlue = new Color (35,99,215);
        loginPanel.setBounds(0,100,600,600);
        loginPanel.setBackground(Color.DARK_GRAY);
        loginPanel.setForeground(Color.white);
        usernameLbl.setBounds(100,100,100,40);
        usernameLbl.setForeground(Color.white);
	usernameTxt.setBounds(250,105,200,30);
        passwordLbl.setBounds(100,150,100,40);
        passwordLbl.setForeground(Color.white);
        loginBtn.setBounds(250, 250,100,30);
        loginBtn.setBackground(myBlue);
        loginBtn.setForeground(Color.WHITE);
	passwordTxt.setBounds(250,155,200,30);
        msgLbl.setBounds(100,200,400,40);
        msgLbl.setForeground(Color.red);
        
        loginPanel.add(usernameLbl);
        loginPanel.add(usernameTxt);
        loginPanel.add(passwordLbl);
        loginPanel.add(passwordTxt);
        loginPanel.add(loginBtn);
        loginPanel.add(msgLbl);
        
        
        loginPanel.setLayout(null);
    }
    public boolean authenticate(){  
        readEmpId = usernameTxt.getText();
        readEmpPass = passwordTxt.getText();
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
			ResultSet rs=stmt.executeQuery("SELECT EMP_ID, BRANCH_ID, PASSWORD, DESIGNATION FROM EMPLOYEE WHERE EMP_ID = '"+readEmpId+"'"); 			
			while(rs.next())  
                        {
                            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +"  |"+ rs.getString(4)+"|");  
                            currEmpId = rs.getString(1);
                            currEmpPass = rs.getString(3);
                            if(rs.getString(4).equals("STAFF"))
                                level = 1;
                            else if(rs.getString(4).equals("MANAGER"))
                                level = 2;
                            
                        }
	}
        catch(ClassNotFoundException | SQLException ex)
        { 
            System.out.println(ex);
        }
        if( currEmpPass.equals(readEmpPass) && !currEmpPass.equals("") )
        {
            Authentication = true;
            return true;
        }
        else
        {
            Authentication = false;
            return false;
        } 
    }
    void clear()
    {
        usernameTxt.setText("");
        passwordTxt.setText("");
        Authentication = false;
        msgLbl.setText("Incorrect Username (or) Password...");
    }
}