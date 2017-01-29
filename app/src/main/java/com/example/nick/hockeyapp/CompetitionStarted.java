package com.example.nick.hockeyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Nick on 2017-01-29.
 */

public class CompetitionStarted extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competetion_started_screen);

        ArrayList<Athlete> myList = (ArrayList<Athlete>) getIntent().getSerializableExtra("athleteList");



    }

}
