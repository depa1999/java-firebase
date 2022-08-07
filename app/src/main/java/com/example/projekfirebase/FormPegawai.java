package com.example.projekfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormPegawai extends AppCompatActivity {

    EditText xkodebar,xnamabar,xjumlahbar, xhargabar, xsatbar;
    Button tblsimpan;

    DatabaseReference dbref;
    Pegawai pegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pegawai);

        xkodebar=findViewById(R.id.kode);
        xnamabar=findViewById(R.id.nama_barang);
        xsatbar=findViewById(R.id.satuan);
        xjumlahbar=findViewById(R.id.jumlah);
        xhargabar=findViewById(R.id.harga);
        tblsimpan=findViewById(R.id.tombolsimpan);

        pegawai=new Pegawai();
        dbref= FirebaseDatabase.getInstance().getReference().child("DataBarang");

        tblsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pegawai.setKode_bar(xkodebar.getText().toString().trim());
                pegawai.setNama_bar(xnamabar.getText().toString().trim());
                pegawai.setSatuan(xsatbar.getText().toString().trim());
                pegawai.setJumlah(xjumlahbar.getText().toString().trim());
                pegawai.setHarga(xhargabar.getText().toString().trim());
                dbref.push().setValue(pegawai);
            }
        });

    }
}