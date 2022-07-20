import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class UC_shopping extends JPanel {
    public static UC_shopping instance;
    private  JPanel cardSubContainer;
    public JLabel totalAmount,totalPrice;
    private int cardAmount = 36;
    private ArrayList<String> foodData;
    public  ShoppingCart shoppingCart;

    JLabel historyPicLabel,finishPicLabel;

    public UC_shopping(){
        instance =this;
        this.setLayout(null);

        this.setPreferredSize(new Dimension(1280,720));

        this.setOpaque(true);
        this.setBackground(new Color(227, 180, 91));


        initLabels();
        initPics();

        initData();
        addCardContainer();

    }
    private  void initLabels()
    {
        JLabel welcome = new JLabel("Welcome " + Status.userName);
        AddLabel(welcome,80,30,200,24,"left");


        totalAmount= new JLabel("Total: 0");
        AddLabel(totalAmount,920,30,100,24,"left");


        totalPrice= new JLabel("RM  0");
        AddLabel(totalPrice,1080,30,100,24,"right");

    }

    private  void initPics(){
        ImageIcon historyPic = new ImageIcon("src/images/history.png");
        historyPic.setImage(historyPic.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        historyPicLabel = new JLabel() ;
        historyPicLabel.setIcon(historyPic);
        historyPicLabel.setBounds(1200,530,45,45);
        instance.add(historyPicLabel);
        historyPicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                historyPicLabel.setCursor(cur);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                addHistoryPanel();

                Status.currentPage = "history";
            }
        });

        ImageIcon finishPic = new ImageIcon("src/images/finish.png");
        finishPic.setImage(finishPic.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        finishPicLabel = new JLabel() ;
        finishPicLabel.setIcon(finishPic);
        finishPicLabel.setBounds(1200,600,45,45);
        instance.add(finishPicLabel);
        finishPicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                finishPicLabel.setCursor(cur);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(Status.currentShoppingCart.getTotoalPrice() !=0){
                    Status.currentPage = "payment";
                    MainFrame.changeToPayment();

                    System.out.println(Status.currentPage);
                }else{
                    JOptionPane.showMessageDialog(instance,"You haven't choose any food");
                }

            }
        });
    }

    private  void initData()
    {
        ArrayList<String> data = Data.readFile(Setting.foodDataPath);
        cardAmount = data.size();
        foodData = data;

        shoppingCart = new ShoppingCart();
        Status.currentShoppingCart =shoppingCart;
    }

    private void AddLabel(JLabel label,int x,int y,int w,int h,String align){
        label.setBounds(x,y,w,h);
        if(Objects.equals(align, "right")){
            label.setHorizontalAlignment(SwingConstants.RIGHT);
        }else if(Objects.equals(align, "left")){
            label.setHorizontalAlignment(SwingConstants.LEFT);
        }

        /*label.setOpaque(true);
        label.setBackground(new Color(6, 0, 255));*/

        label.setFont(new Font("Segoe UI",Font.BOLD,20));
        instance.add(label);
    }

    private void addCardContainer(){
        JPanel cardContainer = new JPanel();
        cardContainer.setBounds(60,70,1125,575);
        cardContainer.setLayout(null);
        this.add(cardContainer);


        cardSubContainer =  new JPanel();
        cardSubContainer.setOpaque(true);
        cardSubContainer.setBackground(new Color(227, 180, 91));
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(20);
        flowLayout.setVgap(20);
        cardSubContainer.setLayout(flowLayout);
        cardSubContainer.setBounds(0,-20,1100,cardAmount*300);

        cardContainer.add(cardSubContainer,BorderLayout.WEST);


        //JScrollBar vbar =new JScrollBar(JScrollBar.VERTICAL);
        JScrollBar vbar =new JScrollBar(JScrollBar.VERTICAL, 0, 20, 0, 50);
        vbar.addAdjustmentListener(new MyAdjustmentListener());


        vbar.setBounds(1100,0,250,575);
        cardContainer.add(vbar,BorderLayout.EAST);




        addCard(cardSubContainer,cardAmount);
    }

    private  void addCard(JPanel panel, int amount){
        for (int i = 0; i < amount; i++) {
            String[] args = foodData.get(i).split(",");
            JPanel uc_foodCard = new UC_foodCard(args[0],Integer.parseInt(args[1]),args[0] + ".png");
            uc_foodCard.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217)));
            panel.add(uc_foodCard);
        }
    }

    private  class MyAdjustmentListener implements AdjustmentListener{
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {

            //System.out.println("value: " + e.getValue());
            cardSubContainer.setBounds(0,e.getValue()*(cardAmount*300 /-90),1100,cardAmount*300);
        }
    }

    private void addHistoryPanel(){
        UC_history frame = new UC_history("Order History");

        frame.setResizable(false);

        frame.setSize(400, 420);
        frame.setVisible(true);

        //System.out.println("historyyyyyyy");

    }
}
