package com.example.cronoswim.entidades;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroCronometros extends AppCompatActivity {
    private int id;
    private String milisegudno;
    private String segundo;
    private String minuto;
    private String hora;
    private int onOff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMilisegudno() {
        return milisegudno;
    }

    public void setMilisegudno(String milisegudno) {
        this.milisegudno = milisegudno;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getOnOff() {
        return onOff;
    }

    public void setOnOff(int onOff) {
        this.onOff = onOff;
    }



}
