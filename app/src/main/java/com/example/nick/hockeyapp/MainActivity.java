package com.example.nick.hockeyapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView listView ;
    ArrayList<Athlete> liste;

    private int player_number = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        liste = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);

        ShowAthleteListAdapter adapter = new ShowAthleteListAdapter(this, R.layout.list_adapter_view,liste);
        listView = (ListView) findViewById(R.id.listView);
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

                        Athlete athlete = new Athlete(player_number++, firstName,lastName,country);
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
}
