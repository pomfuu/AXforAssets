package com.example.ux_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imageView;
    Animation animation;
    TextView username, email;
    EditText usertxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usertxt = findViewById(R.id.Emailfield);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        imageView = findViewById(R.id.menuSVG);
        animation = AnimationUtils.loadAnimation(this, R.anim.down );

        navigationView.startAnimation(animation);
        drawerLayout.setVisibility(View.VISIBLE);
        navigationView.setNavigationItemSelectedListener(this);

        username = findViewById(R.id.labelUsername);
        Intent intent = getIntent();
        String hi = "Hi, " + intent.getStringExtra("username");
        username.setText(hi);

        email = findViewById(R.id.Emailfield);
        String em = intent.getStringExtra("username") + "@gmail.com";
        username.setText(em);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.home:
                intent = new Intent(ProfileActivity.this, HomeActivity.class);
                intent.putExtra("username", R.id.labelUsername);
                startActivity(intent);
                break;
            case R.id.items:
                intent = new Intent(ProfileActivity.this, ItemsActivity.class);
                intent.putExtra("username", R.id.labelUsername);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                intent.putExtra("username", R.id.labelUsername);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.putExtra("username", R.id.labelUsername);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}