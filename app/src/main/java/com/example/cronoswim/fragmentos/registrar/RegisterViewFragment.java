package com.example.cronoswim.fragmentos.registrar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.Conexion_BD;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.data_bases.Tablas_BD;
import com.example.cronoswim.dialogos.Modal;
import com.example.cronoswim.entidades.Metros;
import com.example.cronoswim.fragmentos.consultas.ConsultarMetrosFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarTiemposFragment;

import java.util.ArrayList;

public class RegisterViewFragment extends Fragment {
    ListView listView;
    TextView titulo;
    ArrayList<String> listaInformacion;
    ArrayList<Metros> listaEntidad;
    Fragment Fragment;
    Conexion_BD conn;
    FragmentTransaction transaction;
    Button btnConsultar,btnRegistrar;
    String sentencia;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_register_view, container, false);
        conn=new Conexion_BD(getActivity(), Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        btnConsultar =(Button) rootView.findViewById(R.id.btnConsultar);
        btnRegistrar =(Button) rootView.findViewById(R.id.btnRegistrar);
        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        ConsultarTiemposFragment myFragment = new ConsultarTiemposFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedorFragments, myFragment)
                .addToBackStack(null)
                .commit();


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modal modal = new Modal(getActivity());
                modal.setTittle("Registrar");
                modal.createModalRegistrar();
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modal modal = new Modal(getActivity());
                modal.setTittle("Ver Consultas");
                modal.createModalConsultas();
            }
        });
        return rootView ;
    }
}
