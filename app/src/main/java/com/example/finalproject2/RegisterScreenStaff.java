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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreenStaff extends AppCompatActivity implements View.OnClickListener {

    private EditText name, pass, Cpass, email;
    private FirebaseAuth mAuth;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen_staff);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.userStaff);
        pass = (EditText) findViewById(R.id.passRegis);
        Cpass = (EditText) findViewById(R.id.confirmPass);
        email = (EditText) findViewById(R.id.emailStaff);
        create = (Button) findViewById(R.id.RegisterStaff);
//        progress = (ProgressBar) findViewById(R.id.progressBar);
        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RegisterStaff:
                regiter();
        }
    }
    private void back() {
        startActivity(new Intent(this,LoginScreenStaff.class));
    }
    private void regiter() {
        String nama = name.getText().toString().trim();
        String pass1 = pass.getText().toString().trim();
        String pass2 = Cpass.getText().toString().trim();
        String emails =  email.getText().toString().trim();

        if (!pass1.equals(pass2)) {
            Cpass.setError("Password Tidak Sama");
            Cpass.requestFocus();
            return;
        }
        if(pass1.isEmpty()){
            pass.setError("Password Tidak Boleh Kosong");
            pass.requestFocus();
            return;
        }
        if(pass2.isEmpty()){
            Cpass.setError("Password Tidak Boleh Kosong");
            Cpass.requestFocus();
            return;
        }
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
        if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            email.setError("Masukan Email Yang Valid");
            email.requestFocus();
            return;
        }
        if(pass1.length() < 6){
            pass.setError("Password minimal lebih dari 6");
            pass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emails,pass1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            DataUser user = new DataUser(nama,"0800000000",pass1,emails,"staff");
                            String uId = task.getResult().getUser().getUid();
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(uId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterScreenStaff.this,"Berhasil Mendaftar",Toast.LENGTH_SHORT).show();
                                                try {
                                                    Thread.sleep(1000);
                                                    back();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            else{
                                                Toast.makeText(RegisterScreenStaff.this,"Gagal Mendaftar",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(RegisterScreenStaff.this,"Gagal Mendaftar",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}