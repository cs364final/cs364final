package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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

    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM Teams;";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
    
        while (rs.next()) {
            int teamId = rs.getInt("team_id");
            String name = rs.getString("team_name");
            String owner = rs.getString("owner");
            String record = rs.getString("record");
            teams.add(new Team(teamId, name, owner, record));
        }
    
        return teams;
    }

    public String getTeamNameById(int teamId) throws SQLException {
        String sql = "SELECT team_name FROM Teams WHERE team_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, teamId);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return rs.getString("team_name");
            } else {
                return null;
            }
        }
    }

    public List<Coach> getAllCoaches() throws SQLException {
        List<Coach> coaches = new ArrayList<>();
        String sql = "SELECT * FROM Coach;";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
    
        while (rs.next()) {
            int coachId = rs.getInt("coach_id");
            String fname = rs.getString("first_name");
            String lname = rs.getString("last_name");
            String role = rs.getString("role");
            int teamId = rs.getInt("team_id");
            coaches.add(new Coach(coachId, fname, lname, role, teamId));
        }
    
        return coaches;
    }

    public List<Games> getAllGames() throws SQLException {
    List<Games> games = new ArrayList<>();
    String sql = """
        SELECT g.game_id, g.game_date, g.home_team_id, g.away_team_id,
               g.home_score, g.away_score, g.stadium,
               ht.team_name AS home_team_name,
               at.team_name AS away_team_name
        FROM Games g
        JOIN Teams ht ON g.home_team_id = ht.team_id
        JOIN Teams at ON g.away_team_id = at.team_id
        ORDER BY g.game_id;
    """;

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int gameId = rs.getInt("game_id");
            Date gameDate = rs.getDate("game_date");
            int homeTeamId = rs.getInt("home_team_id");
            int awayTeamId = rs.getInt("away_team_id");
            int homeScore = rs.getInt("home_score");
            int awayScore = rs.getInt("away_score");
            String stadium = rs.getString("stadium");
            String homeTeamName = rs.getString("home_team_name");
            String awayTeamName = rs.getString("away_team_name");

            games.add(new Games(gameId, gameDate, homeTeamId, awayTeamId,
                                homeScore, awayScore, stadium,
                                homeTeamName, awayTeamName));
        }
    }

    return games;
}


    public List<Player> getAllPlayers() throws SQLException {
    List<Player> players = new ArrayList<>();
    String sql = """
        SELECT p.player_id, p.first_name, p.last_name, p.nickname,
               p.position, p.birthdate, p.team_id, t.team_name
        FROM Players p
        JOIN Teams t ON p.team_id = t.team_id
        ORDER BY p.player_id
    """;

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            players.add(new Player(
                rs.getInt("player_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("nickname"),
                rs.getString("position"),
                rs.getDate("birthdate"),
                rs.getInt("team_id"),
                rs.getString("team_name")
            ));
        }
    }

    return players;
}


    public String getPlayerNameById(int playerId) throws SQLException {
        String sql = "SELECT last_name, first_name FROM Players WHERE player_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return rs.getString("first_name") + " " + rs.getString("last_name");
            } else {
                return null;
            }
        }
    }



public List<PlayerStats> getAllPlayerStats() throws SQLException {
    List<PlayerStats> playerStats = new ArrayList<>();

    String sql = """
        SELECT ps.stat_id, ps.game_id, ps.player_id, ps.passing_yards, ps.rushing_yards,
               ps.receiving_yards, ps.touchdowns, ps.tackles, ps.sacks, ps.interceptions,
               p.first_name, p.last_name
        FROM PlayerStats ps
        JOIN Players p ON ps.player_id = p.player_id
        ORDER BY ps.stat_id;
    """;

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int statId = rs.getInt("stat_id");
            int gameId = rs.getInt("game_id");
            int playerId = rs.getInt("player_id");
            int passingYards = rs.getInt("passing_yards");
            int rushingYards = rs.getInt("rushing_yards");
            int receivingYards = rs.getInt("receiving_yards");
            int touchdowns = rs.getInt("touchdowns");
            int tackles = rs.getInt("tackles");
            int sacks = rs.getInt("sacks");
            int interceptions = rs.getInt("interceptions");

            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String name = (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");

            playerStats.add(new PlayerStats(name.trim(), gameId, passingYards, playerId,
                receivingYards, rushingYards, statId, touchdowns, tackles, sacks, interceptions));
        }
    }

    return playerStats;
}



    public String getAwardById(int awardId) throws SQLException {
        String sql = "SELECT award_name FROM Award WHERE award_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, awardId);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return rs.getString("award_name");
            } else {
                return null;
            }
        }
    }

   public List<PlayerAward> getAllPlayerAwards() throws SQLException {
    List<PlayerAward> playerAwards = new ArrayList<>();

    String sql = """
        SELECT pa.player_id, pa.award_id, pa.year,
               p.first_name, p.last_name,
               a.award_name
        FROM PlayerAward pa
        JOIN Players p ON pa.player_id = p.player_id
        JOIN Award a ON pa.award_id = a.award_id
        ORDER BY pa.player_id, pa.award_id;
    """;

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int playerId = rs.getInt("player_id");
            int awardId = rs.getInt("award_id");
            int year = rs.getInt("year");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String name = (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
            String award = rs.getString("award_name");

            PlayerAward pa = new PlayerAward(award, awardId, name.trim(), playerId, year);
            playerAwards.add(pa);
        }
    }

    return playerAwards;
}

 
}
