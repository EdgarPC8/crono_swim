package com.example.cronoswim.funciones;

import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

public class MessageSender {

    private BluetoothSocket bluetoothSocket;

    public MessageSender(BluetoothSocket bluetoothSocket) {
        this.bluetoothSocket = bluetoothSocket;
    }

    public void sendMessage(String message) {
        new SendMessageTask().execute(message);
    }

    private class SendMessageTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... messages) {
            try {
                OutputStream outputStream = bluetoothSocket.getOutputStream();
                outputStream.write(messages[0].getBytes());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                showToast("Mensaje enviado correctamente");
            } else {
                showToast("Error al enviar el mensaje");
            }
        }

        private void showToast(String msg) {
            // Aquí puedes usar el contexto adecuado para mostrar el Toast
//             Ejemplo: Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}

