import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class UC_payment extends JPanel {
    public  static  UC_payment instance;
    boolean clicked = false;
    boolean existed =false;
    public static JPanel cardContainer;
    private static JLabel totalPrice;
    private JButton commentBtn;
    private JTextArea area;
    private  int star =0;
    private ArrayList<JLabel> stars;
    public UC_payment(){
        instance =this;
        this.setLayout(null);

        this.setPreferredSize(new Dimension(1280,720));

        this.setOpaque(true);
        this.setBackground(new Color(180, 160, 255));

        initLabel();

        addFoodItem();

        if(Objects.equals(Status.currentPage, "history")){
            //System.out.println("Payment think this is history");
            addStars();
            addTexTFieldAndButton();
            prepareCommentData();
        }
        else if(Objects.equals(Status.currentPage, "payment"))
        {
            //System.out.println("Payment think this is payment");
            addPics();

        }else{
            System.out.println("Payment think this is ???");
        }
    }

    private  void initLabel()
    {
        JLabel payment = new JLabel("Payment");
        payment.setBounds(80,30,200,24);
        payment.setFont(new Font("Segoe UI",Font.BOLD,20));
        instance.add(payment);

        totalPrice = new JLabel("RM " + Status.currentShoppingCart.getTotoalPrice());
        totalPrice.setBounds(880,572,280,80);
        totalPrice.setFont(new Font("Segoe UI",Font.BOLD,64));
        totalPrice.setHorizontalAlignment(SwingConstants.RIGHT);

        /*totalPrice.setOpaque(true);
        totalPrice.setBackground(new Color(6, 0, 255));*/

        instance.add(totalPrice);
    }

    private void addTexTFieldAndButton()
    {
        commentBtn = new JButton("Comment");
        commentBtn.setBounds(1050,462,115,41);
        commentBtn.setFont(new Font("Segoe UI",Font.BOLD,16));
        instance.add(commentBtn);

        area = new JTextArea();
        area.setBounds(843,230,326,200);
        area.setFont(new Font("Segoe UI",Font.BOLD,16));
        instance.add(area);

        commentBtn.addActionListener(e -> {
            if(area.getText() !=null && star!=0){
                String[] feedback = new String[4];
                feedback[0] = Status.orderIDchose;
                feedback[1] = String.valueOf(star);
                feedback[2] = area.getText();
                feedback[3] = Data.getDateTime();

                String feedbackData = String.join(",",feedback);
                Data.addStringToFile(Setting.feedbackDataPath, feedbackData);

                commentBtn.setEnabled(false);
                JOptionPane.showMessageDialog(this,"Thank you for comment.");
            }else{
                JOptionPane.showMessageDialog(this,"Please star and comment.");
            }


        });
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
                int totalPrice = Status.currentShoppingCart.getTotoalPrice();
                int select = JOptionPane.showConfirmDialog(instance,"This order will cost RM "+ totalPrice+ ",\n are you sure to pay? ","Confirm",JOptionPane.YES_NO_OPTION);
                if(select ==0){
                    Status.currentShoppingCart.generateOrderArray();
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

        ArrayList<UC_foodCard> shoppingCart =Status.currentShoppingCart.getAllFood();
        
        for (UC_foodCard fd : shoppingCart) {
            System.out.println(fd.name + fd.price + fd.amount + "paymnetJava161L");
            UC_foodItem foodItem = new UC_foodItem(fd.name, fd.price, fd.amount);
            foodItem.setPreferredSize(new Dimension(600, 50));
            cardContainer.add(foodItem);
        }
        instance.add(cardContainer);
    }

    public static void  updateTotalPrice(){
        totalPrice.setText("RM "  + Status.currentShoppingCart.getTotoalPrice());
    }


    private void addStars()
    {
        ImageIcon bStar = new ImageIcon("src/images/bStar.png");
        bStar.setImage(bStar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        ImageIcon yStar = new ImageIcon("src/images/yStar.png");
        yStar.setImage(yStar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        stars  = new ArrayList<>();
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
                    star = count +1;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                    starPicLabel.setCursor(cur);
                    if(!clicked && !existed){
                        setYellowStars(stars,count);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(!clicked&& !existed){
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

    private void prepareCommentData(){
        ArrayList<String[]> data = Data.readFileToArray(Setting.feedbackDataPath);
        String[] comment = new String[4];
        boolean exist = false;
        for (String[] ele: data
             ) {
            if(Objects.equals(ele[0], Status.orderIDchose)){
                comment = ele;
                exist =true;
                existed = true;
                break;
            }
        }

        if(exist){
            System.out.println();
            commentBtn.setEnabled(false);
            area.setText(comment[2]);
            setYellowStars(stars,Integer.parseInt(comment[1])-1);
        }
    }
}
