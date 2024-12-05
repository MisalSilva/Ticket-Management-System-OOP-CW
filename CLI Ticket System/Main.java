import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the maximum capacity for the ticket queue: ");
        int maxCapacity = sc.nextInt();

        System.out.print("Enter the total number of Tickets for each vendor: ");
        int totalTickets = sc.nextInt();

        System.out.print("Enter the ticket release rate (in seconds) for each Vendor: ");
        int ticketReleaseRate = sc.nextInt();

        System.out.print("Enter the Quantity you want to Buy: ");
        int quantity = sc.nextInt();

        System.out.print("Enter the ticket retrieval rate (in seconds) for each Customer: ");
        int ticketRetrievalRate = sc.nextInt();

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
