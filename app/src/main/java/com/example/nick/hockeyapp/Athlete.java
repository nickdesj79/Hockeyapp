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
    private boolean dnf = false;

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

    public void addTime(double time) {
        this.timeNoPenalty += time;
    }


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

    public void setDidNotFinish(boolean etat) {
        this.dnf = true;
    }

    public String toMenuString() {
        return dossard + " - " + firstName + "  " + lastName + " - " + country.substring(0, 3).toUpperCase();
    }


    public String toString() {

        double time = getTimeWithPenalty();
        int seconds = (int) ((time / 1000) % 60);
        int minutes = (int) ((time / Chronometer.MILLIS_TO__MINUTES) % 60);
        int hours = (int) ((time / Chronometer.MILLIS_TO_HOURS) % 24);
        int millis = (int) time % 1000;


        if (disqualified) {
            return dossard + " - " + firstName + "  " + lastName + " - " + country.substring(0, 3).toUpperCase() + " - DISQUALIFIED";
        } else if (getTimeWithPenalty() == 0) {
            return dossard + " - " + firstName + "  " + lastName + " - " + country.substring(0, 3).toUpperCase() + " - NA";
        } else if (dnf) {
            return dossard + " - " + firstName + "  " + lastName + " - " + country.substring(0, 3).toUpperCase() + " - Did not finish";
        } else {
            return dossard + " - " + firstName + "  " + lastName + " - " + country.substring(0, 3).toUpperCase() + " - " + String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis);
        }
    }

}
