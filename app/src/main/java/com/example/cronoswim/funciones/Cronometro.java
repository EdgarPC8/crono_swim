package com.example.cronoswim.funciones;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.dialogos.Dialogo_Ajustes_Cronometro;
import com.example.cronoswim.entidades.ConfiguracionCrono;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Vueltas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Cronometro {
    private final Handler handler=new Handler();
    private final Funciones funcion=new Funciones();
    private final Context context;
    public final LinearLayout linearLayout;
    public TextView viewTiempo, viewNombre, viewFecha, viewMetros,viewPrueba, viewTimeRecord,viewVueltas;
    private Button btnStart, btnReset;
    private long millisecondTime, startTime, timeBuff, updateTime = 0L ;
    private int seconds, minutes, milliseconds,hours ;
    ArrayList<String> vuelta = new ArrayList<>();
    private int actualMilSeg,actualSeg,actualMin,actualHour;
    private static final String FORMATO_TIEMPO = "%02d:%02d:%02d,%02d";
    public boolean isRunning = true; // Variable para indicar si el cronómetro está en ejecución
    public final Cronometros cronometro;
    public View itemView;
    public ArrayList<Cronometro> listaCronometros;
    public ArrayList<Vueltas> arrayListVueltas = new ArrayList<Vueltas>();
    BluetoothManager bluetoothManager;
//    private GlobalBluetoothManager bluetoothManager;
    ConfiguracionCrono configuracion;
    private SentenciasSql sql ;

    public BluetoothManager getBluetoothManager() {
        return bluetoothManager;
    }

    public void setBluetoothManager(BluetoothManager bluetoothManager) {
        this.bluetoothManager = bluetoothManager;
    }

    public Cronometro(Context context, LinearLayout linearLayout, Cronometros cronometro) {
        this.context = context;
        this.linearLayout = linearLayout;
        this.cronometro = cronometro;
//        this.bluetoothManager = bluetoothManager;
        crearElementosDeVista();
        tiempo();
        correrCronometro();
//        Configuracion();
//        bluetoothManager = new BluetoothManager(context,this);
        sql= new SentenciasSql(context);
        configuracion =funcion.stringToConfiguracionCrono(cronometro.getConfig());


    }
    public ArrayList<Cronometro> getListaCronometros() {
        return listaCronometros;
    }
    public void setListaCronometros(ArrayList<Cronometro> listaCronometros) {
        this.listaCronometros = listaCronometros;
    }
    private void crearElementosDeVista() {
        LayoutInflater inflater = LayoutInflater.from(context);
        itemView = inflater.inflate(R.layout.crono_items, linearLayout, false);
        viewNombre = itemView.findViewById(R.id.viewNombre);
        viewTiempo = itemView.findViewById(R.id.viewTiempo);
        viewFecha = itemView.findViewById(R.id.viewVelocidad);
        viewVueltas = itemView.findViewById(R.id.viewPorcentaje);
        viewMetros = itemView.findViewById(R.id.viewMetros);
        viewPrueba = itemView.findViewById(R.id.viewPrueba);
        viewTimeRecord = itemView.findViewById(R.id.viewInfo);
        btnStart = itemView.findViewById(R.id.btnStart);
        btnReset = itemView.findViewById(R.id.btnReset);
        linearLayout.addView(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogo_Ajustes_Cronometro dialogo = new Dialogo_Ajustes_Cronometro(context,listaCronometros);
                dialogo.createAlertDialogEstadisticaCrono(linearLayout,cronometro.getId(),v,Cronometro.this).show();
            }
        });
    }
    public void correrCronometro(){
        SentenciasSql sql = new SentenciasSql(context);
        viewTimeRecord.setText(sql.getTimeRecord(cronometro));
        viewFecha.setText(sql.getDateTimeREcord(cronometro));
        cronometro.setInfo(sql.getDateTimeREcord(cronometro)+" "+cronometro.getMetros()+" "+cronometro.getPrueba());
        viewNombre.setText(cronometro.getNombre());
        viewMetros.setText(cronometro.getMetros());
        viewPrueba.setText(cronometro.getPrueba());

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    PararCrono();
                } else {
                    EmpezarCrono();
//                    bluetoothManager.getDispositivo();

                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {

                    GuardarMilSegMinHor();
                    Configuracion();

//                    Toast.makeText(context,""+configuracion.getSolo_vueltas(),
//                            Toast.LENGTH_SHORT).show();

                } else {
                    resetearCronometro();
                    cronometro.setVuelta("");
                    arrayListVueltas.clear();
                }
            }
        });

    }
    public final void tiempo() {
        String[] timeParts = cronometro.getTiempo().split(":");
        actualHour = Integer.parseInt(timeParts[0]);
        actualMin = Integer.parseInt(timeParts[1]);
        String[] secondsParts = timeParts[2].split(",");
        actualSeg = Integer.parseInt(secondsParts[0]);
        actualMilSeg = Integer.parseInt(secondsParts[1]);
        hours=actualHour;
        minutes=actualMin;
        seconds=actualSeg;
        milliseconds=actualMilSeg;
        viewTiempo.setText(formatTiempo(hours, minutes, seconds, milliseconds));
    }
    public ArrayList<String> getVuelta() {
        return vuelta;
    }
    public void EmpezarCrono (){
        isRunning = false;
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
        btnReset.setText("Vuelta");
        btnStart.setText("Parar");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", cronometro.getPosicion());
            jsonObject.put("nombre", cronometro.getNombre());
            jsonObject.put("activo", true);
            // Aquí puedes añadir más datos según necesites
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(bluetoothManager.enviarMensaje(jsonObject)){
            viewNombre.setText(cronometro.getNombre()+"BT"+cronometro.getPosicion());
        }

    }
    public void PararCrono(){
        isRunning=true;
        Estadisticas estadistica = new Estadisticas();
//        viewVelocidad.setText(estadistica.obtenerMetrosSobreSegundos(viewTiempo.getText().toString(), viewMetros.getText().toString()));
        btnStart.setText("Comenzar");
        btnReset.setText("Resetear");
        timeBuff += millisecondTime;
        handler.removeCallbacks(runnable);
        cronometro.setTiempo(viewTiempo.getText().toString());
        GuardarMilSegMinHor();//aqui se que da guardado el tiempo

    }
    public Runnable runnable = new Runnable() {
        //ESTE EJECUTA EL CRONOMETRO
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime+actualMilSeg;
            updateTime = timeBuff + millisecondTime;
            seconds = (int) (updateTime / 1000)+actualSeg;
            minutes = (seconds / 60)+actualMin;
            hours=minutes/60+actualHour;
            seconds = seconds % 60;
            minutes= minutes%60;
            milliseconds = (int) ((updateTime % 1000)/10);
            viewTiempo.setText(formatTiempo(hours, minutes, seconds, milliseconds));
            cronometro.setTiempo(formatTiempo(hours, minutes, seconds, milliseconds));
            handler.postDelayed(this, 0);
        }
    };
    private  void GuardarMilSegMinHor(){
        SentenciasSql sql= new SentenciasSql(context);
        sql.editCrono(cronometro);
    }
    public void resetearCronometro (){
        PararCrono();
        millisecondTime = 0L ;
        startTime = 0L ;
        timeBuff = 0L ;
        updateTime = 0L ;
        seconds = 0 ;
        minutes = 0 ;
        milliseconds = 0 ;
        actualMilSeg=0;
        actualSeg=0;
        actualMin=0;
        actualHour=0;
        viewTiempo.setText(formatTiempo(0, 0, 0, 0));

        cronometro.setTiempo(viewTiempo.getText().toString());

        cronometro.setVuelta("");
        viewVueltas.setText("");
        GuardarMilSegMinHor();//aqui se que da guardado el tiempo

    }
    private String formatTiempo(int hours, int minutes, int seconds, int milliseconds) {
        return String.format(FORMATO_TIEMPO, hours, minutes, seconds, milliseconds);
    }
    public void Configuracion(){
        if (configuracion.getSolo_vueltas()==1){



            if(!isRunning){
                Vueltas vueltas = new Vueltas(cronometro.getMetros(),cronometro.getPrueba(), cronometro.getTiempo());

                arrayListVueltas.add(vueltas);
                Funciones funcion = new Funciones();
                cronometro.setVuelta(funcion.getVueltas(arrayListVueltas));

                String[] arrayNombres = cronometro.getMetros().split(" ");
                int intervaloMetros = configuracion.getIntervaloMetros();
                int result = intervaloMetros + Integer.parseInt(arrayNombres[0]);
                viewMetros.setText(result + " Metros");
                cronometro.setMetros(result + " Metros");
                viewTimeRecord.setText(sql.getTimeRecord(cronometro));
                viewFecha.setText(sql.getDateTimeREcord(cronometro));
                viewVueltas.setText(""+arrayListVueltas.size());
            }

        }

    }

}
