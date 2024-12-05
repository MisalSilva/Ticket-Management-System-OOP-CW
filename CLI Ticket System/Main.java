//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TicketPool ticketPool = new TicketPool(10); //The Ticketpool which is shared among the vendors and customers

        Vendor[] vendors = new Vendor[10]; //Array of vendor, for convenience i have used an array of objects
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool,20, 5);
            Thread vendorThread = new Thread(vendors[i], "Vendor " + i); // used 3rd constructor of thread class
            vendorThread.start();// start the vendor thread
        }


        Customer[] customers = new Customer[10];// Array of customers, for convenience i have used an array of objects
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool,5,5);
            Thread customerThread = new Thread(customers[i], "Customer " + i);// used 3rd constructor of thread class
            customerThread.start();
        }
    }
}