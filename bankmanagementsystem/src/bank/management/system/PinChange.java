package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PinChange extends JFrame implements ActionListener{

    JPasswordField pin, rePin;
    JButton change, goBackButton;
    String pinNumber;

    PinChange(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD, 16));
        text.setBounds(250,280,500,35);
        image.add(text);

        JLabel pinText = new JLabel("New PIN:");
        pinText.setForeground(Color.WHITE);
        pinText.setFont(new Font("System",Font.BOLD, 16));
        pinText.setBounds(165,320,180,25);
        image.add(pinText);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330,320,180,25);
        image.add(pin);

        JLabel rePinText = new JLabel("Re-enter new PIN:");
        rePinText.setForeground(Color.WHITE);
        rePinText.setFont(new Font("System",Font.BOLD, 16));
        rePinText.setBounds(165,360,180,25);
        image.add(rePinText);

        rePin = new JPasswordField();
        rePin.setFont(new Font("Raleway", Font.BOLD, 25));
        rePin.setBounds(330,360,180,25);
        image.add(rePin);

        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        goBackButton = new JButton("BACK");
        goBackButton.setBounds(355, 520, 150, 30);
        goBackButton.addActionListener(this);
        image.add(goBackButton);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try{   
                String nPin = pin.getText();
                String rPin = rePin.getText();
                if (!nPin.equals(rPin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }
                if (nPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN:");
                    return;
                }
                if (rPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN:");
                    return; 
                }

                Conn conn = new Conn();
                String query1 = "update bank set pin = '"+rPin+"' where pin = '"+pinNumber+"' ";
                String query2 = "update login set pin = '"+rPin+"' where pin = '"+pinNumber+"' ";
                String query3 = "update signupthree set pin = '"+rPin+"' where pin = '"+pinNumber+"' ";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transactions(rPin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }
    }

    public static void main(String args[]){
        new PinChange("").setVisible(true);
    }
}