package com.example;

public class PlayerStats {
    private int statId;
    private int gameId;
    private int playerId;
    private int passingYards;
    private int rushingYards;
    private int receivingYards;
    private int touchdowns;
    private String name;
   

    public PlayerStats(String name, int gameId, int passingYards, int playerId, int receivingYards, int rushingYards, int statId, int touchdowns) {
        this.name = name;
        this.gameId = gameId;
        this.passingYards = passingYards;
        this.playerId = playerId;
        this.receivingYards = receivingYards;
        this.rushingYards = rushingYards;
        this.statId = statId;
        this.touchdowns = touchdowns;
    }

    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPassingYards() {
        return passingYards;
    }

    public void setPassingYards(int passingYards) {
        this.passingYards = passingYards;
    }

    public int getRushingYards() {
        return rushingYards;
    }

    public void setRushingYards(int rushingYards) {
        this.rushingYards = rushingYards;
    }

    public int getReceivingYards() {
        return receivingYards;
    }

    public void setReceivingYards(int receivingYards) {
        this.receivingYards = receivingYards;
    }

    public int getTouchdowns() {
        return touchdowns;
    }

    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
