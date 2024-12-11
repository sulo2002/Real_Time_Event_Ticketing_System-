package com.example.Backend.Controller;


import com.example.Backend.services.TicketingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketing")
@CrossOrigin

public class TicketController {
    @Autowired
    private TicketingServiceImpl ticketingService;

    @PostMapping("/start")
    @ResponseBody
    public void startSys() {
        ticketingService.startSystem();
    }
    @PostMapping("/stop")
    @ResponseBody
    public void stopSys() {
        ticketingService.stopSystem();
    }

}


