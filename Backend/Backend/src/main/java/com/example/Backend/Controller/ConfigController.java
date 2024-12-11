package com.example.Backend.Controller;

import com.example.Backend.model.Config;
import com.example.Backend.services.ConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form")
@CrossOrigin
public class ConfigController {
    @Autowired
    private ConfigServiceImpl configService;

    @PostMapping("/insert")
    public String addConfig(@RequestBody Config config) {
        configService.saveConfiguration(config);
        return "Success";
    }
    @GetMapping("/totTickets")
    public int getStatus() {
        Config config = configService.getLatestConfig(); // Assuming you have a method to fetch the latest config
        if (config != null) {
            return config.getTotalTickets();
        } else {
            return 0;
        }

    }
    @GetMapping("/numvendors")
    public int numvendors() {
        Config config = configService.getLatestConfig(); // Assuming you have a method to fetch the latest config
        if (config != null) {
            return config.getNoVendors();
        } else {
            return 0;
        }
    }
    @GetMapping("/numcustomers")
    public int numcustomers() {
        Config config = configService.getLatestConfig(); // Assuming you have a method to fetch the latest config
        if (config != null) {
            return config.getNoCustomers();
        } else {
            return 0;
        }
    }
}
