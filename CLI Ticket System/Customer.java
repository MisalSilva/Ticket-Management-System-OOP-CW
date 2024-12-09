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
            // Delay based on the retrieval rate
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}





