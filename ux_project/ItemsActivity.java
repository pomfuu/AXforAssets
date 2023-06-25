package com.example.ux_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;;import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ItemsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imageView;
    Animation animation;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        RecyclerView gameRecycler = findViewById(R.id.game_rv);
        Vector<Game> games = new Vector<>();
        Intent intent = getIntent();
        List<Drawable> gameImage = new ArrayList<>();

        String username = intent.getStringExtra("usernameglobal");

        gameImage.add(ContextCompat.getDrawable(this, R.drawable.items1));
        gameImage.add(ContextCompat.getDrawable(this, R.drawable.items2));
        gameImage.add(ContextCompat.getDrawable(this, R.drawable.items3));
        gameImage.add(ContextCompat.getDrawable(this, R.drawable.items4));
        gameImage.add(ContextCompat.getDrawable(this, R.drawable.items5));


        games.add(new Game("Sprouts Land", "cute pixel pastel farming asset pack", 0, gameImage.get(0)));
        games.add(new Game("Oak Woods", "2D side-scroller, platformer tileset", 1, gameImage.get(1)));
        games.add(new Game("Stringstar Fields", "A dark, soothing 16x16 tileset", 2, gameImage.get(2)));
        games.add(new Game("Pixel Planet Generator", "Showcase of shader code for Godot game engine",3, gameImage.get(3)));
        games.add(new Game("Cozy People", "Animated characters, hairstyles and clothes!",4, gameImage.get(4)));

        GameAdapter gameAdapter = new GameAdapter(this, intent);
        gameAdapter.setGameVector(games);

        gameRecycler.setAdapter(gameAdapter);
        gameRecycler.setLayoutManager(new GridLayoutManager(this, 1 ));

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        imageView = findViewById(R.id.menuSVG);
        animation = AnimationUtils.loadAnimation(this, R.anim.down );

        navigationView.startAnimation(animation);
        drawerLayout.setVisibility(View.VISIBLE);
        navigationView.setNavigationItemSelectedListener(this);

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
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(ItemsActivity.this, HomeActivity.class));
                break;
            case R.id.items:
                startActivity(new Intent(ItemsActivity.this, ItemsActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(ItemsActivity.this, ProfileActivity.class));
                break;
            case R.id.logout:
                startActivity(new Intent(ItemsActivity.this, LoginActivity.class));
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
