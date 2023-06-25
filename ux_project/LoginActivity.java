package com.example.ux_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usertxt, passwordtxt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usertxt = findViewById(R.id.Emailfield);
        passwordtxt = findViewById(R.id.PasswordField);
        loginBtn = findViewById(R.id.LoginBtn);

        loginBtn.setOnClickListener((view -> {
            String username = usertxt.getText().toString();
            String password = passwordtxt.getText().toString();
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(LoginActivity.this,"Username and password must be filled", Toast.LENGTH_LONG ).show();
            }
            else if( password.length() <= 8 ){
                Toast.makeText(LoginActivity.this,"Password must be greater than 8 characters!", Toast.LENGTH_LONG ).show();
            }
            else{
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("username", username );
                startActivity(intent);
                finish();
            }
        }));

    }
}