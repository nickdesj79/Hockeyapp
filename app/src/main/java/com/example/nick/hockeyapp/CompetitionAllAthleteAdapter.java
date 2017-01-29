package com.example.nick.hockeyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nick on 2017-01-29.
 */

public class CompetitionAllAthleteAdapter extends ArrayAdapter<Athlete> {

    private LayoutInflater inflater;
    private ArrayList<Athlete> list;
    private int viewRessourceId;

    public CompetitionAllAthleteAdapter(Context context, int textViewRessourceId, ArrayList<Athlete> list) {
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
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView country = (TextView) convertView.findViewById(R.id.country);
        TextView totaltime = (TextView) convertView.findViewById(R.id.totaltime);

        number.setText(""+athlete.getNumber());
        name.setText(athlete.getFname().substring(0,1) + "."+athlete.getLname());
        country.setText(athlete.getCountry());
        totaltime.setText(athlete.getTotal_time());

        return convertView;
    }
}
