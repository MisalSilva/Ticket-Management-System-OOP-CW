import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxCapacity;
        while (true) {
            System.out.print("Enter the maximum capacity for the ticket queue: ");
            maxCapacity = sc.nextInt();
            sc.nextLine();
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
            sc.nextLine();
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
            sc.nextLine();
            if (ticketReleaseRate <= 0) {
                System.out.println("Error: Ticket Release Rate cannot be less than or equal to zero. Please try again.");
            }else {
                break;
            }
        }

        int ticketRetrievalRate;
        while (true) {
            System.out.print("Enter the ticket retrieval rate for each vendor: ");
            ticketRetrievalRate = sc.nextInt();
            sc.nextLine();
            if (ticketRetrievalRate <= 0) {
                System.out.println("Error: Ticket Release Rate cannot be less or equal to zero. Please try again.");
            }else {
                break;
            }
        }


        String command;
        while (true) {
            System.out.print("Enter 'start' to begin: ");
            command = sc.nextLine();


            if (command.equals("stop")) {
                System.out.println("Stopping program...");
                break;

            }else if (command.equals("start")) {
                System.out.println("Starting program...");

                TicketPool ticketPool = new TicketPool(maxCapacity); //The Ticket pool which is shared among the vendors and customers

                Vendor[] vendors = new Vendor[5]; //Array of vendor, for convenience i have used an array of objects
                for (int i = 0; i < vendors.length; i++) {
                    vendors[i] = new Vendor(ticketPool,totalTickets, ticketReleaseRate);
                    Thread vendorThread = new Thread(vendors[i], "Vendor " + (i+1)); // used 3rd constructor of thread class
                    vendorThread.start();// start the vendor thread
                }

                Customer[] customers = new Customer[5];// Array of customers, for convenience i have used an array of objects
                for (int i = 0; i < customers.length; i++) {
                    customers[i] = new Customer(ticketPool,ticketRetrievalRate,5);
                    Thread customerThread = new Thread(customers[i], "Customer " + (i+1));// used 3rd constructor of thread class
                    customerThread.start();
                }
            }
        }
    }
}


