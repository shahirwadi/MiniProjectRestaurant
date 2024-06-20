import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
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
            while (readTable.hasNextLine()){
                tableId = readTable.nextInt();
                capacity = readTable.nextInt();
                tables.add(new Table(tableId, capacity));
            }
            readTable.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
        }
    }

    public static LocalDateTime setStartTime(Scanner sc){
        while (true) {
            System.out.print("Enter a Start date and Time (yyyy-MM-dd HH:mm): ");
            String input = sc.nextLine();
            // Define the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                // Parse the input string to a LocalDate object
                LocalDateTime resStart = LocalDateTime.parse(input, formatter);
                System.out.println("You entered: " + resStart);
                return resStart;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Please enter the date in yyyy-MM-dd HH:mm format.");
            }
        }
    }

    public static LocalDateTime setEndTime(Scanner sc){
        while (true) {
            System.out.print("Enter a End date and Time (yyyy-MM-dd HH:mm): ");
            String input = sc.nextLine();
            // Define the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                // Parse the input string to a LocalDate object
                LocalDateTime resEnd = LocalDateTime.parse(input, formatter);
                System.out.println("You entered: " + resEnd);
                return resEnd;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Please enter the date in yyyy-MM-dd HH:mm format.");
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
        System.out.println("[0] Exit");
        Scanner sc = new Scanner(System.in);
        
        int choice = 0;
        do {
            System.out.print("Please choose provided option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("The program stopped...");
                    System.exit(0);

                case 1:
                    System.out.println("Please provide your personal information as below: ");
                    System.out.print("Name      : ");
                    String name = sc.nextLine();
                    System.out.print("Phone No. : ");
                    String phone = sc.nextLine();
                    System.out.print("Email     : ");
                    String email = sc.nextLine();
                    customers.add(new Customer(name, phone, email));
                    
                    System.out.print("Enter the Number of People: ");
                    int numOfPeople = sc.nextInt();
                    sc.nextLine();

                    LocalDateTime resStartTime = Restaurant.setStartTime(sc);
                    LocalDateTime resEndTime = Restaurant.setEndTime(sc);
                    TimeSession session = new TimeSession(resStartTime, resEndTime);
                    
                    //Read all tables from files
                    Restaurant.readTables(tables);

                    Reservation reservation = new Reservation(numOfPeople, new Customer(name, phone, email), null, session);

                    for(Table table : tables){
                        if(table.getAvailable(session) && table.getCapacity() >= numOfPeople){  
                            reservation.confirm(table, session); 
                            reservations.add(reservation);
                            break;
                            
                        } else{
                            waitlist.addToWaitlist(reservation);
                        } 
                    }
                    
                    reservation.displayReservation();
                    System.out.println(tables.get(0).getAvailable(session));
                    System.out.println(tables.get(1).getAvailable(session));
                        
                    break;
                case 2:
                    
                    break;
            
                default:
                    System.out.println("Invalid input! Please enter the provided option.");
                    break;
            }
        }while(choice>0);
        
    }
}
