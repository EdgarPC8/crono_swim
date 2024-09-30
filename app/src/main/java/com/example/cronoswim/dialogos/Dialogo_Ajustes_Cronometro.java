package com.example.cronoswim.dialogos;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cronoswim.R;
import com.example.cronoswim.adaptadores.CheckBoxAdapter;
import com.example.cronoswim.adaptadores.CheckBoxAdapterNadador;
import com.example.cronoswim.adaptadores.ListaAdapterCronometro;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.entidades.ConfiguracionCrono;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Tiempos;
import com.example.cronoswim.entidades.Vueltas;
import com.example.cronoswim.funciones.BluetoothManager;
import com.example.cronoswim.funciones.Cronometro;
import com.example.cronoswim.funciones.Funciones;
import com.example.cronoswim.funciones.Opciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Dialogo_Ajustes_Cronometro extends AlertDialog{

    private Context context;
    private ListaAdapterCronometro adapter;
    private ArrayList<Cronometro> listaCronometros;
    private View rootview;
    private RecyclerView recyclerView;
    private Cronometro crono;
    public Dialogo_Ajustes_Cronometro(Context context,ArrayList<Cronometro> listaCronometros) {
        super(context);
        this.context=context;
        this.listaCronometros=listaCronometros;
    }
    @SuppressLint("SetTextI18n")
    public AlertDialog createAlertDialogEstadisticaCrono(LinearLayout linearLayout, int id, View itemView, Cronometro crono) {
        SentenciasSql sentenciaSql = new SentenciasSql(context);
        Opciones opcion= new Opciones();
        RecyclerView recyclerView;
        Button btnGuardar,btnEliminar,btnCambiar,btnPosicion,btnGuardarAll;
        TextView viewNombre,viewVelocidad,viewRendimiento,viewVueltas,viewInfo;
        Cronometros entidadCrono=new Cronometros();
        Spinner comboPrueba,comboMetros;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_estadisticas_crono, null);
        builder.setView(view);
        viewNombre = view.findViewById(R.id.viewNombre);
//        viewRendimiento = view.findViewById(R.id.viewRendimiento);
        viewVueltas = view.findViewById(R.id.viewVueltas);
//        viewVelocidad = view.findViewById(R.id.viewVelocidad);
        viewInfo = view.findViewById(R.id.viewInfo);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        btnGuardarAll = view.findViewById(R.id.btnGuardarAll);
        btnCambiar = view.findViewById(R.id.btnCambiar);
        btnPosicion = view.findViewById(R.id.btnPosicion);
        btnEliminar = view.findViewById(R.id.btnEliminar);
        comboPrueba = view.findViewById(R.id.comboPrueba);
        comboMetros = view.findViewById(R.id.combo);
        SentenciasSql sql = new SentenciasSql(context);



        viewNombre.setText(crono.cronometro.getNombre());
        viewVueltas.setText(crono.cronometro.getVuelta());
        viewInfo.setText(crono.cronometro.getInfo());
        ArrayList<String>  listaPruebas=sentenciaSql.obtenerArrayPruebas();
        ArrayList<String>  listaMetros=sentenciaSql.obtenerArrayMetros();
        ArrayAdapter<CharSequence> adaptadorPruebas=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaPruebas);
        ArrayAdapter<CharSequence> adaptadorMetros=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaMetros);
        comboPrueba.setAdapter(adaptadorPruebas);
        comboMetros.setAdapter(adaptadorMetros);
        comboPrueba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String prueba =parent.getItemAtPosition(position).toString();
                String [] lista=prueba.split(".-");
                if (lista[0]!="Seleccione la Prueba"){
                    entidadCrono.setPrueba(lista[1]);
                }else{
                    entidadCrono.setPrueba(crono.cronometro.getPrueba());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboMetros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String metros =parent.getItemAtPosition(position).toString();
                String [] lista=metros.split(".-");
                if (metros!="Seleccione los Metros"){
                    entidadCrono.setMetros(lista[1]);
                }else{
                    entidadCrono.setMetros(crono.cronometro.getMetros());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle("Cronometro").setNegativeButton("Cancelar", null).setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Mostrar el cuadro de diálogo
        dialog.show();
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opciones opcion = new Opciones();
                opcion.deleteCrono(linearLayout,itemView);
                listaCronometros.remove(crono);
                opcion.actualizarPositions(linearLayout,listaCronometros);
                SentenciasSql crono = new SentenciasSql(context);
                crono.eliminarRegistro(id);
                dialog.dismiss();
            }
        });
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crono.viewMetros.setText(entidadCrono.getMetros());
                crono.viewPrueba.setText(entidadCrono.getPrueba());
                crono.cronometro.setMetros(entidadCrono.getMetros());
                crono.cronometro.setPrueba(entidadCrono.getPrueba());
                crono.viewTimeRecord.setText(sentenciaSql.getTimeRecord(crono.cronometro));
                crono.cronometro.setPrueba(entidadCrono.getPrueba());
                crono.cronometro.setMetros(entidadCrono.getMetros());
                crono.viewFecha.setText(sql.getDateTimeREcord(crono.cronometro));
                crono.cronometro.setInfo(sql.getDateTimeREcord(crono.cronometro)+" "+entidadCrono.getMetros()+" "+entidadCrono.getPrueba());
                sentenciaSql.editCrono(crono.cronometro);
//                sentenciaSql.saveTime(crono.cronometro);

                dialog.dismiss();
            }
        });
        btnPosicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlertDialogPositions(linearLayout,itemView,crono,listaCronometros);
                dialog.dismiss();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Objects.equals(crono.cronometro.getTiempo(), "00:00:00,00")){
                    Toast.makeText(context,crono.cronometro.getTiempo()+"Tiempo registrado",Toast.LENGTH_SHORT).show();
                    sql.saveTime(crono.cronometro);
                    crono.Configuracion();
                    viewVueltas.setText(crono.cronometro.getVuelta());

                }
            }
        });
        btnGuardarAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Objects.equals(crono.cronometro.getTiempo(), "00:00:00,00")){
//                    Toast.makeText(context,crono.cronometro.getTiempo()+"Tiempo registrado",Toast.LENGTH_SHORT).show();
//                    sql.saveTime(crono.cronometro);
//                    crono.Configuracion();
//                    viewVueltas.setText(crono.cronometro.getVuelta());
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("¿Estás seguro de que quieres continuar?")
                            .setTitle("Confirmación")
                            .setPositiveButton("Sí", new OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Acción a realizar cuando se presiona "Sí"
                                    for (int i = 0; i < crono.arrayListVueltas.size(); i++) {
                                        Vueltas vuelta = crono.arrayListVueltas.get(i);
                                        Cronometros cronoVuelta= new Cronometros();
                                        cronoVuelta.setPrueba(crono.cronometro.getPrueba());
                                        cronoVuelta.setMetros(vuelta.getMetros());
                                        cronoVuelta.setTiempo(vuelta.getTiempo());
                                        cronoVuelta.setCedula(crono.cronometro.getCedula());
                                        sql.saveTime(cronoVuelta);
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Acción a realizar cuando se presiona "No"
                                }
                            });

                    // Crear y mostrar la alerta
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            }
        });

        return dialog;
    }
    public AlertDialog controlesAlertDialog(LinearLayout linearLayout) {
        SentenciasSql sql = new SentenciasSql(context);
        Opciones opcion= new Opciones();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final CharSequence[] items = new CharSequence[8];
        items[0] = "Comenzar todo";
        items[1] = "Resetear Todo";
        items[2] = "Parar todo";
        items[3] = "Limpiar todo";
        items[4] = "Cambiar todo";
        items[5] = "Organizar MinTime";
        items[6] = "Seleccionar Controles";
        items[7] = "Conectar al ESP32";
        builder.setTitle("Configuracion")
                //.setMessage("El Mensaje para el usuario")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which] == items[0]) {
                            opcion.startAllCronos(listaCronometros);
                        }else if(items[which] == items[1]){
                            opcion.resetAllCronos(listaCronometros);
                        }else if(items[which] == items[2]){
                            opcion.stopAllCronos(listaCronometros);
                        }else if(items[which] == items[3]){
                            opcion.deleteAllCronos(linearLayout,listaCronometros);
                        }else if(items[which] == items[4]){
                            createChangeAll(context,listaCronometros).show();
                        }else if(items[which] == items[5]){
                            opcion.organizarCronoMin(linearLayout,listaCronometros);
                        }else if(items[which] == items[6]){
                            createDialogSelectCronos(listaCronometros).show();
                        }else if(items[which] == items[5]){
//                            createDialogSelectCronos(listaCronometros).show();
//                            BluetoothManager bluetoothManager = new BluetoothManager(context);
//                            String dispositivo = bluetoothManager.getDispositivo();
                        }
                    }
                });
        return builder.create();
    }
    public AlertDialog createDialogSelectCronos(ArrayList<Cronometro> cronoList) {
        SentenciasSql sentenciaSql = new SentenciasSql(context);
        Opciones opcion= new Opciones();
        RecyclerView recyclerView;
        Button btnStart,btnStop,btnEliminar,btnReset,btnVuelta,btnCambiar;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_opciones, null);
        builder.setView(view);
        recyclerView = view.findViewById(R.id.ListaCronometros);
        btnStart = view.findViewById(R.id.btnStart);
        btnStop = view.findViewById(R.id.btnStop);
        btnEliminar = view.findViewById(R.id.btnEliminar);
        btnReset = view.findViewById(R.id.btnReset);
        btnCambiar = view.findViewById(R.id.btnCambiar);
        btnVuelta = view.findViewById(R.id.btnVuelta);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        sentenciaSql.mostrarCrono();

        CheckBoxAdapter adapterCheckBox = new CheckBoxAdapter(sentenciaSql.getListaCronometros());
        recyclerView.setAdapter(adapterCheckBox);
        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle("Título")
                .setNegativeButton("Cancelar", null)
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Mostrar el cuadro de diálogo
        dialog.show();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion.actionSelectItemsCronos(context,cronoList,adapterCheckBox,"start");
                dialog.dismiss();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion.actionSelectItemsCronos(context,cronoList,adapterCheckBox,"stop");
                dialog.dismiss();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion.actionSelectItemsCronos(context,cronoList,adapterCheckBox,"reset");
                dialog.dismiss();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opcion.actionSelectItemsCronos(context,cronoList,adapterCheckBox,"delete");
                dialog.dismiss();
            }
        });
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChangeSelect(context,listaCronometros,adapterCheckBox).show();
                dialog.dismiss();
            }
        });
        return dialog;
    }
    public AlertDialog createChangeAll(Context context,ArrayList<Cronometro>listaCronometros) {
        SentenciasSql sentenciaSql = new SentenciasSql(context);
        Opciones opcion= new Opciones();
        LinearLayout linearLayout,linearContainer;
        RecyclerView recyclerView;
        Button btnGuardar,btnNext,btnBack;
        TextView txtNadador;
        Spinner comboPrueba,comboMetros;
        Cronometros entidadCrono=new Cronometros();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_add_cronos, null);
        builder.setView(view);
        recyclerView = view.findViewById(R.id.ListaCronometros);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        linearLayout = view.findViewById(R.id.linear);
        linearContainer = view.findViewById(R.id.linearContainer);
        comboPrueba = view.findViewById(R.id.comboPrueba);
        comboMetros = view.findViewById(R.id.combo);
        btnNext = view.findViewById(R.id.btnNext);
        btnBack = view.findViewById(R.id.btnBack);
        txtNadador = view.findViewById(R.id.textViewNum);
        linearLayout.removeView(recyclerView);
        linearContainer.removeView(btnNext);
        linearContainer.removeView(btnBack);
        linearContainer.setGravity(Gravity.NO_GRAVITY);

        txtNadador.setText("Cambiar Pruebas y Metros: ");

        ArrayList<String>  listaPruebas=sentenciaSql.obtenerArrayPruebas();
        ArrayList<String>  listaMetros=sentenciaSql.obtenerArrayMetros();
        ArrayList<String>  listaNadador=sentenciaSql.obtenerNadadores();
        ArrayAdapter<CharSequence> adaptadorPruebas=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaPruebas);
        ArrayAdapter<CharSequence> adaptadorMetros=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaMetros);
        ArrayAdapter<CharSequence> adaptadorNadador=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaNadador);
        comboPrueba.setAdapter(adaptadorPruebas);
        comboMetros.setAdapter(adaptadorMetros);

        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle("Filtros").setNegativeButton("Cancelar", null).setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Mostrar el cuadro de diálogo
        dialog.show();
        comboPrueba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String prueba =parent.getItemAtPosition(position).toString();
                String [] listaPrueba=prueba.split(".-");
                if (!Objects.equals(prueba, "Seleccione la Prueba")){
                    entidadCrono.setPrueba(listaPrueba[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboMetros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String metros =parent.getItemAtPosition(position).toString();
                String [] listaMetros=metros.split(".-");
                if (!Objects.equals(metros, "Seleccione los Metros")){
                    entidadCrono.setMetros(listaMetros[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listaCronometros.size(); i++) {
                    Cronometro crono = listaCronometros.get(i);
                    crono.cronometro.setPrueba(entidadCrono.getPrueba());
                    crono.cronometro.setMetros(entidadCrono.getMetros());
                    crono.viewPrueba.setText(entidadCrono.getPrueba());
                    crono.viewMetros.setText(entidadCrono.getMetros());
                    crono.viewTimeRecord.setText(sentenciaSql.getTimeRecord(crono.cronometro));
                    sentenciaSql.editCrono(crono.cronometro);
                }
                dialog.dismiss();
            }
        });
        return dialog;
    }
    public AlertDialog createChangeSelect(Context context,ArrayList<Cronometro>listaCronometros,CheckBoxAdapter adapterCheckBox) {
        SentenciasSql sentenciaSql = new SentenciasSql(context);
        Opciones opcion= new Opciones();
        LinearLayout linearLayout,linearContainer;
        RecyclerView recyclerView;
        Button btnGuardar,btnNext,btnBack;
        TextView txtNadador;
        Spinner comboPrueba,comboMetros;
        Cronometros entidadCrono=new Cronometros();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_add_cronos, null);
        builder.setView(view);
        recyclerView = view.findViewById(R.id.ListaCronometros);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        linearLayout = view.findViewById(R.id.linear);
        linearContainer = view.findViewById(R.id.linearContainer);
        comboPrueba = view.findViewById(R.id.comboPrueba);
        comboMetros = view.findViewById(R.id.combo);
        btnNext = view.findViewById(R.id.btnNext);
        btnBack = view.findViewById(R.id.btnBack);
        txtNadador = view.findViewById(R.id.textViewNum);
        linearLayout.removeView(recyclerView);
        linearContainer.removeView(btnNext);
        linearContainer.removeView(btnBack);
        linearContainer.setGravity(Gravity.NO_GRAVITY);

        txtNadador.setText("Cambiar Pruebas y Metros: ");

        ArrayList<String>  listaPruebas=sentenciaSql.obtenerArrayPruebas();
        ArrayList<String>  listaMetros=sentenciaSql.obtenerArrayMetros();
        ArrayList<String>  listaNadador=sentenciaSql.obtenerNadadores();
        ArrayAdapter<CharSequence> adaptadorPruebas=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaPruebas);
        ArrayAdapter<CharSequence> adaptadorMetros=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaMetros);
        ArrayAdapter<CharSequence> adaptadorNadador=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaNadador);
        comboPrueba.setAdapter(adaptadorPruebas);
        comboMetros.setAdapter(adaptadorMetros);

        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle("Filtros").setNegativeButton("Cancelar", null).setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Mostrar el cuadro de diálogo
        dialog.show();
        comboPrueba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String prueba =parent.getItemAtPosition(position).toString();
                String [] listaPrueba=prueba.split(".-");
                if (!Objects.equals(prueba, "Seleccione")){
                    entidadCrono.setPrueba(listaPrueba[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboMetros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String metros =parent.getItemAtPosition(position).toString();
                String [] listaMetros=metros.split(".-");
                if (!Objects.equals(metros, "Seleccione")){
                    entidadCrono.setMetros(listaMetros[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Cronometros> dataList = adapterCheckBox.getListaAdapterNadador();
                for (int i = 0; i < dataList.size(); i++) {
                    Cronometros cronometro = dataList.get(i);

                    if(cronometro.isChecked()){
                        Cronometro crono = listaCronometros.get(i);
                            crono.cronometro.setPrueba(entidadCrono.getPrueba());
                            crono.viewPrueba.setText(entidadCrono.getPrueba());
                            crono.cronometro.setMetros(entidadCrono.getMetros());
                            crono.viewMetros.setText(entidadCrono.getMetros());
                            sentenciaSql.editCrono(crono.cronometro);
                            crono.viewTimeRecord.setText(sentenciaSql.getTimeRecord(crono.cronometro));

                    }
                }
                dialog.dismiss();
            }
        });
        return dialog;
    }
    @SuppressLint("MissingInflatedId")
    public AlertDialog createDialogSelectTimes(ListView listView,ArrayList<Tiempos> listaEntidad) {
        SentenciasSql sentenciaSql = new SentenciasSql(context);
        Opciones opcion= new Opciones();
        LinearLayout linearLayout,linearContainer;
        RecyclerView recyclerView;
        Button btnGuardar,btnNext,btnBack;
        TextView txtNadador;
        Spinner comboPrueba,comboMetros;
        Cronometros entidadCrono=new Cronometros();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_add_cronos, null);
        builder.setView(view);
        recyclerView = view.findViewById(R.id.ListaCronometros);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        linearLayout = view.findViewById(R.id.linear);
        linearContainer = view.findViewById(R.id.linearContainer);
        comboPrueba = view.findViewById(R.id.comboPrueba);
        comboMetros = view.findViewById(R.id.combo);
        btnNext = view.findViewById(R.id.btnNext);
        btnBack = view.findViewById(R.id.btnBack);
        txtNadador = view.findViewById(R.id.textViewNum);
        linearLayout.removeView(recyclerView);
        linearContainer.removeView(btnNext);
        linearContainer.removeView(btnBack);
        linearContainer.setGravity(Gravity.NO_GRAVITY);

        txtNadador.setText("Nadador: ");
        // Crear un Spinner sin modelo
        Spinner comboNadador = new Spinner(context);
        linearContainer.addView(comboNadador);
        // Cambiar el ancho del comboNadador
        int width = 700; // Ancho deseado en píxeles
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        comboNadador.setLayoutParams(layoutParams);
        ArrayList<String>  listaPruebas=sentenciaSql.obtenerArrayPruebas();
        ArrayList<String>  listaMetros=sentenciaSql.obtenerArrayMetros();
        ArrayList<String>  listaNadador=sentenciaSql.obtenerNadadores();
        ArrayAdapter<CharSequence> adaptadorPruebas=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaPruebas);
        ArrayAdapter<CharSequence> adaptadorMetros=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaMetros);
        ArrayAdapter<CharSequence> adaptadorNadador=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaNadador);
        comboPrueba.setAdapter(adaptadorPruebas);
        comboMetros.setAdapter(adaptadorMetros);
        comboNadador.setAdapter(adaptadorNadador);
        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle("Filtros").setNegativeButton("Cancelar", null).setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Mostrar el cuadro de diálogo
        dialog.show();
        comboPrueba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String prueba =parent.getItemAtPosition(position).toString();
                String [] listaPrueba=prueba.split(".-");
                if (!Objects.equals(prueba, "Seleccione")){
                    entidadCrono.setPrueba(listaPrueba[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboMetros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String metros =parent.getItemAtPosition(position).toString();
                String [] listaMetros=metros.split(".-");
                if (!Objects.equals(metros, "Seleccione")){
                    entidadCrono.setMetros(listaMetros[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboNadador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String nadador =parent.getItemAtPosition(position).toString();
                String [] listaNadador=nadador.split(".-");
                if (!Objects.equals(nadador, "Seleccione")){
                    entidadCrono.setCedula(Integer.parseInt(listaNadador[2]));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conditions="WHERE prueba='"+entidadCrono.getPrueba()+"' AND metros='"+
                        entidadCrono.getMetros()+"'"+"AND cedula="+entidadCrono.getCedula()+"";

                ArrayAdapter adaptador=new ArrayAdapter(context,android.R.layout.simple_list_item_1,sentenciaSql.getTiemposString(conditions));
                listView.setAdapter(adaptador);

                Toast.makeText(context,
                "Pruebas: "+entidadCrono.getPrueba()+
                    "Metros: "+entidadCrono.getMetros()
                ,Toast.LENGTH_SHORT).show();

            }
        });
        return dialog;
    }
    @SuppressLint("MissingInflatedId")
    public AlertDialog createAlertDialogEditTime(Tiempos entidadTiempo,ListView listView,ArrayAdapter adaptador,ArrayList<Tiempos> listaEntidad,int position) {
        SentenciasSql sql = new SentenciasSql(context);
        Button btnEditar,btnEliminar;
        TextView viewInfo;
        EditText metros,prueba,tiempo;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_tiempos, null);
        builder.setView(view);
        viewInfo = view.findViewById(R.id.viewInfo);
        metros = view.findViewById(R.id.metros);
        prueba = view.findViewById(R.id.prueba);
        tiempo = view.findViewById(R.id.tiempo);
        btnEditar = view.findViewById(R.id.btnEditar);
        btnEliminar = view.findViewById(R.id.btnEliminar);
        viewInfo.setText("Cedula: "+entidadTiempo.getCedula()+" Nadador: "+entidadTiempo.getNadador());
        metros.setText(entidadTiempo.getMetros());
        prueba.setText(entidadTiempo.getPrueba());
        tiempo.setText(entidadTiempo.getTiempo());
        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle("Tiempo").setNegativeButton("Cancelar", null).setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Mostrar el cuadro de diálogo
        dialog.show();
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.deleteTime(entidadTiempo.getId());
                adaptador.clear();
                adaptador.addAll(sql.getTiemposString(""));
                listView.setAdapter(adaptador);
                listaEntidad.remove(position);
                dialog.dismiss();
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entidadTiempo.setTiempo(tiempo.getText().toString());
                entidadTiempo.setPrueba(prueba.getText().toString());
                entidadTiempo.setMetros(metros.getText().toString());
                sql.editTime(entidadTiempo);
                adaptador.clear();
                adaptador.addAll(sql.getTiemposString(""));
                listView.setAdapter(adaptador);
                dialog.dismiss();
            }
        });

        return dialog;
    }
    public AlertDialog createAddCronosDialog(LinearLayout linearLayout) {
        SentenciasSql sentenciaSql = new SentenciasSql(context);
        Opciones opcion= new Opciones();
        RecyclerView recyclerView;
        Button btnGuardar,btnNext,btnBack,btnConfig;
        TextView textViewGrupo;
        Spinner comboPrueba,comboMetros;
        Cronometros entidadCrono=new Cronometros();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_add_cronos, null);
        builder.setView(view);
        recyclerView = view.findViewById(R.id.ListaCronometros);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        btnConfig = view.findViewById(R.id.btnConfig);
        btnBack = view.findViewById(R.id.btnBack);
        textViewGrupo = view.findViewById(R.id.textViewNum);
        btnNext = view.findViewById(R.id.btnNext);
        comboPrueba = view.findViewById(R.id.comboPrueba);
        comboMetros = view.findViewById(R.id.combo);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<String>  listaPruebas=sentenciaSql.obtenerArrayPruebas();
        ArrayList<String>  listaMetros=sentenciaSql.obtenerArrayMetros();
        ArrayAdapter<CharSequence> adaptadorPruebas=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaPruebas);
        ArrayAdapter<CharSequence> adaptadorMetros=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaMetros);
        comboPrueba.setAdapter(adaptadorPruebas);
        comboMetros.setAdapter(adaptadorMetros);

        CheckBoxAdapterNadador adapterCheckBox = new CheckBoxAdapterNadador(sentenciaSql.getNadadores(2));
        recyclerView.setAdapter(adapterCheckBox);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewGrupo.getText().toString().equals("Grupo")){
                    textViewGrupo.setText("1");
                }else if(Integer.parseInt(textViewGrupo.getText().toString())<4){
                    textViewGrupo.setText((Integer.parseInt(textViewGrupo.getText().toString())+1)+"");
                }
                CheckBoxAdapterNadador adapterCheckBox = new CheckBoxAdapterNadador(sentenciaSql.getNadadores(Integer.parseInt(textViewGrupo.getText().toString())));
                recyclerView.setAdapter(adapterCheckBox);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewGrupo.getText().toString().equals("Grupo")){
                    textViewGrupo.setText("4");
                }else if(Integer.parseInt(textViewGrupo.getText().toString())>1){
                    textViewGrupo.setText((Integer.parseInt(textViewGrupo.getText().toString())-1)+"");
                }
                CheckBoxAdapterNadador adapterCheckBox = new CheckBoxAdapterNadador(sentenciaSql.getNadadores(Integer.parseInt(textViewGrupo.getText().toString())));
                recyclerView.setAdapter(adapterCheckBox);

            }
        });
        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle("Agregar Cronometro")
                .setNegativeButton("Cancelar", null)
                .setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Mostrar el cuadro de diálogo
        dialog.show();
        comboPrueba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String prueba =parent.getItemAtPosition(position).toString();
                String [] lista=prueba.split(".-");
                if (lista[0]!="Seleccione la Prueba"){
                    entidadCrono.setPrueba(lista[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboMetros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String metros =parent.getItemAtPosition(position).toString();
                String [] lista=metros.split(".-");
                if (metros!="Seleccione los Metros"){
                    entidadCrono.setMetros(lista[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ConfiguracionCrono configCrono = new ConfiguracionCrono();
        Funciones funcion = new Funciones();
        entidadCrono.setConfig(funcion.arrayListObjetosToString(configCrono));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion.createCronos(linearLayout,listaCronometros,entidadCrono,adapterCheckBox);
//                System.out.println(entidadCrono.getConfig());
                dialog.dismiss();
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modal modal = new Modal(context);
                modal.createModalConfigCrono(entidadCrono,configCrono);

            }
        });
        return dialog;
    }
    public AlertDialog createAlertDialogPositions(LinearLayout linearLayout,View itemView,Cronometro crono,ArrayList<Cronometro>listaCronometros) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    Opciones opcion = new Opciones();
    // Crear el View personalizado
    LinearLayout layout = new LinearLayout(context);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setPadding(16, 16, 16, 16);
    layout.setGravity(Gravity.CENTER); // Establecer la gravedad centrada

    // Crear un TextView
    TextView viewOrden = new TextView(context);
    viewOrden.setText("Cambiar Orden");
    viewOrden.setGravity(Gravity.CENTER); // Centrar el texto
    layout.addView(viewOrden);

    // Crear un TextView
    TextView viewTexto= new TextView(context);
    viewTexto.setText("Posicion "+(crono.cronometro.getPosicion()));
    viewTexto.setGravity(Gravity.CENTER); // Centrar el texto
    layout.addView(viewTexto);

    // Crear un botón 1
    Button btnArriba = new Button(context);
    btnArriba.setText("Up");
    btnArriba.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SentenciasSql sql = new SentenciasSql(context);
            int itemPosition = linearLayout.indexOfChild(itemView);

            if(itemPosition!=0){
                Collections.swap(listaCronometros, itemPosition, itemPosition-1);
                viewTexto.setText("Posicion "+(itemPosition));

                linearLayout.removeView(itemView);
                linearLayout.addView(itemView, itemPosition-1);
                opcion.actualizarPositions(linearLayout,listaCronometros);

            }
        }
    });
    LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );
    buttonParams.width = 200; // Establecer el ancho más pequeño para el botón
    layout.addView(btnArriba, buttonParams);



    // Crear un botón 2
    Button btnAbajo = new Button(context);
    btnAbajo.setText("Down");
    btnAbajo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int itemPosition = linearLayout.indexOfChild(itemView);
            if(itemPosition<listaCronometros.size()-1){

                Collections.swap(listaCronometros, itemPosition, itemPosition+1);
                viewTexto.setText("Posicion "+(itemPosition+2));

                linearLayout.removeView(itemView);
                linearLayout.addView(itemView, itemPosition+1);
                opcion.actualizarPositions(linearLayout,listaCronometros);
            }
        }
    });
    layout.addView(btnAbajo, buttonParams);
    // Establecer el View personalizado en el builder
    builder.setView(layout);

    // Configurar el estilo del AlertDialog para hacerlo transparente
    AlertDialog dialog = builder.create();
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    // Establecer la anchura del cuadro de diálogo
    dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);

    // Mostrar el cuadro de diálogo
    dialog.show();
    // Crear un botón 3
    Button btnCerrar = new Button(context);
    btnCerrar.setText("Menu Cerrar");
    btnCerrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
    LinearLayout.LayoutParams btnCerrarParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );
    btnCerrarParams.width = 400; // Establecer el ancho independiente para el botón de cierre
    layout.addView(btnCerrar, btnCerrarParams);

    return dialog;
}



}

