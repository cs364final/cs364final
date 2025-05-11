package com.example;

public class PlayerStats {
    private int stat_id;
    private int game_id;
    private int player_id;
    private int passing_yards;
    private int rushing_yards;
    private int receiving_yards;
    private int touchdowns;

    public PlayerStats() {}

    public PlayerStats(int stat_id, int game_id, int player_id, int passing_yards,
                       int rushing_yards, int receiving_yards, int touchdowns) {
        this.stat_id = stat_id;
        this.game_id = game_id;
        this.player_id = player_id;
        this.passing_yards = passing_yards;
        this.rushing_yards = rushing_yards;
        this.receiving_yards = receiving_yards;
        this.touchdowns = touchdowns;
    }

    public int getStat_id() {
        return stat_id;
    }

    public void setStat_id(int stat_id) {
        this.stat_id = stat_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getPassing_yards() {
        return passing_yards;
    }

    public void setPassing_yards(int passing_yards) {
        this.passing_yards = passing_yards;
    }

    public int getRushing_yards() {
        return rushing_yards;
    }

    public void setRushing_yards(int rushing_yards) {
        this.rushing_yards = rushing_yards;
    }

    public int getReceiving_yards() {
        return receiving_yards;
    }

    public void setReceiving_yards(int receiving_yards) {
        this.receiving_yards = receiving_yards;
    }

    public int getTouchdowns() {
        return touchdowns;
    }

    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }
}
