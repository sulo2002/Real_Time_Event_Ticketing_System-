package com.example.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private int totalTickets;
    private int remainingTickets;
    private int availableTickets;
    private int soldTickets;
    private boolean systemRunning;

    public double getProgressPercentage() {
        if (totalTickets == 0) return 0;
        return ((double) soldTickets / totalTickets) * 100;
    }


    public boolean isAllTicketsSold() {
        return soldTickets == totalTickets;
    }


    public double getEfficiencyRate() {
        if (totalTickets == 0) return 0;
        long currentTime = System.currentTimeMillis();

        return (double) soldTickets / totalTickets;
    }


    public String getSystemHealth() {
        if (!systemRunning)
            return "STOPPED";
        return "Running";
    }

}
