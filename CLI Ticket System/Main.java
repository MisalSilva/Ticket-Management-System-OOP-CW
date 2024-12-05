import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxCapacity;
        while (true) {
            System.out.print("Enter the maximum capacity for the ticket queue: ");
            maxCapacity = sc.nextInt();
            if (maxCapacity <= 0) {
                System.out.println("Error: Maximum capacity cannot be less than or equal to zero. Please try again.");
            }else {
                break;
            }
        }

        int totalTickets;
        while (true) {
            System.out.print("Enter the total number of Tickets for each vendor: ");
            totalTickets = sc.nextInt();
            if (totalTickets <= 0) {
                System.out.println("Error: Total number of tickets cannot be less than or equal to zero. Please try again.");
            }else{
                break;
            }
        }

        int ticketReleaseRate;
        while (true) {
            System.out.print("Enter the ticket release rate for each vendor: ");
            ticketReleaseRate = sc.nextInt();
            if (ticketReleaseRate <= 0) {
                System.out.println("Error: Ticket Release Rate cannot be less than or equal to zero. Please try again.");
            }else {
                break;
            }
        }

        int quantity;
        while (true) {
            System.out.print("Enter the Ticket Quantity for each customer: ");
            quantity = sc.nextInt();
            if (quantity <= 0) {
                System.out.println("Error: Ticket Quantity cannot be less or equal to zero. Please try again.");
            }else {
                break;
            }
        }

        int ticketRetrievalRate;
        while (true) {
            System.out.print("Enter the ticket retrieval rate for each vendor: ");
            ticketRetrievalRate = sc.nextInt();
            if (ticketRetrievalRate <= 0) {
                System.out.println("Error: Ticket Release Rate cannot be less or equal to zero. Please try again.");
            }else {
                break;
            }
        }

        TicketPool ticketPool = new TicketPool(maxCapacity); //The Ticket pool which is shared among the vendors and customers

        Vendor[] vendors = new Vendor[5]; //Array of vendor, for convenience i have used an array of objects
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool,totalTickets, ticketReleaseRate);
            Thread vendorThread = new Thread(vendors[i], "Vendor " + (i+1)); // used 3rd constructor of thread class
            vendorThread.start();// start the vendor thread
        }


        Customer[] customers = new Customer[5];// Array of customers, for convenience i have used an array of objects
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool,ticketRetrievalRate,quantity);
            Thread customerThread = new Thread(customers[i], "Customer " + (i+1));// used 3rd constructor of thread class
            customerThread.start();
        }
    }
}

//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        // Prompt for the total number of tickets and ticket release rate
//        System.out.print("Enter the total number of Tickets for each Vendor: ");
//        int totalTickets = sc.nextInt();
//
//        System.out.print("Enter the ticket release rate (in seconds) for each Vendor: ");
//        int ticketReleaseRate = sc.nextInt();
//
//        // Create a shared TicketPool
//        TicketPool ticketPool = new TicketPool(15); // The TicketPool which is shared among the vendors and customers
//
//        // Create and start Vendor threads
//        Vendor[] vendors = new Vendor[5]; // Array of Vendors, here size is 10
//        for (int i = 0; i < vendors.length; i++) {
//            vendors[i] = new Vendor(ticketPool, totalTickets, ticketReleaseRate);
//            Thread vendorThread = new Thread(vendors[i], "Vendor " + (i + 1)); // Name the threads "Vendor 1", "Vendor 2", etc.
//            vendorThread.start(); // Start the Vendor thread
//        }
//
//        // Close the scanner
//        sc.close();
//    }
//}
