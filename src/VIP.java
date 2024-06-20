public class VIP extends Customer {
    private double VIPdiscount;

    public VIP (String name, String phone, String email, double VIPdiscount){
        super(name, phone, email);
        this.VIPdiscount = VIPdiscount;
    }
}
