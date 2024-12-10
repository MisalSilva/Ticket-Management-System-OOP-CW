package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Ticket;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final TicketPoolService ticketPoolService;

    public CustomerService(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    public Ticket buyTicket() throws InterruptedException {
        return ticketPoolService.buyTicket();
    }
}
