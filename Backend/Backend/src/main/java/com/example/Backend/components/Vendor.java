package com.example.Backend.components;

import java.lang.module.Configuration;
import java.util.logging.Logger;

import com.example.Backend.services.ConfigServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
@Scope("prototype")
public class Vendor implements Runnable {
    private static final Logger logger = Logger.getLogger(Vendor.class.getName());
    private final Ticketpool ticketPool;
    private final int releaseRate;
    private final int vendorId;
    private final ConfigServiceImpl configService;

    public Vendor(Ticketpool ticketpool, int releaseRate, int vendorId, ConfigServiceImpl configService) {
        this.ticketPool = ticketpool;
        this.releaseRate = releaseRate;
        this.vendorId = vendorId;
        this.configService = configService;
    }


    @Override
    public void run() {
        int ticketCount = 1;
        try {
            while (configService.getRemainingTickets() > 0) {
                int actualReleaseRate = Math.min(releaseRate, configService.getRemainingTickets());
                if (actualReleaseRate > 0) {
                    for (int i = 0; i < actualReleaseRate; i++) {
                        ticketPool.addTicket("Ticket-V" + vendorId + "-" + ticketCount++);
                    }
                    configService.decrementRemainingTickets(actualReleaseRate);
                    logger.info("Vendor " + vendorId + " released " + actualReleaseRate +
                            " tickets. Remaining total: " + configService.getRemainingTickets());
                }
                Thread.sleep(1000);
            }
            logger.info("Vendor " + vendorId + " finished - no more tickets to release");
        } catch (InterruptedException e) {
            logger.info("Vendor " + vendorId + " Interrupted");
        }
    }
}
