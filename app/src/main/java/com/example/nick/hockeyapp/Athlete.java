package com.example.nick.hockeyapp;

import java.io.Serializable;

/**
 * Created by Nick on 2017-01-28.
 */

public class Athlete implements Serializable {

    private String firstName;
    private String lastName;
    private String country;
    private int dossard;
    private double timeNoPenalty = 0;
    private String total_time = "-";
    private int penalty;
    private boolean disqualified = false;

    public Athlete(int number, String fname, String lname, String country) {
        this.firstName = fname;
        this.lastName = lname;
        this.country = country;
        this.dossard = number;
        this.penalty = 0;
    }

    public String getCountry() {
        return country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDossard() {
        return dossard;
    }

    public double getTimeNoPenalty() {
        return timeNoPenalty;
    }

    public double getTimeWithPenalty() {
        return timeNoPenalty + 30000 * penalty;
    }

    public boolean getDisqualified() {
        return disqualified;
    }

    public void setTimeNoPenalty(double time) {
        this.timeNoPenalty = time;
    }

    public void addTime(double time) {this.timeNoPenalty += time;}


    //public void setTotal_time(String time) {
    //this.total_time = time;
    //}

    public void setDossard(int dossard) {
        this.dossard = dossard;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        if (penalty > 2) {
            disqualified = true;
        }
        this.penalty = penalty;
    }

    public String toString() {

        return dossard + "  " + firstName + "  " + lastName + "  " + country.substring(0, 3).toUpperCase() + "  " + timeNoPenalty + 30000 * penalty;
    }


}
