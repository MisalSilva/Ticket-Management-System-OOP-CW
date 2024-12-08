//public class Customer implements Runnable{
//    private TicketPool ticketPool; // this is shared between the vendors and customers
//    private int customerRetrivelRate; //frequency which the tickets will be removed from the pool
//    private int quantity; //quantity customer willing to purchase
//
//    public Customer(TicketPool ticketPool, int customerRetrivelRate, int quantity) {
//        this.ticketPool = ticketPool;
//        this.customerRetrivelRate = customerRetrivelRate;
//        this.quantity = quantity;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < quantity; i++) {
//            Ticket ticket = ticketPool.buyTicket();
//            System.out.println("Ticket bought by "+ Thread.currentThread().getName()+". Ticket is "+ticket+".");
//
//            //delay which the tickets will be removed
//            try{
//                Thread.sleep(customerRetrivelRate*1000);
//            }catch(InterruptedException e){
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}

public class Customer implements Runnable {
    private TicketPool ticketPool; // this is shared between the vendors and customers
    private int customerRetrievalRate; // frequency which the tickets will be removed from the pool

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (true) { // Allow continuous ticket purchasing
            Ticket ticket = ticketPool.buyTicket();
            System.out.println("Ticket bought by " + Thread.currentThread().getName() + ". Ticket is " + ticket + ".");

            // Delay based on the retrieval rate
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}




