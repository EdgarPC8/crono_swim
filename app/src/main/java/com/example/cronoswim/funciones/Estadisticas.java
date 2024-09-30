package com.example.cronoswim.funciones;

public class Estadisticas {
    Funciones funcion = new Funciones();
    public String obtenerMetrosSobreSegundos(String tiempo,String metrosStr){
        int segundos=funcion.obtenerSegundos(tiempo);
        int metros=funcion.obtenerMetros(metrosStr);
        double velocidad=metros/segundos;
        return velocidad+"m/s";

    }
}
