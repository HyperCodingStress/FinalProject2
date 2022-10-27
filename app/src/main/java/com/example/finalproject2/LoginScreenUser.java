package com.example.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreenUser extends AppCompatActivity implements View.OnClickListener{

    private EditText emailUser, passUser;
    private Button button;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailUser = (EditText) findViewById(R.id.emailUser);
        passUser = (EditText) findViewById(R.id.passUser);
        button = (Button) findViewById(R.id.loginUser);
        progressBar = (ProgressBar) findViewById(R.id.loads);
        button.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginUser:
                userlogin();
        }
    }

    private void userlogin() {
        String email = emailUser.getText().toString().trim();
        String password = passUser.getText().toString().trim();

        if(email.isEmpty()){
            emailUser.setError("Email Tidak Boleh Kosong");
            emailUser.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passUser.setError("Password Tidak Boleh Kosong");
            passUser.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        System.out.println(email + "\n" + password);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginScreenUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                            databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    boolean isExcist = snapshot.exists();
                                    progressBar.setVisibility(View.GONE);
                                    if(isExcist) {
                                        DataUser user = snapshot.getValue(DataUser.class);
                                        String status = user.getRole().toString();
                                        if(status.equals("user")){
                                            back();
                                        }else{
                                            Toast.makeText(LoginScreenUser.this,"Bukan User",Toast.LENGTH_LONG);
                                        }

                                    } else {
                                        Log.i("Result Sigin","Failed");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    progressBar.setVisibility(View.GONE);
                                    Log.i("Result Sigin","Failed");
                                }
                            });

                        } else {
                            progressBar.setVisibility(View.GONE);
                            Log.i("Result Sigin","Failed");
                        }
                    }
                });


    }
    private void back() {
        startActivity(new Intent(this,MainActivity.class));
    }
}