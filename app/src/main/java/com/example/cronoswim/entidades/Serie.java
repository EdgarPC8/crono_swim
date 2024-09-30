package com.example.cronoswim.entidades;

import java.io.Serializable;

public class Serie implements Serializable {
    //Constantes campos tabla SERIE
    private int id;
    private int id_evento;
    private int numero;
    private int carril;
    private int cedula;
    private String nadador;
    private int id_institucion;
    private String tiempo;
    private int descalificado;
    private int puntos;
    private int lugar;
    private int premiado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCarril() {
        return carril;
    }

    public void setCarril(int carril) {
        this.carril = carril;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNadador() {
        return nadador;
    }

    public void setNadador(String nadador) {
        this.nadador = nadador;
    }

    public int getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(int id_institucion) {
        this.id_institucion = id_institucion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getDescalificado() {
        return descalificado;
    }

    public void setDescalificado(int descalificado) {
        this.descalificado = descalificado;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public int getPremiado() {
        return premiado;
    }

    public void setPremiado(int premiado) {
        this.premiado = premiado;
    }
}
