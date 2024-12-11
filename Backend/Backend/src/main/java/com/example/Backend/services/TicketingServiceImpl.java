package com.example.Backend.services;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.Backend.components.Ticketpool;
import com.example.Backend.components.Vendor;
import com.example.Backend.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.components.Customer;

@Service
public class TicketingServiceImpl implements TicketingService {
    private static final Logger logger = Logger.getLogger(TicketingServiceImpl.class.getName());
    private boolean systemRunning = false;
    private List<Thread> vendorThreads = new ArrayList<>();
    private List<Thread> customerThreads = new ArrayList<>();

    @Autowired
    private ConfigServiceImpl configService;

    @Autowired
    private Ticketpool ticketpool;

    @Autowired
    private Vendor vendorComponent;

    @Autowired
    private Customer customerComponent;

    public void startSystem() {
        if (systemRunning) {
            logger.info("System is already running.");
            return;
        }

        Config currentConfig = configService.getLatestConfig();
        if (!validateConfiguration(currentConfig)) {
            logger.warning("Invalid configuration");
            return;
        }

        configService.setRemainingTickets(currentConfig.getTotalTickets());
        ticketpool.init(currentConfig.getMaxCap(), currentConfig.getTotalTickets());

        int ratePerVendor = Math.max(1, currentConfig.getReleaseRate() / currentConfig.getNoVendors());

        for (int i = 0; i < currentConfig.getNoVendors(); i++) {
            Vendor vendor = new Vendor(ticketpool, ratePerVendor, i + 1, configService);
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        for (int i = 0; i < currentConfig.getNoCustomers(); i++) {
            Customer customer = new Customer(ticketpool, 1000 / currentConfig.getRetrievalRate(), i + 1);
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread);
            customerThread.start();
        }

        systemRunning = true;
        logger.info("System started with " + currentConfig.getTotalTickets() + " total tickets");
    }

    private boolean validateConfiguration(Config config) {
        if (config == null) return false;
        if (config.getTotalTickets() <= 0) return false;
        if (config.getMaxCap() <= 0 || config.getMaxCap() > config.getTotalTickets()) return false;
        if (config.getNoVendors() <= 0) return false;
        if (config.getNoCustomers() <= 0) return false;
        return true;
    }

    public void stopSystem() {
        if (!systemRunning) {
            logger.info("System is not running.");
            return;
        }

        for (Thread thread : vendorThreads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }

        for (Thread thread : customerThreads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }

        systemRunning = false;
        ticketpool.stopSystem();
        vendorThreads.clear();
        customerThreads.clear();

        logger.info("System stopped successfully.");
    }
}