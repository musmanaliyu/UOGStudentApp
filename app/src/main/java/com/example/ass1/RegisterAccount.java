package com.example.ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        findViewById(R.id.btnSignIn).setOnClickListener(v->{
            Intent intent = new Intent(RegisterAccount.this,LoginPage.class);
            startActivity(intent);
            finish();
        });


    }
}