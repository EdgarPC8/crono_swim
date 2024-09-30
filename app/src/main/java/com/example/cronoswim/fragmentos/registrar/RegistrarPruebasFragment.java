package com.example.cronoswim.fragmentos.registrar;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.Conexion_BD;
import com.example.cronoswim.data_bases.Tablas_BD;

public class RegistrarPruebasFragment extends Fragment {
    EditText campoNombres;
    Button btnRegistrar;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_registrar_pruebas, container, false);
        campoNombres= (EditText) rootView.findViewById(R.id.campoNombres);
        btnRegistrar= (Button) rootView.findViewById(R.id.btnPrimer);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
                campoNombres.setText("");

            }
        });
        return rootView;
    }

    private void registrar() {

        Conexion_BD conn=new Conexion_BD(getActivity(),Tablas_BD.NOMBRE_BASE_DATOS,null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();

        if (campoNombres.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(getActivity(),"Llene los campos vacios",Toast.LENGTH_LONG).show();
        }else{
            values.put(Tablas_BD.CAMPO_PRUEBAS_NOMBRE,campoNombres.getText().toString());


            Long idResultante=db.insert(Tablas_BD.TABLA_PRUEBAS,Tablas_BD.CAMPO_PRUEBAS_ID,values);

            Toast.makeText(getActivity(),"Id: "+idResultante,Toast.LENGTH_SHORT).show();
            db.close();
        }

    }
}