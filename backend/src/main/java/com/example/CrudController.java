package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/crud")
public class CrudController {
    private final String url = "jdbc:mysql://138.49.184.47:3306/hansen6239_final_FootballStats";
    private final String user = "siegersma8025";
    private final String password = "A!fSGPd*W8w#DB&u";

    @Autowired
    private DatabaseService db;

    // --------------------- PLAYERS ---------------------
    @PostMapping("/players/create")
    public ResponseEntity<String> createPlayer(@RequestBody Player player) throws SQLException {
        String sql = "INSERT INTO Players (first_name, last_name, nickname, position, birthdate, team_id, player_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, player.getFirstName());
            stmt.setString(2, player.getLastName());
            stmt.setString(3, player.getNickname());
            stmt.setString(4, player.getPosition());
            stmt.setDate(5, player.getBirthDate());
            stmt.setInt(6, player.getTeamId());
            stmt.setInt(7, player.getPlayerId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Player created");
        }
    }

    @GetMapping("/players/read")
    public List<Player> readPlayers() throws SQLException {
        return db.getAllPlayers();
    }

    @PutMapping("/players/update")
    public ResponseEntity<String> updatePlayer(@RequestBody Player player) throws SQLException {
        String sql = "UPDATE Players SET first_name = ?, last_name = ?, nickname = ?, position = ?, birthdate = ?, team_id = ? WHERE player_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, player.getFirstName());
            stmt.setString(2, player.getLastName());
            stmt.setString(3, player.getNickname());
            stmt.setString(4, player.getPosition());
            stmt.setDate(5, player.getBirthDate());
            stmt.setInt(6, player.getTeamId());
            stmt.setInt(7, player.getPlayerId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Player updated");
        }
    }

    @DeleteMapping("/players/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) throws SQLException {
        String sql = "DELETE FROM Players WHERE player_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return ResponseEntity.ok("Player deleted");
        }
    }

    // --------------------- TEAMS ---------------------
    @PostMapping("/teams/create")
    public ResponseEntity<String> createTeam(@RequestBody Team team) throws SQLException {
        String sql = "INSERT INTO Teams (team_name, owner, team_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, team.getTeamName());
            stmt.setString(2, team.getOwner());
            stmt.setInt(3, team.getTeamId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Team created");
        }
    }

    @GetMapping("/teams/read")
    public List<Team> readTeams() throws SQLException {
        return db.getAllTeams();
    }

    @PutMapping("/teams/update")
    public ResponseEntity<String> updateTeam(@RequestBody Team team) throws SQLException {
        String sql = "UPDATE Teams SET team_name = ?, owner = ? WHERE team_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, team.getTeamName());
            stmt.setString(2, team.getOwner());
            stmt.setInt(3, team.getTeamId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Team updated");
        }
    }

    @DeleteMapping("/teams/delete/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable int id) throws SQLException {
        String sql = "DELETE FROM Teams WHERE team_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return ResponseEntity.ok("Team deleted");
        }
    }

    // --------------------- COACH ---------------------
    @PostMapping("/coach/create")
    public ResponseEntity<String> createCoach(@RequestBody Coach coach) throws SQLException {
        String sql = "INSERT INTO Coach (first_name, last_name, role, team_id, coach_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, coach.getFirstName());
            stmt.setString(2, coach.getLastName());
            stmt.setString(3, coach.getRole());
            stmt.setInt(4, coach.getTeamId());
            stmt.setInt(5, coach.getCoachId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Coach created");
        }
    }

    @GetMapping("/coach/read")
    public List<Coach> readCoaches() throws SQLException {
        return db.getAllCoaches();
    }

    @PutMapping("/coach/update")
    public ResponseEntity<String> updateCoach(@RequestBody Coach coach) throws SQLException {
        String sql = "UPDATE Coach SET first_name = ?, last_name = ?, role = ?, team_id = ? WHERE coach_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, coach.getFirstName());
            stmt.setString(2, coach.getLastName());
            stmt.setString(3, coach.getRole());
            stmt.setInt(4, coach.getTeamId());
            stmt.setInt(5, coach.getCoachId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Coach updated");
        }
    }

    @DeleteMapping("/coach/delete/{id}")
    public ResponseEntity<String> deleteCoach(@PathVariable int id) throws SQLException {
        String sql = "DELETE FROM Coach WHERE coach_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return ResponseEntity.ok("Coach deleted");
        }
    }

    // --------------------- GAMES ---------------------
    @PostMapping("/games/create")
    public ResponseEntity<String> createGame(@RequestBody Games game) throws SQLException {
        String sql = "INSERT INTO Games (game_date, home_team_id, away_team_id, home_score, away_score, stadium, game_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, game.getGameDate());
            stmt.setInt(2, game.getHomeTeamId());
            stmt.setInt(3, game.getAwayTeamId());
            stmt.setInt(4, game.getHomeScore());
            stmt.setInt(5, game.getAwayScore());
            stmt.setString(6, game.getStadium());
            stmt.setInt(7, game.getGameId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Game created");
        }
    }

    @GetMapping("/games/read")
    public List<Games> readGames() throws SQLException {
        return db.getAllGames();
    }

    @PutMapping("/games/update")
    public ResponseEntity<String> updateGame(@RequestBody Games game) throws SQLException {
        String sql = "UPDATE Games SET game_date = ?, home_team_id = ?, away_team_id = ?, home_score = ?, away_score = ?, stadium = ? WHERE game_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, game.getGameDate());
            stmt.setInt(2, game.getHomeTeamId());
            stmt.setInt(3, game.getAwayTeamId());
            stmt.setInt(4, game.getHomeScore());
            stmt.setInt(5, game.getAwayScore());
            stmt.setString(6, game.getStadium());
            stmt.setInt(7, game.getGameId());
            stmt.executeUpdate();
            return ResponseEntity.ok("Game updated");
        }
    }

    @DeleteMapping("/games/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable int id) throws SQLException {
        String sql = "DELETE FROM Games WHERE game_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return ResponseEntity.ok("Game deleted");
        }
    }

// --------------------- PLAYER STATS ---------------------

@PostMapping("/playerStats/create")
public ResponseEntity<String> createPlayerStats(@RequestBody PlayerStats stats) throws SQLException {
    String sql = "INSERT INTO PlayerStats (game_id, player_id, passing_yards, rushing_yards, receiving_yards, touchdowns, tackles, sacks, interceptions, stat_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, stats.getGameId());
        stmt.setInt(2, stats.getPlayerId());
        stmt.setInt(3, stats.getPassingYards());
        stmt.setInt(4, stats.getRushingYards());
        stmt.setInt(5, stats.getReceivingYards());
        stmt.setInt(6, stats.getTouchdowns());
        stmt.setInt(7, stats.getTackles());
        stmt.setInt(8, stats.getSacks());
        stmt.setInt(9, stats.getInterceptions());
        stmt.setInt(10, stats.getStatId());
        stmt.executeUpdate();
        return ResponseEntity.ok("Player stats created");
    }
}

@GetMapping("/playerStats/read")
public List<PlayerStats> readPlayerStats() throws SQLException {
    return db.getAllPlayerStats();  // already returns full PlayerStats with new fields
}

@PutMapping("/playerStats/update")
public ResponseEntity<String> updatePlayerStats(@RequestBody PlayerStats stats) throws SQLException {
    String sql = "UPDATE PlayerStats SET game_id = ?, player_id = ?, passing_yards = ?, rushing_yards = ?, receiving_yards = ?, touchdowns = ?, tackles = ?, sacks = ?, interceptions = ? WHERE stat_id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, stats.getGameId());
        stmt.setInt(2, stats.getPlayerId());
        stmt.setInt(3, stats.getPassingYards());
        stmt.setInt(4, stats.getRushingYards());
        stmt.setInt(5, stats.getReceivingYards());
        stmt.setInt(6, stats.getTouchdowns());
        stmt.setInt(7, stats.getTackles());
        stmt.setInt(8, stats.getSacks());
        stmt.setInt(9, stats.getInterceptions());
        stmt.setInt(10, stats.getStatId());
        stmt.executeUpdate();
        return ResponseEntity.ok("Player stats updated");
    }
}

@DeleteMapping("/playerStats/delete/{statId}")
public ResponseEntity<String> deletePlayerStats(@PathVariable int statId) throws SQLException {
    String sql = "DELETE FROM PlayerStats WHERE stat_id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, statId);
        stmt.executeUpdate();
        return ResponseEntity.ok("Player stats deleted");
    }
}


 // --------------------- PLAYER AWARD ---------------------

@PostMapping("/playerAwards/create")
public ResponseEntity<String> createPlayerAward(@RequestBody PlayerAward award) throws SQLException {
    String sql = "INSERT INTO PlayerAward (player_id, award_id, year) VALUES (?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, award.getPlayerId());
        stmt.setInt(2, award.getAwardId());
        stmt.setInt(3, award.getYear());
        stmt.executeUpdate();
        return ResponseEntity.ok("Player award created");
    }
}

@GetMapping("/playerAwards/read")
public List<PlayerAward> readPlayerAwards() throws SQLException {
    return db.getAllPlayerAwards();  // Now returns PlayerAward with name + award info
}

@PutMapping("/playerAwards/update")
public ResponseEntity<String> updatePlayerAward(@RequestBody PlayerAward award) throws SQLException {
    String sql = "UPDATE PlayerAward SET year = ? WHERE player_id = ? AND award_id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, award.getYear());
        stmt.setInt(2, award.getPlayerId());
        stmt.setInt(3, award.getAwardId());
        stmt.executeUpdate();
        return ResponseEntity.ok("Player award updated");
    }
}

@DeleteMapping("/playerAwards/delete")
public ResponseEntity<String> deletePlayerAward(@RequestParam int playerId, @RequestParam int awardId) throws SQLException {
    String sql = "DELETE FROM PlayerAward WHERE player_id = ? AND award_id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, playerId);
        stmt.setInt(2, awardId);
        stmt.executeUpdate();
        return ResponseEntity.ok("Player award deleted");
    }
}

}
