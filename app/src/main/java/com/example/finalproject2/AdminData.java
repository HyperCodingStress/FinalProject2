package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminData extends AppCompatActivity implements View.OnClickListener{
    ImageView pakaian,elektronik,other,books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data);
        pakaian = (ImageView) findViewById(R.id.pakaianAdm);
        elektronik = (ImageView) findViewById(R.id.elektronikAdm);
        other = (ImageView) findViewById(R.id.otherAdm);
        books = (ImageView) findViewById(R.id.booksAdm);
        pakaian.setOnClickListener(this);
        elektronik.setOnClickListener(this);
        other.setOnClickListener(this);
        books.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(AdminData.this, adminProduct.class);
        switch (v.getId()){
            case R.id.booksAdm:
                intent.putExtra("status","books");
                startActivity(intent);
                break;
            case R.id.otherAdm:
                intent.putExtra("status","other");
                startActivity(intent);
                break;
            case R.id.pakaianAdm:
                intent.putExtra("status","pakaian");
                startActivity(intent);
                break;
            case R.id.elektronikAdm:
                intent.putExtra("status","elektronik");
                startActivity(intent);
                break;
        }
    }
}