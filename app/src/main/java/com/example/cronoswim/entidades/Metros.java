package com.example.cronoswim.entidades;

import java.io.Serializable;

public class Metros implements Serializable {
    private int id;
    private String metros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetros() {
        return metros;
    }

    public void setMetros(String metros) {
        this.metros = metros;
    }
}
