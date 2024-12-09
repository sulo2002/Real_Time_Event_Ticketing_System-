package com.example.Backend.components;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;


import com.example.Backend.model.Config;
import com.example.Backend.services.ConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Ticketpool {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private static final Logger logger = Logger.getLogger(Ticketpool.class.getName());
    private int maxCap;
    private volatile boolean isSystemRunning = true;
    private int totalSoldTickets = 0;
    private int totalTickets;
    @Autowired
    private ConfigServiceImpl configService;

    public void init(int maxCap, int totalTickets) {
        this.maxCap = maxCap;
        this.totalTickets = totalTickets;

    }

    public void addTicket(String ticket) {
        synchronized(this.tickets) {
            if (this.isSystemRunning) {
                if (this.tickets.size() < this.maxCap) {
                    this.tickets.add(ticket);
                    logger.info("Ticket Added: " + ticket);
                } else {
                    logger.warning("Ticket Pool is Full. Cannot add more tickets to the Ticket Pool.");
                }

            }
        }
    }

    public String removeTicket() {
        synchronized(this.tickets) {
            if (!this.isSystemRunning) {
                return null;
            } else if (!this.tickets.isEmpty()) {
                String ticket = (String)this.tickets.remove(0);
                ++this.totalSoldTickets;
                logger.info("Ticket Removed: " + ticket);
                if (this.totalSoldTickets >= this.totalTickets) {
                    logger.info("All tickets have been sold. System will now stop.");
                    this.isSystemRunning = false;
                }

                return ticket;
            } else {
                if (this.totalSoldTickets < this.totalTickets) {
                    logger.warning("Ticket Pool is empty. Waiting for more tickets.");
                }

                return null;
            }
        }
    }

    public int noTickets() {
        synchronized(this.tickets) {
            return this.tickets.size();
        }
    }

    public boolean isSystemRunning() {
        return this.isSystemRunning;
    }

    public void stopSystem() {
        synchronized(this.tickets) {
            this.isSystemRunning = false;
        }
    }

}
