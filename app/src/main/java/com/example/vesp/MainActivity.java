package com.example.vesp;

import androidx.annotation.NonNull;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;
import android.window.SplashScreen;

import com.example.vesp.admindashboard.d1;
import com.example.vesp.hamburger.adminlogin;
import com.example.vesp.hamburger.studentlogin;
import com.example.vesp.hamburger.teacherlogin;
import com.example.vesp.studentdashboard.s1;
import com.example.vesp.teacherdashboard.t1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView ;
    NavController navcontroller;
    BottomNavigationView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        btn = findViewById(R.id.btmnavi);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navcontroller = Navigation.findNavController(MainActivity.this,R.id.fragment);
        NavigationUI.setupWithNavController(btn,navcontroller);

        navigationView = findViewById(R.id.navigation1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.adminlid:
                        SharedPreferences sharedPreferences = getSharedPreferences(adminlogin.PREFS_NAME , MODE_PRIVATE);
                        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn" ,false);
                        Intent intent;
                        if(hasLoggedIn)
                        {
                            intent = new Intent(MainActivity.this, d1.class);
                        }else
                        {
                            intent = new Intent(MainActivity.this, adminlogin.class);
                        }

                        startActivity(intent);
                        finish();

                        break;
                    case R.id.teacherlid:
                        SharedPreferences sharedPreferences2 = getSharedPreferences(teacherlogin.PREFS_NAME , MODE_PRIVATE);
                        boolean hasLoggedIn2 = sharedPreferences2.getBoolean("hasLoggedIn" ,false);
                        Intent intent2;
                        if(hasLoggedIn2)
                        {
                            intent = new Intent(MainActivity.this, t1.class);
                        }else
                        {
                            intent = new Intent(MainActivity.this, teacherlogin.class);
                        }

                        startActivity(intent);
                        finish();
                        break;
                    case R.id.studentlid:
                        SharedPreferences sharedPreferences3 = getSharedPreferences(studentlogin.PREFS_NAME , MODE_PRIVATE);
                        boolean hasLoggedIn3 = sharedPreferences3.getBoolean("hasLoggedIn" ,false);
                        Intent intent3;
                        if(hasLoggedIn3)
                        {
                            intent = new Intent(MainActivity.this, s1.class);
                        }else
                        {
                            intent = new Intent(MainActivity.this, studentlogin.class);
                        }

                        startActivity(intent);
                        finish();
                        break;
                    case R.id.websiteid:
                        Toast.makeText(MainActivity.this,"Website",Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this , adminlogin.class);
                        //startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}