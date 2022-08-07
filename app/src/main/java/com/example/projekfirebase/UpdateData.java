package com.example.projekfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateData extends AppCompatActivity {

    //Deklarasi Variable
    private EditText kodebaru;
    private EditText namabarangbaru;
    private EditText satuanbaru;
    private EditText jumlahbaru;
    private EditText hargabaru;
    private Button btnupdate, btndelete, btnview;
    private DatabaseReference database;
    private String cekKode, cekNamaBarang, cekJumlah, cekHarga, cekSatuan;
    Pegawai pegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        kodebaru = findViewById(R.id.new_kode);
        namabarangbaru = findViewById(R.id.new_namabarang);
        satuanbaru = findViewById(R.id.new_satuan);
        jumlahbaru = findViewById(R.id.new_jumlah);
        hargabaru = findViewById(R.id.new_harga);
        btnupdate = findViewById(R.id.tblupdate);
        btndelete = findViewById(R.id.tbldelete);
        btnview = findViewById(R.id.tblview);

        pegawai=new Pegawai();
        database = FirebaseDatabase.getInstance().getReference();
        getData();

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pegawai setpegawai = new Pegawai();
                setpegawai.setKode_bar(kodebaru.getText().toString());
                setpegawai.setNama_bar(namabarangbaru.getText().toString());
                setpegawai.setJumlah(jumlahbaru.getText().toString());
                setpegawai.setHarga(hargabaru.getText().toString());
                setpegawai.setSatuan(satuanbaru.getText().toString());
                deleteMahasiswa(setpegawai);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mendapatkan Data
                cekKode = kodebaru.getText().toString();
                cekNamaBarang = namabarangbaru.getText().toString();
                cekSatuan = satuanbaru.getText().toString();
                cekJumlah = jumlahbaru.getText().toString();
                cekHarga = hargabaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong
                if(isEmpty(cekNamaBarang) || isEmpty(cekNamaBarang)){
                    Toast.makeText(UpdateData.this,"Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    Pegawai setpegawai = new Pegawai();
                    setpegawai.setKode_bar(kodebaru.getText().toString());
                    setpegawai.setNama_bar(namabarangbaru.getText().toString());
                    setpegawai.setSatuan(satuanbaru.getText().toString());
                    setpegawai.setJumlah(jumlahbaru.getText().toString());
                    setpegawai.setHarga(hargabaru.getText().toString());
                    updateMahasiswa(setpegawai);
                }
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateData.this,TampilDataGuru.class);
                startActivity(intent);
            }
        });
    }

    private void deleteMahasiswa(Pegawai pegawai) {
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("DataBarang")
                .child(getKey)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateData.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    //Menampilkan data yang akan diupdate
    private void getData(){
        //Menampilkan data dari item yang dipilih sebelumnya
        final String getkode = getIntent().getExtras().getString("dataKode");
        final String getnama = getIntent().getExtras().getString("dataNama");
        final String getsatuan = getIntent().getExtras().getString("dataSatuan");
        final String getjumlah = getIntent().getExtras().getString("dataJumlah");
        final String getharga = getIntent().getExtras().getString("dataHarga");

        kodebaru.setText(getkode);
        namabarangbaru.setText(getnama);
        satuanbaru.setText(getsatuan);
        jumlahbaru.setText(getjumlah);
        hargabaru.setText(getharga);
    }

    //Proses update data yang sudah ditentukan
    private void updateMahasiswa(Pegawai pegawai){
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("DataBarang")
                .child(getKey)
                .setValue(pegawai)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        kodebaru.setText("");
                        namabarangbaru.setText("");
                        satuanbaru.setText("");
                        jumlahbaru.setText("");
                        hargabaru.setText("");
                        Toast.makeText(UpdateData.this,"Data Berhasil Diupdate",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
