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
                //immediately after try block the catch block or finally block must come
                e.printStackTrace(); // for CLI we can use this
                throw new RuntimeException(e.getMessage()); // if its client server application the error should be thrown to client
            }
        }
        this.ticktetQueue.add(ticket); // Adding the ticket to the Queue
        notifyAll(); // Notify all the waiting threads
        // print the message to show the thread name who added and the updated size of the pool
        System.out.println(Thread.currentThread().getName() + " has added a ticket to the pool. " +
                "Available Tickets: " + this.ticktetQueue.size()+".");
    }

    // Buy ticket method used by the customers
    public synchronized Ticket buyTicket() {
        while(ticktetQueue.isEmpty()) {
            try{
                wait(); //wait if the queue is empty
            }catch(InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        Ticket ticket = ticktetQueue.poll(); //remove ticket from queue(front of the queue)
        notifyAll(); // notify all waiting threads
        // print the message to show the thread name who bought and current size of the pool
        System.out.println("Ticket bought by " + Thread.currentThread().getName() + ": Ticket " + ticket + "," +
                "Available Tickets: " + ticktetQueue.size() +".");
        return ticket;
    }
}



