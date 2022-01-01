package com.example.clickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TheList extends AppCompatActivity {

    Button btnBack;
    ListView lvList;

    ArrayAdapter aa;
    ArrayList<Count> countList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_list);

        //========================Setup=====================
        btnBack = findViewById(R.id.btnBack);
        lvList = (ListView) this.findViewById(R.id.lvList);
        //========================Setup=====================

        //========================Buttons=====================
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TheList.this, MainActivity.class);
                startActivity(i);
            }
        });
        //========================Buttons=====================


        //========================List====================
        DBHelper dbh = new DBHelper(TheList.this);
        countList = new ArrayList<Count>();
        countList.clear();
        countList.addAll(dbh.getAllCounts());
        dbh.close();

        aa = new CountAdapter(TheList.this, R.layout.listview, countList);
        lvList.setAdapter(aa);
        //========================List====================


        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Count target = countList.get(position);
                Intent i = new Intent(TheList.this, EditList.class);
                i.putExtra("data",target);
                startActivityForResult(i,9);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper dbh = new DBHelper(TheList.this);
            countList.clear();
            countList.addAll(dbh.getAllCounts());
            dbh.close();

            aa.notifyDataSetChanged();
        }
    }
}