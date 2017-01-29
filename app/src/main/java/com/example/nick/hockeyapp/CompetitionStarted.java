package com.example.nick.hockeyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by Nick on 2017-01-29.
 */

public class CompetitionStarted extends AppCompatActivity {

    ArrayList<Athlete> myList;
    ListView listView ;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competetion_started_screen);

        myList = (ArrayList<Athlete>) getIntent().getSerializableExtra("athleteList");

    }

    public void ViewAllPlayer(View v) {

        String x = "";
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStarted.this);

        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStarted.this);
        final View promptView = layoutInflater.inflate(R.layout.show_all_athlete_competition, null);

        alertDialogBuilder.setView(promptView);

        listView = (ListView) promptView.findViewById(R.id.allPlayerListView);

        CompetitionAllAthleteAdapter adapter = new CompetitionAllAthleteAdapter(this, R.layout.list_adapter_view_all_athlete,myList);


        AlertDialog alert = alertDialogBuilder.create();


        listView.setAdapter(adapter);
        alert.show();
    }

}
