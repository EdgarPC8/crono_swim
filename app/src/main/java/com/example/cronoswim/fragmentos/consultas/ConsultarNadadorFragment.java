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
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.Conexion_BD;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.data_bases.Tablas_BD;
import com.example.cronoswim.entidades.Nadador;
import com.example.cronoswim.fragmentos.detalles.DetalleNadadorFragment;

import java.util.ArrayList;

public class ConsultarNadadorFragment extends Fragment {
    ListView listView;
    TextView titulo;
    ArrayList<Nadador> listaEntidad;
    Fragment Fragment;
    Conexion_BD conn;
    FragmentTransaction transaction;
    ArrayList<String> listaInformacion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_consultar,container,false);

        conn=new Conexion_BD(getActivity(),Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SentenciasSql sql = new SentenciasSql(getActivity());
        listView =(ListView) rootView.findViewById(R.id.listView);
        titulo =(TextView) rootView.findViewById(R.id.txtViewTituloConsulta);
        titulo.setText("Lista de Nadadores");
        // Crear los botones dinámicamente
        LinearLayout buttonLayout = new LinearLayout(getActivity());
        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button button1 = new Button(getActivity());
        button1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button1.setText("Grupo 1");
        buttonLayout.addView(button1);

        Button button2 = new Button(getActivity());
        button2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button2.setText("Grupo 2");
        buttonLayout.addView(button2);

        Button button3 = new Button(getActivity());
        button3.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button3.setText("Grupo 3");
        buttonLayout.addView(button3);

        Button button4 = new Button(getActivity());
        button4.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button4.setText("Offline");
        buttonLayout.addView(button4);

        // Agregar el LinearLayout con los botones encima del ListView
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ((ViewGroup) rootView).addView(buttonLayout, 0, params);

        ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,consultarLista(2));
        listView.setAdapter(adaptador);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,consultarLista(1));
                listView.setAdapter(adaptador);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,consultarLista(2));
                listView.setAdapter(adaptador);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,consultarLista(3));
                listView.setAdapter(adaptador);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,consultarLista(4));
                listView.setAdapter(adaptador);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Nadador user=listaEntidad.get(pos);
                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);
                getParentFragmentManager().setFragmentResult("llave",bundle);

                transaction=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment= new DetalleNadadorFragment();
                transaction.replace(R.id.contenedorFragments, Fragment);
                //transaction. addToBackStack(null) ;
                transaction.commit();
            }
        });
        return rootView;
    }
    private ArrayList<String> consultarLista(int grupo) {
        SentenciasSql sql = new SentenciasSql(getActivity());
        listaInformacion=new ArrayList<>();
        listaEntidad= sql.getNadadores(grupo);
        for (int i=0; i<listaEntidad.size();i++){
            listaInformacion.add(
                    (i+1)+"- ("+listaEntidad.get(i).getCedula()+")"+
                            listaEntidad.get(i).getNadador());
        }
        return listaInformacion;
    }


}
