package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Product extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);
        String status = getIntent().getStringExtra("status");
        recyclerView = (RecyclerView)findViewById(R.id.listProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(status.equals("books")){

            FirebaseRecyclerOptions<MainModel> options =
                    new FirebaseRecyclerOptions.Builder<MainModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Books"),MainModel.class)
                            .build();
            mAdapter = new MainAdapter(options);
            recyclerView.setAdapter(mAdapter);

        }else if(status.equals("elektronik")){

            FirebaseRecyclerOptions<MainModel> options =
                    new FirebaseRecyclerOptions.Builder<MainModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Elektronik"),MainModel.class)
                            .build();
            mAdapter = new MainAdapter(options);
            recyclerView.setAdapter(mAdapter);

        }else if(status.equals("pakaian")){
            FirebaseRecyclerOptions<MainModel> options =
                    new FirebaseRecyclerOptions.Builder<MainModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Pakaian"),MainModel.class)
                            .build();
            mAdapter = new MainAdapter(options);
            recyclerView.setAdapter(mAdapter);
        }else{
            FirebaseRecyclerOptions<MainModel> options =
                    new FirebaseRecyclerOptions.Builder<MainModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Others"),MainModel.class)
                            .build();
            mAdapter = new MainAdapter(options);
            recyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }
}