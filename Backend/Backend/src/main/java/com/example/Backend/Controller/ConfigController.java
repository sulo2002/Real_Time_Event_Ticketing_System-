package com.example.Backend.Controller;

import com.example.Backend.model.Config;
import com.example.Backend.services.ConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configure")
@CrossOrigin
public class ConfigController {
    @Autowired
    private ConfigServiceImpl configService;

    private  static Config config;

    @PostMapping("/add")
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

}
