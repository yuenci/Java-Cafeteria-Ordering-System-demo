import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public static ArrayList<String[]> readFileToArray(String path) {
        ArrayList<String> contentStr = readFile(path);
        ArrayList<String[]> content = new ArrayList<>();
        for (String ele:contentStr
             ) {
            content.add(ele.split(","));
        }
        return content;

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
            return Integer.parseInt(lastOrderID+1);
        }
        catch(Exception e)
        {
            return 0;
        }

    }
    public static String  getNewOrderStrID() {
        ArrayList<String> data = readFile(Setting.orderDataPath);
        int size = data.size();
        try {
            String lastOrder = data.get(size - 1);
            int lastOrderID = Integer.parseInt(lastOrder.split(",")[0]);

            return String.format("%08d", lastOrderID+1);
        } catch (Exception e) {
            return "00000001";
        }
    }

    public static String getDateTime()
    {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return ft.format(dNow);
    }

    public  static  ArrayList<String[]> getItemsByCurrentTPNumAndTimeDESC(String time)
    {
        ArrayList<String> data = readFile(Setting.orderDataPath);
        ArrayList<String[]> res = new ArrayList<>();
        for (String ele:data) {
            String[] eleArgs = ele.split(",");
            if(Objects.equals(eleArgs[5], Status.tpNum) && Objects.equals(eleArgs[6], time)){
                res.add(eleArgs);
            }
        }
        Status.orderIDchose = res.get(0)[0];
        return res;
    }

    public static  ArrayList<String[]> getbriefOrderDataByTpNum(){
        ArrayList<String[]> data =readFileToArray(Setting.orderDataPath);
        ArrayList<String[]> res = new ArrayList<>();

        for (int i = data.size()-1; i >=0 ; i--) {
            if(Objects.equals(data.get(i)[5], Status.tpNum)){
                boolean flag = false;
                for (String[] ele: res
                     ) {

                    if(Objects.equals(data.get(i)[6], ele[0])){
                        flag =true;
                        break;
                    }
                }

                if(!flag){
                    String[] timeAndPrice = {data.get(i)[6],data.get(i)[4]};
                    res.add(timeAndPrice);
                }
            }
        }

        return res;
    }

    public  static ArrayList<String[]> dateBetween(String path,String starDate,String endDate){
        ArrayList<String[]> data = readFileToArray(path);
        ArrayList<String[]> res = readFileToArray(path);
        int starDateInt  = dateToint(starDate);
        int endDateint= dateToint(endDate);

        int lastIndex = data.get(0).length -1;

        for (String[] ele:data
             ) {
            int itemDate = dateToint(ele[lastIndex]);
            if( itemDate>= starDateInt && itemDate <= endDateint){
                res.add(ele);
            }
        }
        return res;
    }

    private  static int dateToint(String date){
        if(date.length() == 7){
            String year = date.substring(0,4);
            String month = date.substring(5,7);
            return Integer.parseInt(year+month);
        }
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,9);

        return Integer.parseInt(year+month+day);
    }

    public   static ArrayList<String> getDateRangeYyyyMm(String path){
        ArrayList<String[]> data = readFileToArray(path);
        ArrayList<String> res = new ArrayList<>();
        int lastIndex = data.get(0).length -1;
        //System.out.println(data.get(0)[lastIndex].substring(0,7));

        try{
            for (String[] ele:data
            ) {
                String yyyyMM = ele[lastIndex].substring(0,7);
                if(!res.contains(yyyyMM)){
                    res.add(yyyyMM);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //
        for (int i = 1; i < res.size(); i++) {
            for (int j = 0; j < res.size()-1; j++) {
                if(dateToint(res.get(j)) <dateToint(res.get(j+1))){
                    String temp  = res.get(j);
                    res.set(j,res.get(j+1));
                    res.set(j+1,temp);
                }
            }
        }
        return  res;
    }

    public   static ArrayList<String> getDateRangeYyyy(String path){
        ArrayList<String[]> data = readFileToArray(path);
        ArrayList<String> res = new ArrayList<>();
        int lastIndex = data.get(0).length -1;
        //System.out.println(data.get(0)[lastIndex].substring(0,7));

        try{
            for (String[] ele:data
            ) {
                String yyyy = ele[lastIndex].substring(0,4);
                if(!res.contains(yyyy)){
                    res.add(yyyy);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //
        for (int i = 1; i < res.size(); i++) {
            for (int j = 0; j < res.size()-1; j++) {
                if(Integer.parseInt(res.get(j)) <Integer.parseInt(res.get(j+1))){
                    String temp  = res.get(j);
                    res.set(j,res.get(j+1));
                    res.set(j+1,temp);
                }
            }
        }
        return  res;
    }

    public  static ArrayList<String[]> getAnalysis(String dataType,String year){
        if(Objects.equals(dataType, "Income"))
        {
            return  getIncomeAnalysis(Setting.orderDataPath,year);
        }
        else  if(Objects.equals(dataType, "Amount"))
        {
            return  getAmountAnalysis(Setting.orderDataPath,year);
        }
        else  if(Objects.equals(dataType, "Avg Star"))
        {
            return  getStarAnalysis(Setting.feedbackDataPath,year);
        }
        return null;
    }

    private  static ArrayList<String[]> getIncomeAnalysis(String path,String year){
        ArrayList<String[]> data = readFileToArray(path);
        ArrayList<String[]> res = new ArrayList<>();
        int[] incomeTemp = {0,0,0,0,0,0,0,0,0,0,0,0};
        String currentOrderID ="";

        System.out.println("Incomeeeee");
        System.out.println(year);
       try {
            for (String[] ele:data) {
                
                if(ele[6].substring(0, 4).equals(year) && !Objects.equals(ele[0], currentOrderID)){
                    int index = Integer.parseInt(ele[6].substring(5,7)) - 1;
                    incomeTemp[index] += Integer.parseInt(ele[4]);
                    currentOrderID = ele[0];
                }
            }
        }
        catch (Exception e){
            //System.out.println(e.getMessage());
        }


        for (int j = 0; j < 12; j++) {
            String[] temp = new String[2];
            temp[0] = year +"-" + String.format("%02d", j+1);
            temp[1] = "RM " + incomeTemp[j];
            res.add(temp);
        }

        /*for (String[] ele:res
        ) {
            System.out.println(ele[0] + "+++" + ele[1]);
        }*/

        return res;
    }
    private  static ArrayList<String[]> getAmountAnalysis(String path,String year){
        ArrayList<String[]> data = readFileToArray(path);
        ArrayList<String[]> res = new ArrayList<>();
        int[] amountTemp = {0,0,0,0,0,0,0,0,0,0,0,0};

        System.out.println("amounrttttttt");

        try{
        for (String[] ele:data) {
            if(ele[6].substring(0, 4).equals(year)){
                int index = Integer.parseInt(ele[6].substring(5,7)) - 1;
                amountTemp[index] += Integer.parseInt(ele[3]);
            }
        }}
        catch (Exception e){
            //System.out.println(e.getMessage());
        }

        for (int j = 0; j < 12; j++) {
            String[] temp = new String[2];
            temp[0] = year +"-"+ String.format("%02d", j+1);
            temp[1] = String.valueOf(amountTemp[j]);
            res.add(temp);
        }

        /*for (String[] ele:res
        ) {
            System.out.println(ele[0] + "+++" + ele[1]);
        }*/


        return res;
    }

    private  static ArrayList<String[]> getStarAnalysis(String path,String year){
        ArrayList<String[]> data = readFileToArray(path);
        ArrayList<String[]> res = new ArrayList<>();
        int[] startTemp = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] peopleTemp  = {0,0,0,0,0,0,0,0,0,0,0,0};

        try{
        for (String[] ele:data) {
            if(ele[3].substring(0, 4).equals(year)){
                int index = Integer.parseInt(ele[3].substring(5,7)) - 1;
                startTemp[index] += Integer.parseInt(ele[1]);
                peopleTemp[index] += 1;
            }
        }}
        catch (Exception e){
            //System.out.println(e.getMessage());
        }

        for (int j = 0; j < 12; j++) {
            String[] temp = new String[2];
            temp[0] = year +"-" + String.format("%02d", j+1);
            if(peopleTemp[j] !=0){
                temp[1] = String.valueOf(startTemp[j] / peopleTemp[j]);
            }else {
                temp[1] = "0";
            }

            res.add(temp);
        }

        /*for (String[] ele:res
        ) {
            System.out.println(ele[0] + "+++" + ele[1]);
        }*/
        return res;
    }

    public  static  String getNewTPNum(){
        ArrayList<String[]>  adminData = readFileToArray(Setting.adminDataPath);
        ArrayList<String[]>  cusData = readFileToArray(Setting.customerDataPath);

        int adminMax = 0 ;
        int cusMax = 0;

        for (String[] ele: adminData
             ) {
            int num = Integer.parseInt(ele[0].substring(2,8));
            if(num>adminMax){
                adminMax = num;
            }
        }
        for (String[] ele: cusData
        ) {
            int num = Integer.parseInt(ele[0].substring(2,8));
            if(num>cusMax){
                cusMax = num;
            }
        }

        if(adminMax > cusMax){
            return String.format( "tp%06d", adminMax+1);
        }
        else{
            return String.format( "tp%06d", cusMax+1);
        }
    }
}
