package com.example.Backend.services;

import com.example.Backend.model.Config;

public interface ConfigServices {

    Config saveConfiguration(Config config);
    Config getLatestConfig();
    void decrementRemainingTickets(int count);
}