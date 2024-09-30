//package com.example.cronoswim.interfaces_principales;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.cronoswim.R;
//import com.example.cronoswim.adaptadores.ListaAdapterCronometro;
//import com.example.cronoswim.data_bases.SentenciasSql;
//import com.example.cronoswim.entidades.RegistroCronometros;
//
//import java.util.ArrayList;
//
//public class CronoActivity  {
//    public  static Context appContext;
//    Button btnAgregarItem,btnStart;
//    TextView viewTiempo;
//    RecyclerView listaCronometros;
//    ArrayList<RegistroCronometros> listaArrayRegistros;
//    SentenciasSql cronometros = new SentenciasSql(appContext);
//    ListaAdapterCronometro adapter=new ListaAdapterCronometro(cronometros.getListaCronometros());
////    Dialogo_Ajustes_Cronometro dialogo = new Dialogo_Ajustes_Cronometro(appContext);
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        appContext=getApplicationContext();
//        cronometros.setContext(appContext);
//        setContentView(R.layout.activity_cronometro);
//        btnAgregarItem=findViewById(R.id.btnAgregarCrono);
//        btnStart=findViewById(R.id.btnStart);
//        viewTiempo=findViewById(R.id.viewTiempo);
//        listaCronometros = findViewById(R.id.ListaCronometros);
//        listaCronometros.setLayoutManager(new LinearLayoutManager(this));
//        mostrar(adapter);
//        btnAgregarItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addObjeto(adapter);
//            }
//        });
//    }
//    public void addObjeto(ListaAdapterCronometro adapter){
////        long id = cronometros.insertar_MSMH();
////        RegistroCronometros registro = new RegistroCronometros();
////        registro.setId(Long.valueOf(id).intValue());
////        registro.setMilisegudno("0");
////        registro.setSegundo("0");
////        registro.setMinuto("0");
////        registro.setHora("0");
////        registro.setOnOff(0);
////
////        if (id > 0) {
////            Toast.makeText(CronoActivity.this, "Registro Guardado", Toast.LENGTH_LONG).show();
////
////        } else {
////            Toast.makeText(CronoActivity.this, "ERROR AL GUARDAR", Toast.LENGTH_LONG).show();
////        }
//////        cronometros.getListaCronometros().add(registro);
////        adapter.notifyItemInserted(cronometros.getListaCronometros().size()-1);
//    }
//    public void deleteObjeto(ListaAdapterCronometro adapter, int position){
//        // Elimina el objeto del ArrayList
//        cronometros.getListaCronometros().remove(position);
//        // Notifica al adaptador del RecyclerView que se ha eliminado un elemento
//        adapter.notifyItemRemoved(position);
//    }
//    public void mostrar(ListaAdapterCronometro adapter){
//
//        cronometros.mostrarCrono();
//        adapter.setOnItemClickListener(this);
//        listaCronometros.setAdapter(adapter);
////        adapter.notifyItemInserted(listaArrayRegistros.size() - 1);
//
////        for(RegistroCronometros elemento : cronometros.mostrarCrono()){
////            System.out.println("ID:"+elemento.getId()+" crono: "+elemento.getMilisegudno());
////        }
//
//    }
//    public AlertDialog createSimpleDialog(int id,int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(CronoActivity.this);
//        final CharSequence[] items = new CharSequence[4];
//        items[0] = "Resetar todo";
//        items[1] = "Eliminar";
//        items[2] = "Parar todo";
//        items[3] = "Seleccionar";
//        builder.setTitle("Configuracion")
//                //.setMessage("El Mensaje para el usuario")
//                .setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (items[which] == items[0]) {
//                            int i;
//                            for (i=0;i>100;i++){
//                                SentenciasSql crono = new SentenciasSql(CronoActivity.this);
//                                crono.editarRegistro(i,0,0,0,0);
//                            }
//                        }else if(items[which] == items[1]){
//                            deleteObjeto(adapter,position);
//                            SentenciasSql crono = new SentenciasSql(CronoActivity.this);
//                            crono.eliminarRegistro(id);
//                        }
//                    }
//                });
//        return builder.create();
//
//    }
//    public static Context getAppContext(){
//        return appContext;
//    }
//    public void Insertar_tiempo(int id,int milseg,int seg,int min,int hora){
//        cronometros.editarRegistro(id,milseg,seg,min,hora);
//        //Toast.makeText(getAppContext(),"Se guardo muy bien",Toast.LENGTH_LONG).show();
//    }
//    public void onItemClick(View v, int position, int id,"ff") {
//        createSimpleDialog(id,position).show();
//    }
//}
