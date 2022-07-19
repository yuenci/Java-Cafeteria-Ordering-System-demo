import javax.swing.*;
import java.awt.*;

public class UC_admin_dashboard extends JPanel {
    public UC_admin_dashboard(){
        this.setLayout(null);

        this.setPreferredSize(new Dimension(430,550));
        this.setOpaque(true);
        this.setBackground(new Color(79, 174, 238));


        JLabel accountlabel = new JLabel("Account Number");
        accountlabel.setBounds(54,43,160,24);
        this.add(accountlabel);
    }
}
