package com.example.cronoswim.funciones;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import com.example.cronoswim.R;
import com.example.cronoswim.adaptadores.CheckBoxAdapter;
import com.example.cronoswim.adaptadores.CheckBoxAdapterNadador;
import com.example.cronoswim.adaptadores.ListaAdapterCronometro;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Nadador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Opciones {

    public void deleteAllCronos(LinearLayout linearLayout,ArrayList<Cronometro> listaCronometros){
        if(listaCronometros.size()>0){
            listaCronometros.get(0).bluetoothManager.disconnect();
        }
        SentenciasSql sql = new SentenciasSql(linearLayout.getContext());
        linearLayout.removeAllViews();
        sql.eliminarTodosRegistros();
        listaCronometros.clear();
    }
    public void deleteCrono(LinearLayout linearLayout,View itemView){
        int itemPosition = linearLayout.indexOfChild(itemView);
//        System.out.println("Se hizo clic en el ItemView en la posición: " + itemPosition);
        linearLayout.removeView(itemView);
    }
    public void startAllCronos(ArrayList<Cronometro> listaCronometros) {
        for (int i = 0; i < listaCronometros.size(); i++) {
            Cronometro crono= listaCronometros.get(i);
            crono.EmpezarCrono();
        }
    }
    public void stopAllCronos(ArrayList<Cronometro> listaCronometros) {
        for (int i = 0; i < listaCronometros.size(); i++) {
            Cronometro crono= listaCronometros.get(i);
            crono.PararCrono();
        }
    }
    public void resetAllCronos(ArrayList<Cronometro> listaCronometros) {
        for (int i = 0; i < listaCronometros.size(); i++) {
            Cronometro crono= listaCronometros.get(i);
            crono.resetearCronometro();
        }
    }
    public void createCronos(LinearLayout linearLayout,ArrayList<Cronometro> listaCronometros,Cronometros entidadCrono,CheckBoxAdapterNadador checkBoxadapter) {
        ArrayList<Nadador> dataList = checkBoxadapter.getListaAdapterNadador();
        SentenciasSql cronoSql = new SentenciasSql(linearLayout.getContext());
        for (int i = 0; i < dataList.size(); i++) {
            Nadador nadador = dataList.get(i);
            if(nadador.isChecked()){
                Cronometros cronoEntidad = new Cronometros();
                cronoEntidad.setNombre(nadador.getNadador());
                cronoEntidad.setMetros(entidadCrono.getMetros());
                cronoEntidad.setPrueba(entidadCrono.getPrueba());
                cronoEntidad.setConfig(entidadCrono.getConfig());
                cronoEntidad.setTiempo("00:00:00,00");
                cronoEntidad.setPosicion(i);
                cronoEntidad.setCedula(nadador.getCedula());
                long id = cronoSql.insertarCrono(cronoEntidad);
                cronoEntidad.setId(Long.valueOf(id).intValue());
                Cronometro crono = new Cronometro(linearLayout.getContext(), linearLayout,cronoEntidad);
                listaCronometros.add(crono);
            }
        }
        BluetoothManager bluetoothManager = new BluetoothManager(linearLayout.getContext());
        String dispositivo = bluetoothManager.getDispositivo();
        if (dispositivo != null) {
            bluetoothManager.connectToDevice();
        } else {
            // Manejar el caso donde no se encuentra el dispositivo
        }
//        bluetoothManager.getDispositivo();
        for (int i = 0; i < listaCronometros.size(); i++) {
            Cronometro crono = listaCronometros.get(i);
            crono.setListaCronometros(listaCronometros);
            crono.cronometro.setPosicion(i);
            crono.setBluetoothManager(bluetoothManager); // Establece el BluetoothManager en cada Cronometro
//            bluetoothManager.setCrono(crono);
        }
        bluetoothManager.setListaCronometros(listaCronometros);
        actualizarPositions(linearLayout,listaCronometros);
    }
    public void actionSelectItemsCronos(Context context, ArrayList<Cronometro> listaCronometros, CheckBoxAdapter checkBoxadapter, String action) {
        ArrayList<Cronometros> dataList = checkBoxadapter.getListaAdapterNadador();
        for (int i = 0; i < dataList.size(); i++) {
            Cronometros cronometro = dataList.get(i);

            if(cronometro.isChecked()){
                Cronometro crono = listaCronometros.get(i);
                    System.out.println("Nombre"+crono.cronometro.getNombre());
                if (Objects.equals(action, "start")){
//                    Toast.makeText(context, "Si sale el crono",Toast.LENGTH_SHORT).show();
                    crono.EmpezarCrono();
                }else if (Objects.equals(action, "stop")){
                    crono.PararCrono();
                }else if (Objects.equals(action, "reset")){
                    crono.resetearCronometro();
                }else if (Objects.equals(action, "delete")){
                    SentenciasSql sql = new SentenciasSql(context);
                    sql.eliminarRegistro(crono.cronometro.getId());
                    deleteCrono(crono.linearLayout,crono.itemView);
                }
            }
        }
    }
    public String obtenerVueltas(int position, View rootView) {
        System.out.println(position+"");
        RecyclerView recyclerView = rootView.findViewById(R.id.ListaCronometros);
        ListaAdapterCronometro.RegistroViewHolder holder = (ListaAdapterCronometro.RegistroViewHolder) recyclerView.findViewHolderForAdapterPosition(position);
        String string="hhh";

        if (holder!=null){
//            string=holder.crono.obtenerVueltas();
            string="NO es nulo";
        }
        return string;
    }
    public void organizarCronoMin(LinearLayout linearLayout,ArrayList<Cronometro> listaCronometros) {
        SentenciasSql cronoSql = new SentenciasSql(linearLayout.getContext());

        // Ordenar la lista en función del valor específico 'valor' de cada objeto
        Collections.sort(listaCronometros, new Comparator<Cronometro>() {
            @Override
            public int compare(Cronometro entidad1, Cronometro entidad2) {
                return entidad1.viewTimeRecord.getText().toString().compareTo(entidad2.viewTimeRecord.getText().toString());
            }
        });
        linearLayout.removeAllViews();
        actualizarPositions(linearLayout,listaCronometros);
        for (Cronometro entidad : listaCronometros) {
            linearLayout.addView(entidad.itemView);
        }
    }
    public void actualizarPositions(LinearLayout linearLayout,ArrayList<Cronometro> listaCronometros) {
        SentenciasSql sql = new SentenciasSql(linearLayout.getContext());

        for (int i = 0; i < listaCronometros.size() ; i++) {
            Cronometro crono = listaCronometros.get(i);
            crono.cronometro.setPosicion(i+1);
            sql.editarPositionCrono(crono.cronometro.getCedula(),i+1);
            System.out.println(crono.cronometro.getNombre());
        }


    }

}
