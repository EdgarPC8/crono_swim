package com.example.cronoswim.fragmentos.consultas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.Conexion_BD;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.data_bases.Tablas_BD;
import com.example.cronoswim.dialogos.Dialogo_Ajustes_Cronometro;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Tiempos;
import com.example.cronoswim.fragmentos.detalles.DetallePruebasFragment;

import java.util.ArrayList;
import java.util.Objects;

public class ConsultarTiemposFragment extends Fragment {
    ListView listView;
    TextView titulo;
    Fragment Fragment;
    Conexion_BD conn;
    FragmentTransaction transaction;
    ArrayList<Tiempos> listaEntidad;
    String conditionPrueba="";
    String conditionMetros="";
    String conditionNadador="";
    String conditions="WHERE 1=1 ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_consultar,container,false);
        SentenciasSql sql = new SentenciasSql(getActivity());
        listaEntidad=sql.getTiempos("");
        Cronometros entidadCrono=new Cronometros();



        Spinner comboNadador = new Spinner(getActivity());
        Spinner comboPrueba = new Spinner(getActivity());
        Spinner comboMetros = new Spinner(getActivity());


        conn=new Conexion_BD(getActivity(),Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        listView =(ListView) rootView.findViewById(R.id.listView);
        titulo =(TextView) rootView.findViewById(R.id.txtViewTituloConsulta);
        titulo.setText("Lista de Tiempos");

        ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sql.getTiemposString(""));
        listView.setAdapter(adaptador);
        // Crear los botones dinámicamente
        LinearLayout buttonLayout = new LinearLayout(getActivity());
        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        buttonLayout.setOrientation(LinearLayout.VERTICAL);

        buttonLayout.addView(comboNadador);
        buttonLayout.addView(comboMetros);
        buttonLayout.addView(comboPrueba);
        ArrayList<String>  listaNadador=sql.obtenerNadadores();
        ArrayList<String>  listaPruebas=sql.obtenerArrayPruebas();
        ArrayList<String>  listaMetros=sql.obtenerArrayMetros();
        ArrayAdapter<CharSequence> adaptadorPruebas=new ArrayAdapter
                (getActivity(),android.R.layout.simple_spinner_item,listaPruebas);
        ArrayAdapter<CharSequence> adaptadorMetros=new ArrayAdapter
                (getActivity(),android.R.layout.simple_spinner_item,listaMetros);
        ArrayAdapter<CharSequence> adaptadorNadador=new ArrayAdapter
                (getActivity(),android.R.layout.simple_spinner_item,listaNadador);
        comboNadador.setAdapter(adaptadorNadador);
        comboPrueba.setAdapter(adaptadorPruebas);
        comboMetros.setAdapter(adaptadorMetros);

        // Agregar el LinearLayout con los botones encima del ListView
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ((ViewGroup) rootView).addView(buttonLayout, 0, params);

        comboPrueba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
                conditions=" WHERE 1=1 ";
                String prueba =parent.getItemAtPosition(position).toString();
                String [] listaPrueba=prueba.split(".-");
                if (!Objects.equals(prueba, "Seleccione la Prueba")){
                    conditionPrueba="AND prueba='"+listaPrueba[1]+"' ";
                    conditions=conditions+conditionPrueba+conditionMetros+conditionNadador;
                    ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sql.getTiemposString(conditions));
                    listView.setAdapter(adaptador);
                    listaEntidad=sql.getTiempos(conditions);
                }else{
                    conditionPrueba=" ";
                    conditions=conditions+conditionPrueba+conditionMetros+conditionNadador;
                    ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sql.getTiemposString(conditions));
                    listView.setAdapter(adaptador);
                    listaEntidad=sql.getTiempos(conditions);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboMetros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
                conditions=" WHERE 1=1 ";
                String metros =parent.getItemAtPosition(position).toString();
                String [] listaMetros=metros.split(".-");
                if (!Objects.equals(metros, "Seleccione los Metros")){
                    conditionMetros="AND metros='"+listaMetros[1]+"' ";
                    conditions=conditions+conditionMetros+conditionPrueba+conditionNadador;
                    ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sql.getTiemposString(conditions));
                    listView.setAdapter(adaptador);
                    listaEntidad=sql.getTiempos(conditions);

                }else{
                    conditionMetros=" ";
                    conditions=conditions+conditionMetros+conditionPrueba+conditionNadador;
                    ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sql.getTiemposString(conditions));
                    listView.setAdapter(adaptador);
                    listaEntidad=sql.getTiempos(conditions);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        comboNadador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
                conditions=" WHERE 1=1 ";
                String nadador =parent.getItemAtPosition(position).toString();
                String [] listaNadador=nadador.split(".-");
                if (!Objects.equals(nadador, "Seleccione el Nadador")){
                    conditionNadador=" AND tiempos.cedula="+Integer.parseInt(listaNadador[2])+" ";
                    conditions=conditions+conditionMetros+conditionPrueba+conditionNadador;
                    ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sql.getTiemposString(conditions));
                    listView.setAdapter(adaptador);
                    listaEntidad=sql.getTiempos(conditions);

                }else{
                    conditionNadador=" ";
                    conditions=conditions+conditionMetros+conditionPrueba+conditionNadador;
                    ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sql.getTiemposString(conditions));
                    listView.setAdapter(adaptador);
                    listaEntidad=sql.getTiempos(conditions);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Tiempos user=listaEntidad.get(pos);
                System.out.println("se ejecuta el timpo seleccionado"+listView.getAdapter().getItem(pos));
                Dialogo_Ajustes_Cronometro dialogo = new Dialogo_Ajustes_Cronometro(getActivity(),new ArrayList<>());
                dialogo.createAlertDialogEditTime(user,listView,adaptador,listaEntidad,pos);
            }
        });

        return rootView;

    }

}
