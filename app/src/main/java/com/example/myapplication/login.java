package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class login extends AppCompatActivity {
    Button b1,b2;
    TextInputEditText edtxtEmail , edtxtPass ;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressBar progressBar;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
        Intent iHome = new Intent(login.this , parentChildOpt.class);
        startActivity(iHome);
        finish();//poping from stack so is activity can't get open even after pressing back button

    }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1= findViewById(R.id.btnLogin);
        b2= findViewById(R.id.btnSignUp);
        edtxtEmail = findViewById(R.id.edtxtLogInEmail);
        edtxtPass= findViewById(R.id.edtxtLogInPassword);
        progressBar= findViewById(R.id.LoginPrograssBar);
        mAuth= FirebaseAuth.getInstance();

        //---------------------------------------
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                String email ,password;
                email = String.valueOf(edtxtEmail.getText());
                //can also use  email= edtxtEmail.getText().toString();
                password = String.valueOf(edtxtPass.getText());

                //check email pass is empty or not

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "ENTER EMAIL", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(login.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                                    //passing intent

                                            Intent iHome = new Intent(login.this , parentChildOpt.class);
                                            startActivity(iHome);
                                            finish();//poping from stack so is activity can't get open even after pressing back button


                                } else {
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });





            }
        });

        b2= findViewById(R.id.btnSignUp);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Intent iHome = new Intent(login.this , signin.class);
                        startActivity(iHome);
                        //finish();//poping from stack so is activity can't get open even after pressing back button




            }
        });

//--------------------------------------



    }
}