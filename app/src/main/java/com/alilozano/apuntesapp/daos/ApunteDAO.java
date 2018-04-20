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
            ApunteTable.C_ETIQUETAS,
            ApunteTable.C_IS_ESTRELLA,
            ApunteTable.C_IS_FIJO
    };
    public ApunteDAO(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void save(Apunte apunte) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ApunteTable.C_TITULO, apunte.getTitulo());
        values.put(ApunteTable.C_CONTENIDO, apunte.getContenido());
        values.put(ApunteTable.C_ETIQUETAS, apunte.getEtiquetas());
        values.put(ApunteTable.C_IS_ESTRELLA, apunte.isEstrella());
        values.put(ApunteTable.C_IS_FIJO, apunte.isFijo());

        if(apunte.getId()== -1) {
            db.insert(ApunteTable.TABLE_NAME, null, values);
        }else{
            // actualizar
            db.update(ApunteTable.TABLE_NAME, values, "id=?",
                    new String[]{String.valueOf(apunte.getId())});
        }
        db.close();
    }

    public List<Apunte> all() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ApunteTable.TABLE_NAME, columns, null, null, null, null, ApunteTable.C_IS_FIJO + " desc");
        List<Apunte> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ApunteTable.C_ID));
                String titulo = cursor.getString(cursor.getColumnIndex(ApunteTable.C_TITULO));
                String contenido = cursor.getString(cursor.getColumnIndex(ApunteTable.C_CONTENIDO));
                String etiquetas = cursor.getString(cursor.getColumnIndex(ApunteTable.C_ETIQUETAS));
                boolean isEstrella = cursor.getInt(cursor.getColumnIndex(ApunteTable.C_IS_ESTRELLA)) == 1;
                boolean isFijo = cursor.getInt(cursor.getColumnIndex(ApunteTable.C_IS_FIJO)) == 1;
                Apunte apunte = new Apunte(titulo, contenido, etiquetas);
                apunte.setEstrella(isEstrella);
                apunte.setFijo(isFijo);
                apunte.setId(id);
                list.add(apunte);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public int delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows =  db.delete(ApunteTable.TABLE_NAME, ApunteTable.C_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public Apunte get(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ApunteTable.TABLE_NAME, columns, "id=?",
                new String[]{String.valueOf(id)}, null, null, null);
        Apunte apunte = null;
        if (cursor.moveToFirst()) {
            String titulo = cursor.getString(cursor.getColumnIndex(ApunteTable.C_TITULO));
            String contenido = cursor.getString(cursor.getColumnIndex(ApunteTable.C_CONTENIDO));
            String etiquetas = cursor.getString(cursor.getColumnIndex(ApunteTable.C_ETIQUETAS));
            boolean isEstrella = cursor.getInt(cursor.getColumnIndex(ApunteTable.C_IS_ESTRELLA)) == 1;
            boolean isFijo = cursor.getInt(cursor.getColumnIndex(ApunteTable.C_IS_FIJO)) == 1;
            apunte = new Apunte(titulo, contenido, etiquetas);
            apunte.setEstrella(isEstrella);
            apunte.setFijo(isFijo);
            apunte.setId(id);
        }
        cursor.close();
        db.close();
        return apunte;
    }

}
