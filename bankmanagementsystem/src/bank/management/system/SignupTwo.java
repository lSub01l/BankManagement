package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    long random;
    JButton next;
    JRadioButton sYes, sNo, eAccYes, eAccNo;
    JDateChooser dateChooser;
    JComboBox<String> iCategory, eCategory, oCategory;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L)+ 1000L);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2 OF X");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel income = new JLabel("Income: ");
        income.setFont(new Font("Raleway", Font.BOLD, 20));
        income.setBounds(100, 140, 200, 30);
        add(income);

        String incomeCategory[] = {"", "< 2500", "< 5000", "< 10000", "< 20000", "> 20000"};
        iCategory = new JComboBox<String>(incomeCategory);
        iCategory.setBounds(300,140,400,30);
        iCategory.setBackground(Color.WHITE);
        add(iCategory);

        JLabel education = new JLabel("Education: ");
        education.setFont(new Font("Raleway", Font.BOLD, 20));
        education.setBounds(100, 190, 200, 30);
        add(education);

        String educationCategory[] = {"Non-Graduation", "Graduate", "Post Graduation", "Doctrate", "Others"};
        eCategory = new JComboBox<String>(educationCategory);
        eCategory.setBounds(300,190,400,30);
        eCategory.setBackground(Color.WHITE);
        add(eCategory);

        JLabel occupation = new JLabel("Occupation: ");
        occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        occupation.setBounds(100, 240, 200, 30);
        add(occupation);

        String occupationCategory[] = {"Salaried", "Self-Employed", "Bussiness", "Student", "Retired", "Other"};
        oCategory = new JComboBox<String>(occupationCategory);
        oCategory.setBounds(300,240,400,30);
        oCategory.setBackground(Color.WHITE);
        add(oCategory);

        JLabel seniorCitizen = new JLabel("Senior Citizen: ");
        seniorCitizen.setFont(new Font("Raleway", Font.BOLD, 20));
        seniorCitizen.setBounds(100, 290, 200, 30);
        add(seniorCitizen);

        sYes = new JRadioButton("Yes");
        sYes.setBounds(300, 290, 100, 30);
        sYes.setBackground(Color.WHITE);
        add(sYes);

        sNo = new JRadioButton("No");
        sNo.setBounds(450, 290, 100, 30);
        sNo.setBackground(Color.WHITE);
        add(sNo);

        ButtonGroup seniorCitizenshipGroup = new ButtonGroup();
        seniorCitizenshipGroup.add(sYes);
        seniorCitizenshipGroup.add(sNo);

        JLabel existingAccount = new JLabel("Existing Account: ");
        existingAccount.setFont(new Font("Raleway", Font.BOLD, 20));
        existingAccount.setBounds(100, 340, 200, 30);
        add(existingAccount);

        eAccYes = new JRadioButton("Yes");
        eAccYes.setBounds(300, 340, 100, 30);
        eAccYes.setBackground(Color.WHITE);
        add(eAccYes);

        eAccNo = new JRadioButton("No");
        eAccNo.setBounds(450, 340, 100, 30);
        eAccNo.setBackground(Color.WHITE);
        add(eAccNo);

        ButtonGroup existingAccountGroup = new ButtonGroup();
        existingAccountGroup.add(eAccYes);
        existingAccountGroup.add(eAccNo);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(350, 10);
        setVisible(true);
        }

    public void actionPerformed(ActionEvent ae) {
        
        String sIncome = (String) iCategory.getSelectedItem();
        String eEducation = (String) eCategory.getSelectedItem();
        String sOccupation = (String) oCategory.getSelectedItem();
        String sSeniorCitizen = null;
        if (sYes.isSelected()) {
            sSeniorCitizen = "Yes";
        } else if(sNo.isSelected()) {
            sSeniorCitizen = "No";
        }
        String sExistingAccount = null;
        if (eAccYes.isSelected()) {
            sExistingAccount = "Yes";
        } else if(eAccNo.isSelected()) {
            sExistingAccount = "No";
        }

        try {
                Conn c = new Conn();
                String query = "insert into signuptwo values ('"+formno+"', '"+sIncome+"', '"+eEducation+"', '"+sOccupation+"', '"+sSeniorCitizen+"', '"+sExistingAccount+"')";
                c.s.executeUpdate(query);

                setVisible(false);
                new SignupThree(formno).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        } 

    public static void main(String[] args) throws Exception {
        new SignupTwo("");
    }
}