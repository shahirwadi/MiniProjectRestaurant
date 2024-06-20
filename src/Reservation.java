import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Reservation {
    private static int reservationID;
    private int numOfPeople;
    private Customer customer;
    private Table table;
    private LocalDate resDate;
    private LocalTime resTime;


    public Reservation(int numOfPeople, Customer customer, Table table, LocalDate resDate, LocalTime resTime){
        this.numOfPeople = numOfPeople;
        reservationID++;
        this.customer = customer;
        this.table = table;
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
}
