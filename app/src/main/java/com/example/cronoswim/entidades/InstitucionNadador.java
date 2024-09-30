package com.example.cronoswim.entidades;

import java.io.Serializable;

public class InstitucionNadador implements Serializable {
    private int id;
    private int id_nadador;
    private int id_institucion;
    private int id_competencia;
    private String categoria;
    private String configCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_nadador() {
        return id_nadador;
    }

    public void setId_nadador(int id_nadador) {
        this.id_nadador = id_nadador;
    }

    public int getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(int id_institucion) {
        this.id_institucion = id_institucion;
    }

    public int getId_competencia() {
        return id_competencia;
    }

    public void setId_competencia(int id_competencia) {
        this.id_competencia = id_competencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConfigCheck() {
        return configCheck;
    }

    public void setConfigCheck(String configCheck) {
        this.configCheck = configCheck;
    }
}
