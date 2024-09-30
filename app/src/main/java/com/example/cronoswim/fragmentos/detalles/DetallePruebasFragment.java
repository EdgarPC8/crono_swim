package com.example.cronoswim.fragmentos.detalles;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.Conexion_BD;
import com.example.cronoswim.data_bases.Tablas_BD;
import com.example.cronoswim.entidades.Pruebas;
import com.example.cronoswim.fragmentos.consultas.ConsultarPruebasFragment;

import java.io.Serializable;

public class DetallePruebasFragment extends Fragment {

    TextView campoId ;
    EditText campoNombres;
    Button btnActualizar,btnEliminar;

    Fragment Fragment;
    FragmentTransaction transaction;
    Conexion_BD conn;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_detalle_pruebas,container,false);
        conn=new Conexion_BD(getActivity(), Tablas_BD.NOMBRE_BASE_DATOS,null,1);

        campoId = (TextView) rootView.findViewById(R.id.campoId);
        campoNombres = (EditText) rootView.findViewById(R.id.campoNombres);
        btnActualizar = (Button) rootView.findViewById(R.id.btnActualizar);
        btnEliminar = (Button) rootView.findViewById(R.id.btnEliminar);

        getParentFragmentManager().setFragmentResultListener("llave", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Serializable message= result.getSerializable("usuario");
                Pruebas user=null;
                user= (Pruebas) message;
                campoId.setText(String.valueOf(user.getId()));
                campoNombres.setText(user.getNombre());
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });
        return rootView;
    }


    private void eliminar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};

        db.delete(Tablas_BD.TABLA_PRUEBAS,Tablas_BD.CAMPO_PRUEBAS_ID+"=?",parametros);
        Toast.makeText(getActivity(),"Eliminado Correctamente",Toast.LENGTH_LONG).show();
        campoId.setText("");
        db.close();
        cambiarFragment();
    }

    private void actualizar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};
        ContentValues values=new ContentValues();

        values.put(Tablas_BD.CAMPO_PRUEBAS_NOMBRE,campoNombres.getText().toString());

        db.update(Tablas_BD.TABLA_PRUEBAS,values,Tablas_BD.CAMPO_PRUEBAS_ID+"=?",parametros);
        Toast.makeText(getActivity(),"Actualizado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
        cambiarFragment();
    }
    public void cambiarFragment(){
        transaction=getActivity().getSupportFragmentManager().beginTransaction();
        Fragment= new ConsultarPruebasFragment();
        transaction.replace(R.id.contenedorFragments, Fragment);
        //transaction. addToBackStack(null) ;
        transaction.commit();
    }

}
