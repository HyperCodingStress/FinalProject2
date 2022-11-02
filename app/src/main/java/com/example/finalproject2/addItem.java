package com.example.finalproject2;

import static com.google.firebase.database.core.RepoManager.clear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addItem extends AppCompatActivity {
    EditText name,stok,url;
    Button add;
    private RadioGroup radioGroup;
    public String kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        name = (EditText) findViewById(R.id.tambahNAMA);
        stok = (EditText) findViewById(R.id.tambahSTOK);
        url = (EditText) findViewById(R.id.tambahURL);
        add = (Button)findViewById(R.id.tombolADD);
        radioGroup = findViewById(R.id.kategori);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                kategori = radioButton.getText().toString();
            }
        });
        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void clearText() {
        name.setText("");
        url.setText("");
        stok.setText("");
    }

    private void insertData(){
        Map<String, Object> data = new HashMap<>();
        data.put("nama",name.getText().toString());
        data.put("stok",stok.getText().toString());
        data.put("url",url.getText().toString());
        System.out.println(data);
        FirebaseDatabase.getInstance().getReference().child("Barang").child(kategori).push().
                setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(addItem.this,"Data Telah Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                        clearText();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addItem.this,"Data Gagal Ditambahkan",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}