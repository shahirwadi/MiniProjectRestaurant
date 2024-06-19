import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Reservation {
    private static int reservationID;
    private static int numOfPeople;
    private Customer customer;
    private Table table;
    private LocalDate resDate;
    private LocalTime resTime;

    public Reservation(){}

    public void makeReservation(Customer customer, Table table){
        reservationID++;
        numOfPeople++;
        this.customer = customer;
        this.table = table;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a date (yyyy-MM-dd): ");
            String inputDate = sc.nextLine();
            // Define the date format
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                // Parse the input string to a LocalDate object
                resDate = LocalDate.parse(inputDate, formatterDate);
                System.out.println("You entered the date: " + resDate);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        while(true){
            System.out.print("Enter a time (Hours-Minutes): ");
            String inputTime = sc.nextLine();
            // Define the time format
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
            try {
                // Parse the input string to a LocalTime object
                resTime = LocalTime.parse(inputTime, formatterTime);
                System.out.println("You entered the time: " + resTime);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the time in HH:mm format.");
            }
        }   
        sc.close();
    }
}
