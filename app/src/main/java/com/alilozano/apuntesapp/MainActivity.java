package com.alilozano.apuntesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alilozano.apuntesapp.adapters.ApunteAdapter;
import com.alilozano.apuntesapp.daos.ApunteDAO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Hola Mundo", Toast.LENGTH_SHORT).show();

        Button button = (Button) findViewById(R.id.btnNuevoApunte);

        button.setOnClickListener(this);
        ApunteDAO apunteDAO = new ApunteDAO(this);
        ApunteAdapter adapter = new ApunteAdapter(this, apunteDAO.all());
        ListView listView = (ListView) findViewById(R.id.listViewApuntes);
        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), ApunteFormActivity.class);
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });*/
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = (ListView) findViewById(R.id.listViewApuntes);
        ArrayAdapter adapter = (ArrayAdapter)listView.getAdapter();
        adapter.clear();
        adapter.addAll(new ApunteDAO(this).all());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ApunteFormActivity.class);
        startActivity(intent);

    }
}
