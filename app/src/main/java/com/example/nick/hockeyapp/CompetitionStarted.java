package com.example.nick.hockeyapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;
import android.app.AlertDialog;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nick on 2017-01-29.
 */

public class CompetitionStarted extends AppCompatActivity {

    ArrayList<Athlete> allPlayerList;
    ArrayList<String> allPlayerListString;

    ArrayList<Athlete> upcomingAthlete;
    ArrayList<String> upcomingAthleteStringList;

    ArrayList<Athlete> top3AthleteList;
    ArrayList<String> top3AthleteStringList;

    ListView listView ;
    ListView upcomingAthleteView;
    ListView top3athleteView;

    private com.example.nick.hockeyapp.Chronometer chrono;
    private Thread t;

    private TextView chronometerView;
    private Context context;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competetion_started_screen);


        chronometerView = (TextView) findViewById(R.id.chronometer1);
        context = this;

        allPlayerList = (ArrayList<Athlete>) getIntent().getSerializableExtra("athleteList");
        allPlayerListString = new ArrayList<>();

        for(int i = 0; i < allPlayerList.size();i++) {
            allPlayerListString.add(allPlayerList.get(i).toString());
        }


        //initialize initial incoming players lists
        upcomingAthlete = new ArrayList<>();
        upcomingAthleteStringList = new ArrayList<>();

        //initialize top 3 players lists
        top3AthleteList = new ArrayList<>();
        top3AthleteStringList = new ArrayList<>();

        for (int i = 0; i<3;i++) {
            if (allPlayerList.get(i) != null) {
                upcomingAthlete.add(allPlayerList.get(i));
                upcomingAthleteStringList.add(allPlayerList.get(i).toString());
                top3AthleteStringList.add("#"+i+"  "+allPlayerList.get(i).toString());
            }
        }
        currentAthlete = upcomingAthlete.get(0);
        upcomingAthleteView = (ListView) findViewById(R.id.upcomingPlayer);
        top3athleteView = (ListView) findViewById(R.id.topplayer);

        ArrayAdapter<String> incomingPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                upcomingAthleteStringList );

        //Since there is no top player list on the creation of this view,just take top 3 incoming players.
        ArrayAdapter<String> top3ArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                top3AthleteStringList );

        TextView currentPlayer = (TextView)this.findViewById(R.id.currentPlayer);
        currentPlayer.setText(upcomingAthleteStringList.get(0));


        upcomingAthleteView.setAdapter(incomingPlayerArrayAdapter);
        top3athleteView.setAdapter(top3ArrayAdapter);
    }

    public void start(View v) {


        chrono = new com.example.nick.hockeyapp.Chronometer(context);



    }

    public void stop(View v) {

    }

    public void ViewAllPlayer(View v) {

        String x = "";
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStarted.this);

        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStarted.this);
        final View promptView = layoutInflater.inflate(R.layout.show_all_athlete_competition, null);

        alertDialogBuilder.setView(promptView);

        listView = (ListView) promptView.findViewById(R.id.allPlayerListView);

        ArrayAdapter<String> allPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,allPlayerListString
                 );

        AlertDialog alert = alertDialogBuilder.create();


        listView.setAdapter(allPlayerArrayAdapter);
        alert.show();
    }

    public void updateTime(final String time) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                chronometerView.setText(time);
            }
        });
    }

    public void setPenalty0(){ currentAthlete.setPenalty(0); }

    public void setPenalty1(){ currentAthlete.setPenalty(1); }

    public void setPenalty2(){ currentAthlete.setPenalty(2); }

    public void setPenalty3(){ currentAthlete.setPenalty(3); }

}
