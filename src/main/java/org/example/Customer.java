package org.example;

import java.util.logging.*;

class Customer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());
    private final String id;
    private final TicketPool pool;
    private final int interval;
    private int purchased = 0;
    private volatile boolean running = true;

    public Customer(String id, TicketPool pool, int interval) {
        this.id = id;
        this.pool = pool;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (running) {
                String ticket = pool.removeTicket();
                if (ticket != null) {
                    purchased++;
                    LOGGER.info(id + " bought " + ticket);
                }
                Thread.sleep(interval);
            }
        } catch (InterruptedException e) {
            LOGGER.warning(id + " interrupted");
        }
    }

    public void stop() {
        running = false;
    }

    public int getPurchased() { return purchased; }

    @Override
    public String toString() { return id; }
}