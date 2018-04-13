package com.alilozano.apuntesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alilozano.apuntesapp.daos.ApunteDAO;
import com.alilozano.apuntesapp.models.Apunte;

public class ApunteFormActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apunte_form);
        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        if(index >= 0){
            ApunteDAO apunteDAO = new ApunteDAO(this);
            Apunte apunte = apunteDAO.all().get(index);
            TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
            TextView txtEtiquetas = (TextView) findViewById(R.id.txtEtiquetas);
            TextView txtContenido= (TextView) findViewById(R.id.txtContenido);

            txtTitulo.setText(apunte.getTitulo());
            txtEtiquetas.setText(apunte.getTitulo());
            txtContenido.setText(apunte.getContenido());
        }


    }

    @Override
    public void onClick(View view) {
        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        TextView txtEtiquetas = (TextView) findViewById(R.id.txtEtiquetas);
        TextView txtContenido= (TextView) findViewById(R.id.txtContenido);
        Apunte apunte = new Apunte();
        apunte.setTitulo(txtTitulo.getText().toString());
        apunte.setEtiquetas(txtEtiquetas.getText().toString());
        apunte.setContenido(txtContenido.getText().toString());
        ApunteDAO apunteDAO = new ApunteDAO(this);
        apunteDAO.save(apunte);
        finish();
    }
}
