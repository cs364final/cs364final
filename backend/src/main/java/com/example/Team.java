package com.example;

public class Team {

    private int teamId;
    private String teamName;
    private String owner;
    private String record;

    public Team(int teamId, String teamName, String owner, String record) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.owner = owner;
        this.record = record;
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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }


}

