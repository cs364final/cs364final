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
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private DatabaseService dbService;

    
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    @GetMapping
    public ResponseEntity<?> getAllGames() {
        try {
            List<Games> games = dbService.getAllGames();
            return ResponseEntity.ok(games);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching teams: " + e.getMessage());
        }
    }
   
}


