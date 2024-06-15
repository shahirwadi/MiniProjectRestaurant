class Customer extends User{
    
    private int custID;
    private Review review;

    public Customer(String name, String phone, String email, int custID, Review review){
        super(name, phone, email);
        this.custID = custID;
        this.review = review;
    }

    public int getCustomerID(){
        return custID;
    }

}
