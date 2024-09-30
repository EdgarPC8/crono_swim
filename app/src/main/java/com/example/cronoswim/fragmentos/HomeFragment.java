package com.example.cronoswim.fragmentos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.example.cronoswim.R;
import com.example.cronoswim.funciones.CreateSql;
import com.example.cronoswim.funciones.JsonFileManager;

public class HomeFragment extends Fragment {
    TextView textViewHome;
    Button btnExportar, btnSql, btnImportar;
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    TextView mStatusBlueTv, mPairedTv;
    ImageView mBlueIv;
    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn;
    BluetoothAdapter mBlueAdapter;
    BluetoothDevice selectedDevice;
    BluetoothSocket bluetoothSocket;
    JsonFileManager jsonFileManager;

    private final ActivityResultLauncher<Intent> exportLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        jsonFileManager.exportToJson(uri);
                    }
                }
            });

    private final ActivityResultLauncher<Intent> importLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        jsonFileManager.importFromJson(uri);
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        textViewHome = rootView.findViewById(R.id.txtViewHome);
        btnExportar = rootView.findViewById(R.id.btnExportar);
        btnImportar = rootView.findViewById(R.id.btnImportar);
        btnSql = rootView.findViewById(R.id.btnSql);

        mStatusBlueTv = rootView.findViewById(R.id.statusBluetoothTv);
        mPairedTv = rootView.findViewById(R.id.pairedTv);
        mBlueIv = rootView.findViewById(R.id.bluetoothIv);
        mOnBtn = rootView.findViewById(R.id.onBtn);
        mOffBtn = rootView.findViewById(R.id.offBtn);
        mDiscoverBtn = rootView.findViewById(R.id.discoverableBtn);
        mPairedBtn = rootView.findViewById(R.id.pairedBtn);
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        jsonFileManager = new JsonFileManager(getActivity());

        btnImportar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/json");
            importLauncher.launch(intent);
        });

        btnExportar.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("application/json");
//            intent.putExtra(Intent.EXTRA_TITLE, "natacion.json");
//            exportLauncher.launch(intent);
            jsonFileManager.exportToBackend();
        });

        btnSql.setOnClickListener(view -> {
            CreateSql archivo = new CreateSql(getActivity());
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Etiqueta", archivo.createText());
            clipboard.setPrimaryClip(clip);
            archivo.createSql(archivo.createText());
            Toast.makeText(getActivity(), "Archivo Creado y Texto copiado", Toast.LENGTH_SHORT).show();
        });

        return rootView;
    }
}
