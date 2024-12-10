import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

const TicketDashboard = () => {
    const [availableTickets, setAvailableTickets] = useState(0);
    const [totalTickets, setTotalTickets] = useState(0);

    useEffect(() => {
        const socket = new SockJS("http://localhost:8080/ws");
        const stompClient = new Client({
            webSocketFactory: () => socket,
            debug: (str) => console.log(str),
            onConnect: () => {
                stompClient.subscribe("/topic/ticketUpdates", (message) => {
                    const data = JSON.parse(message.body);
                    setAvailableTickets(data.availableTickets);
                    setTotalTickets(data.totalTickets);
                });
            },
        });
        stompClient.activate();

        return () => stompClient.deactivate();
    }, []);

    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>Ticket Management Dashboard</h1>
            <h2>Total Tickets: {totalTickets}</h2>
            <h2>Available Tickets in Pool: {availableTickets}</h2>
        </div>
    );
};

export default TicketDashboard;
