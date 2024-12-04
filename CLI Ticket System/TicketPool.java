import java.util.LinkedList;

public class TicketPool {
    private Queue<Ticket> ticktetQueue;
    private int maximumCapacity;


    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticktetQueue = new LinkedList<>();
    }

}
