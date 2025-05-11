package com.example;

public class PlayerAward {
    private int player_id;
    private int award_id;
    private int year;

    public PlayerAward() {}

    public PlayerAward(int player_id, int award_id, int year) {
        this.player_id = player_id;
        this.award_id = award_id;
        this.year = year;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getAward_id() {
        return award_id;
    }

    public void setAward_id(int award_id) {
        this.award_id = award_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
