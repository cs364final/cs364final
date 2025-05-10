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
            String teamName = getTeamNameById(teamId);
            coaches.add(new Coach(coachId, fname, lname, role, teamId, teamName));
        }
    
        return coaches;
    }

    public List<Games> getAllGames() throws SQLException {
        List<Games> games = new ArrayList<>();
        String sql = "SELECT * FROM Games;";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
    
        while (rs.next()) {
            int gamesId = rs.getInt("game_id");
            Date gameDate = rs.getDate("game_date");
            int homeTeamId = rs.getInt("home_team_id");
            int awayTeamId = rs.getInt("away_team_id");
            int homeScore = rs.getInt("home_score");
            int awayScore = rs.getInt("away_score");
            String stadium = rs.getString("stadium");

            String homeTeamName = getTeamNameById(homeTeamId);
            String awayTeamName = getTeamNameById(awayTeamId);


            games.add(new Games(gamesId, gameDate, homeTeamId, awayTeamId, homeScore, awayScore, stadium, homeTeamName, awayTeamName));
        }
    
        return games;
    }

    public List<Player> getAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players;";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
    
        while (rs.next()) {
            int playerId = rs.getInt("player_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String nickName = rs.getString("nickname");
            String position = rs.getString("position");
            Date birthDate = rs.getDate("birthdate");
            int teamId = rs.getInt("team_id");
        
            String teamName = getTeamNameById(teamId);

            players.add(new Player(playerId, firstName, lastName, nickName, position, birthDate, teamId, teamName));
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
        String sql = "SELECT * FROM PlayerStats;";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
    
        while (rs.next()) {
            int statId = rs.getInt("stat_id");
            int gameId = rs.getInt("game_id");
            int playerId = rs.getInt("player_id");
            int passingYards = rs.getInt("passing_yards");
            int rushingYards = rs.getInt("rushing_yards");
            int receivingYards = rs.getInt("receiving_yards");
            int touchdowns = rs.getInt("touchdowns");
           
            String name = getPlayerNameById(playerId);

            playerStats.add(new PlayerStats(name, gameId, passingYards, playerId, receivingYards, rushingYards, statId, touchdowns));
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
        String sql = "SELECT * FROM PlayerAward;";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
    
        while (rs.next()) {
            int playerId = rs.getInt("player_id");
            int awardId = rs.getInt("award_id");
            int year = rs.getInt("year");
            
            String name = getPlayerNameById(playerId);
            String award = getAwardById(awardId);


            playerAwards.add(new PlayerAward(award, awardId, name, playerId, year));
        }
    
        return playerAwards;
    }
    
}
