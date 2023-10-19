package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.os.Handler;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity {

    TextInputEditText edtxtSignUpEmail , edttxtSignUpPass ;
    TextView txtlogin;
    Button button;
    FirebaseAuth mAuth;
    ProgressBar progressBar;


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent iHome = new Intent(getApplicationContext() , parentChildOpt.class);
            startActivity(iHome);
            finish();//poping from stack so is activity can't get open even after pressing back button

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth= FirebaseAuth.getInstance();
        txtlogin = findViewById(R.id.signUp_login);
        button = findViewById(R.id.btnSignUp);
        edtxtSignUpEmail = findViewById(R.id.SignUpEmail);
        edttxtSignUpPass = findViewById(R.id.SignUpPassword);
        progressBar = findViewById(R.id.SignUpPrograssBar);


        txtlogin .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent iHome = new Intent(signin.this , login.class);
                        startActivity(iHome);
                        finish();//poping from stack so is activity can't get open even after pressing back button
            }
        });


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            progressBar.setVisibility(View.VISIBLE);
            String email ,password;
            email = String.valueOf(edtxtSignUpEmail.getText());
             /* can also use  email= edtxtEmail.getText().toString();
            or
            password = String.valueOf(edttxtSignUpPass.getText());
             */
            password = String.valueOf(edttxtSignUpPass.getText());

            //check email pass is empty or not

            if(TextUtils.isEmpty(email)){
                Toast.makeText(signin.this, "ENTER EMAIL", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(signin.this, "ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                return;
            }




                            mAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(signin.this, "ACCOUNT CREATED", Toast.LENGTH_SHORT).show();

                                                Intent  intent = new Intent(getApplicationContext(), login.class);
                                                startActivity(intent);
                                                finish();


                                            } else {

                                                Toast.makeText(signin.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

        }
    });

    }
}

//refernce https://firebase.google.com/docs/auth/android/password-auth#java_6