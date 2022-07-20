import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UC_payment extends JPanel {
    public  static  UC_payment instance;
    boolean clicked = false;
    public static JPanel cardContainer;
    private static JLabel totalPrice;
    public UC_payment(){
        instance =this;
        this.setLayout(null);

        this.setPreferredSize(new Dimension(1280,720));

        this.setOpaque(true);
        this.setBackground(new Color(180, 160, 255));

        initLabel();
        addTexTFieldAndButton();
        addPics();
        addStars();
        addFoodItem();
    }

    private  void initLabel()
    {
        JLabel payment = new JLabel("Payment");
        payment.setBounds(80,30,200,24);
        payment.setFont(new Font("Segoe UI",Font.BOLD,20));
        instance.add(payment);

        totalPrice = new JLabel("RM " + ShoppingCart.getTotoalPrice());
        totalPrice.setBounds(880,572,280,80);
        totalPrice.setFont(new Font("Segoe UI",Font.BOLD,64));
        totalPrice.setHorizontalAlignment(SwingConstants.RIGHT);

        /*totalPrice.setOpaque(true);
        totalPrice.setBackground(new Color(6, 0, 255));*/

        instance.add(totalPrice);
    }

    private void addTexTFieldAndButton()
    {
        JButton btn = new JButton("Comment");
        btn.setBounds(1050,462,115,41);
        btn.setFont(new Font("Segoe UI",Font.BOLD,16));
        instance.add(btn);

        JTextArea area = new JTextArea();
        area.setBounds(843,230,326,200);
        area.setFont(new Font("Segoe UI",Font.BOLD,16));
        instance.add(area);
    }

    private void addPics(){
        ImageIcon finishImg = new ImageIcon("src/images/finish.png");
        finishImg.setImage(finishImg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        JLabel finishImgLabel = new JLabel() ;
        finishImgLabel.setIcon(finishImg);
        finishImgLabel.setBounds(840,590,50,50);
        instance.add(finishImgLabel);

        finishImgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                finishImgLabel.setCursor(cur);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int totalPrice = ShoppingCart.getTotoalPrice();
                int select = JOptionPane.showConfirmDialog(instance,"This order will cost RM "+ totalPrice+ ",\n are you sure to pay? ","Confirm",JOptionPane.YES_NO_OPTION);
                if(select ==0){
                    ShoppingCart.generateOrderArray();
                    //System.out.println("Yessss");
                }else {
                    System.out.println("Not Pay");
                }
            }
        });
    }


    private  void addFoodItem()
    {
        cardContainer = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(20);
        cardContainer.setLayout(flowLayout);
        cardContainer.setBounds(72,135,630,515);

        cardContainer.setOpaque(true);
        cardContainer.setBackground(new Color(6, 0, 255));

        ArrayList<UC_foodCard> shoppingCart = ShoppingCart.getAllFood();

        for (UC_foodCard fd : shoppingCart) {
            UC_foodItem foodItem = new UC_foodItem(fd.name, fd.price, fd.amount);
            foodItem.setPreferredSize(new Dimension(600, 50));
            cardContainer.add(foodItem);
        }
        instance.add(cardContainer);
    }

    public static void  updateTotalPrice(){
        totalPrice.setText("RM "  + ShoppingCart.getTotoalPrice());
    }


    private void addStars()
    {
        ImageIcon bStar = new ImageIcon("src/images/bStar.png");
        bStar.setImage(bStar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        ImageIcon yStar = new ImageIcon("src/images/yStar.png");
        yStar.setImage(yStar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        ArrayList<JLabel> stars  = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {

            JLabel starPicLabel = new JLabel(String.valueOf(i)) ;
            starPicLabel.setIcon(bStar);
            starPicLabel.setBounds(840 + i*70,140,50,50);
            this.add(starPicLabel);
            stars.add(starPicLabel);
        }

        for (int i = 0; i < 5; i++) {
            final int count = i;
            JLabel starPicLabel = stars.get(i);

            starPicLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    reSetStars(stars);

                    setYellowStars(stars,count);

                    clicked = true;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                    starPicLabel.setCursor(cur);
                    if(!clicked){
                        setYellowStars(stars,count);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(!clicked){
                        reSetStars(stars);
                    }
                }
            });
        }


    }

    private  void setYellowStars(ArrayList<JLabel> stars,int amount)
    {
        ImageIcon yStar = new ImageIcon("src/images/yStar.png");
        yStar.setImage(yStar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        for (int i = 0; i < amount+1; i++) {
            stars.get(i).setIcon(yStar);
        }
    }

    private  void reSetStars(ArrayList<JLabel> stars)
    {
        ImageIcon bStar = new ImageIcon("src/images/bStar.png");
        bStar.setImage(bStar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        for (JLabel ele:stars
             ) {
            ele.setIcon(bStar);
        }
    }
}
