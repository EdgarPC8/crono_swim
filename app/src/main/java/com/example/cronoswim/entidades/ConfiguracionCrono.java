package com.example.cronoswim.entidades;

public class ConfiguracionCrono {
    private int solo_vueltas=0;
    private int intervaloMetros=25;


    public int getSolo_vueltas() {
        return solo_vueltas;
    }

    public void setSolo_vueltas(int solo_vueltas) {
        this.solo_vueltas = solo_vueltas;
    }

    public int getIntervaloMetros() {
        return intervaloMetros;
    }

    public void setIntervaloMetros(int intervaloMetros) {
        this.intervaloMetros = intervaloMetros;
    }
}
