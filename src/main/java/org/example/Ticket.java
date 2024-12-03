package org.example;

import java.io.Serializable;
import java.util.UUID;

public class Ticket implements Serializable {
    private final String ticketId;
    private final String eventName;
    private final double price;

    public Ticket(String eventName, double price) {
        this.ticketId = UUID.randomUUID().toString();
        this.eventName = eventName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + ticketId + '\'' +
                ", event='" + eventName + '\'' +
                ", price=" + price +
                '}';
    }

    // Getters
    public String getTicketId() { return ticketId; }
    public String getEventName() { return eventName; }
    public double getPrice() { return price; }
}