package com.example.clickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditList extends AppCompatActivity {

    Button btnBck, btnDelete, btnSave1;
    EditText etName;
    TextView tvCounts;
    Count data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        //====================Setup==================
        btnBck = findViewById(R.id.btnBck);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave1 = findViewById(R.id.btnSave1);

        etName = findViewById(R.id.etName);
        tvCounts = findViewById(R.id.tvCounts);

        Intent i = getIntent();
        data = (Count) i.getSerializableExtra("data");

        etName.setText(data.getTitle());
        tvCounts.setText(String.valueOf(data.getCounts()));
        //====================Setup==================



        //=====================Buttons=======================
        btnBck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditList.this, TheList.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditList.this);
                dbh.deleteCounts(data.get_id());
                dbh.close();
                Intent intent = new Intent(EditList.this, TheList.class);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditList.this);

                dbh.updateCounts(new Count(data.get_id(), etName.getText().toString(), Integer.valueOf(tvCounts.getText().toString())));
                dbh.close();

                Intent intent = new Intent(EditList.this, TheList.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        //=====================Buttons=======================
    }
}