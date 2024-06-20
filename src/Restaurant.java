import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Restaurant {
    
    public static void readTables(ArrayList<Table> tables ){   
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

    public static LocalDate setReservationDate(Scanner sc){
        while (true) {
            System.out.print("Enter a date (yyyy-MM-dd): ");
            String inputDate = sc.nextLine();
            // Define the date format
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                // Parse the input string to a LocalDate object
                LocalDate resDate = LocalDate.parse(inputDate, formatterDate);
                System.out.println("You entered the date: " + resDate);
                return resDate;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
    }

    public static LocalTime setReservationTime(Scanner sc){
        while(true){
            System.out.print("Enter a time (Hours:Minutes): ");
            String inputTime = sc.nextLine();
            // Define the time format
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
            try {
                // Parse the input string to a LocalTime object
                LocalTime resTime = LocalTime.parse(inputTime, formatterTime);
                System.out.println("You entered the time: " + resTime);
                return resTime;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the time in HH:mm format.");
            }
        }  
    }


    public static void main(String []args){
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();
        ArrayList<Table> tables = new ArrayList<>();
        Waitlist waitlist = new Waitlist();
        

        System.out.println("WELCOME TO OUR RESTAURANT!");
        System.out.println("[1] Create Reservation");
        System.out.println("[2] Order Meals");
        Scanner sc = new Scanner(System.in);
        System.out.print("Please choose provided option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        do{
            switch (choice) {
            case 1:
                System.out.println("Please provide your personal information as below: ");
                System.out.print("Name      : ");
                String name = sc.nextLine();
                System.out.print("Phone No. : ");
                String phone = sc.nextLine();
                System.out.print("Email     : ");
                String email = sc.nextLine();
                customers.add(new Customer(name, phone, email));

                LocalDate resDate = Restaurant.setReservationDate(sc);
                LocalTime resTime = Restaurant.setReservationTime(sc);

                System.out.print("Enter the Number of People: ");
                int numOfPeople = sc.nextInt();

                //Read all tables from files
                Restaurant.readTables(tables);

                Reservation reservation = new Reservation(numOfPeople, new Customer(name, phone, email), null, resDate, resTime);

                for(Table table : tables){
                    if(table.getAvailable() && table.getCapacity() >= numOfPeople){  
                        reservation.confirm(table); 
                        reservations.add(reservation);
                        break;
                        
                    } else{
                        waitlist.addToWaitlist(reservation);
                    } 
                }
                
                reservation.displayReservation();
                System.out.println(tables.get(0).getAvailable());
                    
                break;
            case 2:
                
                break;
        
            default:
                System.out.println("Invalid input! Please enter the provided option.");
                break;
        }
        } while(choice<1 || choice>2);
        
    }
}
