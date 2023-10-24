package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

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

                int id=item.getItemId();
                if(id==R.id.feat1){             //switch case can be used here
                    loadFragement(new CallLogFragment());
                    Toast.makeText(MainActivity.this, "call log tracking", Toast.LENGTH_SHORT).show();

                } else if (id==R.id.feat2) {
                    loadFragement(new ScreenTimeFragment());
                    Toast.makeText(MainActivity.this, "Screen time tracking", Toast.LENGTH_SHORT).show();

                } else if (id==R.id.feat3) {
                    loadFragement(new GeoLocationFragment());
                    Toast.makeText(MainActivity.this, "set boundaries to your chid", Toast.LENGTH_SHORT).show();

                } else if (id==R.id.feat4) {
                    loadFragement(new GeoFencingFragment());
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

                } else if (id==R.id.feat5) {
                    //add new feature and there fregement
                    Toast.makeText(MainActivity.this, "still in planning phase", Toast.LENGTH_SHORT).show();

                } else  {
                    Toast.makeText(MainActivity.this, "still in planning phase", Toast.LENGTH_SHORT).show();

                }

                drawerLayout.closeDrawer(GravityCompat.START); //when any feature is open drawer will get close

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
            public void onClick(View view) {   //siginout method
                FirebaseAuth.getInstance().signOut();

                Intent  intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    //check if drawer is open then when backpressed first drawer will get backed
    public void onBackPressed() {
//        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed(); //if there is nothing(stack) to get back then home screen apper app is closed
        }
    }

    private  void loadFragement(Fragment fragment) {
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container ,  fragment);
        ft.commit();
    }


}





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.navigation_item,menu);
//        return true;
//    }

//        onOptionsItemSelected();

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.feat1:Toast.makeText(this, "call log is feature active", Toast.LENGTH_SHORT).show();
//                return  true;
//            case R.id.feat2:Toast.makeText(this, "screen time", Toast.LENGTH_SHORT).show();
//                return  true;
//            case R.id.feat3:Toast.makeText(this, " geo locstion", Toast.LENGTH_SHORT).show();
//                return  true;
//            case R.id.feat4:Toast.makeText(this, "geo location", Toast.LENGTH_SHORT).show();
//                return  true;
//            case R.id.feat5:Toast.makeText(this, "sample add feature", Toast.LENGTH_SHORT).show();
//                return  true;
//            case R.id.feat6:Toast.makeText(this, " sample add feature", Toast.LENGTH_SHORT).show();
//                return  true;
//
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
