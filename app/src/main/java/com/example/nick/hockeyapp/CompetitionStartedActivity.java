package com.example.nick.hockeyapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.AlertDialog;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nick on 2017-01-29.
 */

public class CompetitionStartedActivity extends AppCompatActivity {

    ArrayList<Athlete> allPlayerList;
    ArrayList<String> allPlayerListStringNotSorted;
    ArrayList<String> allPlayerListString;
    ArrayList<Athlete> sortedAllPlayerList;


    ArrayList<String> listeDeTousLesDescentesString;
    ArrayList<Athlete> listeDeTousLesDescentes;

    ArrayList<Athlete> upcomingAthlete;
    ArrayList<String> upcomingAthleteStringList;
    ArrayList<String> top3AthleteStringList;


    ListView listView;
    ListView upcomingAthleteView;
    ListView top3athleteView;

    private Athlete currentAthlete;
    private Athlete lastAthlete;
    double currentTime;
    private int currentPlayerPosition = 0;

    private com.example.nick.hockeyapp.Chronometer chrono;
    private Thread t;

    private TextView chronometerView;
    private Context context;

    TextView currentPlayer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competetion_started);

        chronometerView = (TextView) findViewById(R.id.chronometer1);
        context = this;

        allPlayerList = (ArrayList<Athlete>) getIntent().getSerializableExtra("athleteList");
        sortedAllPlayerList = (ArrayList<Athlete>) getIntent().getSerializableExtra("athleteList");
        //TODO: vérifier si necessaire.
        allPlayerListString = new ArrayList<>();

        listeDeTousLesDescentesString = new ArrayList<>();
        listeDeTousLesDescentes = new ArrayList<>();
        allPlayerListStringNotSorted = new ArrayList<>();

        for(int i=0; i<2;i++) {
            for(int j = 0; j < allPlayerList.size();j++) {
                listeDeTousLesDescentes.add(allPlayerList.get(j));
                listeDeTousLesDescentesString.add(allPlayerList.get(j).toString());
            }
        }

        for (int i = 0; i < allPlayerList.size(); i++) {
            allPlayerListString.add(allPlayerList.get(i).toString());
            allPlayerListStringNotSorted.add(allPlayerList.get(i).toMenuString());
        }


        //initialize initial incoming players lists
        //TODO: verifier si necessaire.
        upcomingAthlete = new ArrayList<>();
        upcomingAthleteStringList = new ArrayList<>();

        //initialize top 3 players lists
        top3AthleteStringList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (allPlayerList.get(i) != null) {
                upcomingAthlete.add(allPlayerList.get(i));
                upcomingAthleteStringList.add(allPlayerList.get(i).toString());
            }
        }


        currentAthlete = upcomingAthlete.get(0);
        upcomingAthleteView = (ListView) findViewById(R.id.upcomingPlayer);
        top3athleteView = (ListView) findViewById(R.id.topplayer);

        ArrayAdapter<String> incomingPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                upcomingAthleteStringList);



        TextView currentPlayer = (TextView) this.findViewById(R.id.currentPlayer);
        currentPlayer.setText(upcomingAthleteStringList.get(0));


        upcomingAthleteView.setAdapter(incomingPlayerArrayAdapter);
    }

    public void viewAllUpcomingAthlete(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStartedActivity.this);

        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStartedActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.show_all_athlete_competition, null);

        alertDialogBuilder.setView(promptView);

        listView = (ListView) promptView.findViewById(R.id.allPlayerListView);

        ArrayAdapter<String> allPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,listeDeTousLesDescentesString
        );

        AlertDialog alert = alertDialogBuilder.create();


        listView.setAdapter(allPlayerArrayAdapter);
        alert.show();
    }

    public void ShowAllAthlete(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStartedActivity.this);

        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStartedActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.show_all_athlete_competition, null);

        alertDialogBuilder.setView(promptView);

        listView = (ListView) promptView.findViewById(R.id.allPlayerListView);

        ArrayAdapter<String> allPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,allPlayerListStringNotSorted
        );

        AlertDialog alert = alertDialogBuilder.create();


        listView.setAdapter(allPlayerArrayAdapter);
        alert.show();
    }

    public void start(View v) {

        if (chrono == null) {
            chrono = new com.example.nick.hockeyapp.Chronometer(context);
            t = new Thread(chrono);
            t.start();
            chrono.start();
        }

    }

    public void stop(View v) {
        if (chrono != null) {
            chrono.stop();
            t.interrupt();
            t = null;
            chrono = null;
        }

        String chronoToConcatenated = chronometerView.getText().toString();

        String[] chronoTab = new String[4];
        chronoTab = chronoToConcatenated.split(":");

        double player_race_time = Integer.parseInt(chronoTab[0]) * 3600000 + Integer.parseInt(chronoTab[1]) * 60000 + Integer.parseInt(chronoTab[2]) * 1000 + Integer.parseInt(chronoTab[3]);
        currentTime = player_race_time;

        currentAthlete.addTime(currentTime);

        showPenaltyAlert();
    }

    public void showPenaltyAlert() {
        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStartedActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.penalty_popup, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStartedActivity.this);
        alertDialogBuilder.setView(promptView);

        TextView tv = (TextView)promptView.findViewById(R.id.penaltyText);


        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStartedActivity.this);
                        //final View promptView = layoutInflater.inflate(R.layout.penalty_popup, null);
                        TextView w = ((TextView) promptView.findViewById(R.id.penaltyText));
                        if(!w.getText().toString().isEmpty()) {
                            int x = Integer.parseInt(w.getText().toString());
                            currentAthlete.setPenalty(x);
                        }


                        goToNextPlayer();
                        showLastAthleteAlert();
                    }
                });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void ViewAllPlayer(View v) {


       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStartedActivity.this);

        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStartedActivity.this);
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

    private void algorithmeTri(ArrayList<Athlete> allPlayerList) {

        Athlete temp;
        int x = allPlayerList.size();
        /*
        for(int i = allPlayerList.size() - 1; i < allPlayerList.size(); i++) {
            for(int j = 0; j < i-1; j++) {
                if(allPlayerList.get(j + 1).getTimeWithPenalty() < allPlayerList.get(j).getTimeWithPenalty() && allPlayerList.get(j + 1).getTimeWithPenalty() != 0) {
                    temp = allPlayerList.get(j + 1);
                    allPlayerList.set(j + 1, allPlayerList.get(j));
                    allPlayerList.set(j, temp);
                }
            }
        }
        */

    }

    public void goToNextPlayer() {


        //Augmente la position du prochaine joueur et update la liste de upcoming players.
        int nextPlayerOutOfBounds = 0;
        currentPlayerPosition++;
        if(currentPlayerPosition==allPlayerList.size()) {
            currentPlayerPosition = 0;
        }
        upcomingAthlete = new ArrayList<>();
        upcomingAthleteStringList = new ArrayList<>();


        algorithmeTri(sortedAllPlayerList);
        updateTop3View();
        updateAllLeaderBoardView();
        listeDeTousLesDescentesString.remove(0);
        listeDeTousLesDescentes.remove(0);
        for(int i = 0; i < 3;i++) {
            if(i < listeDeTousLesDescentesString.size()){
                upcomingAthlete.add(listeDeTousLesDescentes.get(i));
                upcomingAthleteStringList.add(listeDeTousLesDescentesString.get(i));
            }
        }

        upcomingAthleteView = (ListView) findViewById(R.id.upcomingPlayer);

        ArrayAdapter<String> incomingPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                upcomingAthleteStringList);
        upcomingAthleteView.setAdapter(incomingPlayerArrayAdapter);



        TextView currentPlayer = (TextView) this.findViewById(R.id.currentPlayer);


        //all race has been done
        if(listeDeTousLesDescentesString.size() == 0) {
            showWinner();
        } else {
            lastAthlete = currentAthlete;
            currentAthlete = upcomingAthlete.get(0);
            currentPlayer.setText(upcomingAthleteStringList.get(0));
        }
    }

    private void showWinner() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStartedActivity.this);

        ArrayList<String> medalList = new ArrayList<>();

        medalList.add("Gold : "+sortedAllPlayerList.get(0));
        medalList.add("Silver : "+sortedAllPlayerList.get(1));
        medalList.add("Bronze : "+sortedAllPlayerList.get(2));


        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStartedActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.show_all_athlete_competition, null);

        alertDialogBuilder.setView(promptView);

        listView = (ListView) promptView.findViewById(R.id.allPlayerListView);

        ArrayAdapter<String> allPlayerArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,medalList
        );

        AlertDialog alert = alertDialogBuilder.create();


        listView.setAdapter(allPlayerArrayAdapter);
        alert.show();

        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

    private void updateTop3View() {

        top3AthleteStringList = new ArrayList<>();
        algorithmeTri(sortedAllPlayerList);

        for(int z = 0; z < 3;z++) {
            if(sortedAllPlayerList.get(z).getTimeWithPenalty() != 0) {
                top3AthleteStringList.add(sortedAllPlayerList.get(z).toString());
            }
        }

        //Since there is no top player list on the creation of this view,just take top 3 incoming players.
        ArrayAdapter<String> top3ArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                top3AthleteStringList);


        top3athleteView.setAdapter(top3ArrayAdapter);
    }

    private void updateAllLeaderBoardView() {

        allPlayerListString = new ArrayList<>();

        for (int i = 0; i < sortedAllPlayerList.size(); i++) {
            allPlayerListString.add(sortedAllPlayerList.get(i).toString());
        }
    }

    public void updateTime(final String time) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                chronometerView.setText(time);
            }
        });
    }

    public void didNotFinish(View v){
        if (chrono != null) {
            chrono.stop();
            t.interrupt();
            t = null;
            chrono = null;
        }

        currentAthlete.setDidNotFinish(true);
        goToNextPlayer();
    }

    public void showLastAthleteAlert(){
        LayoutInflater layoutInflater = LayoutInflater.from(CompetitionStartedActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.popup_show_last_athlete, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompetitionStartedActivity.this);
        alertDialogBuilder.setView(promptView);

        TextView athlete = (TextView)promptView.findViewById(R.id.athlete);
        ListView top3 = (ListView) promptView.findViewById(R.id.firstThree);

        ArrayAdapter<String> top3ArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                top3AthleteStringList);

        top3.setAdapter(top3ArrayAdapter);
        int position = 0;
        for(int i = 0 ; i< sortedAllPlayerList.size(); i++){
            if (sortedAllPlayerList.get(i).getDossard() == lastAthlete.getDossard()){
                position = i+1;
            }
        }
        if(lastAthlete.getDisqualified() || lastAthlete.getDidNotFinish()){
            athlete.setText(lastAthlete.getFirstName() + " " +  lastAthlete.getLastName() + " DISQUALIFIED");
        }else {
            athlete.setText(lastAthlete.getFirstName() + " " + lastAthlete.getLastName() + " Position: " + position);
        }
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
}


