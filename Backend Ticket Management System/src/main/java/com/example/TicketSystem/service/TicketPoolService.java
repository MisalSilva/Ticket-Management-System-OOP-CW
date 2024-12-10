package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class TicketPoolService {
    private final Queue<Ticket> ticketQueue = new LinkedList<>();
    private final int maxCapacity = 100; // Example capacity

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (ticketQueue.size() >= maxCapacity) {
            wait();
        }
        ticketQueue.add(ticket);
        notifyAll();
    }

    public synchronized Ticket buyTicket() throws InterruptedException {
        while (ticketQueue.isEmpty()) {
            wait();
        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return ticketQueue.size();
    }
}
