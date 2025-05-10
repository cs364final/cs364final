package com.example;

public class PlayerAward {
    private int playerId;
    private int awardId;
    private int year;
    private String name;
    private String award;

    public PlayerAward(String award, int awardId, String name, int playerId, int year) {
        this.award = award;
        this.awardId = awardId;
        this.name = name;
        this.playerId = playerId;
        this.year = year;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
