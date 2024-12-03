package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.*;

public class TicketVendor implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(TicketVendor.class.getName());

    private final String vendorName;
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final Random random = new Random();

    public TicketVendor(String vendorName, TicketPool ticketPool, int releaseRate) {
        this.vendorName = vendorName;
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Generate tickets
                List<Ticket> newTickets = generateTickets(releaseRate);

                // Add tickets to the pool
                boolean added = ticketPool.addTickets(newTickets);

                if (added) {
                    LOGGER.info(vendorName + " released " + newTickets.size() + " tickets.");
                }

                // Sleep to simulate time between ticket releases
                Thread.sleep(1000); // 1 second between releases
            }
        } catch (InterruptedException e) {
            LOGGER.warning(vendorName + " interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    private List<Ticket> generateTickets(int count) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // Randomize ticket prices
            double price = 50 + random.nextDouble() * 150; // Tickets between $50-$200
            tickets.add(new Ticket("Concert Event", price));
        }
        return tickets;
    }
}