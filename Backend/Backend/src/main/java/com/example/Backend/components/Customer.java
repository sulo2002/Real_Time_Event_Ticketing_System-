package com.example.Backend.components;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
@Scope("prototype")
public class Customer implements Runnable {
    private static final Logger logger = Logger.getLogger(Customer.class.getName());
    private final Ticketpool ticketpool;
    private final int retrievalInterval;
    private final int customerId;

    public Customer(Ticketpool ticketpool, int retrievalInterval, int customerId) {
        this.ticketpool = ticketpool;
        this.retrievalInterval = retrievalInterval;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String ticket = ticketpool.removeTicket();
                if (ticket != null) {
                    logger.info("Customer " + customerId + " retrieved ticket: " + ticket);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.info("Customer " + customerId + " Interrupted");
        }
    }
}


