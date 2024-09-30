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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.Conexion_BD;
import com.example.cronoswim.data_bases.Tablas_BD;
import com.example.cronoswim.entidades.Nadador;
import com.example.cronoswim.fragmentos.consultas.ConsultarNadadorFragment;


import java.io.Serializable;
import java.util.Objects;

public class DetalleNadadorFragment extends Fragment {

    EditText campoUsuario,campoNombres,campoApellidos,campoCedula,campoDia,campoMes,campoAnio,campoGrupo,campoGenero;
    Button btnActualizar,btnEliminar;
    Fragment Fragment;
    FragmentTransaction transaction;
    Conexion_BD conn;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_detalle_nadador,container,false);
        conn=new Conexion_BD(getActivity(), Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        campoUsuario = (EditText) rootView.findViewById(R.id.campoUsuario);
        campoNombres = (EditText) rootView.findViewById(R.id.campoNombres);
        campoApellidos = (EditText) rootView.findViewById(R.id.campoApellidos);
        campoGrupo = (EditText) rootView.findViewById(R.id.campoGrupo);
        campoCedula = (EditText) rootView.findViewById(R.id.campoCedula);
        campoDia = (EditText) rootView.findViewById(R.id.campoDia);
        campoMes = (EditText) rootView.findViewById(R.id.campoMes);
        campoAnio = (EditText) rootView.findViewById(R.id.campoAnio);
        campoGenero = (EditText) rootView.findViewById(R.id.campoGenero);
        btnActualizar = (Button) rootView.findViewById(R.id.btnActualizar);
        btnEliminar = (Button) rootView.findViewById(R.id.btnEliminar);

        getParentFragmentManager().setFragmentResultListener("llave", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Serializable message= result.getSerializable("usuario");
                Nadador user=null;
                user= (Nadador) message;
                campoCedula.setText(String.valueOf(user.getCedula()));
                campoUsuario.setText(user.getNadador());
                campoNombres.setText(user.getNombres());
                campoApellidos.setText(user.getApellidos());
                campoGenero.setText(user.getGenero());
                campoGrupo.setText((String.valueOf(user.getGrupo())));
                String valor=String.valueOf(user.getFecha_nacimiento());

                System.out.println("es: "+valor);
                String[] date = valor.split("-");
                if(!Objects.equals(date[0], "null") && !Objects.equals(date[1], "null") && !Objects.equals(date[2], "null")){
                    campoDia.setText(date[0]);
                    campoMes.setText(date[1]);
                    campoAnio.setText(date[2]);
                }

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
        String[] parametros={campoCedula.getText().toString()};

        db.delete(Tablas_BD.TABLA_NADADOR,Tablas_BD.CAMPO_NADADOR_CEDULA+"=?",parametros);
        Toast.makeText(getActivity(),"Eliminado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
        cambiarFragment();
    }

    private void actualizar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoCedula.getText().toString()};
        ContentValues values=new ContentValues();

        values.put(Tablas_BD.CAMPO_NADADOR_NOMBRE,campoUsuario.getText().toString());
        values.put(Tablas_BD.CAMPO_NADADOR_NOMBRES,campoNombres.getText().toString());
        values.put(Tablas_BD.CAMPO_NADADOR_APELLIDOS,campoApellidos.getText().toString());
        values.put(Tablas_BD.CAMPO_NADADOR_FECHA_NACIMIENTO,campoDia.getText()+"-"+campoMes.getText()+"-"+campoAnio.getText());
        values.put(Tablas_BD.CAMPO_NADADOR_GRUPO,Integer.parseInt(campoGrupo.getText().toString()));
        values.put(Tablas_BD.CAMPO_NADADOR_GENERO,campoGenero.getText().toString());

        db.update(Tablas_BD.TABLA_NADADOR,values,Tablas_BD.CAMPO_NADADOR_CEDULA+"=?",parametros);
        Toast.makeText(getActivity(),"Actualizado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
        cambiarFragment();
    }
    public void cambiarFragment(){
        transaction=getActivity().getSupportFragmentManager().beginTransaction();
        Fragment= new ConsultarNadadorFragment();
        transaction.replace(R.id.contenedorFragments, Fragment);
        //transaction. addToBackStack(null) ;
        transaction.commit();
    }

}
