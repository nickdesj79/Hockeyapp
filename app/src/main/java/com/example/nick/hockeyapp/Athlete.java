package com.example.nick.hockeyapp;

import java.io.Serializable;

/**
 * Created by Nick on 2017-01-28.
 */

public class Athlete implements Serializable {

    private String fname;
    private String lname;
    private String country;
    private int number;
    private String total_time = "-";

    public Athlete(int number, String fname, String lname, String country) {
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }


    public String getFname() {
        return fname;
    }


    public String getLname() {
        return lname;
    }

    public int getNumber() {
        return number;
    }

    public String getTotal_time() { return total_time; }

    public void setTotal_time(String time) {
        this.total_time = time;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
