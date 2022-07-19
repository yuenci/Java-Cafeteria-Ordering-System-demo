import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    static MainFrame instance;
    public MainFrame(String title){
        super(title);
        instance= this;
        this.setLayout(null);

        UC_login uc_login = new UC_login();
        uc_login.setBounds(425,85,430,550);

        this.add(uc_login);



    }
}
