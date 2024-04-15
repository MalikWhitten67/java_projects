import javax.swing.*; 
import java.awt.*;
import Bank.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer; 
import java.util.TimerTask;   
 
public class main { 
 
     public static void main(String[] args) {  
        bank b = new bank(); 
        JFrame frame = new JFrame("Capitol None Banking ");  
        Font arial = new Font("Monospace", Font.PLAIN, 20);
        frame.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(8, 5, 120, 20));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        JLabel money = new JLabel(" Your balance is $" +b.balance);
        money.setFont(arial);
        panel.add(money);
        JLabel apyJLabel = new JLabel("Account apy is at 2.5%"); 
        apyJLabel.setFont(arial); 
        panel.add(apyJLabel); 
        panel.add(new JLabel("Make a Deposit"));
        JTextField field = new JTextField("Insert Amount to Deposit", 0);
        field.addMouseListener( new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e){
            field.setText("");
          }
        });
        panel.add(field);
        JButton depobutton = new JButton("Deposit");
        depobutton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(java.awt.event.ActionEvent e){
           double amount = new Double(field.getText());
          if(amount > 0){
             b.balance = b.balance + amount - amount * 2.5 / 100;
             field.setText("Insert Amount To Deposit");  
             JOptionPane.showMessageDialog(frame, "Deposited $" + new String().valueOf(amount - amount * 2.5 / 100) + " Successfully");
          }
         }
        });
        JLabel withdrawJLabel = new JLabel("Withdraw Money From Your Account");
        withdrawJLabel.setFont(arial);
        panel.add(depobutton); 
        panel.add(withdrawJLabel);

        JButton witdButton = new JButton("Withdraw");

        JTextField field2 = new JTextField("Insert Amount to Withdraw", 0);

        field2.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) { 
            field2.setText("");
          }
        });

        panel.add(field2);
        
        witdButton.addMouseListener(new MouseAdapter() { 
         @Override
         public void mouseClicked(MouseEvent e) { 
            double amount = new Double(field2.getText());
            if(amount >= b.balance){
               JOptionPane.showMessageDialog(frame, "Error: You inputted a value higher than you have in your account");
               field2.setText("Insert Amount to Withdraw");
               return;
            }
            amount = amount  - amount * 2.5 / 100;
            b.balance = b.balance - amount; 
            System.out.println(b.balance);
            
            JOptionPane.showMessageDialog(frame,  "Successfully withdrew $" + amount);

         }
        });

        panel.add(witdButton);
         
        new Timer().schedule(new TimerTask() {
           public void run(){
               b.balance = Math.round( b.balance  + b.balance * 2.5 / 100);
               money.setText("Your balance is $" + String.valueOf(b.balance));
           }
        }, 20, 3005);
        frame.add(panel);
        frame.setVisible(true);
     }
}
