package com.example.clickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnList, btnPlus, btnMinus, btnSave;
    EditText etNameUnit;
    TextView tvCount;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //========================Setup=====================
        btnList = findViewById(R.id.btnList);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnSave = findViewById(R.id.btnSave);

        etNameUnit = findViewById(R.id.etNameUnit);
        tvCount = findViewById(R.id.tvCount1);

        tvCount.setText("0");
        //========================Setup=====================

        //========================Buttons=====================
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TheList.class);
                startActivity(i);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity < 1) {
                    Toast.makeText(MainActivity.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                displayQuantity();
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etNameUnit.getText().toString();
                String test = "";

                if (title.isEmpty()){
                    test = "Untitled";
                }
                else {
                    test = title;
                }

                int quantityA = Integer.valueOf(tvCount.getText().toString());


                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(test,quantityA);
                dbh.close();

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        //========================Buttons=====================




    }

    //==================FunctionZone========================
    private void displayQuantity() {
        tvCount.setText(String.valueOf(quantity));
    }
    //==================FunctionZone========================
}