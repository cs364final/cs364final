package com.example;

public class Team {

    private int teamId;
    private String teamName;
    private String owner;

    public Team(int teamId, String teamName, String owner) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.owner = owner;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}

