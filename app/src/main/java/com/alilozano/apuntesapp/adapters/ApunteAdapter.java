package com.alilozano.apuntesapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alilozano.apuntesapp.ApunteFormActivity;
import com.alilozano.apuntesapp.R;
import com.alilozano.apuntesapp.daos.ApunteDAO;
import com.alilozano.apuntesapp.models.Apunte;
import java.util.List;

public class ApunteAdapter extends ArrayAdapter {
    public ApunteAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @Override
    public int getItemViewType(int position) {
        final Apunte apunte = (Apunte) getItem(position);
        if(apunte.isEstrella()){
            return 0;
        }else{
            return 1;
        }
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final Apunte apunte = (Apunte) getItem(position);
        if(view == null){
            if(apunte.isEstrella()){
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.nota_detail_layout_favorito, parent,false);
            }else{
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.nota_detail_layout, parent,false);
            }
        }

        TextView txtTitulo = view.findViewById(R.id.txtTitulo);
        TextView txtResumen = view.findViewById(R.id.txtResumen);
        TextView txtEtiquetas = view.findViewById(R.id.txtEtiquetas);
        txtTitulo.setText(apunte.getTitulo() + (apunte.isFijo() ? "(Fijo)" : ""));
        txtResumen.setText(apunte.getContenido());
        txtEtiquetas.setText(apunte.getEtiquetas());
        ImageButton btnDelete = view.findViewById(R.id.btnEliminar);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApunteDAO apunteDAO = new ApunteDAO(getContext());
                apunteDAO.delete(apunte.getId());
                actualizar();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ApunteFormActivity.class);
                intent.putExtra("id", apunte.getId());
                getContext().startActivity(intent);
            }
        });

        ImageView star = view.findViewById(R.id.btnStar);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apunte.setEstrella(!apunte.isEstrella());
                ApunteDAO apunteDAO = new ApunteDAO(getContext());
                apunteDAO.save(apunte);
                actualizar();
            }
        });

        ImageView lock = view.findViewById(R.id.btnLock);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apunte.setFijo(!apunte.isFijo());
                ApunteDAO apunteDAO = new ApunteDAO(getContext());
                apunteDAO.save(apunte);
                actualizar();
            }
        });

        return view;
    }
    private void actualizar(){
        this.clear();
        this.addAll(new ApunteDAO(getContext()).all());
        this.notifyDataSetChanged();
    }
}
