package com.example.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class RegisterScreenUser extends AppCompatActivity implements View.OnClickListener{

    private EditText name,Phone,password,email;
    private FirebaseAuth mAuth;
    private Button create;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen_user);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.name);
        Phone = (EditText) findViewById(R.id.Phone);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.Email);
        create = (Button) findViewById(R.id.create);
        progress = (ProgressBar) findViewById(R.id.progressBarUser);
        progress.setVisibility(View.GONE);
        create.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.create:
                progress.setVisibility(View.GONE);
                regiter();
//
        }
    }

    private void back() {
        startActivity(new Intent(this,MainScreen.class));
    }

    private void regiter() {
        String nama = name.getText().toString().trim();
        String nomor = Phone.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String emails =  email.getText().toString().trim();

        if(nama.isEmpty()){
            name.setError("Nama Tidak Boleh Kosong");
            name.requestFocus();
            return;
        }
        if(emails.isEmpty()){
            email.setError("Email Tidak Boleh Kosong");
            email.requestFocus();
            return;
        }
        if(nomor.isEmpty()){
            Phone.setError("Nomor Tidak Boleh Kosong");
            Phone.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Password Tidak Boleh Kosong");
            password.requestFocus();
            return;
        }
        if(password.length() < 6){
            password.setError("Password minimal lebih dari 6");
            password.requestFocus();
            return;
        }
        if(nama.length() < 4){
            name.setError("Nama minimal lebih dari 4");
            name.requestFocus();
            return;
        }
        if(nomor.length() < 10){
            Phone.setError("Nomor harus lebih dari 10");
            Phone.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(nomor).matches()){
            Phone.setError("Nomor Harus Berupa Angka");
            Phone.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            email.setError("Masukan Email Yang Valid");
            email.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emails,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.setVisibility(View.VISIBLE);
                        if(task.isSuccessful()){
                            DataUser user = new DataUser(nama,nomor,pass,emails,"user");
                            String uId = task.getResult().getUser().getUid();
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(uId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterScreenUser.this,"Berhasil Mendaftar",Toast.LENGTH_SHORT).show();
                                                progress.setVisibility(View.GONE);
                                                try {
                                                    Thread.sleep(1000);
                                                    back();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            else{
                                                Toast.makeText(RegisterScreenUser.this,"Gagal Mendaftar",Toast.LENGTH_SHORT).show();
                                                progress.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(RegisterScreenUser.this,"Gagal Mendaftar",Toast.LENGTH_LONG).show();
                            progress.setVisibility(View.GONE);
                        }
                    }
                });
    }
}