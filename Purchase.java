package pharmacy_management;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class Purchase  implements ActionListener{
    Color myBlue = new Color (35,99,215);
    String currEmpId = "",currCusId = "CUS101", newId = "";
    //String itemList[][] = new String[8][5];
    ArrayList<ArrayList<String> > itemList = new ArrayList<ArrayList<String> >();
    
    String bufferMedCode = "",  bufferMedName = "";
    int bufferMedCost = 0, bufferMedQty = 0;
    int itemCounter = 0 ;
    JPanel purchasePanel = new JPanel();
    JLabel titleLbl = new JLabel("Sales Entry Page");
    JLabel customerLbl = new JLabel("Customer ID : ");
    JTextField customerTxt = new JTextField();
    JLabel purchaseLbl = new JLabel("Item Code");
    JLabel QtyLbl = new JLabel("Quantity");
    JButton plusBtn = new JButton("add");
    JTextField itemCodeTxt = new JTextField();
    JTextField itemQtyTxt = new JTextField();
    JLabel[] itemCodeLbl = new JLabel[10];
    JLabel[] itemQtyLbl = new JLabel[10];
    JLabel[] itemCostLbl = new JLabel[10];
    JLabel quantityLbl = new JLabel("Quantitiy");
    JButton[] itemRemoveBtn = new JButton[10];
    JLabel itemCodeHead = new JLabel("Item Code");
    JLabel itemQtyHead = new JLabel("Quantity");
    JLabel itemCostHead = new JLabel("Cost");
    JLabel totalLbl = new JLabel("Total");
    JLabel totalAmountLbl = new JLabel("");
    JButton backBtn = new JButton("Back");
    JButton placeBtn = new JButton("Place Purchase");
    JLabel qtyMsgLbl = new JLabel("Out of Stock");
    Purchase() {
        
        
        
        purchasePanel.setBounds(0,100,600,600);
        purchasePanel.setBackground(Color.DARK_GRAY);
        customerLbl.setBounds(50,50,100,40);
        customerLbl.setForeground(Color.WHITE);
        customerTxt.setBounds(150,55,200,30);
        titleLbl.setForeground(Color.GREEN);
        titleLbl.setBounds(200,10,200,40);
        titleLbl.setFont (titleLbl.getFont ().deriveFont (16.0f));
        
        purchaseLbl.setBounds(50,100,100,40);
        purchaseLbl.setForeground(Color.WHITE);
        QtyLbl.setBounds(280,100,100,40);
        QtyLbl.setForeground(Color.WHITE);
        itemCodeTxt.setBounds(50,150,200,30);
        itemQtyTxt.setBounds(280,150,80,30);
        plusBtn.setBounds(400, 150,100,30);
        plusBtn.addActionListener(this);
        placeBtn.addActionListener(this);
        plusBtn.setBackground(Color.GREEN);
        
        
        qtyMsgLbl.setBounds(400, 100,100,30);
        qtyMsgLbl.setForeground(Color.red);
        qtyMsgLbl.setVisible(false);
        itemCodeHead.setBounds(50,200,100,30);
        itemQtyHead.setBounds(400,200,100,30);
        itemCostHead.setBounds(500,200,100,30);
        itemCodeHead.setForeground(Color.WHITE);
        itemQtyHead.setForeground(Color.WHITE);
        itemCostHead.setForeground(Color.WHITE);
        totalLbl.setBounds(50,470,100,30);
        totalLbl.setForeground(Color.WHITE);
        totalAmountLbl.setBounds(400,470,100,30);
        totalAmountLbl.setForeground(Color.WHITE);
        backBtn.setBounds(50, 500,100,30);
        backBtn.setBackground(myBlue);
        backBtn.setForeground(Color.WHITE);
        placeBtn.setBounds(410, 500,140,30);
        placeBtn.setBackground(myBlue);
        placeBtn.setForeground(Color.WHITE);
        
        purchasePanel.add(titleLbl);
        purchasePanel.add(customerLbl);
        purchasePanel.add(customerTxt);
        purchasePanel.add(purchaseLbl);
        purchasePanel.add(plusBtn);
        purchasePanel.add(itemCodeTxt);
        purchasePanel.add(itemQtyTxt);
        purchasePanel.add(QtyLbl);
        purchasePanel.add(itemCodeHead);
        purchasePanel.add(itemQtyHead);
        purchasePanel.add(itemCostHead);
        purchasePanel.add(totalLbl);
        purchasePanel.add(qtyMsgLbl);
        purchasePanel.add(totalAmountLbl);
        purchasePanel.add(backBtn);
        purchasePanel.add(placeBtn);
        
        for(int i=0;i<10;i++)
        {   
            itemCodeLbl[i] = new JLabel("item");
            itemCodeLbl[i].setForeground(Color.WHITE);
            itemCodeLbl[i].setVisible(false);
            itemQtyLbl[i] = new JLabel("item");
            itemQtyLbl[i].setForeground(Color.WHITE);
            itemQtyLbl[i].setVisible(false);
            itemCostLbl[i] = new JLabel("item");
            itemCostLbl[i].setForeground(Color.WHITE);
            itemCostLbl[i].setVisible(false);
            itemRemoveBtn[i] = new JButton("remove "+i);
            itemRemoveBtn[i].setVisible(false);
            itemCodeLbl[i].setBounds(50,(i*30)+230,100,30);
            itemQtyLbl[i].setBounds(400,(i*30)+230,100,30);
            itemCostLbl[i].setBounds(500,(i*30)+230,100,30);
            //itemRemoveBtn[i].setBounds(300,(i*30)+200,90,30);
            
            purchasePanel.add(itemCodeLbl[i]);
            purchasePanel.add(itemQtyLbl[i]);
            purchasePanel.add(itemCostLbl[i]);
            purchasePanel.add(itemRemoveBtn[i]);
            
        }
        purchasePanel.setLayout(null);
        purchasePanel.setVisible(false);
        
    }
    
    int calculateAmount()
    {
        int amount = 0;
        for(int i=0;i<itemCounter;i++)
        {
            //amount += Integer.parseInt(itemList[i][2]) * Integer.parseInt(itemList[i][4]);
            amount += Integer.parseInt(itemList.get(i).get(2)) * Integer.parseInt(itemList.get(i).get(4));
        }
        return amount;
    }
    
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if( s == "add")
        {
            if( !itemCodeTxt.getText().equals("")  && !itemQtyTxt.getText().equals("") )
            {   
                getMedicine(itemCodeTxt.getText());
                if(bufferMedCode.equals(itemCodeTxt.getText() ) && bufferMedQty >= Integer.parseInt(itemQtyTxt.getText()))
                {
                    
                    //itemList[itemCounter][0] = bufferMedCode ;
                    //itemList[itemCounter][1] = bufferMedName ;
                    //itemList[itemCounter][2] = ""+bufferMedCost ;
                    //itemList[itemCounter][3] = ""+bufferMedQty ;
                    //itemList[itemCounter][4] = itemQtyTxt.getText() ;
                    itemCodeLbl[itemCounter].setText(itemCodeTxt.getText());
                    itemList.add(new ArrayList<String>(Arrays.asList(bufferMedCode, bufferMedName, ""+bufferMedCost, ""+bufferMedQty,itemQtyTxt.getText())));
                    itemQtyLbl[itemCounter].setText(itemQtyTxt.getText());
                    
                    itemCostLbl[itemCounter].setText(""+Integer.parseInt(itemList.get(itemCounter).get(2)) * Integer.parseInt(itemList.get(itemCounter).get(4)));
                    System.out.println(itemCounter);
                    itemCodeLbl[itemCounter].setVisible(true);
                    itemQtyLbl[itemCounter].setVisible(true);
                    itemCostLbl[itemCounter].setVisible(true);
                    itemRemoveBtn[itemCounter].setVisible(true);
                    itemCounter++;
                    totalAmountLbl.setText("Rs."+ calculateAmount());
                    itemCodeTxt.setText("");
                    itemQtyTxt.setText("");
                    qtyMsgLbl.setVisible(false);
                }
                else if(bufferMedQty < Integer.parseInt(itemQtyTxt.getText()))
                {
                    qtyMsgLbl.setVisible(true);
                    System.out.print("\nOUT OF STOCK");
                }
            }
            
        }
        if( s == "Place Purchase" )
        {
            qtyMsgLbl.setVisible(false);
            currCusId = customerTxt.getText();
            if(currCusId.equals(checkCusId()))
            {
                for(int i=0;i<10;i++)
                {
                    itemCodeLbl[i].setText("");
                    itemQtyLbl[i].setText("");
                    itemCostLbl[i].setText("");
                }
                totalAmountLbl.setText("");
                customerTxt.setText("");
                updateSales();
                insertItems();
                updateStock();
                for(int i=0;i<itemCounter;i++)
                {
                    System.out.println("INSERT INTO SALES_ITEMS VALUES('"+ "','"+ itemList.get(i).get(0) +"'," + itemList.get(i).get(4) + "," + ( Integer.parseInt(itemList.get(i).get(2)) * Integer.parseInt(itemList.get(i).get(4)) )+ ")");
                }
            }
        }
    }
    
    int getMedicine(String MedCode)
    {
        int cost=0;
        try
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			//step3 create the statement object  
			Statement stmt=con.createStatement();
                        //"SELECT MEDICINE.MEDICINE_CODE, MEDICINE.MEDICINE_NAME , MEDICINE.COST, STOCK.QUANTITY FROM MEDICINE FULL JOIN STOCK ON MEDICINE.MEDICINE_CODE = STOCK.MEDICINE_CODE WHERE MEDICINE.MEDICINE_CODE = '"+MedCode+"' AND STOCK.BRANCH_ID=(SELECT BRANCH_ID FROM EMPLOYEE WHERE EMP_ID = '" + currEmpId +"')"
			ResultSet rs=stmt.executeQuery("SELECT MEDICINE.MEDICINE_CODE, MEDICINE.MEDICINE_NAME , MEDICINE.COST, STOCK.QUANTITY FROM MEDICINE FULL JOIN STOCK ON MEDICINE.MEDICINE_CODE = STOCK.MEDICINE_CODE WHERE MEDICINE.MEDICINE_CODE = '"+MedCode+"' AND STOCK.BRANCH_ID=(SELECT BRANCH_ID FROM EMPLOYEE WHERE EMP_ID = '" + currEmpId +"')"); 			
			while(rs.next())  
                        {
                            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +"  "+ rs.getString(4) );  
                            bufferMedCode = rs.getString(1);
                            bufferMedName = rs.getString(2);
                            bufferMedCost = Integer.parseInt(rs.getString(3));
                            bufferMedQty= Integer.parseInt(rs.getString(4));
                            //getStock(MedCode);
                        }
	}
        catch(ClassNotFoundException | SQLException ex)
        { 
            System.out.println(ex);
        }
        return cost;
    }
    
    int getStock(String MedCode)
    {
        int qty = 0;
        try
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT QUANTITY FROM STOCK WHERE MEDICINE_CODE = '"+ MedCode +"' AND BRANCH_ID = '"+ getBranchId() + "'"); 			
			while(rs.next())  
                        {
                            System.out.println(rs.getString(1));
                            bufferMedQty= Integer.parseInt(rs.getString(1));
                        }
	}
        catch(ClassNotFoundException | SQLException ex)
        { 
            System.out.println(ex);
        }
        return qty;
    }
    
    void updateStock()
    {
        
        System.out.println("Place Purchase");
        while(itemCounter>0)
        {
            itemCounter--;
            try
            {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			//step3 create the statement object  
			Statement stmt=con.createStatement();
                        int i = Integer.parseInt( itemList.get(itemCounter).get(3) ) - ( Integer.parseInt(itemList.get(itemCounter).get(4)) );
                        System.out.println("i-"+i);
			stmt.executeUpdate("UPDATE STOCK SET QUANTITY = "+ i +" WHERE MEDICINE_CODE = '"+itemList.get(itemCounter).get(0)+"' AND BRANCH_ID = '" + getBranchId() + "'"); 			
			stmt.executeUpdate("COMMIT");
            }
            catch(ClassNotFoundException | SQLException ex)
            { 
                System.out.println(ex);
            }
            
        }
    }
    
    void updateSales()
    {
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
            try
            {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");		//step3 create the statement object  
			Statement stmt=con.createStatement();
                        System.out.println(date);
                        newId = "PUR" + (Integer.parseInt(getLastId().substring(3)) + 1 );
                        stmt.executeUpdate("INSERT INTO SALES VALUES('"+ newId+ "',"+ calculateAmount() +",'" + currEmpId + "','" + currCusId + "','"+ getBranchId() + "',SYSDATE)"); 			
			stmt.executeUpdate("COMMIT");
            }
            catch(ClassNotFoundException | SQLException ex)
            { 
                System.out.println(ex);
            }
            
    }
    String getLastId()
    {   String id= "";
        int idNo=100;
        try
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			//step3 create the statement object  
			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery("SELECT PURCHASE_ID FROM SALES"); 			
			while(rs.next())
                        {
                            System.out.println("Branch : " + rs.getString(1));
                            if( Integer.parseInt(rs.getString(1).substring(3)) > idNo)
                            {
                                idNo = Integer.parseInt(rs.getString(1).substring(3));
                                id = rs.getString(1);
                            }
                        }
	}
        catch(ClassNotFoundException | SQLException ex)
        { 
            System.out.println(ex);
        }
        return id;
    }
    String getBranchId()
    {
        String branchId = "";
        try
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			//step3 create the statement object  
			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery("SELECT BRANCH_ID FROM EMPLOYEE WHERE EMP_ID = '" + currEmpId +"'"); 			
			while(rs.next())
                        {
                            System.out.println(rs.getString(1));
                            branchId = rs.getString(1);
                        }
            
	}
        catch(ClassNotFoundException | SQLException ex)
        { 
            System.out.println(ex);
        }
        return branchId;
    }
    String checkCusId()
    {
        String CusId = "";
        try
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");
			//step3 create the statement object  
			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery("SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_ID = '" + currCusId +"'"); 			
			while(rs.next())
                        {
                            System.out.println(rs.getString(1));
                            CusId = rs.getString(1);
                        }
                        return CusId;
            
	}
        catch(ClassNotFoundException | SQLException ex)
        { 
            System.out.println(ex);
        }
        return CusId;
    }
    void insertItems()
    {
        for(int i=0;i<itemCounter;i++)
        {
         try
            {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl","sys as sysdba","!Passw0rd");		//step3 create the statement object  
			Statement stmt=con.createStatement();
			stmt.executeUpdate("INSERT INTO SALES_ITEMS VALUES('"+ newId+ "','"+ itemList.get(i).get(0) +"'," + itemList.get(i).get(4) + "," + ( Integer.parseInt(itemList.get(i).get(2)) * Integer.parseInt(itemList.get(i).get(4)) )+ ")"); 			
			System.out.println();
                
                        stmt.executeUpdate("COMMIT");
            }
            catch(ClassNotFoundException | SQLException ex)
            { 
                System.out.println(ex);
            }
        }    
    }
        
}
