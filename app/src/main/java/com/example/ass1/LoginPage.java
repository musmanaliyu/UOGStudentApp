package com.example.ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        EditText editUsername =findViewById(R.id.editUsername);
        EditText editPassword =findViewById(R.id.editPassword);

        String str = editUsername.getText().toString();
        String str1 = editPassword.getText().toString();
        if (str != null && str1 != null)
        {
            findViewById(R.id.floatingActionButton2).setOnClickListener(v->{
                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                startActivity(intent);
                finish();
            });
        }
        findViewById(R.id.btnSignUp).setOnClickListener(v->{
            Intent intent = new Intent(LoginPage.this,RegisterAccount.class);
            startActivity(intent);
            finish();
        });
    }
}