package com.example;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/playerStats")
public class PlayerStatsController {

    @Autowired
    private DatabaseService dbService;

    
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    @GetMapping
    public ResponseEntity<?> getAllPlayerStats() {
        try {
            List<PlayerStats> playerStats = dbService.getAllPlayerStats();
            return ResponseEntity.ok(playerStats);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching teams: " + e.getMessage());
        }
    }
   
}

