package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class adminProduct extends AppCompatActivity {
    RecyclerView recyclerView;
    adminAdapter Adm;
    public static String data;

    public static String getData() {
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        String status = getIntent().getStringExtra("status");
        data = status;
        recyclerView = (RecyclerView)findViewById(R.id.listProductAdm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Barang").child(status),MainModel.class)
                        .build();
        Adm = new adminAdapter(options);
        recyclerView.setAdapter(Adm);
    }
    @Override
    protected void onStop() {
        super.onStop();
        Adm.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Adm.startListening();
    }
}