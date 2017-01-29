package com.example.nick.hockeyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nick on 2017-01-28.
 */

public class ShowAthleteListAdapter extends ArrayAdapter<Athlete> {

    private LayoutInflater inflater;
    private ArrayList<Athlete> list;
    private int viewRessourceId;

    public ShowAthleteListAdapter(Context context, int textViewRessourceId, ArrayList<Athlete> list) {
        super(context, textViewRessourceId, list);

        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewRessourceId = textViewRessourceId;

    }



    @Override
    public View getView(int position , View convertView, ViewGroup parent) {
    convertView = inflater.inflate(viewRessourceId,null);

        Athlete athlete = list.get(position);

        TextView number = (TextView) convertView.findViewById(R.id.number);
        TextView firstName = (TextView) convertView.findViewById(R.id.firstName);
        TextView lastName = (TextView) convertView.findViewById(R.id.lastName);
        TextView country = (TextView) convertView.findViewById(R.id.country);

        number.setText(""+athlete.getNumber());
        firstName.setText(athlete.getFname());
        lastName.setText(athlete.getLname());
        country.setText(athlete.getCountry());

        return convertView;
    }
}
