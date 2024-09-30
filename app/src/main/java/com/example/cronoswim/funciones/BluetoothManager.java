
package com.example.cronoswim.funciones;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
public class BluetoothManager {

    private BluetoothAdapter mBlueAdapter;
    private BluetoothDevice selectedDevice;
    private BluetoothSocket bluetoothSocket;
    private Context context;
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    private ArrayList<Cronometro> listaCronometros;


    public BluetoothManager(Context context) {
        this.context = context;
        initBluetooth();
    }

    public void setListaCronometros(ArrayList<Cronometro> listaCronometros) {
        this.listaCronometros = listaCronometros;
    }

    private void initBluetooth() {
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBlueAdapter == null) {
            showToast("El Bluetooth no está disponible");
        }
    }

    @SuppressLint("MissingPermission")
    public String getDispositivo() {
        if (mBlueAdapter.isEnabled()) {
            Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {
                if ("ESP32-BT-Slave".equals(device.getName())) {
                    selectedDevice = device;
                }
            }

            if (selectedDevice != null) {
                return selectedDevice.getName();
            } else {
                showToast("No se encontró el dispositivo ESP32-BT-Slave");
            }
        } else {
            showToast("Enciende el Bluetooth para obtener dispositivos emparejados");
        }
        return null;
    }

    public void connectToDevice() {
        if (selectedDevice != null) {
            new ConnectBluetoothTask().execute(selectedDevice);
        } else {
            showToast("Selecciona un dispositivo emparejado primero");
        }
    }

    public void disconnect() {
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket.close();
                showToast("Desconectado del dispositivo Bluetooth");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Error al desconectar");
        }
    }

    private class ConnectBluetoothTask extends AsyncTask<BluetoothDevice, Void, Boolean> {
        @SuppressLint({"MissingPermission", "WrongThread"})
        @Override
        protected Boolean doInBackground(BluetoothDevice... params) {
            try {
                if (bluetoothSocket != null) {
                    try {
                        bluetoothSocket.close(); // Cerrar el socket anterior si existe
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                bluetoothSocket = params[0].createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                bluetoothSocket.connect();
                new ReceiveMessageTask().execute();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                showToast("Conectado al dispositivo Bluetooth");
                // Aquí podrías notificar a la actividad principal o realizar otras operaciones necesarias
            } else {
                showToast("Error al conectar al dispositivo Bluetooth");
            }
        }
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    // Getter para obtener el socket Bluetooth (necesario si quieres enviar mensajes desde otra clase)
    public BluetoothSocket getBluetoothSocket() {
        return bluetoothSocket;
    }
    public boolean enviarMensaje(JSONObject json) {
        if (bluetoothSocket != null && bluetoothSocket.isConnected()) {
            try {
                OutputStream outputStream = bluetoothSocket.getOutputStream();
                String jsonString = json.toString(); // Convertir el objeto JSON a String
                jsonString = jsonString + "\n"; // Añadir un salto de línea al final
                outputStream.write(jsonString.getBytes());
//                showToast("Mensaje JSON enviado: " + jsonString);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
//                showToast("Error al enviar el mensaje JSON");
                return false;
            }
        } else {
//            showToast("La conexión Bluetooth no está establecida");
            return false;
        }
    }

    private class ReceiveMessageTask extends AsyncTask<Void, String, Boolean> {
        private StringBuilder buffer = new StringBuilder();

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                InputStream inputStream = bluetoothSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    publishProgress(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ReceiveMessageTask", "Error al obtener el InputStream", e);
                return false;
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // Procesar cada línea recibida
            String receivedMessage = values[0];

            if (!receivedMessage.isEmpty()) {
                showToast("Mensaje recibido: " + receivedMessage);
                try {
                    JSONObject json = new JSONObject(receivedMessage);

                    // Extraer datos del objeto JSON
                    String nombre = json.getString("nombre");
                    String mensaje = json.getString("mensaje");
                    int id = json.getInt("id");

                    // Aquí puedes manejar los datos según lo recibido
                    // Por ejemplo, actualizar una lista de cronómetros


                    // Si tienes una lista de cronómetros (suponiendo listaCronometros es un ArrayList)
                    // Verificar si el cronómetro existe en la lista
                    if (id > 0 && id <= listaCronometros.size()) {
                        Cronometro cronoBT = listaCronometros.get(id - 1);

                        // Verificar si cronoBT no es nulo antes de acceder a sus métodos
                        if (cronoBT != null) {
//                            cronoBT.viewVueltas.setText(mensaje);
                            cronoBT.PararCrono();
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("id", cronoBT.cronometro.getPosicion());
                                jsonObject.put("nombre", cronoBT.cronometro.getNombre());
                                jsonObject.put("activo", false);
                                // Aquí puedes añadir más datos según necesites
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // Aquí puedes realizar otras operaciones con cronoBT si es necesario
                        } else {
                            showToast("Cronómetro con ID " + id + " no encontrado en la lista");
                        }
                    } else {
                        showToast("ID de cronómetro fuera de rango: " + id);
                    }

                    // También puedes actualizar la interfaz de usuario, etc.

                } catch (JSONException e) {
                    e.printStackTrace();
                    showToast("Error al procesar el mensaje JSON");
                }
            }
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                showToast("Error al recibir mensajes");
            }
        }
    }

}

