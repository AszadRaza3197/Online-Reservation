import java.util.*;

class OnlineReservationSystem {
    
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>(); // Store login id and password
    static Map<String, Reservation> reservations = new HashMap<>(); // Store reservations by PNR

    public static void main(String[] args) {
        // Add a sample user
        users.put("user1", "password1");

        System.out.println("Welcome to the Online Reservation System");
        login();
    }

    // Login Form
    public static void login() {
        System.out.print("Enter login ID: ");
        String loginID = sc.nextLine();
        
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        if (users.containsKey(loginID) && users.get(loginID).equals(password)) {
            System.out.println("Login Successful!");
            mainMenu();
        } else {
            System.out.println("Invalid login. Please try again.");
            login();
        }
    }

    // Main Menu
    public static void mainMenu() {
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Exit");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    System.out.println("Thank you for using the Online Reservation System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Reservation System
    public static void makeReservation() {
        System.out.println("Reservation Form:");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter train number: ");
        String trainNumber = sc.nextLine();

        System.out.println("Train Name (Auto-filled): Express Train"); // Simulating auto-fill

        System.out.print("Enter class type (First Class / Second Class): ");
        String classType = sc.nextLine();
        
        System.out.print("Enter date of journey (DD/MM/YYYY): ");
        String journeyDate = sc.nextLine();

        System.out.print("From (Place): ");
        String from = sc.nextLine();
        
        System.out.print("To (Destination): ");
        String to = sc.nextLine();
        
        String pnr = UUID.randomUUID().toString(); // Generate a random PNR number
        reservations.put(pnr, new Reservation(pnr, name, trainNumber, "Express Train", classType, journeyDate, from, to));
        
        System.out.println("Reservation successful! Your PNR number is: " + pnr);
    }

    // Cancellation Form
    public static void cancelReservation() {
        System.out.print("Enter your PNR number to cancel: ");
        String pnr = sc.nextLine();
        
        if (reservations.containsKey(pnr)) {
            Reservation res = reservations.get(pnr);
            System.out.println("Reservation Details:");
            System.out.println(res);
            
            System.out.print("Do you want to confirm cancellation? (yes/no): ");
            String confirm = sc.nextLine();
            
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Your reservation has been successfully cancelled.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR number. No reservation found.");
        }
    }
}

// Reservation class to store reservation details
class Reservation {
    String pnr;
    String name;
    String trainNumber;
    String trainName;
    String classType;
    String journeyDate;
    String from;
    String to;

    public Reservation(String pnr, String name, String trainNumber, String trainName, String classType, String journeyDate, String from, String to) {
        this.pnr = pnr;
        this.name = name;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + "\nName: " + name + "\nTrain Number: " + trainNumber + "\nTrain Name: " + trainName +
               "\nClass: " + classType + "\nDate of Journey: " + journeyDate + "\nFrom: " + from + "\nTo: " + to;
    }
}
