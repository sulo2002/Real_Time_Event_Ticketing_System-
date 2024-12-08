package org.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonObjectBuilder;

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
    // Method to save configuration to a JSON file
    public void saveToJson(String filename) {
        try (JsonWriter writer = Json.createWriter(new FileWriter(filename))) {
            JsonObjectBuilder builder = Json.createObjectBuilder()
                    .add("totalTickets", totalTickets)
                    .add("ticketReleaseRate", ticketReleaseRate)
                    .add("customerRetrievalRate", customerRetrievalRate)
                    .add("maxTicketCapacity", maxTicketCapacity)
                    .add("noVendors", noVendors)
                    .add("noCustomers", noCustomers)
                    .add("remainingTickets", remainingTickets);

            writer.writeObject(builder.build());
            System.out.println("Configuration saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Method to load configuration from a JSON file
    public void loadFromJson(String filename) {
        try (JsonReader reader = Json.createReader(new FileReader(filename))) {
            JsonObject jsonConfig = reader.readObject();

            // Set configuration values from JSON
            setTotalTickets(jsonConfig.getInt("totalTickets", 0));
            setTicketReleaseRate(jsonConfig.getInt("ticketReleaseRate", 0));
            setCustomerRetrievalRate(jsonConfig.getInt("customerRetrievalRate", 0));
            setMaxTicketCapacity(jsonConfig.getInt("maxTicketCapacity", 0));
            setNoVendors(jsonConfig.getInt("noVendors", 0));
            setNoCustomers(jsonConfig.getInt("noCustomers", 0));

            // Directly set remaining tickets to ensure consistency
            this.remainingTickets = jsonConfig.getInt("remainingTickets", 0);

            System.out.println("Configuration loaded from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    public void displayConfig() {
        System.out.println("\n____System Configuration____");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Remaining Tickets: " + remainingTickets);
        System.out.println("Release Rate/sec: " + ticketReleaseRate);
        System.out.println("Retrieval Rate/sec: " + customerRetrievalRate);
        System.out.println("Max Capacity: " + maxTicketCapacity);
        System.out.println("Vendors: " + noVendors);
        System.out.println("Customers: " + noCustomers);
    }
}