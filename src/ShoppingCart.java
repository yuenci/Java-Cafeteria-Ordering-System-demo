import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCart {
    private static ArrayList<UC_foodCard> content ;
    private static int amount, totalPrice;
    private static boolean paid = false;
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

    public static void addFoodToShoppingCart(UC_foodItem foodCard){
        for (UC_foodCard fc : content) {
            if (Objects.equals(fc.name, foodCard.name)) {
                fc.amount += 1;
            }
        }

        totalPrice += foodCard.unitPrice;
    }

    public static void  deleteFood(UC_foodItem foodCard){
        for (UC_foodCard fc : content) {
            if (Objects.equals(fc.name, foodCard.name)) {
                fc.amount -= 1;
            }
        }
        totalPrice -= foodCard.unitPrice;
    }

    public static ArrayList<UC_foodCard> getAllFood()
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

    public static int getTotoalPrice(){
        return  totalPrice;
    }

    public static void generateOrderArray(){
        if(!paid){
            ArrayList<String> data = new ArrayList<>();
            int total = getTotoalPrice();
            for (UC_foodCard fc : content) {
                String name = fc.name;
                int price = fc.price;
                int amount = fc.amount;

                String[] dataItemList = new String[6];
                dataItemList[0] = Data.getNewOrderStrID();
                dataItemList[1] = String.valueOf(name);
                dataItemList[2] = String.valueOf(price);
                dataItemList[3] = String.valueOf(amount);
                dataItemList[4] = String.valueOf(total);
                dataItemList[5] = Status.tpNum;
                String dataItem = String.join(",", dataItemList);
                data.add(dataItem);
            }

            Data.addArrayToFile(Setting.orderDataPath,data);
            paid = true;
        }

    }
}
