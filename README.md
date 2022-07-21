# Aim

The purpose of this project is to practice the knowledge of Java and Java Swing, so only the basic functions have been completed, and the user experience and UI design have not been considered and implemented.



# JAVA Part

## File operation



### How to read text file?

```Java
public static boolean rewriteFile(String path, String content) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println(file.createNewFile());
            }
            OutputStream fOut = new FileOutputStream(file);
            fOut.write(content.getBytes());
            fOut.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
```



### How to write content to text file?

```Java
public static boolean rewriteFile(String path, String content) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println(file.createNewFile());
            }
            OutputStream fOut = new FileOutputStream(file);
            fOut.write(content.getBytes());
            fOut.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
```



### How to append a content text file?

```Java
public static boolean addStringToFile(String path, String content) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println(file.createNewFile());
            }
            OutputStream fOut = new FileOutputStream(file, true);
            String str = content + "\n";
            fOut.write(str.getBytes());
            fOut.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
```



### How to read from text file?

```Java
public static ArrayList<String> readFile(String path) {
        ArrayList<String> content = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                content.add(data);
            }

            myReader.close();
            return content;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return content;
        }
    }
```



### How to read  from text file as array?

```Java
public static ArrayList<String> readFile(String path) {
        ArrayList<String> content = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                content.add(data);
            }

            myReader.close();
            return content;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return content;
        }
    }
```





### How to read  from text file two-dimensional array?

```Java
public static ArrayList<String[]> readFileToArray(String path) {
        ArrayList<String> contentStr = readFile(path);
        ArrayList<String[]> content = new ArrayList<>();
        for (String ele:contentStr
             ) {
            content.add(ele.split(","));
        }
        return content;

    }
```



### String

### Split

```Java
String content = "Java,is,great"
String[] args = content.split(",");
```

### Join

```Java
String[] content =  { "Hello", "World", "2022" }

String wholeStr = String.join(",", content);
```

### equals

```Java
Objects.equals("a", "b")
```

### fill

```Java
String.format("%08d", 1)
//00000001
```





## ArrayList

### add(element)

```Java
ArrayList<String> res = new ArrayList<>();
res.add("java");
```



### get(index)

```Java
ArrayList<String> res = new ArrayList<>();
res.add("java");
System.out.println(res.get(0));
```



## size()

```Java
ArrayList<String> res = new ArrayList<>();
res.add("java");
System.out.println(res.size());
```



## set(index,element)

```Java
ArrayList<String> res = new ArrayList<>();
res.add("java");
System.out.println(res.set(0,"C sharp"));
```



### For Loop

```Java
ArrayList<String[]> res = new ArrayList<>();
for (String[] ele: res) {
		System.out.println(ele);
    }
}
```



## Error

```Java
try {
           
}
catch (Exception e){
    System.out.println(e.getMessage());
}
```



# Swing Part

## Window

### Create  A program to manage frame

```Java
import javax.swing.*;

public class Programs {
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame("Window title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setSize(1280, 720);
        frame.setVisible(true);
    }

}

```



### Frame

```Java
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(String title){
        super(title);
        instance= this;
    	root = new JPanel();
        root.setLayout(null);

        this.setContentPane(root);

        JPanel uc_login = new UC_login();
        uc_login.setBounds(425,85,430,550);
       
}
```



### Panel

```Java
import javax.swing.*;

public class UC_login extends JPanel {
    public UC_login(){
        instance = this;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(430,550));
        this.setOpaque(true);
        this.setBackground(new Color(79, 174, 238));
    
}
```



## Layout

### null

```Java
this.setLayout(null);


passwordlabel.setBounds(54,211,176,24);
//Each component needs to be manually laid out using setBounds().
```



### FlowLayout

```Java
//default
cardContainer.setLayout(new FlowLayout());

//custom
FlowLayout flowLayout = new FlowLayout();
flowLayout.setVgap(20);
flowLayout.setHgap(20);
cardContainer.setLayout(flowLayout);

```



### Controls



### JLabel

```java
import javax.swing.*;

public class MainFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        frame.setContentPane(panel);

        JLabel label = new JLabel("This is a lable");
        panel.add(label);
        //add label to panel

        panel.add(new JLabel("this is a short format"));
        //short form

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
```

```Java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    public MyFrame(String title){
        super(title);
        JPanel root = new JPanel();
        this.setContentPane(root);

        JLabel label = new JLabel("text");
        root.add(label);

        label.setText("Hello world");
        //set content

        label.setFont(new Font("Segoe UI",Font.BOLD,20));
        //set font

        label.setForeground(new Color(212,94,155));
        //set colot

        label.setOpaque(true);
        label.setBackground(new Color(6, 0, 255));
        //set bgcColor

        label.setPreferredSize(new Dimension(200,50));
        //set size

        label.setHorizontalAlignment(SwingConstants.CENTER);
        //set align


    }
}

```



### Jbutton

```Java
import javax.swing.*;

public class MainFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        frame.setContentPane(panel);


        JButton btn = new JButton("Button");
        panel.add(btn);


        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
```



### JTextFileld

```Java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFrame extends JFrame {
    public MyFrame(String title){
        super(title);

        JPanel root = new JPanel();
        this.setContentPane(root);

        JTextField textField = new JTextField(20);
        root.add(textField);

        textField.setText("Hello");


        JButton btn = new JButton("Click me");
        root.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = textField.getText();
                // get content
                System.out.println(str);
            }
        });
    }
}

```



### JCheckBox

```Java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    public MyFrame(String title){
        super(title);
        JPanel root = new JPanel();
        this.setContentPane(root);

        JCheckBox agree = new JCheckBox("Accept agreement");
        root.add(agree);

        agree.setSelected(false);
        // status

        JButton btn = new JButton("Click me");
        root.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean sel = agree.isSelected();
                //read status
                agree.setSelected(!sel);
            }
        });
    }
}

```



### JcomboBox

```Java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    JComboBox<String> color  = new JComboBox<>();
    public MyFrame(String title){
        super(title);
        JPanel root = new JPanel();
        this.setContentPane(root);

		//add items
        root.add(color);
        color.addItem("red");
        color.addItem("yellow");
        color.addItem("blue");

        JButton btn1 = new JButton("Click");
        root.add(btn1);


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setselectedItemStr1();
            }
        });


    }

    //APIS
    private  void itemNumber1()
    {
        System.out.println(color.getItemCount());
    }

    private  void getItemByIndex1()
    {
        System.out.println(color.getItemAt(2));
    }


    private  void getSelectedIndex1()
    {
        System.out.println(color.getSelectedIndex());
    }

    private  void setSelectedIndex1()
    {
        color.setSelectedIndex(2);
    }

    private  void removeItemByIndex1()
    {
        color.removeItem(1);
    }

 
    private  void getselectedItemStr1()
    {
        System.out.println(color.getSelectedItem());
    }

    private  void setselectedItemStr1()
    {
        color.setSelectedItem("yellow");
    }

    private  void removeItemByStr()
    {
        color.removeItem(1);
    }

}

```





## Event

### General form

```Java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    public MyFrame(String title){
        super(title);

        JPanel root = new JPanel();
        this.setContentPane(root);

        JButton btn = new JButton("Button");
        root.add(btn);

        MyActionListener listener = new MyActionListener();
        btn.addActionListener(listener);
        //add a Event instance as listenter

    }

    //Event class
    private  class  MyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked");
        }
    }
}
```



### Short form

```Java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    public MyFrame(String title){
        super(title);

        JPanel root = new JPanel();
        this.setContentPane(root);

        JButton btn = new JButton("Button");
        root.add(btn);

        MyActionListener listener = new MyActionListener();
        btn.addActionListener(listener);

        
        //way 1
        ActionListener listener = new MyActionListener();
        btn.addActionListener(listener);
        
        //way2
        btn.addActionListener(new MyActionListener());

    }


    private  class  MyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked");
        }
    }
}
```



### Lambda form

```Java
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFrame extends JFrame {
    public MyFrame(String title){
        super(title);

        JPanel root = new JPanel();
        this.setContentPane(root);

        JButton btn = new JButton("Button");
        root.add(btn);

        btn.addActionListener((e -> {
            showTime();
        }));

    }

    public void showTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeStr = sdf.format(new Date());
        System.out.println(timeStr);
    }
}

```



### Mouse Event

```Java
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;

public class MyFrame extends JFrame {

    public MyFrame(String title) {
        super(title);
        JPanel root = new JPanel();
        this.setContentPane(root);
        this.setLayout(null);

        JLabel label = new JLabel("HIIIIIIIIIIIIIII");
        label.setBounds(0,0,200,200);
        label.setOpaque(true);
        label.setBackground(Color.GREEN);

        root.add(label);

        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("Clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getPoint());
                //Where is the click
                
                System.out.println(e.getLocationOnScreen());
                //Where is the click in the screen
                
                System.out.println(e.getSource());
                //where is the click from
                
                System.out.println(e.getButton());
                //which btn
                
                System.out.println(e.getClickCount());
                //How was is clicked

                if(e.getButton() ==MouseEvent.BUTTON1){
                    System.out.println("left");
                }
                else if(e.getButton() ==MouseEvent.BUTTON2){
                    System.out.println("center");
                }else if(e.getButton() == MouseEvent.BUTTON3){
                    System.out.println("right");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



    }
}

```



### Mouse Event short form

```Java
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

public class MyFrame extends JFrame {

    public MyFrame(String title) {
        super(title);
        JPanel root = new JPanel();
        this.setContentPane(root);
        this.setLayout(null);

        JLabel label = new JLabel("HIIIIIIIIIIIIIII");
        label.setBounds(0,0,200,200);
        label.setOpaque(true);
        label.setBackground(Color.GREEN);

        root.add(label);

		//adapter
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("yes");
            }
        });

    }
}
```
# UI Part
## Design 
### Login / Register
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Login.png)

### Shopping
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Shopping.png)

### Payment
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Payment.png)

### Anasysis
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/anasysis.png)

## Implement
### Login
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/20220721_221116.png)

### Regiter
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Snipaste_2022-07-21_22-12-02.png)

### Welcome
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Snipaste_2022-07-21_22-13-19.png)


### Payment
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Snipaste_2022-07-21_22-13-28.png)


### View history
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Snipaste_2022-07-21_22-52-42.png)


### Comment
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Snipaste_2022-07-21_23-02-54.png)


### Analysis
![](https://github.com/yuenci/javaACOSDemo/blob/master/src/images/Snipaste_2022-07-21_23-16-00.png)


> To be continued





