package com.example.cronoswim.dialogos;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.entidades.ConfiguracionCrono;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.fragmentos.consultas.ConsultarMetrosFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarNadadorFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarPruebasFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarTiemposFragment;
import com.example.cronoswim.fragmentos.registrar.RegisterViewFragment;
import com.example.cronoswim.fragmentos.registrar.RegistrarMetrosFragment;
import com.example.cronoswim.fragmentos.registrar.RegistrarNadadorFragment;
import com.example.cronoswim.fragmentos.registrar.RegistrarPruebasFragment;
import com.example.cronoswim.funciones.Funciones;

import java.util.ArrayList;

public class Modal extends AlertDialog{
    private Context context;
    private String tittle="Titulo";

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }


    public Modal(Context context) {
        super(context);
        this.context=context;
    }
    @SuppressLint("MissingInflatedId")
    public AlertDialog createModalConsultas() {
        Button btnTiempos,btnMetros,btnPruebas,btnNadador;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SentenciasSql sql = new SentenciasSql(context);
        View view = LayoutInflater.from(context).inflate(R.layout.modal_consultas, null);
        builder.setView(view);
        btnMetros =(Button) view.findViewById(R.id.btnMetros);
        btnPruebas =(Button) view.findViewById(R.id.btnPruebas);
        btnTiempos =(Button) view.findViewById(R.id.btnTiempos);
        btnNadador =(Button) view.findViewById(R.id.btnNadadores);
        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle(tittle).setNegativeButton("Cancelar", null).setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Hacer transparente el fondo del AlertDialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // Mostrar el cuadro de diálogo
        dialog.show();

        btnMetros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                ConsultarMetrosFragment myFragment = new ConsultarMetrosFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnPruebas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                ConsultarPruebasFragment myFragment = new ConsultarPruebasFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnNadador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                ConsultarNadadorFragment myFragment = new ConsultarNadadorFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnTiempos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String sentence=
//                        "SELECT nadador.nadador || ' ' || tiempos.fecha ||'\n' || " +
//                        "tiempos.metros || ' ' || tiempos.prueba || ': ' || tiempos.tiempo " +
//                        "AS nombre_completo,tiempos.* FROM tiempos INNER JOIN " +
//                        "nadador ON nadador.cedula=tiempos.cedula ORDER BY tiempos.tiempo ASC LIMIT 100";
//                ArrayAdapter adaptador=new ArrayAdapter(context,android.R.layout.simple_list_item_1,sql.getList(sentence));
//                listView.setAdapter(adaptador);
                // Obtener el FragmentManager de la actividad que muestra el AlertDialog
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                ConsultarTiemposFragment myFragment = new ConsultarTiemposFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return dialog;
    }
    public AlertDialog createModalRegistrar() {
        Button btnTiempos,btnMetros,btnPruebas,btnNadador;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SentenciasSql sql = new SentenciasSql(context);
        View view = LayoutInflater.from(context).inflate(R.layout.modal_consultas, null);
        builder.setView(view);
        btnMetros =(Button) view.findViewById(R.id.btnMetros);
        btnPruebas =(Button) view.findViewById(R.id.btnPruebas);
        btnTiempos =(Button) view.findViewById(R.id.btnTiempos);
        btnNadador =(Button) view.findViewById(R.id.btnNadadores);
        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle(tittle).setNegativeButton("Cancelar", null).setCancelable(false);
        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Hacer transparente el fondo del AlertDialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // Mostrar el cuadro de diálogo
        dialog.show();

        btnMetros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                RegistrarMetrosFragment myFragment = new RegistrarMetrosFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnPruebas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                RegistrarPruebasFragment myFragment = new RegistrarPruebasFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnNadador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                RegistrarNadadorFragment myFragment = new RegistrarNadadorFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnTiempos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String sentence=
//                        "SELECT nadador.nadador || ' ' || tiempos.fecha ||'\n' || " +
//                        "tiempos.metros || ' ' || tiempos.prueba || ': ' || tiempos.tiempo " +
//                        "AS nombre_completo,tiempos.* FROM tiempos INNER JOIN " +
//                        "nadador ON nadador.cedula=tiempos.cedula ORDER BY tiempos.tiempo ASC LIMIT 100";
//                ArrayAdapter adaptador=new ArrayAdapter(context,android.R.layout.simple_list_item_1,sql.getList(sentence));
//                listView.setAdapter(adaptador);
                // Obtener el FragmentManager de la actividad que muestra el AlertDialog
//                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//                RegistrarTiemposFragment myFragment = new ConsultarTiemposFragment();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.contenedorFragments, myFragment)
//                        .addToBackStack(null)
//                        .commit();
            }
        });


        return dialog;
    }
    public AlertDialog createModalConfigCrono(Cronometros cronometro, ConfiguracionCrono configCrono) {
        Button btnTiempos,btnMetros,btnPruebas,btnNadador;
        Spinner spinnerMetros;
        RadioGroup radioGroup;
        RadioButton radioButton1,radioButton2;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SentenciasSql sql = new SentenciasSql(context);
        View view = LayoutInflater.from(context).inflate(R.layout.modal_config_crono, null);
        builder.setView(view);
        spinnerMetros =(Spinner) view.findViewById(R.id.spinnerMetros);
        radioGroup =(RadioGroup) view.findViewById(R.id.radio_group);
        radioButton1 = (RadioButton) view.findViewById(R.id.radio_button1);
        radioButton2 = (RadioButton) view.findViewById(R.id.radio_button2);
        Funciones funcion = new Funciones();
        SentenciasSql sentenciasSql = new SentenciasSql(context);
        ArrayList<String> listaMetros=sentenciasSql.obtenerArrayMetros();
        ConfiguracionCrono cronoConfig=funcion.stringToConfiguracionCrono(cronometro.getConfig());

        ArrayAdapter<CharSequence> adaptadorMetros=new ArrayAdapter
                (context,android.R.layout.simple_spinner_item,listaMetros);
        spinnerMetros.setAdapter(adaptadorMetros);
        spinnerMetros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                String metros =parent.getItemAtPosition(position).toString();
                String [] lista=metros.split(".-");
                if (metros!="Seleccione los Metros"){
                    String [] stringMetros=lista[1].split(" ");

                    configCrono.setIntervaloMetros(Integer.parseInt(stringMetros[0]));
                }else{
                    configCrono.setIntervaloMetros(25);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if(cronoConfig.getSolo_vueltas()==0){
            radioButton1.setChecked(true);
        }else{
            radioButton2.setChecked(true);
        }

        // Establecer el título y los botones del cuadro de diálogo
        builder.setTitle(tittle).setNegativeButton("Cancelar", null).setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = view.findViewById(selectedRadioButtonId);
                    String selectedText = selectedRadioButton.getText().toString();
                    // Aquí tienes el texto del RadioButton seleccionado
                    if (selectedText.equals("Solo")){
                        configCrono.setSolo_vueltas(0);
                        cronometro.setConfig(funcion.arrayListObjetosToString(configCrono));
                        sql.editCrono(cronometro);
                    }else if(selectedText.equals("Con Vueltas")){
                        configCrono.setSolo_vueltas(1);
                        cronometro.setConfig(funcion.arrayListObjetosToString(configCrono));
                        sql.editCrono(cronometro);

                    }
                }
            }
        });

        AlertDialog dialog = builder.create();
        // Establecer la anchura del cuadro de diálogo
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Hacer transparente el fondo del AlertDialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // Mostrar el cuadro de diálogo
        dialog.show();


        return dialog;
    }


}

