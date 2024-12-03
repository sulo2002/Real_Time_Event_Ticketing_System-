package org.example;

import java.util.*;
import java.util.logging.*;

class TicketPool {
    private static final Logger LOGGER = Logger.getLogger(TicketPool.class.getName());
    private final List<String> tickets = new ArrayList<>();
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(String ticket) {
        if (tickets.size() < maxCapacity) {
            tickets.add(ticket);
            LOGGER.info("Added: " + ticket);
            notifyAll();
        } else {
            LOGGER.warning("Pool full - capacity: " + maxCapacity);
        }
    }

    public synchronized String removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            LOGGER.info("Waiting for tickets");
            wait();
        }
        String ticket = tickets.remove(0);
        LOGGER.info("Sold: " + ticket);
        return ticket;
    }

    public synchronized int getCount() {
        return tickets.size();
    }
}