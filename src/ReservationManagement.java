import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReservationManagement {
    private ArrayList<Reservation> reservations;
    private ArrayList<Table> tables;
    private Waitlist waitlist;

    public ReservationManagement() {
        this.reservations = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.waitlist = new Waitlist();
    }

    public void readTables(){
        
        try{
            File tableFile = new File("src//Tablelist.csv");
            Scanner readTable = new Scanner(tableFile);
            int tableId;
            int capacity;
            boolean available;
            while (readTable.hasNextLine()){
                tableId = readTable.nextInt();
                capacity = readTable.nextInt();
                available = readTable.nextBoolean();
                tables.add(new Table(tableId, capacity, available));
            }
            readTable.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
        }
    }

    public void makeReservation(Reservation reservation){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a date (yyyy-MM-dd): ");
            String inputDate = sc.nextLine();
            // Define the date format
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                // Parse the input string to a LocalDate object
                LocalDate resDate = LocalDate.parse(inputDate, formatterDate);
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
                LocalTime resTime = LocalTime.parse(inputTime, formatterTime);
                System.out.println("You entered the time: " + resTime);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the time in HH:mm format.");
            }
        }   
        sc.close();

        for(Table table : tables){
            if(table.getAvailable() && table.getCapacity() >= reservation.getNumberOfPeople()){
                reservation.confirm(table);
                reservations.add(reservation);
                return;
            }
        }
        waitlist.addToWaitlist(reservation);
    }
}
