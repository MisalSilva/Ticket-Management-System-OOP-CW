import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticktetQueue;
    private int maximumCapacity;


    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticktetQueue = new LinkedList<>();
    }

    //Add Ticket method used by vendors to add tickets
    public synchronized void addTicket(Ticket ticket) { //need to synchronize
        while(ticktetQueue.size() >= maximumCapacity) {
            try{
                wait();
            }
            catch(InterruptedException e) {
                //immeadiatly after try block the catch block or finally block must come
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}
