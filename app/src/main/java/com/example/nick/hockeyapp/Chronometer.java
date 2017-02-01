package com.example.nick.hockeyapp;

import android.content.Context;

/**
 * Created by Nick on 2017-02-01.
 */

public class Chronometer implements Runnable {

    public static final long MILLIS_TO__MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;

    private Context context;
    private long startTime;
    private boolean isRunning;

    public Chronometer(Context context) {
        this.context = context;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }


    @Override
    public void run() {

        while(isRunning) {
            long since = System.currentTimeMillis() - startTime;

            int seconds = (int)((since / 1000)%60);
            int minutes = (int) ((since/MILLIS_TO__MINUTES)%60);
            int hours = (int) ((since/MILLIS_TO_HOURS)%24);
            int millis = (int) since%1000;

            ((CompetitionStarted)context).updateTime(String.format("%02d:%02d:%02d:%03d",hours,minutes,hours,millis));

        }
    }
}
