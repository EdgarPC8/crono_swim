package com.example.cronoswim.funciones;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JsonSender {

    private static final String URL_BACKEND = "http://192.168.137.250:8888/natacion/backend/comands/json"; // Cambia por tu URL
    private OkHttpClient client;

    public JsonSender() {
        client = new OkHttpClient();
    }

    public void sendJson(Context context, JSONObject jsonObject) {
        new Thread(() -> {
            try {
                // Crear el cuerpo de la solicitud
                RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.get("application/json; charset=utf-8"));

                // Crear la solicitud
                Request request = new Request.Builder()
                        .url(URL_BACKEND)
                        .post(body)
                        .build();

                // Ejecutar la solicitud
                Response response = client.newCall(request).execute();

                // Manejar la respuesta
                if (response.isSuccessful()) {
                    // Si la respuesta fue exitosa
                    String responseData = response.body().string();
                    ((Activity) context).runOnUiThread(() ->
                            Toast.makeText(context, "Datos enviados correctamente: " + responseData, Toast.LENGTH_LONG).show());
                } else {
                    // Si hubo un error en la respuesta
                    ((Activity) context).runOnUiThread(() ->
                            Toast.makeText(context, "Error al enviar datos: " + response.message(), Toast.LENGTH_LONG).show());
                }
            } catch (IOException e) {
                e.printStackTrace();
                ((Activity) context).runOnUiThread(() ->
                        Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start(); // Ejecutar el hilo
    }
}
