package com.example.nick.hockeyapp;

import android.content.Context;
import android.content.DialogInterface;
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
import java.util.Collections;

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

    private Athlete currentAthlete;
    double currentTime;
    private int currentPlayerPosition = 0;

    private com.example.nick.hockeyapp.Chronometer chrono;
    private Thread t;

    private TextView chronometerView;
    private Context context;

    TextView currentPlayer;






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
                top3AthleteStringList.add("#"+(i+1)+"  "+allPlayerList.get(i).toString());
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

         currentPlayer = (TextView)this.findViewById(R.id.currentPlayer);
        currentPlayer.setText(upcomingAthleteStringList.get(0));


        upcomingAthleteView.setAdapter(incomingPlayerArrayAdapter);
        top3athleteView.setAdapter(top3ArrayAdapter);
    }

    public void start(View v) {

        if(chrono ==  null) {
            chrono = new com.example.nick.hockeyapp.Chronometer(context);
            t = new Thread(chrono);
            t.start();
            chrono.start();
        }

    }

    public void stop(View v) {
        if(chrono !=  null) {
            chrono.stop();
            t.interrupt();
            t = null;
            chrono = null;
        }

        String chronoToConcatenated = chronometerView.getText().toString();

        String[] chronoTab = new String[4];
        chronoTab = chronoToConcatenated.split(":");

        double player_race_time = Integer.parseInt(chronoTab[0])*3600000 + Integer.parseInt(chronoTab[1])*60000 + Integer.parseInt(chronoTab[2])*1000+Integer.parseInt(chronoTab[3]);
        currentTime = player_race_time;

        currentAthlete.addTime(currentTime);

        showPenaltyAlert();


    }

    public void showPenaltyAlert() {
        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStarted.this);
        final View promptView = layoutInflater.inflate(R.layout.penalty_popup, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStarted.this);
        alertDialogBuilder.setView(promptView);


        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                    }
                });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void ViewAllPlayer(View v) {


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

    public void goToNextPlayer() {
        currentPlayerPosition++;
        upcomingAthlete = new ArrayList<>();
        for (int i = currentPlayerPosition; i < currentPlayerPosition +3;i++) {
            upcomingAthlete.add(allPlayerList.get(i));
            upcomingAthleteStringList.add(allPlayerList.get(i).toString());
        }
        upcomingAthleteView = (ListView) findViewById(R.id.upcomingPlayer);

        ArrayAdapter<String> incomingPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                upcomingAthleteStringList );

        TextView currentPlayer = (TextView)this.findViewById(R.id.currentPlayer);
        currentPlayer.setText(upcomingAthleteStringList.get(0));



        upcomingAthleteView.setAdapter(incomingPlayerArrayAdapter);

        currentAthlete = upcomingAthlete.get(0);

        currentPlayer = (TextView)this.findViewById(R.id.currentPlayer);
        currentPlayer.setText(upcomingAthleteStringList.get(0));
    }


    /*public void algo (ArrayList<Athlete> allPlayerList {
        Collections.sort(allPlayerList, allPlayerList.get);
    }*/


    /*public void AlgorithmeTri (ArrayList<Athlete> allPlayerList){
        int temp;
        int x = allPlayerList.size();

        for(int i = 0; i < allPlayerList.size() - 1; i++) {
            for(int j = 1; j < allPlayerList.size() - i; j++) {
                if(allPlayerList[j - 1] > allPlayerList[j]) {
                    temp = allPlayerList[j -1];
                    allPlayerList[j - 1] = allPlayerList[j];
                    allPlayerList[j];
                }
            }
            allPlayerList.
        }
    }*/
    public void updateTime(final String time) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                chronometerView.setText(time);
            }
        });
    }

    public void setPenalty0(View v){
        currentAthlete.setPenalty(0);
        goToNextPlayer();
    }

    public void setPenalty1(View v){
        currentAthlete.setPenalty(1);
        currentAthlete.addTime(30*1000);
        goToNextPlayer();

    }

    public void setPenalty2(View v){
        currentAthlete.setPenalty(2);
        currentAthlete.addTime(60*1000);
        goToNextPlayer();
    }

    public void setPenalty3(View v){
        currentAthlete.setPenalty(3);
        currentAthlete.setTotal_time_float(-1);
        goToNextPlayer();
    }

}


