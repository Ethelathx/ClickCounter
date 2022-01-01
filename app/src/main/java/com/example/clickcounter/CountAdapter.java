package com.example.clickcounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CountAdapter extends ArrayAdapter<Count> {
    Context context;
    ArrayList<Count> count;
    int resource;

    TextView tvName,tvCountValue;

    public CountAdapter(Context context, int resource, ArrayList<Count> counts) {
        super(context, resource, counts);

        this.context = context;
        this.count = counts;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listview, parent, false);

        tvName = rowView.findViewById(R.id.tvName1);
        tvCountValue = rowView.findViewById(R.id.tvCountValue);



        Count note = count.get(position);
        int countValue = note.getCounts();
        String title = note.getTitle();


        tvName.setText(title);
        tvCountValue.setText(String.valueOf(countValue));



        return rowView;
    }



}


