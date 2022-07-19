import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UC_foodCard extends JPanel {
    public int price;
    public String name;
    private JLabel addPicLabel;
    private UC_foodCard instance;

    public UC_foodCard(String foodName,int price,String imgName){
        instance =this;
        this.price = price;
        this.name = foodName;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(250,250));

        ImageIcon img = new ImageIcon("src/images/food1.png");
        img.setImage(img.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        JLabel foodPicLabel = new JLabel() ;
        foodPicLabel.setIcon(img);
        foodPicLabel.setBounds(50,10,150,150);
        this.add(foodPicLabel);

        JLabel foodNameLabel = new JLabel(foodName) ;
        foodNameLabel.setBounds(20,175,120,24);
        foodNameLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));
        this.add(foodNameLabel);

        JLabel priceLabel = new JLabel(String.valueOf(price)) ;
        priceLabel.setBounds(20,210,120,24);
        priceLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));
        this.add(priceLabel);

        ImageIcon img2 = new ImageIcon("src/images/add.png");
        img2.setImage(img2.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));

        addPicLabel = new JLabel() ;
        addPicLabel.setIcon(img2);
        addPicLabel.setBounds(175,175,64,64);
        this.add(addPicLabel);

        addPicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                addPicLabel.setCursor(cur);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                UC_shopping.instance.shoppingCart.addFoodToShoppingCart(instance);
            }
        });
    }
}
