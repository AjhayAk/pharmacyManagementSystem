/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy_management;
import java.awt.*;
import javax.swing.*;
public class JScrollablePanelTest extends JFrame {
   public JScrollablePanelTest() {
      setTitle("JScrollablePanel Test");
      setLayout(new BorderLayout());
      JPanel panel = createPanel();
      add(BorderLayout.CENTER, new JScrollPane(panel));
      setSize(375, 250);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   public static JPanel createPanel() {
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(10, 4, 10, 10));
      
      return panel;
   }
}