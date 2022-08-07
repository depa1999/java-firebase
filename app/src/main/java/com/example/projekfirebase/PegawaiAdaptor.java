package com.example.projekfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PegawaiAdaptor extends RecyclerView.Adapter<PegawaiAdaptor.ViewHolder> {

    private final ArrayList<Pegawai> listpegawai;
    private final Context context;

    public PegawaiAdaptor(ArrayList<Pegawai> listpegawai, Context context) {
        this.listpegawai = listpegawai;
        this.context = context;
    }



    class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView hkode, hnama,hjumlah,hharga,hsatuan;
        private LinearLayout ListItem;
        ViewHolder(View itemView) {
            super(itemView);
            hkode = itemView.findViewById(R.id.kobar);
            hnama = itemView.findViewById(R.id.nabar);
            hsatuan = itemView.findViewById(R.id.satbar);
            hjumlah=itemView.findViewById(R.id.jumbar);
            hharga=itemView.findViewById(R.id.harbar);
            ListItem = itemView.findViewById(R.id.masterdataguru);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_format_tampilan, parent,false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String kode = listpegawai.get(position).getKode_bar();
        final String nama = listpegawai.get(position).getNama_bar();
        final String satuan = listpegawai.get(position).getSatuan();
        final String jumlah=listpegawai.get(position).getJumlah();
        final String harga=listpegawai.get(position).getHarga();

        holder.hkode.setText(""+kode);
        holder.hnama.setText(""+nama);
        holder.hsatuan.setText(""+satuan);
        holder.hjumlah.setText(""+jumlah);
        holder.hharga.setText(""+harga);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("dataKode",listpegawai.get(position).getKode_bar());
                bundle.putString("dataNama",listpegawai.get(position).getNama_bar());
                bundle.putString("dataSatuan",listpegawai.get(position).getSatuan());
                bundle.putString("dataJumlah",listpegawai.get(position).getJumlah());
                bundle.putString("dataHarga",listpegawai.get(position).getHarga());

                bundle.putString("getPrimaryKey",listpegawai.get(position).getKey());
                Intent intent = new Intent(v.getContext(), UpdateData.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                return true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return listpegawai.size();
    }

    public interface datalistener {
        void onDeleteData(Pegawai data, int position);
    }
    datalistener listener;
}
