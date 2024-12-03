package org.example;

import java.util.Random;
import java.util.logging.*;

public class Customer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());

    private final String customerName;
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private final Random random = new Random();

    private int ticketsPurchased = 0;

    public Customer(String customerName, TicketPool ticketPool, int retrievalRate) {
        this.customerName = customerName;
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Attempt to purchase tickets
                int purchaseCount = random.nextInt(retrievalRate) + 1;

                for (int i = 0; i < purchaseCount; i++) {
                    try {
                        Ticket ticket = ticketPool.removeTicket();
                        ticketsPurchased++;

                        LOGGER.info(customerName + " purchased ticket: " + ticket);
                    } catch (InterruptedException e) {
                        LOGGER.warning(customerName + " interrupted during ticket purchase.");
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                // Sleep between purchase attempts
                Thread.sleep(1500); // 1.5 seconds between purchase attempts
            }
        } catch (InterruptedException e) {
            LOGGER.warning(customerName + " thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    public int getTicketsPurchased() {
        return ticketsPurchased;
    }
}