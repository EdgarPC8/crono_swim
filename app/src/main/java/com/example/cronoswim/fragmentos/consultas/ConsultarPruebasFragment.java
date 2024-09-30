package com.example.cronoswim.fragmentos.consultas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.Conexion_BD;
import com.example.cronoswim.data_bases.Tablas_BD;
import com.example.cronoswim.entidades.Pruebas;
import com.example.cronoswim.fragmentos.detalles.DetalleNadadorFragment;
import com.example.cronoswim.fragmentos.detalles.DetallePruebasFragment;

import java.util.ArrayList;

public class ConsultarPruebasFragment extends Fragment {

    ListView listView;
    TextView titulo;
    ArrayList<String> listaInformacion;
    ArrayList<Pruebas> listaEntidad;
    Fragment Fragment;
    Conexion_BD conn;
    FragmentTransaction transaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_consultar,container,false);

        conn=new Conexion_BD(getActivity(),Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        listView =(ListView) rootView.findViewById(R.id.listView);
        titulo =(TextView) rootView.findViewById(R.id.txtViewTituloConsulta);
        titulo.setText("Lista de Pruebas");
        consultarLista();

        ArrayAdapter adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listaInformacion);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Pruebas user=listaEntidad.get(pos);
                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);
                getParentFragmentManager().setFragmentResult("llave",bundle);

                transaction=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment= new DetallePruebasFragment();
                transaction.replace(R.id.contenedorFragments, Fragment);
                //transaction. addToBackStack(null) ;
                transaction.commit();
            }
        });

        return rootView;

    }


    private void consultarLista() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Pruebas dato=null;
        listaEntidad=new ArrayList<Pruebas>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_PRUEBAS,null);

        while (cursor.moveToNext()){
            dato=new Pruebas();
            dato.setId(cursor.getInt(0));
            dato.setNombre(cursor.getString(1));

            listaEntidad.add(dato);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaEntidad.size();i++){
            listaInformacion.add(i+"- ("+listaEntidad.get(i).getId()+")"+
            listaEntidad.get(i).getNombre());
        }

    }

}
