package com.example.ux_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;;import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ImageView gameImage, backButton;
    TextView gameTitle, gameDesc;
    ImageView imageView, back;
    EditText emailInput;
    Button buy;
    int gameImageIndex;
    AlertDialog.Builder alert;
    Spinner payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        List<Drawable> imageList = new ArrayList<>();
        imageList.add(ContextCompat.getDrawable(this, R.drawable.items1));
        imageList.add(ContextCompat.getDrawable(this, R.drawable.items2));
        imageList.add(ContextCompat.getDrawable(this, R.drawable.items3));
        imageList.add(ContextCompat.getDrawable(this, R.drawable.items4));
        imageList.add(ContextCompat.getDrawable(this, R.drawable.items5));

        Intent intent = getIntent();
        alert = new AlertDialog.Builder(this);

        gameTitle = findViewById(R.id.gametitle);
        gameDesc = findViewById(R.id.gamedesc);
        gameTitle.setText(intent.getStringExtra("titlevariable"));
        gameDesc.setText(intent.getStringExtra("descvariable"));
        gameImageIndex = intent.getIntExtra("imagelistglobal", 0 );

        imageView = findViewById(R.id.gameimage);
        imageView.setImageDrawable(imageList.get(gameImageIndex));

        payment = findViewById(R.id.paymentfield);
//        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.);

        // username
        String uname =  intent.getStringExtra("usernameglobal");
        back = findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, HomeActivity.class );
                intent.putExtra("unameglobal",uname);
                startActivity(intent);
                finish();
            }
        });

        payment = findViewById(R.id.paymentfield);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.payment_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment.setAdapter(adapter);

        payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // buy
        buy = findViewById(R.id.buybutton);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailInput = findViewById(R.id.Emailfield);
                String email = emailInput.getText().toString();
                if( email.isEmpty() ){
                    Toast.makeText(DetailsActivity.this,"Email must not be Empty!", Toast.LENGTH_LONG ).show();
                }
                else {
                    alert.setTitle("Payment Success, a confirmation email has been sent.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(DetailsActivity.this, ItemsActivity.class);
                            intent.putExtra("unameglobal",uname);
                            startActivity(intent);
                            finish();
                        }
                    }).show();
                }
            }
        });
    }}
