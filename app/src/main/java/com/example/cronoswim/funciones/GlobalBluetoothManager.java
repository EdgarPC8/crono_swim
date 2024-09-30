package com.example.cronoswim.funciones;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class GlobalBluetoothManager {

    private static final int REQUEST_BLUETOOTH_PERMISSIONS = 1;
    private static GlobalBluetoothManager instance;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice selectedDevice;
    private BluetoothSocket bluetoothSocket;
    private Context context;
    private static final String DEVICE_NAME = "ESP32-BT-Slave";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private GlobalBluetoothManager(Context context) {
        this.context = context.getApplicationContext(); // Use application context
        initBluetooth();
    }

    public static synchronized GlobalBluetoothManager getInstance(Context context) {
        if (instance == null) {
            instance = new GlobalBluetoothManager(context);
        }
        return instance;
    }

    private void initBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            showToast("El Bluetooth no está disponible");
        } else if (!mBluetoothAdapter.isEnabled()) {
            showToast("Enciende el Bluetooth para conectarte al dispositivo");
        } else {
            checkPermissionsAndConnect();
        }
    }

    private void checkPermissionsAndConnect() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    REQUEST_BLUETOOTH_PERMISSIONS);
        } else {
            connectToDevice();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_BLUETOOTH_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                connectToDevice();
            } else {
                showToast("Permiso de Bluetooth no otorgado");
            }
        }
    }

    private void connectToDevice() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            showToast("Permiso de Bluetooth no otorgado");
            return;
        }

        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            if (DEVICE_NAME.equals(device.getName())) {
                selectedDevice = device;
                break;
            }
        }

        if (selectedDevice != null) {
            new Thread(() -> {
                try {
                    bluetoothSocket = selectedDevice.createRfcommSocketToServiceRecord(MY_UUID);
                    bluetoothSocket.connect();
                    showToast("Conexión Bluetooth establecida");
                } catch (IOException e) {
                    e.printStackTrace();
                    showToast("Error al conectar al dispositivo");
                }
            }).start();
        } else {
            showToast("No se encontró el dispositivo ESP32-BT-Slave");
        }
    }

    public void sendMessage(String message) {
        if (bluetoothSocket != null && bluetoothSocket.isConnected()) {
            try {
                OutputStream outputStream = bluetoothSocket.getOutputStream();
                outputStream.write(message.getBytes());
                showToast("Mensaje enviado: " + message);
            } catch (IOException e) {
                e.printStackTrace();
                showToast("Error al enviar el mensaje");
            }
        } else {
            showToast("No hay conexión Bluetooth establecida");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
