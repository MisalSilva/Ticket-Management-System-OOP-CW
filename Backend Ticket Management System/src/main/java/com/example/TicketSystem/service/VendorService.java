package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Ticket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VendorService {
    private final TicketPoolService ticketPoolService;

    public VendorService(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    public void addTickets(int count) throws InterruptedException {
        for (int i = 1; i <= count; i++) {
            Ticket ticket = new Ticket(i, "OOP Event", new BigDecimal("1000"));
            ticketPoolService.addTicket(ticket);
        }
    }
}
