package com.example.cronoswim.data_bases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion_BD extends SQLiteOpenHelper {
    public Conexion_BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tablas_BD.CREAR_TABLA_CRONO);
        db.execSQL(Tablas_BD.CREAR_TABLA_NADADOR);
        db.execSQL(Tablas_BD.CREAR_TABLA_PRUEBAS);
        db.execSQL(Tablas_BD.CREAR_TABLA_METROS);
        db.execSQL(Tablas_BD.CREAR_TABLA_TIEMPOS);
        db.execSQL(Tablas_BD.CREAR_TABLA_CUENTA);
        db.execSQL(Tablas_BD.CREAR_TABLA_USUARIO);
        db.execSQL(Tablas_BD.CREAR_TABLA_ROLES);
        db.execSQL(Tablas_BD.CREAR_TABLA_COMPETENCIA);
        db.execSQL(Tablas_BD.CREAR_TABLA_SERIE);
        db.execSQL(Tablas_BD.CREAR_TABLA_EVENTO);
        db.execSQL(Tablas_BD.CREAR_TABLA_INSTITUCION_NADADOR);
        db.execSQL(Tablas_BD.CREAR_TABLA_INSTITUCION);
        db.execSQL(Tablas_BD.CREAR_TABLA_LOGS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_NADADOR);
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_TIEMPOS);
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_CRONO);
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_PRUEBAS);
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_METROS);
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_CUENTA);
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_USUARIO);
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_ROLES);
//        db.execSQL("DROP TABLE IF EXISTS competencia");
//        db.execSQL("DROP TABLE IF EXISTS serie");
//        db.execSQL("DROP TABLE IF EXISTS evento");
//        db.execSQL("DROP TABLE IF EXISTS institucion");
//        db.execSQL("DROP TABLE IF EXISTS institucion_nadador");
//        db.execSQL("DROP TABLE IF EXISTS "+Tablas_BD.TABLA_COMPETENCIA);
        onCreate(db);
    }
}


