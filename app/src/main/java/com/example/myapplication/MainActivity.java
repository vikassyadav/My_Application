package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button button;
    TextView textView;
    FirebaseAuth auth ;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigatioView);
        toolbar = findViewById(R.id.toolbar);

        button  =findViewById(R.id.logOutBtn);
        textView = findViewById(R.id.mainActytxt);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        //step 1 first setup toolbar
        setSupportActionBar(toolbar);

        //this constructor takes five paremeter( activity  ,drawer ,toolbar "refernce ,2 flags close open takes integer )
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar ,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//                int id=item.getItemId();
//                if(id==R.id.optfeat1){
//
//                } else if (id==R.id.optfeat2) {
//
//                } else if (id==R.id.optfeat3) {
//
//                }


                return true;
            }
        });


        if(user == null){
            Intent  intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }

        else{
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent  intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();

            }
        });


    }
}