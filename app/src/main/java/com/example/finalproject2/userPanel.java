package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class userPanel extends AppCompatActivity implements View.OnClickListener{
    ImageView pakaian,elektronik,other,books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);
        pakaian = (ImageView) findViewById(R.id.pakaian);
        elektronik = (ImageView) findViewById(R.id.elektronik);
        other = (ImageView) findViewById(R.id.other);
        books = (ImageView) findViewById(R.id.books);
        pakaian.setOnClickListener(this);
        elektronik.setOnClickListener(this);
        other.setOnClickListener(this);
        books.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(userPanel.this,Product.class);
        switch (v.getId()){
            case R.id.books:
                intent.putExtra("status","books");
                startActivity(intent);
                break;
            case R.id.other:
                intent.putExtra("status","other");
                startActivity(intent);
                break;
            case R.id.pakaian:
                intent.putExtra("status","pakaian");
                startActivity(intent);
                break;
            case R.id.elektronik:
                intent.putExtra("status","elektronik");
                startActivity(intent);
                break;
        }
    }
}