package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar ;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.load);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        Loading();
    }

    private void Loading() {
        Loading load = new Loading(this,progressBar,textView,0f,100f);
        load.setDuration(5000);
        progressBar.setAnimation(load);
    }
}