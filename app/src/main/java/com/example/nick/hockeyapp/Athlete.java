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
    private double total_time_float_in_msec = 0;
    private String total_time = "-";
    private int penalty;

    public Athlete(int number, String fname, String lname, String country) {
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.number = number;
        this.penalty = 0;
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

    public double getTotal_time_float() {
        return total_time_float_in_msec;
    }

    public void setTotal_time_float(double total_time_float) {
        this.total_time_float_in_msec = total_time_float;
    }

    public void addTime(double t) {
        this.total_time_float_in_msec += t;
    }

    public String getTotal_time() { return total_time; }

    public void setTotal_time(String time) {
        this.total_time = time;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPenalty(){ return penalty; }

    public void setPenalty(int penalty){ this.penalty = this.penalty + penalty; }




    public String toString() {

        return number + "  " + fname +"  "+lname + "  "+ country.substring(0,3).toUpperCase() + "  "+total_time;
    }


}
