package org.example;

import java.util.concurrent.*;
import java.util.logging.*;

public class TicketSystemMain {
    private static final Logger LOGGER = Logger.getLogger(TicketSystemMain.class.getName());

    public static void main(String[] args) {
        // Configure logging
        setupLogging();

        // Create configuration
        TicketSystemConfig config = new TicketSystemConfig();
        config.promptForConfiguration();

        // Create ticket pool
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

        // Create thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(
                config.getTotalTickets() / 10 + 2  // Dynamic thread pool size
        );

        // Create and start vendors
        for (int i = 1; i <= 3; i++) {
            TicketVendor vendor = new TicketVendor(
                    "Vendor-" + i,
                    ticketPool,
                    config.getTicketReleaseRate() / 3
            );
            executorService.submit(vendor);
        }

        // Create and start customers
        Customer[] customers = new Customer[5];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(
                    "Customer-" + (i+1),
                    ticketPool,
                    config.getCustomerRetrievalRate() / 5
            );
            executorService.submit(customers[i]);
        }

        // Run for a specific duration
        try {
            Thread.sleep(60000); // Run for 1 minute
        } catch (InterruptedException e) {
            LOGGER.severe("Main thread interrupted");
        }

        // Shutdown
        executorService.shutdownNow();

        // Print final report
        printReport(customers, ticketPool);
    }

    private static void setupLogging() {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            handlers[0].setFormatter(new SimpleFormatter());
        }
    }

    private static void printReport(Customer[] customers, TicketPool ticketPool) {
        System.out.println("\n--- Ticket System Report ---");
        int totalTicketsPurchased = 0;

        for (Customer customer : customers) {
            System.out.println(customer + " purchased " + customer.getTicketsPurchased() + " tickets");
            totalTicketsPurchased += customer.getTicketsPurchased();
        }

        System.out.println("\nTotal Tickets Purchased: " + totalTicketsPurchased);
        System.out.println("Remaining Tickets in Pool: " + ticketPool.getTicketCount());
    }
}