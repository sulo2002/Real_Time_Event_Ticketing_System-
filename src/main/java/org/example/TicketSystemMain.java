package org.example;

import java.util.*;
import java.util.logging.*;

public class TicketSystemMain {
    private static final Logger LOGGER = Logger.getLogger(TicketSystemMain.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static Configuration config = new Configuration();
    private static TicketPool pool;
    private static List<Thread> vendorThreads = new ArrayList<>();
    private static List<Thread> customerThreads = new ArrayList<>();
    private static List<Vendor> vendors = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static boolean isRunning = false;

    public static void main(String[] args) {
        setupLogging();
        showMenu();
        processCommands();
    }

    private static void setupLogging() {
        Logger.getLogger("").getHandlers()[0].setFormatter(new SimpleFormatter());
    }

    private static void showMenu() {
        System.out.println("""
            ....Ticket Booking System.....
            1. Start System
            2. Stop System
            3. Configure System
            4. Display Configuration
            5. Save Configuration
            6.Load Configuration
            7.Exit
            """);
    }

    private static void processCommands() {
        while (true) {
            System.out.print("Enter command: ");
            String cmd = scanner.nextLine();

            if ("1".equals(cmd)) {
                startSystem();
            } else if ("2".equals(cmd)) {
                stopSystem();
            } else if ("3".equals(cmd)) {
                configureSystem();
            } else if ("4".equals(cmd)) {
                config.displayConfig();
            } else if ("5".equals(cmd)) {
                saveConfiguration();
            } else if ("6".equals(cmd)) {
                loadConfiguration();
            } else if ("7".equals(cmd)) {
                stopSystem();
                break;
            } else {
                System.out.println("Invalid command");
            }
        }
    }
    private static void saveConfiguration() {
        System.out.print("Enter filename to save configuration (e.g., config.json): ");
        String filename = scanner.nextLine();
        config.saveToJson(filename);
    }

    private static void loadConfiguration() {
        System.out.print("Enter filename to load configuration (e.g., config.json): ");
        String filename = scanner.nextLine();
        config.loadFromJson(filename);
    }

    private static void startSystem() {
        if (isRunning) {
            System.out.println("System already running");
            return;
        }

        if (config.getTotalTickets() <= 0) {
            configureSystem();
        }

        pool = new TicketPool(config.getMaxTicketCapacity());
        int ratePerVendor = Math.max(1, config.getTicketReleaseRate() / config.getNoVendors());

        // Start vendor threads
        for (int i = 1; i <= config.getNoVendors(); i++) {
            Vendor vendor = new Vendor("V" + i, pool, ratePerVendor, config);
            Thread vendorThread = new Thread(vendor);
            vendors.add(vendor);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        // Start customer threads
        for (int i = 1; i <= config.getNoCustomers(); i++) {
            Customer customer = new Customer("C" + i, pool, 1000 / config.getCustomerRetrievalRate());
            Thread customerThread = new Thread(customer);
            customers.add(customer);
            customerThreads.add(customerThread);
            customerThread.start();
        }

        isRunning = true;
        LOGGER.info("System started - Total tickets: " + config.getTotalTickets());
    }

    private static void stopSystem() {
        if (!isRunning) return;

        // Stop all vendors and customers
        for(Vendor vendor : vendors) {
            vendor.stop();
        }
        for(Customer customer : customers) {
            customer.stop();
        }

        // Interrupt all threads
        for(Thread thread : vendorThreads) {
            thread.interrupt();
        }
        for(Thread thread : customerThreads) {
            thread.interrupt();
        }

        // Wait for threads to finish
        try {
            for (Thread t : vendorThreads) {
                t.join(1000);
            }
            for (Thread t : customerThreads) {
                t.join(1000);
            }
        } catch (InterruptedException e) {
            LOGGER.warning("Shutdown interrupted");
        }

        // Clear lists
        vendorThreads.clear();
        customerThreads.clear();
        vendors.clear();
        customers.clear();

        isRunning = false;
        LOGGER.info("System stopped");
    }

    private static void configureSystem() {
        System.out.println("\n____System Configuration____");
        System.out.print("Total tickets: ");
        config.setTotalTickets(scanner.nextInt());
        System.out.print("Release rate/sec: ");
        config.setTicketReleaseRate(scanner.nextInt());
        System.out.print("Retrieval rate/sec: ");
        config.setCustomerRetrievalRate(scanner.nextInt());
        System.out.print("Max capacity: ");
        config.setMaxTicketCapacity(scanner.nextInt());
        System.out.print("Number of vendors: ");
        config.setNoVendors(scanner.nextInt());
        System.out.print("Number of customers: ");
        config.setNoCustomers(scanner.nextInt());
        scanner.nextLine(); // Clear buffer
    }
}