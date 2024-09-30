package com.example.cronoswim.fragmentos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.cronoswim.R;
import com.example.cronoswim.activities.SegundoActivity;
import com.example.cronoswim.fragmentos.registrar.RegistrarNadadorFragment;

public class PrimerFragment extends Fragment {

    Button btnRegistrarNadador,btnRegistrarPruebas,btnRegistrarMetros;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
    View rootView =inflater.inflate(R.layout.fragment_primer, container,false);
    btnRegistrarNadador = (Button) rootView.findViewById(R.id.btnRegistrarNadador);
    btnRegistrarPruebas = (Button) rootView.findViewById(R.id.btnRegistrarPruebas);
    btnRegistrarMetros = (Button) rootView.findViewById(R.id.btnRegistrarMetros);
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getActivity(), SegundoActivity.class);
        btnRegistrarNadador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("clave",0);
                bundle.putInt("clave2",0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnRegistrarPruebas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("clave",1);
                bundle.putInt("clave2",0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnRegistrarMetros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("clave",2);
                bundle.putInt("clave2",0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        return rootView;
    }

}