package com.example.ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity2 extends AppCompatActivity {

    Button btnSave;
    Transcript_Info data;
    EditText []txt = new EditText[12];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);

         btnSave = findViewById(R.id.Save_button);

        txt[0]=findViewById(R.id.txt1);
        txt[1]=findViewById(R.id.txt2);
        txt[2]=findViewById(R.id.txt3);
        txt[3]=findViewById(R.id.txt4);
        txt[4]=findViewById(R.id.txt5);
        txt[5]=findViewById(R.id.txt6);
        txt[6]=findViewById(R.id.txt7);
        txt[7]=findViewById(R.id.txt8);
        txt[8]=findViewById(R.id.txt9);
        txt[9]=findViewById(R.id.txt10);
        txt[10]=findViewById(R.id.txt11);
        txt[11]=findViewById(R.id.txt12);


//////////////////////////////////////////////////////////////////
        btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 data=getdatafromUI();
                 Intent intent = new Intent(EditActivity2.this, MainActivity.class);
                 intent.putExtra("model", (Parcelable) data);
                 startActivity(intent);
             }
         });
/////////////////////////////////////////////////


    }
    public Transcript_Info getdatafromUI(){
        return  new Transcript_Info(
                txt[0].getText().toString(),
                txt[1].getText().toString(),
                txt[2].getText().toString(),
                txt[3].getText().toString(),
                txt[4].getText().toString(),
                txt[5].getText().toString(),
                txt[6].getText().toString(),
                txt[7].getText().toString(),
                txt[8].getText().toString(),
                txt[9].getText().toString(),
                txt[10].getText().toString(),
                txt[11].getText().toString()
        );
    }


}