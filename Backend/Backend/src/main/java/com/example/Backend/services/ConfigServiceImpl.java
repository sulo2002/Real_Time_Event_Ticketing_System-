package com.example.Backend.services;

import com.example.Backend.model.Config;
import com.example.Backend.repository.ConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfigServiceImpl implements ConfigServices {
    @Autowired
    private ConfigRepo configRepo;

    private int remainingTickets;

    public void setRemainingTickets(int remainingTickets) {
        this.remainingTickets = remainingTickets;
    }

    public int getRemainingTickets() {
        return this.remainingTickets;
    }

    @Override
    public Config saveConfiguration(Config config) {
        this.remainingTickets = config.getTotalTickets();
        return configRepo.save(config);
    }

    @Override
    public Config getLatestConfig() {
        return configRepo.findTopByOrderByIdDesc();
    }

    @Override
    public void decrementRemainingTickets(int count) {
        this.remainingTickets = Math.max(0, this.remainingTickets - count);
    }
}



