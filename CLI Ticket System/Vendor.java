import java.math.BigDecimal;

public class Vendor implements Runnable{
    private TicketPool ticketPool; // the ticket pool which is shared among vendors and customers
    private int totalTickets; //tickets vendor will sell
    private int ticketReleaseRate; //frequency tickets will be added to the pool

    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

//    public void setTotalTickets(int totalTickets) {
//        this.totalTickets = totalTickets;
//    }
//    public void setTicketReleaseRate(int ticketReleaseRate) {
//        this.ticketReleaseRate = ticketReleaseRate;
//    }
//
//    public int getTotalTickets() {
//        return totalTickets;
//    }
//    public int getTicketReleaseRate() {
//        return ticketReleaseRate;
//    }

    //implement the thread
    //Runnable interface should write the implementation for Runnable interface
    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket(i, "OOP Evnt", new BigDecimal("1000"));
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
