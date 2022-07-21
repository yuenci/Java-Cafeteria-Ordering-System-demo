import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    static MainFrame instance;
    static JPanel root;
    public MainFrame(String title){
        super(title);
        instance= this;


        root = new JPanel();
        root.setLayout(null);

        this.setContentPane(root);

        /*JPanel uc_login = new UC_login();
        uc_login.setBounds(425,85,430,550);*/


        JPanel uc_shopping = new UC_shopping();
        uc_shopping.setBounds(0,0,1280,720);

        /*JPanel uc_payment = new UC_payment();
        uc_payment.setBounds(0,0,1280,720);*/

        /*JPanel uc_analysis = new UC_analysis();
        uc_analysis.setBounds(0,0,1280,720);*/

        root.add(uc_shopping);


    }

    public static void changeToAdminDashBoard()
    {
        JPanel uc_analysis = new UC_analysis();
        uc_analysis.setBounds(0,0,1280,720);

        addNewPanelToFrame(uc_analysis);

    }

    public static void changeToCustomerShopping()
    {
        JPanel uc_shopping = new UC_shopping();
        uc_shopping.setBounds(0,0,1280,720);

        addNewPanelToFrame(uc_shopping);

    }

    public static void changeToPayment()
    {
        JPanel uc_payment = new UC_payment();
        uc_payment.setBounds(0,0,1280,720);

        addNewPanelToFrame(uc_payment);

    }

    public static void changeToLogin()
    {
        JPanel uc_login = new UC_login();
        uc_login.setBounds(425,85,430,550);

        addNewPanelToFrame(uc_login);

    }

    public static void changeToRegister()
    {
        JPanel uc_register = new UC_register();
        uc_register.setBounds(425,85,430,550);

        addNewPanelToFrame(uc_register);

    }

    public static void addNewPanelToFrame(JPanel panel){
        root.removeAll();
        root.add(panel);
        root.repaint();
        root.validate();
    }
}
