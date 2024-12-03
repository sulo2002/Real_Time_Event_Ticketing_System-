package org.example;

import java.io.*;
import java.util.Scanner;
import java.util.logging.*;

public class TicketSystemConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(TicketSystemConfig.class.getName());

    // Configuration parameters
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Constructor
    public TicketSystemConfig() {
        // Default constructor
    }

    // Method to prompt and validate user inputs
    public void promptForConfiguration() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Total Tickets Input
                System.out.print("Enter Total Number of Tickets (1-1000): ");
                totalTickets = Integer.parseInt(scanner.nextLine());
                if (totalTickets < 1 || totalTickets > 1000) {
                    throw new IllegalArgumentException("Total tickets must be between 1 and 1000");
                }

                // Ticket Release Rate Input
                System.out.print("Enter Ticket Release Rate (1-100 tickets/second): ");
                ticketReleaseRate = Integer.parseInt(scanner.nextLine());
                if (ticketReleaseRate < 1 || ticketReleaseRate > 100) {
                    throw new IllegalArgumentException("Release rate must be between 1 and 100");
                }

                // Customer Retrieval Rate Input
                System.out.print("Enter Customer Retrieval Rate (1-100 tickets/second): ");
                customerRetrievalRate = Integer.parseInt(scanner.nextLine());
                if (customerRetrievalRate < 1 || customerRetrievalRate > 100) {
                    throw new IllegalArgumentException("Retrieval rate must be between 1 and 100");
                }

                // Max Ticket Capacity Input
                System.out.print("Enter Maximum Ticket Capacity (1-500): ");
                maxTicketCapacity = Integer.parseInt(scanner.nextLine());
                if (maxTicketCapacity < 1 || maxTicketCapacity > 500) {
                    throw new IllegalArgumentException("Max capacity must be between 1 and 500");
                }

                // If all inputs are valid, break the loop
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Serialization methods
    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            LOGGER.info("Configuration saved successfully to " + filename);
        } catch (IOException e) {
            LOGGER.severe("Error saving configuration: " + e.getMessage());
        }
    }

    public static TicketSystemConfig loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (TicketSystemConfig) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.severe("Error loading configuration: " + e.getMessage());
            return null;
        }
    }

    // Getters
    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
}