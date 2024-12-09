package com.example.Backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity


public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public void setRetrievalRate(int retrievalRate) {
        this.retrievalRate = retrievalRate;
    }

    public int getReleaseRate() {
        return releaseRate;
    }

    public void setReleaseRate(int releaseRate) {
        this.releaseRate = releaseRate;
    }

    public int getNoVendors() {
        return noVendors;
    }

    public void setNoVendors(int noVendors) {
        this.noVendors = noVendors;
    }

    public int getNoCustomers() {
        return noCustomers;
    }

    public void setNoCustomers(int noCustomers) {
        this.noCustomers = noCustomers;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int totalTickets;
    private int retrievalRate;
    private int releaseRate;
    private int maxCap;
    private int noVendors;
    private int noCustomers;




}
