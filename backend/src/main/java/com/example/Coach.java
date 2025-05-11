package com.example;

public class Coach {

    private int coachId;
    private String firstName;
    private String lastName;
    private String role;
    private int teamId;


    public Coach(int coachId, String firstName, String lastName, String role, int teamId) {
        this.coachId = coachId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.teamId = teamId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}

