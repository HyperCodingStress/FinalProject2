package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
    TextView admin,staff,about;
    Button user,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        user = findViewById(R.id.Login);
        register = findViewById(R.id.Register);
        admin = findViewById(R.id.Admin);
        staff = findViewById(R.id.Staff);
        about = findViewById(R.id.About);
        admin.setTextColor(Color.BLUE);
        staff.setTextColor(Color.BLUE);
        about.setTextColor(Color.BLUE);

        //button event
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login");
                Login();
            }
        });
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("Register");
                Register();
            }
        });
        admin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                admin();
            }
        });
        staff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                staff();
            }
        });
        about.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                about();
            }
        });
    }
    private void Login() {
        Intent intent = new Intent(this, LoginScreenUser.class);
        startActivity(intent);
    }
    private void Register() {
        Intent intent = new Intent(this, RegisterScreenUser.class);
        startActivity(intent);
    }
    private void admin() {
        Intent intent = new Intent(this, LoginScreenAdmin.class);
        startActivity(intent);
    }
    private void staff(){
        Intent intent = new Intent(this, LoginScreenStaff.class);
        startActivity(intent);
    }
    private void about(){
//        Intent intent = new Intent(this, RegisterScreenUser.class);
//        startActivity(intent);
    }

}