package com.example.entryformsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper db=new DbHelper(this);
        e1=findViewById(R.id.name);
        e2=findViewById(R.id.email);
        e3=findViewById(R.id.phone);
        b1=findViewById(R.id.insert);
        b2=findViewById(R.id.show);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=e1.getText().toString();
                String email=e2.getText().toString();
                String phone =e3.getText().toString();

                // Insert data into the database
                boolean inserted = db.insertData(name, email, phone);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ListView listView = findViewById(R.id.listView);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from the database
                List<String> dataList = db.getData();

                // Create an ArrayAdapter to populate the ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
            }
        });
    }
}