package com.example.cronoswim.fragmentos.cronometro;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.dialogos.Dialogo_Ajustes_Cronometro;
import com.example.cronoswim.funciones.BluetoothManager;
import com.example.cronoswim.funciones.Cronometro;
import com.example.cronoswim.funciones.MessageSender;

import java.util.ArrayList;

public class CronoFragment extends Fragment {

    SentenciasSql cronoSql;
    ArrayList<Cronometro> listaCronometros = new ArrayList<>();
    BluetoothManager bluetoothManager;
    private MessageSender messageSender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crono, container, false);
        LinearLayout linearLayout = rootView.findViewById(R.id.linear);
        Button btnAgregar = rootView.findViewById(R.id.btnAgregarCrono);
        Button btnControles = rootView.findViewById(R.id.btnControles);

        cronoSql = new SentenciasSql(getActivity());
        cronoSql.setContext(getActivity());
        bluetoothManager = new BluetoothManager(getActivity());

        // Obtener dispositivos emparejados y conectar
        String dispositivo = bluetoothManager.getDispositivo();
        if (dispositivo != null) {
            bluetoothManager.connectToDevice();
        } else {
            // Manejar el caso donde no se encuentra el dispositivo
        }

            // Si se encontró el dispositivo, crea los cronómetros y establece BluetoothManager
            if (cronoSql.getCantidadCronometros() != 0) {
                for (int i = 0; i < cronoSql.getCantidadCronometros(); i++) {
                    Cronometro cronometro = new Cronometro(getActivity(), linearLayout, cronoSql.getListaCronometros().get(i));
                    cronometro.setBluetoothManager(bluetoothManager); // Establece el BluetoothManager en cada Cronometro
                    listaCronometros.add(cronometro);
                }
                bluetoothManager.setListaCronometros(listaCronometros);
            }

        btnControles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogo_Ajustes_Cronometro dialogo = new Dialogo_Ajustes_Cronometro(getActivity(), listaCronometros);
                dialogo.controlesAlertDialog(linearLayout).show();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre un diálogo o realiza alguna acción relacionada con la adición de cronómetros
                Dialogo_Ajustes_Cronometro dialogo = new Dialogo_Ajustes_Cronometro(getActivity(), listaCronometros);
                dialogo.createAddCronosDialog(linearLayout).show();
            }
        });

        return rootView;
    }

}
