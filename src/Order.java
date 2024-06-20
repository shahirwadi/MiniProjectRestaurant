import java.util.ArrayList;

public class Order {
    private String orderID;
    private Customer customer;
    private ArrayList<Food> foodList;
    private double total_price;

    public Order(String orderID, Customer customer, ArrayList<Food> foodList, double total_price){
        this.orderID = orderID;
        this.customer = customer;
        this.foodList = foodList;
        this.total_price = total_price;
    }

    public void addFood(int foodID){
        
    }

}
