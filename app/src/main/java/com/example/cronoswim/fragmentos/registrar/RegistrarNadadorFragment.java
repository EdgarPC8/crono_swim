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

public class RegistrarNadadorFragment extends Fragment {
    EditText campoCedula,campoDia,campoMes,campoGenero,
            campoAnio,campoPrimerNombre,campoSegundoNombre,
            campoPrimerApellido,campoSegundoApellido,campoGrupo;
    Button btnRegistrar;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_registrar_nadador, container, false);
        campoCedula= (EditText) rootView.findViewById(R.id.campoCedula);
        campoPrimerNombre= (EditText) rootView.findViewById(R.id.campoPrimerNombre);
        campoSegundoNombre= (EditText) rootView.findViewById(R.id.campoSegundoNombre);
        campoPrimerApellido= (EditText) rootView.findViewById(R.id.campoPrimerApellido);
        campoSegundoApellido= (EditText) rootView.findViewById(R.id.campoSegundoApellido);
        campoGrupo= (EditText) rootView.findViewById(R.id.campoGrupo);
        campoGenero= (EditText) rootView.findViewById(R.id.campoGenero);
        campoDia= (EditText) rootView.findViewById(R.id.campoDia);
        campoMes= (EditText) rootView.findViewById(R.id.campoMes);
        campoAnio= (EditText) rootView.findViewById(R.id.campoAnio);
        btnRegistrar= (Button) rootView.findViewById(R.id.btnPrimer);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
                campoCedula.setText("");
                campoPrimerNombre.setText("");
                campoSegundoNombre.setText("");
                campoPrimerApellido.setText("");
                campoSegundoApellido.setText("");
                campoDia.setText("");
                campoMes.setText("");
                campoAnio.setText("");
                campoGenero.setText("");
                campoGrupo.setText("");
            }
        });
        return rootView;
    }

    private void registrar() {

        Conexion_BD conn=new Conexion_BD(getActivity(),Tablas_BD.NOMBRE_BASE_DATOS,null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();

        if (campoCedula.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(getActivity(),"Llene los campos vacios",Toast.LENGTH_LONG).show();
        }else{

                values.put(Tablas_BD.CAMPO_NADADOR_NOMBRE,campoPrimerNombre.getText().toString()+" "+campoPrimerApellido.getText().toString());
                values.put(Tablas_BD.CAMPO_NADADOR_NOMBRES,campoPrimerNombre.getText().toString()+" "+campoSegundoNombre.getText().toString());
                values.put(Tablas_BD.CAMPO_NADADOR_APELLIDOS,campoPrimerApellido.getText().toString()+" "+campoSegundoApellido.getText().toString());
                values.put(Tablas_BD.CAMPO_NADADOR_FECHA_NACIMIENTO,campoAnio.getText()+"-"+campoMes.getText()+"-"+campoDia.getText());
                values.put(Tablas_BD.CAMPO_NADADOR_GRUPO,Integer.parseInt(campoGrupo.getText().toString()));
                values.put(Tablas_BD.CAMPO_NADADOR_GENERO,campoGenero.getText().toString());
                Long idResultante=db.insert(Tablas_BD.TABLA_NADADOR,Tablas_BD.CAMPO_NADADOR_CEDULA,values);
                values.clear();
                Toast.makeText(getActivity(),"Cedula: "+idResultante,Toast.LENGTH_SHORT).show();
                db.close();

        }

    }
}