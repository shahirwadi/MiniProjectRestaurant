import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Restaurant {

    public static void readMenu(ArrayList<Food> food){   
        try{
            File tableFile = new File("src//Menu.csv");
            Scanner readTable = new Scanner(tableFile);
            int foodID;
            String foodName;
            double foodPrice;
            while (readTable.hasNextLine()){
                String line = readTable.nextLine();
                String[] parts = line.split(",");
                foodID = Integer.parseInt(parts[0]);
                foodName = parts[1];
                foodPrice = Double.parseDouble(parts[2]);
                food.add(new Food(foodID, foodName, foodPrice));
            }
            readTable.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
        }
    }
    
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
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Bill> bills = new ArrayList<>();
        ArrayList<Review> reviews = new ArrayList<>();
        int countOrder = -1;
        int countReservation = -1;
        int customerCount =-1;
        int countBill = -1;
        
        int choice = 0;
        do {
            System.out.println("WELCOME TO OUR RESTAURANT!");
            System.out.println("[1] Create Reservation");
            System.out.println("[2] Payment");
            System.out.println("[3] Review");
            System.out.println("[0] Exit");
            Scanner sc = new Scanner(System.in);

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
                    System.out.println("Choose your customer type: ");
                    System.out.println("[1] VIP");
                    System.out.println("[2] Regular");
                    int customerChoice = sc.nextInt();
                    sc.nextLine();
                    if(customerChoice==1){
                        customers.add(new VIP(name, phone, email));
                    } else{
                        customers.add(new Regular(name, phone, email));
                    }
                    customerCount++;
                        
                    int numOfPeople;
                    do{
                        System.out.print("Enter the Number of People: ");
                        numOfPeople = sc.nextInt();
                        sc.nextLine();
                    } while(numOfPeople > 6);

                    LocalDateTime resStartTime = Restaurant.setStartTime(sc);
                    LocalDateTime resEndTime = Restaurant.setEndTime(sc);
                    TimeSession session = new TimeSession(resStartTime, resEndTime);
                    
                    //Read all tables from files
                    Restaurant.readTables(tables);

                    Reservation reservation = new Reservation(numOfPeople, customers.get(customerCount), null, session);

                    for(Table table : tables){
                        if(table.getAvailable(session) && table.getCapacity() >= numOfPeople){  
                            reservation.confirm(table, session); 
                            reservations.add(reservation);
                            countReservation++;
                            break;   
                        } else{
                            waitlist.addToWaitlist(reservation);
                        } 
                    }

                    // order
                    System.out.println("Create an order...");
                    Restaurant.readMenu(foods);
                    for(Food food : foods){
                        food.printFood();
                    }
                    System.out.println("[0] Finish Order");

                    int foodChoice = 1;
                    // Order order = new Order(orderID, customer);
                    ArrayList<Food> foodList = new ArrayList<>();

                    do{
                        System.out.print("Enter your choice: ");
                        foodChoice = sc.nextInt();

                        for(Food food: foods){
                            if(foodChoice == food.getfoodID()){
                                foodList.add(food);
                            }
                        }
                    } while(foodChoice != 0);
                    //add to list
                    Order order = new Order(customers.get(customerCount), foodList);
                    orders.add(order);
                    countOrder++;

                    // display
                    reservations.get(countReservation).displayReservation();
                    orders.get(countOrder).displayOrders();
                        
                    break;
                case 2:

                    //payment
                    System.out.println("Payment Information");
                    System.out.print("Enter order ID: ");
                    int orderID = sc.nextInt();
                    for(Order ord : orders){
                        if(ord.getOrderID() == orderID){
                            bills.add(new Bill(ord));
                            countBill++;
                        }
                    }

                    Payment payment = new Payment();

                    boolean success = true;
                    do{
                        success = payment.processPayment(bills.get(countBill), sc);
                    } while(!success);      
                    break;

                case 3:
                    Review review = new Review();
                    System.out.print("Please enter your name: ");
                    String custName = sc.nextLine();
                    for(Customer customer : customers){
                        if(customer.getName().equals(custName)){
                            review.reviewForm(sc, customer);
                            reviews.add(review);
                        }
                    }
                    reviews.get(0).displayReview();
                    break;
            
                default:
                    System.out.println("Invalid input! Please enter the provided option.");
                    break;
            }
        }while(choice>0);
        
    }
}
