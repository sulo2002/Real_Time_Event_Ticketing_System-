package org.example;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.*;

public class TicketPool {
    private static final Logger LOGGER = Logger.getLogger(TicketPool.class.getName());

    // Use Collections.synchronizedList for thread-safety
    private final List<Ticket> tickets;
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.tickets = Collections.synchronizedList(new ArrayList<>());
    }

    // Synchronized method to add tickets
    public synchronized boolean addTickets(List<Ticket> newTickets) {
        if (tickets.size() + newTickets.size() > maxCapacity) {
            LOGGER.warning("Cannot add tickets. Pool would exceed max capacity.");
            return false;
        }

        tickets.addAll(newTickets);
        LOGGER.info("Added " + newTickets.size() + " tickets. Total tickets: " + tickets.size());
        notifyAll(); // Notify waiting threads
        return true;
    }

    // Synchronized method to remove a ticket
    public synchronized Ticket removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            LOGGER.info("No tickets available. Waiting for tickets to be added.");
            wait(); // Wait if no tickets are available
        }

        Ticket ticket = tickets.remove(0);
        LOGGER.info("Ticket purchased. Remaining tickets: " + tickets.size());
        return ticket;
    }

    // Get current ticket count
    public int getTicketCount() {
        return tickets.size();
    }
}