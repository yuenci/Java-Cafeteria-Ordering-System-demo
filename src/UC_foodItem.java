import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class UC_foodItem extends JPanel {
    private static  UC_foodItem instance;
    public int unitPrice;
    public int amount;
    public String name;
    private JLabel totalPriceLabel;
    private JLabel amountLabel;
    public  UC_foodItem(String name,int unitPrice,int amount)
    {
        instance =this;
        this.unitPrice= unitPrice;
        this.amount = amount;

        this.setLayout(null);

        this.setOpaque(true);
        this.setBackground(new Color(217, 217, 217));

        addLabels(unitPrice,amount);
        addPics();

    }

    private void addLabels(int unitPrice, int amount){
        int y = 12;
        JLabel unitPriceLabel = new JLabel("RM " +unitPrice);
        AddLabel(unitPriceLabel,20,y,100,24,"left");

        JLabel multiple = new JLabel("×");
        AddLabel(multiple,140,y,24,24,"left");

        amountLabel = new JLabel(String.valueOf(amount));
        AddLabel(amountLabel,220,y,80,24,"left");

        totalPriceLabel = new JLabel("RM " + unitPrice * amount);
        AddLabel(totalPriceLabel,350,y,150,24,"left");

    }

    private  void addPics(){
        ImageIcon addPic = new ImageIcon("src/images/add.png");
        addPic.setImage(addPic.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        JLabel addPicLabel = new JLabel() ;
        addPicLabel.setIcon(addPic);
        addPicLabel.setBounds(510,12,30,30);
        instance.add(addPicLabel);

        ImageIcon deletePic = new ImageIcon("src/images/delete.png");
        deletePic.setImage(deletePic.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        JLabel deletePicLabel = new JLabel() ;
        deletePicLabel.setIcon(deletePic);
        deletePicLabel.setBounds(560,12,30,30);
        instance.add(deletePicLabel);

        addPicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                addPicLabel.setCursor(cur);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ShoppingCart.addFoodToShoppingCart(instance);
                amount +=1;
                totalPriceLabel.setText("RM: " + unitPrice*amount);
                amountLabel.setText(String.valueOf(amount));

                UC_payment.updateTotalPrice();
            }
        });

        deletePicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur=new Cursor(Cursor.HAND_CURSOR);
                deletePicLabel.setCursor(cur);

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ShoppingCart.deleteFood(instance);
                amount -=1;
                totalPriceLabel.setText("RM: " + unitPrice*amount);
                amountLabel.setText(String.valueOf(amount));

                if(amount ==0){
                    deleteLabel();
                }

                UC_payment.updateTotalPrice();
            }
        });
    }
    private void deleteLabel(){
        UC_payment.cardContainer.remove(instance);
        UC_payment.cardContainer.repaint();
        UC_payment.cardContainer.validate();
    }

    private void AddLabel(JLabel label,int x,int y,int w,int h,String align){
        label.setBounds(x,y,w,h);
        if(Objects.equals(align, "right")){
            label.setHorizontalAlignment(SwingConstants.RIGHT);
        }else if(Objects.equals(align, "left")){
            label.setHorizontalAlignment(SwingConstants.LEFT);
        }

        label.setFont(new Font("Segoe UI",Font.BOLD,20));
        instance.add(label);
    }
}
