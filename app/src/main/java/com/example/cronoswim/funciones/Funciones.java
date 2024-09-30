package com.example.cronoswim.funciones;

import com.example.cronoswim.entidades.ConfiguracionCrono;
import com.example.cronoswim.entidades.HoraMinSegMilseg;
import com.example.cronoswim.entidades.Vueltas;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Funciones {
    public  String obtenerFechaActual(){
        LocalDate fechaActual = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fechaActual = LocalDate.now();
        }
        String[] partes = fechaActual.toString().split("-");
        String fechaActualString=partes[0]+"-"+partes[1]+"-"+partes[2];

        return fechaActual.toString();
    }
    public int obtenerSegundos(String tiempo){
        int milisegundos;
        int segundos;
        int minutos;
        int horas;
        String[] HoraMin = tiempo.split(":");
        String[] SegMilseg = HoraMin[2].split(",");
        milisegundos=Integer.parseInt(SegMilseg[1]);
        segundos=Integer.parseInt(SegMilseg[0]);
        minutos=Integer.parseInt(HoraMin[1]);
        horas=Integer.parseInt(HoraMin[0]);

        if (minutos>0){
            for (int i = 0; i < minutos; i++) {
                segundos+=60;
            }
        }
        return segundos;
    }
    public int obtenerMetros(String metrosStr){
        int metros;
        String texto = metrosStr.replaceAll(" Metros", "");
        System.out.println("--"+texto+"---");
        metros=Integer.parseInt(texto);
        return metros;
    }
    public  String arrayListObjetosToString(ConfiguracionCrono obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
    public ConfiguracionCrono stringToConfiguracionCrono(String jsonString) {
        Gson gson = new Gson();
        ConfiguracionCrono obj = gson.fromJson(jsonString, ConfiguracionCrono.class);
        return obj;
    }
    private long convertirTiempoAMilisegundos(String tiempo) {
        String[] partes = tiempo.split(":");
        String[] segundosYMilesimas = partes[2].split(",");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        int segundos = Integer.parseInt(segundosYMilesimas[0]);
        int milisegundos = Integer.parseInt(segundosYMilesimas[1]);

        return (horas * 3600 + minutos * 60 + segundos) * 1000 + milisegundos*10;
    }

    private String convertirMilisegundosATiempo(long milisegundos) {
        long horas = milisegundos / 3600000;
        milisegundos %= 3600000;
        long minutos = milisegundos / 60000;
        milisegundos %= 60000;
        long segundos = milisegundos / 1000;
        long milesimas = milisegundos % 1000;

        // Formatear milisegundos para asegurar dos dígitos decimales
        String milesimasStr = String.format("%03d", milesimas);
        milesimasStr = milesimasStr.substring(0, 2);

        return String.format("%02d:%02d:%02d,%s", horas, minutos, segundos, milesimasStr);
    }


    public String getDiferenciasEntreVueltas(ArrayList<Vueltas> arrayListVueltas) {
        if (arrayListVueltas == null || arrayListVueltas.size() < 2) {
            return ""; // No hay suficientes vueltas para calcular diferencias
        }

        StringBuilder result = new StringBuilder();

        for (int i = arrayListVueltas.size() - 1; i > 0; i--) {
            Vueltas vueltaActual = arrayListVueltas.get(i);
            Vueltas vueltaAnterior = arrayListVueltas.get(i - 1);

            long tiempoActualMs = convertirTiempoAMilisegundos(vueltaActual.getTiempo());
            long tiempoAnteriorMs = convertirTiempoAMilisegundos(vueltaAnterior.getTiempo());

            long diferenciaMs = tiempoActualMs - tiempoAnteriorMs;
            String diferenciaTiempo = convertirMilisegundosATiempo(diferenciaMs);

            result.append(vueltaActual.getMetros()).append("--").append(diferenciaTiempo).append("--").append(vueltaActual.getTiempo()).append("\n");
        }

        // Agregar la primera vuelta al final
        Vueltas primeraVuelta = arrayListVueltas.get(0);
        result.append(primeraVuelta.getMetros()).append("--").append(primeraVuelta.getTiempo()).append("--").append(primeraVuelta.getTiempo()).append("\n");

        return result.toString();
    }

    public String getVueltas(ArrayList<Vueltas> arrayListVueltas) {
        if(arrayListVueltas.size()<=1){
            return arrayListVueltas.get(0).getMetros()+""+arrayListVueltas.get(0).getTiempo()+"--"+arrayListVueltas.get(0).getTiempo();
        }

        String result = getDiferenciasEntreVueltas(arrayListVueltas);
        return result;
    }

    private String formatTiempo(int hours, int minutes, int seconds, int milliseconds) {
        return String.format("%02d:%02d:%02d,%02d", hours, minutes, seconds, milliseconds);
    }
    public  HoraMinSegMilseg getEntidadTiempo(String tiempo){
        String[] hora_min_seg = tiempo.split(":");
        String[] milseg = hora_min_seg[2].split(",");
        int hora=Integer.parseInt(hora_min_seg[0]);
        int min=Integer.parseInt(hora_min_seg[1]);
        int seg=Integer.parseInt(milseg[0]);
        int mil=Integer.parseInt(milseg[1]);
        HoraMinSegMilseg time = new HoraMinSegMilseg(hora,min,seg,mil);
        return time;
    }

    public  String getResultSumaTiempo(String tiempo, String tiempo2){
        int hora,min,seg,mil;
        HoraMinSegMilseg time = getEntidadTiempo(tiempo);
        HoraMinSegMilseg time2 = getEntidadTiempo(tiempo2);

        mil=time.getMilisegundo()+time2.getMilisegundo();
        seg=time.getSegundo()+time2.getSegundo();
        min=time.getMinuto()+time2.getMinuto();
        hora=time.getHora()+time2.getHora();
        if(mil>=100){seg++;mil=mil-100;}
        if(seg>=60){min++;seg=seg-60;}
        if(min>=60){hora++;min=min-60;}

        return formatTiempo(hora,min,seg,mil);
    }
    public String getResultRestaTiempo(String tiempo1, String tiempo2) {
        HoraMinSegMilseg time1 = getEntidadTiempo(tiempo1);
        HoraMinSegMilseg time2 = getEntidadTiempo(tiempo2);

        int milisegundos = time1.getMilisegundo() - time2.getMilisegundo();
        int segundos = time1.getSegundo() - time2.getSegundo();
        int minutos = time1.getMinuto() - time2.getMinuto();
        int horas = time1.getHora() - time2.getHora();

        if (milisegundos < 0) {
            segundos--;
            milisegundos += 100;
        }
        if (segundos < 0) {
            minutos--;
            segundos += 60;
        }
        if (minutos < 0) {
            horas--;
            minutos += 60;
        }
        if (horas < 0) {
            horas = 0;
        }

        return formatTiempo(Math.abs(horas), Math.abs(minutos), Math.abs(segundos), Math.abs(milisegundos));
    }




}
