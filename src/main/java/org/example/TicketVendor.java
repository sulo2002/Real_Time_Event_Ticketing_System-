package org.example;

import java.util.logging.*;

class Vendor implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Vendor.class.getName());
    private final String id;
    private final TicketPool pool;
    private final int releaseRate;
    private final Configuration config;
    private int ticketCount = 1;
    private volatile boolean running = true;

    public Vendor(String id, TicketPool pool, int releaseRate, Configuration config) {
        this.id = id;
        this.pool = pool;
        this.releaseRate = releaseRate;
        this.config = config;
    }

    @Override
    public void run() {
        try {
            while (running && config.getRemainingTickets() > 0) {
                int count = Math.min(releaseRate, config.getRemainingTickets());
                if (count > 0) {
                    for (int i = 0; i < count; i++) {
                        pool.addTicket(id + "-Ticket-" + ticketCount++);
                    }
                    config.decrementRemainingTickets(count);
                    LOGGER.info(id + " released " + count + " tickets. Remaining: " + config.getRemainingTickets());
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            LOGGER.warning(id + " interrupted");
        }
    }

    public void stop() {
        running = false;
    }
}