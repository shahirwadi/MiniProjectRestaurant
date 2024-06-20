import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private static int reservationID;
    private int numOfPeople;
    private Customer customer;
    private Table table;
    private LocalDate resDate;
    private LocalTime resTime;

    public Reservation(){

    }

    public Reservation(int numOfPeople, Customer customer, Table table, LocalDate resDate, LocalTime resTime){
        this.numOfPeople = numOfPeople;
        reservationID++;
        this.customer = customer;
        this.table = table;
        this.resDate = resDate;
        this.resTime = resTime;
    }
    
    public void confirm(Table table){
        this.table = table;
        table.reserveTable();
    }

    public void cancel() {
        if (table != null) {
            table.releaseTable();
        }
    }

    public int getReservationId() { return reservationID; }
    public Customer getCustomerName() { return customer; }
    public LocalDate getDate() { return resDate; }
    public LocalTime getTime() { return resTime; }
    public int getNumberOfPeople() { return numOfPeople; }
    public Table getTable() { return table; }
    public void setTable(Table table) { this.table = table; }

    public void displayReservation(){
        System.out.println("Reservation ID  : "+reservationID);
        System.out.println("Customer Name   : "+customer.getName());
        System.out.println("Date            : "+resDate);
        System.out.println("Time            : "+resTime);
        System.out.println("Number Of People: "+numOfPeople);
        System.out.println("Table ID        : "+table.getTableId());
    }
}
