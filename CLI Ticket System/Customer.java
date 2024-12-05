public class Customer implements Runnable{
    private TicketPool ticketPool; // this is shared between the vendors and customers
    private int customerRetrivelRate; //frequency which the tickets will be removed from the pool
    private int quantity; //quantity customer willing to purchase

    public Customer(TicketPool ticketPool, int customerRetrivelRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrivelRate = customerRetrivelRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket();
            System.out.println("Ticket bought by "+ Thread.currentThread().getName()+". Ticket is "+ticket+".");

            //delay which the tickets will be removed
            try{
                Thread.sleep(customerRetrivelRate*1000);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}
