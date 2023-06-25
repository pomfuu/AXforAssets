package com.example.ux_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imageView;
    Animation animation;

    ViewPager viewPager;
    List<Fragment> fragmentList;
    int currpage = 0;
    boolean carouselrunning = false;
    TabLayout tablayout;
    ViewPager2 viewpager2;
    FragmentAdapter adapter;
    TextView username, nameImage;
    int[] images = {R.drawable.slideshow1, R.drawable.slideshow2, R.drawable.slideshow3};
    String[] names = {"Sprout Island","Oak Woods","Springstar Fields"};
    ViewFlipper viewFlipper;
    GestureDetector gestureDetector;
    ViewFlipper viewFlipper2;


    // Slideshow Handler
    Handler carouselHandler = new Handler();
    Runnable carouselRunnable = new Runnable() {
        @Override
        public void run() {
            if(currpage == fragmentList.size()){
                currpage = 0;
            }
            viewPager.setCurrentItem(currpage++ , true );
            carouselHandler.postDelayed(this,2000);
        }
    };

    class Gesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() > e2.getX()){
                viewFlipper.showNext();
            }
            if(e1.getX() < e2.getX()){
                viewFlipper.showPrevious();
            }
            return super.onFling(e1,e2,velocityX,velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        imageView = findViewById(R.id.menuSVG);
        animation = AnimationUtils.loadAnimation(this, R.anim.down );

        navigationView.startAnimation(animation);
        drawerLayout.setVisibility(View.VISIBLE);
        navigationView.setNavigationItemSelectedListener(this);
        viewFlipper = findViewById(R.id.Carousel);

        for (int image : images){
            slideImage(image);
        }

        username = findViewById(R.id.labelUsername);
        Intent intent = getIntent();
        username.setText("Welcome, " + intent.getStringExtra("username"));

        tablayout = findViewById(R.id.tabLayout);
        viewpager2 = findViewById(R.id.viewPager2);

        adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        viewpager2.setAdapter(adapter);

        tablayout.addTab(tablayout.newTab().setText("Terms"));
        tablayout.addTab(tablayout.newTab().setText("Conditions"));

        Gesture gesture = new Gesture();
        gestureDetector = new GestureDetector(this, gesture);

        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

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

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tablayout.selectTab(tablayout.getTabAt(position));
            }
        });

    }

    public void slideImage(int images){
        ImageView imageView1 = new ImageView(this);
        imageView1.setBackgroundResource(images);

        viewFlipper.addView(imageView1);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.home:
                intent = new Intent(HomeActivity.this, HomeActivity.class);
                intent.putExtra("username", R.id.labelUsername);
                startActivity(intent);
                break;
            case R.id.items:
                intent = new Intent(HomeActivity.this, ItemsActivity.class);
                intent.putExtra("username", R.id.labelUsername);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("username", R.id.labelUsername);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(HomeActivity.this, LoginActivity.class);
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


