import javax.swing.*;
import java.awt.*;

public class UC_register  extends JPanel {
    JLabel accountlabel;
    JTextField accountField;
    JLabel passwordlabel;
    JTextField pwsField;
    JButton registerBtn;
    JButton cancelBtn;
    UC_register instance;
    private  boolean registed = false;
    public UC_register(){
        instance = this;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(430,550));
        this.setOpaque(true);
        this.setBackground(new Color(255, 165, 216));

        addControls();
    }

    private void addControls(){
        accountlabel = new JLabel("Name");
        accountlabel.setBounds(54,43,160,24);

        accountField = new JTextField(20);
        accountField.setBounds(54,91,322,73);

        passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(54,211,176,24);

        pwsField = new JTextField(20);
        pwsField.setBounds(54,247,322,73);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(54,401,140,55);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(236,401,140,55);

        this.add(accountlabel);
        this.add(accountField);
        this.add(passwordlabel);
        this.add(pwsField);
        this.add(registerBtn);
        this.add(cancelBtn);

        registerBtn.addActionListener(e -> {
            String name = accountField.getText();
            String pws = pwsField.getText();


            if(name.isEmpty() || pws.isEmpty()){
                JOptionPane.showMessageDialog(instance,"Enter account and password please");
            }else{
                register();
            }
        });

        cancelBtn.addActionListener(e -> MainFrame.changeToLogin());

    }


    private void register(){
        String acs = accountField.getText();
        String pws = pwsField.getText();

        /*MainFrame.changeToCustomerShopping();*/

        if(acs.isEmpty() || pws.isEmpty()){
            JOptionPane.showMessageDialog(instance,"Enter account and password please");
        }else{
            String tpNum =  Data.getNewTPNum();
            String cusRecord = tpNum + "," + acs + "," +pws;
            Data.addStringToFile(Setting.customerDataPath, cusRecord);

            registed = true;

            String msg = "Register successfully!\nYou TP number is "+ tpNum + "\nAre you going to back to Login page?";

            int select = JOptionPane.showConfirmDialog(this,msg,"Success",JOptionPane.YES_NO_OPTION);

            if(select ==0){
                MainFrame.changeToLogin();
            }
        }
    }
}