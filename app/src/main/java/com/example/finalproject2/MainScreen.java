package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity implements View.OnClickListener{
    TextView Admin,Staff,About;
    Button Login,Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        //layoyt
        Login = findViewById(R.id.Login);
        Register = findViewById(R.id.Register);
        Admin = findViewById(R.id.Admin);
        Staff = findViewById(R.id.Staff);
        About = findViewById(R.id.About);
        // warna
        Admin.setTextColor(Color.BLUE);
        Staff.setTextColor(Color.BLUE);
        About.setTextColor(Color.BLUE);
        //event
        Login.setOnClickListener(this);
        Register.setOnClickListener(this);
        Admin.setOnClickListener(this);
        Staff.setOnClickListener(this);
        About.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Register:
                startActivity(new Intent(this,RegisterScreenUser.class));
                break;
            case R.id.Login:
                startActivity(new Intent(this,LoginScreenUser.class));
                break;
            case R.id.Staff:
                startActivity(new Intent(this,LoginScreenStaff.class));
                break;
            case R.id.Admin:
                startActivity(new Intent(this,LoginScreenAdmin.class));
                break;
//            case R.id.About:
//                startActivity(new Intent(this,RegisterScreenUser.class));
//                break;
        }
    }
}