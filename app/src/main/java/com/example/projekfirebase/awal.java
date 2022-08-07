package com.example.projekfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class awal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
    }

    public void masuk_ke(View view) {
        Intent intent=new Intent(awal.this,TampilDataGuru.class);
        startActivity(intent);
    }
}