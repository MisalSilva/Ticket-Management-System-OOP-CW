package com.example.TicketSystem.controller;

import com.example.TicketSystem.model.Ticket;
import com.example.TicketSystem.service.CustomerService;
import com.example.TicketSystem.service.TicketPoolService;
import com.example.TicketSystem.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final VendorService vendorService;
    private final CustomerService customerService;
    private final TicketPoolService ticketPoolService;

    public TicketController(VendorService vendorService, CustomerService customerService, TicketPoolService ticketPoolService) {
        this.vendorService = vendorService;
        this.customerService = customerService;
        this.ticketPoolService = ticketPoolService;
    }

    @PostMapping("/add")
    public Map<String, String> addTickets(@RequestParam int count) {
        try {
            vendorService.addTickets(count);
            return Map.of("message", "Tickets added successfully");
        } catch (InterruptedException e) {
            return Map.of("error", e.getMessage());
        }
    }

    @PostMapping("/buy")
    public Ticket buyTicket() {
        try {
            return customerService.buyTicket();
        } catch (InterruptedException e) {
            return null;
        }
    }

    @GetMapping("/available")
    public Map<String, Integer> getAvailableTickets() {
        return Map.of("availableTickets", ticketPoolService.getAvailableTickets());
    }
}

