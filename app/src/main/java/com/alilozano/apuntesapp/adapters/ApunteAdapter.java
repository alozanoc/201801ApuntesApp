package com.alilozano.apuntesapp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alilozano.apuntesapp.R;
import com.alilozano.apuntesapp.daos.ApunteDAO;
import com.alilozano.apuntesapp.models.Apunte;
import java.util.List;

public class ApunteAdapter extends ArrayAdapter {
    public ApunteAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.nota_detail_layout, parent,false);
        }
        Apunte apunte = (Apunte) getItem(position);
        TextView txtTitulo = view.findViewById(R.id.txtTitulo);
        TextView txtResumen = view.findViewById(R.id.txtResumen);
        TextView txtEtiquetas = view.findViewById(R.id.txtEtiquetas);
        txtTitulo.setText(apunte.getTitulo());
        txtResumen.setText(apunte.getContenido());
        txtEtiquetas.setText(apunte.getEtiquetas());
        return view;
    }
}
