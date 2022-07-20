import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UC_foodItem extends JPanel {
    private static  UC_foodItem instance;
    public  UC_foodItem()
    {
        this.setLayout(null);

        this.setOpaque(true);
        this.setBackground(new Color(217, 217, 217));




    }

    private  void addLabels(){
        JLabel unitPrice = new JLabel();
        AddLabel(unitPrice,25,20,80,24,"left");

        JLabel multiple = new JLabel();
        AddLabel(multiple,25,140,80,24,"left");

        JLabel amount = new JLabel();
        AddLabel(amount,25,220,80,24,"left");

        JLabel totalPrice = new JLabel();
        AddLabel(totalPrice,25,360,80,24,"left");
    }

    private  void addPics(){

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
