package com.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QueryController {

    @Autowired
    private DatabaseService dbService;

    @PostMapping("/query")
    public ResponseEntity<?> runSql(@RequestBody Map<String, String> body) {
        String sql = body.get("sql");

        try {
            List<Map<String, Object>> result = dbService.runQuery(sql);
            return ResponseEntity.ok(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Query failed: " + e.getMessage());
        }
    }
}
