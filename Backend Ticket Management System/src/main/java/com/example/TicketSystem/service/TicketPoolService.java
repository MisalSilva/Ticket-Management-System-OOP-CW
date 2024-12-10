package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class TicketPoolService {
    private final Queue<Ticket> ticketQueue = new LinkedList<>();
    private final int maxCapacity = 100; // Example capacity
    private final TicketBroadcastService broadcastService = null;
    private final int maximumCapacity;
    private final LinkedList<Object> ticktetQueue;

    public TicketPoolService(int maximumCapacity, TicketBroadcastService broadcastService) {
        broadcastService = new TicketBroadcastService();
        this.maximumCapacity = maximumCapacity;
        this.ticktetQueue = new LinkedList<>();
//        this.broadcastService = broadcastService;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (ticketQueue.size() >= maxCapacity) {
            wait();
        }
        ticketQueue.add(ticket);
        notifyAll();

        broadcastService.broadcastTicketUpdate(this.ticktetQueue.size(), maximumCapacity);
    }

    public synchronized Ticket buyTicket() throws InterruptedException {
        while (ticketQueue.isEmpty()) {
            wait();
        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        broadcastService.broadcastTicketUpdate(this.ticktetQueue.size(), maximumCapacity);
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return ticketQueue.size();
    }
}
