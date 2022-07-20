import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UC_payment extends JPanel {
    public  static  UC_payment instance;
    boolean clicked = false;
    public UC_payment(){
        instance =this;
        this.setLayout(null);

        this.setPreferredSize(new Dimension(1280,720));

        this.setOpaque(true);
        this.setBackground(new Color(180, 160, 255));

        initLabel();
        addStars();
    }

    private  void initLabel()
    {
        JLabel payment = new JLabel("Payment");
        payment.setBounds(80,30,200,24);
        payment.setFont(new Font("Segoe UI",Font.BOLD,20));
        instance.add(payment);


    }

    private  void addFoodItem()
    {
        JPanel cardContainer = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(10);
        this.setLayout(flowLayout);
        this.setBounds(72,135,630,515);


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
