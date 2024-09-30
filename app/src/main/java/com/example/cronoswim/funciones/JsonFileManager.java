package com.example.cronoswim.funciones;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.data_bases.SqlJson;
import com.example.cronoswim.entidades.Pruebas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Request;

public class JsonFileManager {
    private Context context;
    private SentenciasSql sentenciasSql;

    public JsonFileManager(Context context) {
        this.context = context;
        this.sentenciasSql = new SentenciasSql(context);
    }

    public void exportToJson(Uri uri) {
        SqlJson json = new SqlJson(context);
        try (OutputStream outputStream = context.getContentResolver().openOutputStream(uri)) {
            if (outputStream != null) {
                JSONArray arrayPruebas = json.getPruebas();
                JSONArray arrayMetros = json.getMetros();
                JSONArray arrayNadadores = json.getNadadores();
                JSONArray arrayTiempos = json.getTiempos();
                JSONArray arrayCuenta = json.getCuenta();
                JSONArray arrayUsuario = json.getUsuario();
                JSONArray arrayEvento = json.getEvento();
                JSONArray arraySerie = json.getSerie();
                JSONArray arrayRoles = json.getRoles();
                JSONArray arrayInstitucionNadador = json.getInstitucionNadador();
                JSONArray arrayInstitucion = json.getInstitucion();
                JSONArray arrayCompetencia = json.getCompetencia();
                JSONArray arrayLogs= json.getLogs();

                // Crear un objeto JSON con una clave específica y el JSONArray como valor
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("pruebas", arrayPruebas);
                jsonObject.put("metros", arrayMetros);
                jsonObject.put("nadador", arrayNadadores);
                jsonObject.put("tiempos", arrayTiempos);
                jsonObject.put("cuenta", arrayCuenta);
                jsonObject.put("usuario", arrayUsuario);
                jsonObject.put("evento", arrayEvento);
                jsonObject.put("serie", arraySerie);
                jsonObject.put("roles", arrayRoles);
                jsonObject.put("institucion_nadador", arrayInstitucionNadador);
                jsonObject.put("institucion", arrayInstitucion);
                jsonObject.put("competencia", arrayCompetencia);
                jsonObject.put("logs", arrayLogs);

                // Convertir el objeto JSON a su representación textual
                String jsonString = jsonObject.toString();

                // Escribir la representación textual en el archivo
                outputStream.write(jsonString.getBytes());
                Toast.makeText(context, "Exportación exitosa", Toast.LENGTH_LONG).show();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al exportar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void importFromJson(Uri uri) {
        SqlJson json = new SqlJson(context);
        SentenciasSql sql = new SentenciasSql(context);

        try (InputStream inputStream = context.getContentResolver().openInputStream(uri)) {
            if (inputStream != null) {
                sql.vaciarTodasLasTablas();
                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                String jsonStr = new String(data, "UTF-8");

                JSONObject jsonData = new JSONObject(jsonStr);
                JSONArray dataPruebas = jsonData.getJSONArray("pruebas");
                JSONArray dataMetros = jsonData.getJSONArray("metros");
                JSONArray dataNadadores = jsonData.getJSONArray("nadador");
                JSONArray dataTiempos = jsonData.getJSONArray("tiempos");
                JSONArray dataCuenta = jsonData.getJSONArray("cuenta");
                JSONArray dataUsuario = jsonData.getJSONArray("usuario");
                JSONArray dataEvento = jsonData.getJSONArray("evento");
                JSONArray dataSerie = jsonData.getJSONArray("serie");
                JSONArray dataRol = jsonData.getJSONArray("roles");
                JSONArray dataInstitucionNadador = jsonData.getJSONArray("institucion_nadador");
                JSONArray dataInstitucion = jsonData.getJSONArray("institucion");
                JSONArray dataCompetencia = jsonData.getJSONArray("competencia");
                JSONArray dataLogs = jsonData.getJSONArray("logs");
                // Recorre el JSONArray y llama al método para insertar cada prueba en la base de datos
                json.setPruebas(dataPruebas);
                json.setMetros(dataMetros);
                json.setNadadores(dataNadadores);
                json.setTiempos(dataTiempos);
                json.setCuenta(dataCuenta);
                json.setUsuario(dataUsuario);
                json.setEvento(dataEvento);
                json.setSerie(dataSerie);
                json.setRol(dataRol);
                json.setInstitucionNadador(dataInstitucionNadador);
                json.setInstitucion(dataInstitucion);
                json.setCompetencia(dataCompetencia);
                json.setLogs(dataLogs);

//                sentenciasSql.importData(jsonData);
//                System.out.println(jsonData);
                Toast.makeText(context, "Importación exitosa", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al importar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void exportToBackend() {
        SqlJson json = new SqlJson(context);
        // Crear el objeto JSON con los datos necesarios
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("pruebas", json.getPruebas());
            jsonObject.put("metros", json.getMetros());
            jsonObject.put("nadador", json.getNadadores());
            jsonObject.put("tiempos", json.getTiempos());
            jsonObject.put("cuenta", json.getCuenta());
            jsonObject.put("usuario", json.getUsuario());
            jsonObject.put("evento", json.getEvento());
            jsonObject.put("serie", json.getSerie());
            jsonObject.put("roles", json.getRoles());
            jsonObject.put("institucion_nadador", json.getInstitucionNadador());
            jsonObject.put("institucion", json.getInstitucion());
            jsonObject.put("competencia", json.getCompetencia());
            jsonObject.put("logs", json.getLogs());

            // Llama al método para enviar el JSON al backend
            JsonSender jsonSender = new JsonSender();
            jsonSender.sendJson(context, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al crear JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}
