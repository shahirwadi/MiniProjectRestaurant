public class Customer extends User{
    
    private int custID;
    private Review review;

    public Customer(String name, String phone, String email, int custID){
        super(name, phone, email);
        this.custID = custID;
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

}
