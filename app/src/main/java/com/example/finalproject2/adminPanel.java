package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

public class adminPanel extends AppCompatActivity implements View.OnClickListener{
    Button admin,crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        admin = (Button) findViewById(R.id.daftarStaff);
        crud = (Button) findViewById(R.id.crudData);
        admin.setOnClickListener(this);
        crud.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daftarStaff:
                startActivity(new Intent(this,LoginScreenAdmin.class));
                break;
            case R.id.crudData:
                startActivity(new Intent(this, AdminData.class));
                break;
        }
    }
}