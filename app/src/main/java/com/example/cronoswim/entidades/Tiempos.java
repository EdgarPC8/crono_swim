package com.example.cronoswim.entidades;

import java.io.Serializable;

public class Tiempos implements Serializable {
    private int id;
    private String fecha;
    private String cedula;
    private String prueba;
    private String metros;
    private String tiempo;
    private String nadador;

    public String getNadador() {
        return nadador;
    }

    public void setNadador(String nadador) {
        this.nadador = nadador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public String getMetros() {
        return metros;
    }

    public void setMetros(String metros) {
        this.metros = metros;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
