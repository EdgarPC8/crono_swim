package com.example.cronoswim.entidades;

public class Cronometros {
    private int id;
    private String nombre;
    private  String metros;
    private  String prueba;
    private  String config;
    private  String rendimiento;
    private String tiempo;
    private String vuelta;
    private String info;
    private int cedula;
    private int posicion;
    private boolean checked;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getMetros() {
        return metros;
    }
    public void setMetros(String metros) {
        this.metros = metros;
    }
    public String getPrueba() {
        return prueba;
    }
    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }
    public String getConfig() {
        return config;
    }
    public void setConfig(String config) {
        this.config = config;
    }
    public String getRendimiento() {
        return rendimiento;
    }
    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }
    public String getVuelta() {
        return vuelta;
    }
    public void setVuelta(String vuelta) {
        this.vuelta = vuelta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

}
