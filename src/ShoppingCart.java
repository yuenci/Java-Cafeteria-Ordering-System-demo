import java.util.ArrayList;

public class ShoppingCart {
    private  ArrayList<UC_foodCard> content ;
    int amount, totalPrice;

    public ShoppingCart(){
        content = new ArrayList<>();
    }

    public void addFoodToShoppingCart(UC_foodCard foodCard){
        content.add(foodCard);

        amount +=1;
        totalPrice += foodCard.price;
        UC_shopping.instance.totalAmount.setText("Total: " + amount);
        UC_shopping.instance.totalPrice.setText("RM " + totalPrice);
    }

    public ArrayList<UC_foodCard> getAllFood()
    {
        return content;
    }
}
