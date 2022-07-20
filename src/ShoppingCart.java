import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCart {
    private static ArrayList<UC_foodCard> content ;
    private  int amount, totalPrice;
    private  boolean paid = false;
    public ShoppingCart(){
        content = new ArrayList<>();
    }

    public void addFoodToShoppingCart(UC_foodCard foodCard){
        if(ifExist(foodCard)){
            foodCard.amount += 1;
        }else{
            foodCard.amount = 1;
            content.add(foodCard);
        }


        amount +=1;
        totalPrice += foodCard.price;
        UC_shopping.instance.totalAmount.setText("Total: " + amount);
        UC_shopping.instance.totalPrice.setText("RM " + totalPrice);
    }

    public void addFoodToShoppingCart(UC_foodItem foodCard){
        for (UC_foodCard fc : content) {
            //System.out.println(fc.name +"---" + foodCard.name);
            if (Objects.equals(fc.name, foodCard.name)) {
                //System.out.println("eixst");
                System.out.println(fc.amount);
                fc.amount += 1;
            }else{
                System.out.println("can't find");
            }
        }
        totalPrice += foodCard.unitPrice;
    }

    public  void  deleteFood(UC_foodItem foodCard){
        for (UC_foodCard fc : content) {
            if (Objects.equals(fc.name, foodCard.name)) {
                fc.amount -= 1;
            }
        }
        totalPrice -= foodCard.unitPrice;
    }

    public  ArrayList<UC_foodCard> getAllFood()
    {
        return content;
    }

    private  boolean ifExist(UC_foodCard foodCard){
        for (UC_foodCard fc : content) {
            if (Objects.equals(fc.name, foodCard.name)) {
                return true;
            }
        }
        return  false;
    }

    public int getTotoalPrice(){
        return  totalPrice;
    }

    public  void generateOrderArray(){
        if(!paid){
            ArrayList<String> data = new ArrayList<>();
            int total = getTotoalPrice();
            for (UC_foodCard fc : content) {
                String name = fc.name;
                int price = fc.price;
                int amount = fc.amount;

                String[] dataItemList = new String[7];
                dataItemList[0] = Data.getNewOrderStrID();
                dataItemList[1] = String.valueOf(name);
                dataItemList[2] = String.valueOf(price);
                dataItemList[3] = String.valueOf(amount);
                dataItemList[4] = String.valueOf(total);
                dataItemList[5] = Status.tpNum;
                dataItemList[6] = Data.getDateTime();
                String dataItem = String.join(",", dataItemList);
                data.add(dataItem);
            }

            Data.addArrayToFile(Setting.orderDataPath,data);
            paid = true;
        }

    }

    public  void addDataToShoppingCart (UC_foodCard foodCard){

        totalPrice += foodCard.price * foodCard.amount;
        content.add(foodCard);
        //System.out.println("createCard done");
    }

    public  void clearShoppingCart(){
        content.clear();
    }

}
