abstract class Customer extends User{
    
    private int custID;
    private Review review;

    public Customer(String name, String phone, String email){
        super(name, phone, email);
        custID++;
    }

    public void addReview(Review review){
        this.review = review;
    }

    public Review getReview(){
        return review;
    }

    public int getCustomerID(){
        return custID;
    }

    abstract double getDiscount();
}
