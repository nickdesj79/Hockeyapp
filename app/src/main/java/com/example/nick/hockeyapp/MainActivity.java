package com.example.nick.hockeyapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

//TODO: ajouter le bouton "did not finish" si un athlete ne fini pas le parcours.
//TODO: afficher le classement du dernier athlete qui a passé.
//TODO: afficher la liste des descentes qui n'ont pas été faites.
//TODO: permettre d'afficher la liste complete des athletes qui participent.
//TODO: remettre au premier lorsque tout le monde a fait le parcours.
//TODO: recevoir les medailles lorsque tout le monde est passé deux fois.
//TODO: regler le tri.
public class MainActivity extends AppCompatActivity {

    ListView listView ;
    ArrayList<Athlete> liste;

    private int player_number = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        liste = new ArrayList<>();

        Athlete athlete = new Athlete(player_number++, "Nicolas","Desjardins","Canada");
        Athlete athlete2 = new Athlete(player_number++, "Jeremie","Desjardins","USA");
        Athlete athlete3 = new Athlete(player_number++, "Marc-antoine","Roberge","Japon");
        Athlete athlete4 = new Athlete(player_number++, "Vincent","Pageau","Chine");
        Athlete athlete5 = new Athlete(player_number++, "Marc-antoine","Fortier","Pays-bas");

        liste.add(athlete);
        liste.add(athlete2);
        liste.add(athlete3);
        liste.add(athlete4);
        liste.add(athlete5);

        listView = (ListView) findViewById(R.id.athleteList);

        ShowAthleteListAdapter adapter = new ShowAthleteListAdapter(this, R.layout.list_adapter_view, liste);
        listView.setAdapter(adapter);
    }

    public void addAthlete(View v) {

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.add_athlete, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        EditText etfirstName = (EditText) promptView.findViewById(R.id.firstname);
                        String firstName = etfirstName.getText().toString();

                        EditText etlastName = (EditText) promptView.findViewById(R.id.lastname);
                        String lastName = etlastName.getText().toString();

                        EditText etCountry = (EditText) promptView.findViewById(R.id.country);
                        String country = etCountry.getText().toString();

                        Athlete athlete = new Athlete(player_number++, firstName, lastName, country);
                        liste.add(athlete);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void startCompetition(View v) {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.start_competition_alert, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent i = new Intent(getApplicationContext(), CompetitionStartedActivity.class);

                        i.putExtra("athleteList", liste);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


}
