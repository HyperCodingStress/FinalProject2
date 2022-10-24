package com.example.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreenUser extends AppCompatActivity implements View.OnClickListener{

    private TextView phone,pass;
    private Button button;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        phone = (TextView) findViewById(R.id.nomorTelp);
        pass = (TextView) findViewById(R.id.passUser);
        button = (Button) findViewById(R.id.loginUser);
        progressBar = (ProgressBar) findViewById(R.id.loads);
        button.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginUser:
                userlogin();
        }
    }

    private void userlogin() {
        String nomor = phone.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(nomor.isEmpty()){
            phone.setError("Nomor Tidak Boleh Kosong");
            phone.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(nomor).matches()) {
            phone.setError("Nomor Tidak Valid");
            phone.requestFocus();
            return;
        }
        if(password.isEmpty()){
            pass.setError("Password Tidak Boleh Kosong");
            pass.requestFocus();
            return;
        }
        if(password.length() < 6){
            pass.setError("Password minimal lebih dari 6");
            pass.requestFocus();
            return;
        }
        if(nomor.length() < 10) {
            phone.setError("Nomor harus lebih dari 10");
            phone.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference DatabaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference.child("Users").orderByChild("role").equalTo("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(nomor).exists()  && snapshot.child(password).exists() && snapshot.child(uid).child("role").equals("user")){
                    Toast.makeText(LoginScreenUser.this,"Login Succes",Toast.LENGTH_SHORT).show();
                    back();
                }else{
                    Toast.makeText(LoginScreenUser.this,"Data Belum Terdaftar",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void back() {
        startActivity(new Intent(this,MainActivity.class));
    }
}