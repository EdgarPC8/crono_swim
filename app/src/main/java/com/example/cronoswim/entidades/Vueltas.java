package com.example.cronoswim.entidades;

public class Vueltas {
    private int id;
    private String metros;
    private String tiempo;
    private String prueba;
    private String vuelta_tiempo;
    private int numero_vuelta;
    private String config;
    private int numVueltas;

    public Vueltas(String metros, String prueba, String tiempo) {
        this.metros = metros;
        this.tiempo = tiempo;
    }

    public String getMetros() {
        return metros;
    }

    public String getTiempo() {
        return tiempo;
    }
    public String getPrueba() {
        return prueba;
    }
}
