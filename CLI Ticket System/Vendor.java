import java.math.BigDecimal;

public class Vendor implements Runnable{
    private TicketPool ticketPool; // the ticket pool which is shared among vendors and customers
    private int totalTickets; //tickets vendor will sell
    private int ticketReleaseRate; //frequency tickets will be added to the pool

    //implement the thread
    //Runnable interface should write the implementation for Runnable interface
    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket(001, "event1", new BigDecimal("1000"));
            ticketPool.addTicket(ticket);

            //the ticket release frequency means the delay
            //we should convert the value in S to Ms
            try{
                Thread.sleep(ticketReleaseRate*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}
