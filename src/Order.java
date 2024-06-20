import java.util.ArrayList;

public class Order {
    private static int orderID;
    private Customer customer;
    private ArrayList<Food> foodList;
    private double total_price;

    public Order(Customer customer, ArrayList<Food> foodList){
        orderID++;
        this.customer = customer;
        this.foodList = foodList;
        this.total_price = 0;
    }

    public void addFood(ArrayList<Food> foodList){
        this.foodList = foodList;
        for(Food food : foodList){
            total_price += food.getfoodPrice();
        }
    }


    public void displayOrders(){
        System.out.println("Order ID       : "+orderID);
        System.out.println("Customer       : "+customer.getName());
        System.out.println("foodList       : "+foodList);
        System.out.println("Total Price    : RM"+total_price);
    }
}
