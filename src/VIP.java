public class VIP extends Customer {
    private double VIPdiscount;

    public VIP (String name, String phone, String email, int custID, double VIPdiscount){
        super(name, phone, email, custID);
        this.VIPdiscount = VIPdiscount;
    }
}
