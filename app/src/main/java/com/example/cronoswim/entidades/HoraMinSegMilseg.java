package com.example.cronoswim.entidades;

import java.io.Serializable;

public class HoraMinSegMilseg implements Serializable {
    private int hora;
    private int minuto;
    private int segundo;
    private int milisegundo;

    public HoraMinSegMilseg(int hora, int minuto, int segundo, int milisegundo) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
        this.milisegundo = milisegundo;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public int getMilisegundo() {
        return milisegundo;
    }
}
