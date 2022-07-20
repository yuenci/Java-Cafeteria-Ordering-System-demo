import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class UC_analysis extends JPanel {
    private  UC_analysis instance;
    private JComboBox<String> dataTypeComb = new JComboBox<>();
    private JComboBox<String> dataRangeComb = new JComboBox<>();
    private JPanel cardContainer;
    public  UC_analysis(){
        instance =this;
        this.setLayout(null);

        this.setPreferredSize(new Dimension(1280,720));

        this.setOpaque(true);
        this.setBackground(new Color(165, 216, 255));


        addLabel();
        addComboxes();
        addPanel();

        for (int i = 0; i < 12; i++) {
            addcards("2022-07","999999");
        }

        updateCards();
    }

    private void addLabel(){
        JLabel payment = new JLabel("Analysed reports");
        payment.setBounds(80,30,200,24);
        payment.setFont(new Font("Segoe UI",Font.BOLD,20));
        instance.add(payment);
    }

    private void addComboxes(){
        dataTypeComb.setBounds(900,60,135,40);
        dataRangeComb.setBounds(1070,60,135,40);

        dataTypeComb.addItem("Income");
        dataTypeComb.addItem("Amount");
        dataTypeComb.addItem("Avg Star");
        instance.add(dataTypeComb);

        ArrayList<String> data = Data.getDateRangeYyyy(Setting.orderDataPath);
        for (String ele: data
             ) {
            dataRangeComb.addItem(ele);
        }
        instance.add(dataRangeComb);

        dataTypeComb.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                //System.out.println("hi");
                updateCards();
            }
        });

        dataRangeComb.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                //System.out.println("hi");
                updateCards();
            }
        });
    }

    private void addPanel(){

        cardContainer = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(30);
        flowLayout.setHgap(60);

        cardContainer.setLayout(flowLayout);
        cardContainer.setBounds(70,130,1135,490);
        cardContainer.setBackground(new Color(92, 124, 250));

        instance.add(cardContainer);
    }

    private void addcards(String date, String number){

            JPanel card = new JPanel();
            card.setPreferredSize(new Dimension(460,45));
            card.setLayout(null);

            JLabel monthLabel = new JLabel(date);
            JLabel numberLabel = new JLabel(number);

            monthLabel.setFont(new Font("Segoe UI",Font.BOLD,20));
            numberLabel.setFont(new Font("Segoe UI",Font.BOLD,20));

            monthLabel.setBounds(20,10,150,30);
            numberLabel.setBounds(220,10,150,30);

            card.add(monthLabel);
            card.add(numberLabel);

            cardContainer.add(card);


    }

    private void updateCards(){
        String dataType= (String) dataTypeComb.getSelectedItem();
        String year = (String) dataRangeComb.getSelectedItem();
        ArrayList<String[]> data = Data.getAnalysis(dataType,year);


       /* for (String[] ele:data11
             ) {
            System.out.println(ele[0] + "+++" + ele[1]);
        }*/
        

        cardContainer.removeAll();
        for (int i = 0; i < 12; i++) {
            assert data != null;
            addcards(data.get(i)[0],data.get(i)[1]);
        }
        cardContainer.repaint();
        cardContainer.validate();
    }
}
