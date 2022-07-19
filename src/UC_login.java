import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UC_login extends JPanel {
    JLabel accountlabel;
    JTextField accountField;
    JLabel passwordlabel;
    JTextField pwsField;
    JButton loginBtn;
    JButton recoveryBtn;
    UC_login instance;
    public UC_login(){
        instance = this;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(430,550));
        this.setOpaque(true);
        this.setBackground(new Color(79, 174, 238));

        addControls();
    }

    private void addControls(){
        accountlabel = new JLabel("TP Number");
        accountlabel.setBounds(54,43,160,24);

        accountField = new JTextField(20);
        accountField.setBounds(54,91,322,73);

        passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(54,211,176,24);

        pwsField = new JTextField(20);
        pwsField.setBounds(54,247,322,73);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(54,401,140,55);

        recoveryBtn = new JButton("Recovery");
        recoveryBtn.setBounds(236,401,140,55);

        this.add(accountlabel);
        this.add(accountField);
        this.add(passwordlabel);
        this.add(pwsField);
        this.add(loginBtn);
        this.add(recoveryBtn);

        loginBtn.addActionListener(new loginBtnListener());


    }

    private class loginBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String acs = accountField.getText();
            String pws = pwsField.getText();

            MainFrame.changeToCustomerShopping();

            /*if(acs.isEmpty() || pws.isEmpty()){
                JOptionPane.showMessageDialog(instance,"Enter account and password please");
            }

            if(Data.authentication(acs,pws)){
                JOptionPane.showMessageDialog(instance,"Welcome" + Status.userName);
                if(Objects.equals(Status.type, "admin")){
                    MainFrame.changeToAdminDashBoard();
                }else{
                    MainFrame.changeToCustomerShopping();
                }

            }else
            {
                JOptionPane.showMessageDialog(instance,"TP number or password has mistake");
            }
             */
        }
    }
}
