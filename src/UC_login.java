import javax.swing.*;
import java.awt.*;

public class UC_login extends JPanel {
    public UC_login(){
        this.setLayout(null);

        this.setPreferredSize(new Dimension(430,550));
        this.setOpaque(true);
        this.setBackground(new Color(79, 174, 238));

        addControls();
    }

    private void addControls(){
        JLabel accountlabel = new JLabel("Account Number");
        accountlabel.setBounds(54,43,160,24);

        JTextField accountField = new JTextField(20);
        accountField.setBounds(54,91,322,73);

        JLabel passwordlabel = new JLabel("Account password");
        passwordlabel.setBounds(54,211,176,24);

        JTextField pwsField = new JTextField(20);
        pwsField.setBounds(54,247,322,73);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(54,401,140,55);

        JButton recoveryBtn = new JButton("Recovery");
        recoveryBtn.setBounds(236,401,140,55);

        this.add(accountlabel);
        this.add(accountField);
        this.add(passwordlabel);
        this.add(pwsField);
        this.add(loginBtn);
        this.add(recoveryBtn);
    }
}
