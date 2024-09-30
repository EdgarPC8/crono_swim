package com.example.cronoswim.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cronoswim.R;
import com.example.cronoswim.activities.SegundoActivity;

public class SegundoFragment extends Fragment {
    Button btnConsultarNadador,btnConsultarPruebas,btnConsultarTiempos,btnConsultarMetros;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the Layout for this fragment
        return inflater.inflate(R.layout.fragment_segundo, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnConsultarNadador = view.findViewById(R.id.btnConsultarNadador);
        btnConsultarPruebas = view.findViewById(R.id.btnConsultarPruebas);
        btnConsultarTiempos = view.findViewById(R.id.btnConsultarTiempos);
        btnConsultarMetros = view.findViewById(R.id.btnConsultarMetros);
        Bundle bundle=new Bundle();
        Intent intent = new Intent(getActivity(), SegundoActivity.class);
        btnConsultarNadador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("clave",0);
                bundle.putInt("clave2",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnConsultarPruebas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("clave",1);
                bundle.putInt("clave2",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnConsultarTiempos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("clave",3);
                bundle.putInt("clave2",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnConsultarMetros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("clave",2);
                bundle.putInt("clave2",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}