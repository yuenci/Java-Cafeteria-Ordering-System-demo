import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Data {
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

    public static boolean addArrayToFile(String path, ArrayList<String> content) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println(file.createNewFile());
            }
            OutputStream fOut = new FileOutputStream(file, true);

            for (String ele:content
                 ) {
                String str = ele + "\n";
                fOut.write(str.getBytes());
            }

            fOut.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

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

    public static boolean authentication(String tpNum,String pws)
    {
        tpNum = tpNum.toLowerCase();
        pws =pws.toLowerCase();
        
        ArrayList<String> admimData = readFile(Setting.adminDataPath);
        for (String admimEle : admimData) {
            
            String[] args = admimEle.split(",");

            if (Objects.equals(args[0], tpNum) && Objects.equals(args[2], pws )) {
                Status.type = "admin";
                Status.tpNum = tpNum;
                Status.userName = args[1];
                return true;
            }
        }


        ArrayList<String> cumstomerData = readFile(Setting.customerDataPath);
        for (String cumstomerEle : cumstomerData) {
            String[] args = cumstomerEle.split(",");
            if (Objects.equals(args[0], tpNum) && Objects.equals(args[2], pws)) {
                Status.type = "customer";
                Status.tpNum = tpNum;
                Status.userName = args[1];
                return true;
            }
        }

        return false;
    }

    public static int getNewOrderIntID(){
        ArrayList<String> data = readFile(Setting.orderDataPath);
        int size = data.size();
        try {
            String lastOrder = data.get(size-1);
            String lastOrderID =  lastOrder.split(",")[0];
            return Integer.parseInt(lastOrderID);
        }
        catch(Exception e)
        {
            return 0;
        }

    }
    public static String  getNewOrderStrID(){
        ArrayList<String> data = readFile(Setting.orderDataPath);
        int size = data.size();
        try {
            String lastOrder = data.get(size-1);
            int lastOrderID = Integer.parseInt(lastOrder.split(",")[0]);

            return  String.format("%08d", lastOrderID);
        }
        catch(Exception e)
        {
            return "00000001";
        }



    }
}
