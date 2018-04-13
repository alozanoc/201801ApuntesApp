package com.alilozano.apuntesapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alilozano.apuntesapp.tables.ApunteTable;

import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "midatabase.db";
    private static DatabaseHelper instancia;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if(instancia == null){
            instancia = new DatabaseHelper(context);
        }
        return instancia;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ApunteTable.TABLE_NAME + " (" +
                ApunteTable.C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApunteTable.C_TITULO + " TEXT," +
                ApunteTable.C_ETIQUETAS + " TEXT," +
                ApunteTable.C_CONTENIDO + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int versionActual,
                          int nuevaVersion) {
        sqLiteDatabase.execSQL("DROP TABLE "+ApunteTable.TABLE_NAME);

        this.onCreate(sqLiteDatabase);

    }
}
