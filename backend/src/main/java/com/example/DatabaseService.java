package com.example;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class DatabaseService {

    private final String url = "jdbc:mysql://138.49.184.47:3306/hansen6239_final_FootballStats";
    private final String user = "siegersma8025";
    private final String password = "A!fSGPd*W8w#DB&u";

    public List<Map<String, Object>> runQuery(String sql) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        }
        return results;
    }
}
