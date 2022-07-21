import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UC_history extends JFrame {
    private static  UC_history instance;
    private static String time;

    public UC_history(String title){
        super(title);
        instance = this;
        this.setLayout(null);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel cardContainer = new JPanel();
        cardContainer.setBounds(20,25,345,280);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(9);
        cardContainer.setLayout(flowLayout);
        cardContainer.setOpaque(true);
        cardContainer.setBackground(new Color(99, 230, 190));
        this.add(cardContainer);

        addButtons();
        addCards(cardContainer);
    }

    private void addButtons(){
        JButton okBtn = new JButton("OK");
        okBtn.setBounds(20,320,140,55);
        instance.add(okBtn);

        JButton cancelbtn = new JButton("Cancel");
        cancelbtn.setBounds(225,320,140,55);
        instance.add(cancelbtn);

        okBtn.addActionListener(e -> {
            Status.currentPage = "history";
            Status.dataTimeChose = time;
            instance.dispose();
            prepareHistoryData();
            MainFrame.changeToPayment();

        });

        cancelbtn.addActionListener(e -> instance.dispose());
    }

    private void addCards(JPanel cardContainer){
        ArrayList<JPanel> cardsArray = new ArrayList<>();
        ArrayList<String[]> orderdata = Data.getbriefOrderDataByTpNum();



        for (int i = 0; i < 5; i++) {
            JPanel card = new JPanel();
            card.setPreferredSize(new Dimension(320,45));


            JLabel price  = new JLabel("RM "+ orderdata.get(i)[1]);
            JLabel time = new JLabel(orderdata.get(i)[0]);

            price.setPreferredSize(new Dimension(100,40));
            time.setPreferredSize(new Dimension(150,40));

            card.add(price);
            card.add(time);
            card.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JPanel ele:cardsArray
                    ) {
                        ele.setBackground(new Color(255, 255, 255));
                    }

                    card.setBackground(new Color(8, 127, 91));
                    UC_history.time = time.getText();
                }

            });

            cardContainer.add(card);
            cardsArray.add(card);

        }
    }

    private  void prepareHistoryData(){
        ArrayList<String[]> data =  Data.getItemsByCurrentTPNumAndTimeDESC(Status.dataTimeChose);
        Status.currentShoppingCart.clearShoppingCart();
        for (String[] ele:data
             ) {
            //System.out.println(ele[1]+ "--"+Integer.parseInt(ele[2])+ele[1] + "history+101");
            UC_foodCard card = new UC_foodCard(ele[1]  ,Integer.parseInt(ele[2]),ele[1]);
            card.amount = Integer.parseInt(ele[3]);
            Status.currentShoppingCart.addDataToShoppingCart(card);
        }

    }
}
