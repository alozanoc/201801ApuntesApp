package com.alilozano.apuntesapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alilozano.apuntesapp.db.DatabaseHelper;
import com.alilozano.apuntesapp.models.Apunte;
import com.alilozano.apuntesapp.tables.ApunteTable;

import java.util.ArrayList;
import java.util.List;

public class ApunteDAO {
    
    final private DatabaseHelper dbHelper;
    final private String[] columns = {ApunteTable.C_ID,
            ApunteTable.C_TITULO,
            ApunteTable.C_CONTENIDO,
            ApunteTable.C_ETIQUETAS};
    public ApunteDAO(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void save(Apunte apunte) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ApunteTable.C_TITULO, apunte.getTitulo());
        values.put(ApunteTable.C_CONTENIDO, apunte.getContenido());
        values.put(ApunteTable.C_ETIQUETAS, apunte.getEtiquetas());
        db.insert(ApunteTable.TABLE_NAME, null, values);
        db.close();
    }

    public List<Apunte> all() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ApunteTable.TABLE_NAME, columns, null, null, null, null, null);
        List<Apunte> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ApunteTable.C_ID));
                String titulo = cursor.getString(cursor.getColumnIndex(ApunteTable.C_TITULO));
                String contenido = cursor.getString(cursor.getColumnIndex(ApunteTable.C_CONTENIDO));
                String etiquetas = cursor.getString(cursor.getColumnIndex(ApunteTable.C_ETIQUETAS));
                list.add(new Apunte(titulo, contenido, etiquetas));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


}
