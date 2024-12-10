package com.example.TicketSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketBroadcastService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void broadcastTicketUpdate(int availableTickets, int totalTickets) {
        messagingTemplate.convertAndSend("/topic/ticketUpdates",
                new TicketUpdate(availableTickets, totalTickets));
    }

    // DTO for WebSocket updates
    public static class TicketUpdate {
        private int availableTickets;
        private int totalTickets;

        public TicketUpdate(int availableTickets, int totalTickets) {
            this.availableTickets = availableTickets;
            this.totalTickets = totalTickets;
        }

        public int getAvailableTickets() {
            return availableTickets;
        }

        public int getTotalTickets() {
            return totalTickets;
        }
    }
}
