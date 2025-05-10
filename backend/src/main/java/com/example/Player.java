package com.example;

import java.sql.Date;


public class Player {
    
    private int playerId;
    private String firstName;
    private String lastName;
    private String nickName;
    private String position;
    private Date birthDate;
    private int teamId;
    private String teamName;

    public Player(int playerId, String firstName, String lastName, String nickName, String position, Date birthDate, int teamId, String teamName){
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.position = position;
        this.birthDate = birthDate;
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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









}
