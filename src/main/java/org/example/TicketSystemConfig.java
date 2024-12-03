package org.example;

import java.util.*;
import java.util.logging.*;

class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int noVendors;
    private int noCustomers;
    private int remainingTickets;

    public Configuration() {
        this.remainingTickets = 0;
    }

    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
    public int getNoVendors() { return noVendors; }
    public int getNoCustomers() { return noCustomers; }
    public int getRemainingTickets() { return remainingTickets; }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
        this.remainingTickets = totalTickets;
    }
    public void setTicketReleaseRate(int rate) { this.ticketReleaseRate = rate; }
    public void setCustomerRetrievalRate(int rate) { this.customerRetrievalRate = rate; }
    public void setMaxTicketCapacity(int capacity) { this.maxTicketCapacity = capacity; }
    public void setNoVendors(int vendors) { this.noVendors = vendors; }
    public void setNoCustomers(int customers) { this.noCustomers = customers; }

    public synchronized void decrementRemainingTickets(int count) {
        this.remainingTickets = Math.max(0, this.remainingTickets - count);
    }

    public void displayConfig() {
        System.out.println("\n=== System Configuration ===");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Remaining Tickets: " + remainingTickets);
        System.out.println("Release Rate/sec: " + ticketReleaseRate);
        System.out.println("Retrieval Rate/sec: " + customerRetrievalRate);
        System.out.println("Max Capacity: " + maxTicketCapacity);
        System.out.println("Vendors: " + noVendors);
        System.out.println("Customers: " + noCustomers);
    }
}