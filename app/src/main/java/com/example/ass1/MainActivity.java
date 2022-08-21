package com.example.ass1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    MyAdapter myAdapter;
    static List  Data = new ArrayList<Transcript_Info>();
    Transcript_Info mydata=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Data.isEmpty()) {
            Data.add(new Transcript_Info("Ibrahim", "535058348743", "19011519-073", "Lahore", "22-09-2001", "34299821309", "6", "03011121144", "HBL", "904399", "15000", "23-04-2020"));
            Data.add(new Transcript_Info("Osama", "535068493847", "19011519-045", "Mandi", "27-03-1999", "32455365467", "6", "03025165611", "HBL", "456728", "15000", "23-04-2020"));
        }

        mydata=getIntent().getParcelableExtra("model");
        if (mydata!=null){
            Data.add(mydata);
        }
        RecyclerView recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        myAdapter = new MyAdapter(Data);
        recyclerView.setAdapter(myAdapter);


        FloatingActionButton fBtn1 = findViewById(R.id.floatingActionButton);

        fBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditActivity2.class);
                startActivity(intent);

            }
        });


    }
}

