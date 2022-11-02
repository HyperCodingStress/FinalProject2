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

public class LoginScreenAdmin extends AppCompatActivity implements View.OnClickListener {
    private EditText emailUser, passUser;
    private Button button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_admin);
        emailUser = (EditText) findViewById(R.id.idAdmin);
        passUser = (EditText) findViewById(R.id.passAdmin);
        button = (Button) findViewById(R.id.LoginAdmin);
        button.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.LoginAdmin:
                userlogin();
        }
    }

    private void userlogin() {
        String email = emailUser.getText().toString().trim();
        String password = passUser.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailUser.setError("Masukan Email Yang Valid");
            emailUser.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailUser.setError("Email Tidak Boleh Kosong");
            emailUser.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passUser.setError("Password Tidak Boleh Kosong");
            passUser.requestFocus();
            return;
        }
        System.out.println(email + "\n" + password);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginScreenAdmin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.println(task);
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                            databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    boolean isExcist = snapshot.exists();
                                    if (isExcist) {
                                        DataUser user = snapshot.getValue(DataUser.class);
                                        String status = user.getRole().toString();
                                        if (status.equals("admin")) {
                                            back();
                                        } else {
                                            Toast.makeText(LoginScreenAdmin.this, "Bukan Admin", Toast.LENGTH_LONG).show();
                                        }

                                    } else {
                                        Toast.makeText(LoginScreenAdmin.this, "Salah Password Atau Username", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.i("Result Sigin", "Failed 100");
                                }
                            });

                        } else {
                            Log.i("Result Sigin", "Failed 105");
                        }
                    }
                });
    }

    private void back() {
        startActivity(new Intent(this, adminPanel.class));
    }

}