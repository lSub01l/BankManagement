package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FastCash extends JFrame implements ActionListener{

    JButton fastWithdraw1, fastWithdraw2, fastWithdraw3, fastWithdraw4, fastWithdraw5, fastWithdraw6 ,backButton;
    String pinNumber;
    FastCash(String pinNumber) {
        this.pinNumber = pinNumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Select amount to withdraw");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        fastWithdraw1 = new JButton("25");
        fastWithdraw1.setBounds(170, 415, 150, 30);
        fastWithdraw1.addActionListener(this);
        image.add(fastWithdraw1);

        fastWithdraw2 = new JButton("50");
        fastWithdraw2.setBounds(355, 415, 150, 30);
        fastWithdraw2.addActionListener(this);
        image.add(fastWithdraw2);

        fastWithdraw3 = new JButton("100");
        fastWithdraw3.setBounds(170, 450, 150, 30);
        fastWithdraw3.addActionListener(this);
        image.add(fastWithdraw3);

        fastWithdraw4 = new JButton("200");
        fastWithdraw4.setBounds(355, 450, 150, 30);
        fastWithdraw4.addActionListener(this);
        image.add(fastWithdraw4);

        fastWithdraw5 = new JButton("500");
        fastWithdraw5.setBounds(170, 485, 150, 30);
        fastWithdraw5.addActionListener(this);
        image.add(fastWithdraw5);

        fastWithdraw6 = new JButton("1000");
        fastWithdraw6.setBounds(355, 485, 150, 30);
        fastWithdraw6.addActionListener(this);
        image.add(fastWithdraw6);

        backButton = new JButton("BACK");
        backButton.setBounds(355, 520, 150, 30);
        backButton.addActionListener(this);
        image.add(backButton);

        setSize(900, 900);
        setLocation(300,0);
//            setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        } else {
            String amount = ((JButton)ae.getSource()).getText();
            Conn c = new Conn();
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinNumber+"'");
                int balance = 0;
                while(rs.next()) {
                    if (rs.getString("type").equals("deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (ae.getSource() != backButton && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values('"+pinNumber+"', '"+date+"', 'Withdraw', '"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Debited: " +amount+ "$ successfully");
                setVisible(false);
                new Transactions(pinNumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
             }
        } 
    }
    public static void main (String args[]){
        new FastCash("");
    }
}