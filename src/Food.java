public class Food {
    private static int foodID;
    private String foodName;
    private double foodPrice;

    public Food (String foodName, double foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        foodID++;
    }

    public int getfoodID(){
        return foodID;
    }

    public String getfoodName(){
        return foodName;
    }

    public double getfoodPrice(){
        return foodPrice;
    }
}

