package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1= findViewById(R.id.btnLogin);
        b2= findViewById(R.id.btnSignUpGoogle);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent iHome = new Intent(login.this , MainActivity.class);
                        startActivity(iHome);
                        //finish();//poping from stack so is activity can't get open even after pressing back button

                    }
                },1000);

            }
        });





    }
}