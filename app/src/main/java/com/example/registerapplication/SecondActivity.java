package com.example.registerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();

        if (i != null) {
            String name = i.getStringExtra("username");
            String email = i.getStringExtra("useremail");
            String password = i.getStringExtra("userpassword");
            String gender = i.getStringExtra("usergender");

            TextView uname = findViewById(R.id.uname);
            TextView uemail = findViewById(R.id.uemail);
            TextView upass = findViewById(R.id.upassword);
            TextView ugender = findViewById(R.id.ugender);

            uname.setText(name);
            uemail.setText(email);
            upass.setText(password);
            ugender.setText(gender);
        }
    }
}