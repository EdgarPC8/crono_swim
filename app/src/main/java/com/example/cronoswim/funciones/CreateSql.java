package com.example.cronoswim.funciones;

import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_METROS_ID;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_METROS_NOMBRE;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_NADADOR_APELLIDOS;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_NADADOR_CEDULA;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_NADADOR_FECHA_NACIMIENTO;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_NADADOR_GENERO;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_NADADOR_GRUPO;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_NADADOR_NOMBRE;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_NADADOR_NOMBRES;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_PRUEBAS_ID;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_PRUEBAS_NOMBRE;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_TIEMPOS_CEDULA;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_TIEMPOS_FECHA;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_TIEMPOS_ID;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_TIEMPOS_METROS;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_TIEMPOS_PRUEBA;
import static com.example.cronoswim.data_bases.Tablas_BD.CAMPO_TIEMPOS_TIEMPO;
import static com.example.cronoswim.data_bases.Tablas_BD.TABLA_METROS;
import static com.example.cronoswim.data_bases.Tablas_BD.TABLA_NADADOR;
import static com.example.cronoswim.data_bases.Tablas_BD.TABLA_PRUEBAS;
import static com.example.cronoswim.data_bases.Tablas_BD.TABLA_TIEMPOS;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.cronoswim.data_bases.SentenciasSql;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//public class CreateSql {
//    private static final int REQUEST_CODE_PICK_ACCOUNT = 1001;
//    private Context context;
//
//    public CreateSql(Context context) {
//        this.context = context;
//    }
//
//    public void solicitudCuenta() {
//        // Solicitar al usuario que seleccione una cuenta de Google
//        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, false, null, null, null, null);
//        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
//        System.out.println(REQUEST_CODE_PICK_ACCOUNT);
//    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == REQUEST_CODE_PICK_ACCOUNT && resultCode == Activity.RESULT_OK) {
//
//            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//
//
//            new Thread(() -> {
//                try {
//                    GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
//                    String accessToken = account.getIdToken();
//
//                    Toast.makeText(context, "Si se ejecuto"+accessToken, Toast.LENGTH_SHORT).show();
//
//                    // Conexión
////                    String fileId = "1Kjjx2oQYv-tc-nPqmK4hws0XKLMONNQ8";
////                    String url = "https://www.googleapis.com/drive/v3/files/" + fileId + "?alt=media";
////                    URL obj = new URL(url);
////                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
////                    con.setRequestMethod("GET");
////                    con.setRequestProperty("Authorization", "Bearer " + accessToken);
////
////                    // Lectura
////                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
////                    String inputLine;
////                    StringBuffer content = new StringBuffer();
////                    while ((inputLine = in.readLine()) != null) {
////                        content.append(inputLine);
////                    }
////                    in.close();
////                    Log.d("TAG", content.toString());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // Aquí puedes agregar el código para manejar el error
//                    System.out.println(e);
//                }
//            }).start();
//        }
//    }
//}




public class CreateSql {
    private Context context;
    public CreateSql(Context context) {
        this.context = context;
    }
    public String createText() {
        SentenciasSql sql = new SentenciasSql(context);

        String msg =
                "DROP DATABASE IF EXISTS natacion; \n" +
                        "CREATE DATABASE natacion;\n" +
                        "USE natacion;\n" +
                        "CREATE TABLE " +
                        TABLA_NADADOR + " (" +
                        CAMPO_NADADOR_CEDULA + " INTEGER PRIMARY KEY, " +
                        CAMPO_NADADOR_NOMBRE + " TEXT, " +
                        CAMPO_NADADOR_NOMBRES + " TEXT, " +
                        CAMPO_NADADOR_APELLIDOS + " TEXT, " +
                        CAMPO_NADADOR_FECHA_NACIMIENTO + " TEXT, " +
                        CAMPO_NADADOR_GENERO + " TEXT, " +
                        CAMPO_NADADOR_GRUPO + " INTEGER " +
                        ")" + ";\n" +
                        "CREATE TABLE " +
                        TABLA_TIEMPOS + " (" + CAMPO_TIEMPOS_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                        CAMPO_TIEMPOS_CEDULA + " INTEGER, " +
                        CAMPO_TIEMPOS_FECHA + " TEXT, " +
                        CAMPO_TIEMPOS_PRUEBA + " TEXT, " +
                        CAMPO_TIEMPOS_METROS + " TEXT, " +
                        CAMPO_TIEMPOS_TIEMPO + " TEXT);\n" +
                        "CREATE TABLE " +
                        TABLA_METROS + " (" + CAMPO_METROS_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                        CAMPO_METROS_NOMBRE + " TEXT);\n" +

                        "CREATE TABLE " +
                        TABLA_PRUEBAS + " (" + CAMPO_PRUEBAS_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                        CAMPO_PRUEBAS_NOMBRE + " TEXT);\n" +


                        "CREATE TABLE IF NOT EXISTS serie (\n" +
                        "                id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                        "                id_evento INTEGER,\n" +
                        "                numero INTEGER,\n" +
                        "                cedula INTEGER,\n" +
                        "                nadador TEXT,\n" +
                        "                institucion TEXT,\n" +
                        "                tiempo TEXT,\n" +
                        "                descalificado INTEGER DEFAULT 0,\n" +
                        "                puntos INTEGER,\n" +
                        "                lugar INTEGER,\n" +
                        "                premiado INTEGER DEFAULT 1\n" +
                        "            );\n" +
                        "            CREATE TABLE IF NOT EXISTS evento (\n" +
                        "                id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                        "                id_competencia INTEGER,\n" +
                        "                numero INTEGER,\n" +
                        "                prueba TEXT,\n" +
                        "                categoria TEXT,\n" +
                        "                genero TEXT\n" +
                        "            );\n" +
                        "            CREATE TABLE IF NOT EXISTS competencia (\n" +
                        "                id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                        "                nombre TEXT,\n" +
                        "                fecha TEXT\n" +
                        "            );" +
                        "            CREATE TABLE IF NOT EXISTS institucion (\n" +
                        "                id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                        "                nombre TEXT\n" +
                        "            );" +
                        "            CREATE TABLE IF NOT EXISTS institucion_nadador (\n" +
                        "                id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                        "                id_nadador INTEGER,\n" +
                        "                id_institucion INTEGER,\n" +
                        "                id_competencia INTEGER,\n" +
                        "                categoria TEXT,\n" +
                        "                configCheck TEXT\n" +
                        "            );" +

                        


                        "INSERT INTO " + TABLA_PRUEBAS +
                        " (" +
                        CAMPO_PRUEBAS_NOMBRE +
                        ")values" + "\n" + sql.getInsertInto(TABLA_PRUEBAS) + "\n" +

                        "INSERT INTO " + TABLA_METROS +
                        " (" +
                        CAMPO_METROS_NOMBRE +
                        ")values" + "\n" + sql.getInsertInto(TABLA_METROS) + "\n" +

                        "INSERT INTO " + TABLA_NADADOR +
                        " (" +
                        CAMPO_NADADOR_CEDULA + "," +
                        CAMPO_NADADOR_NOMBRE + "," +
                        CAMPO_NADADOR_NOMBRES + "," +
                        CAMPO_NADADOR_APELLIDOS + "," +
                        CAMPO_NADADOR_FECHA_NACIMIENTO + "," +
                        CAMPO_NADADOR_GENERO + "," +
                        CAMPO_NADADOR_GRUPO +
                        ")values" + "\n" + sql.getInsertInto(TABLA_NADADOR) + "\n" +
                        "INSERT INTO " + TABLA_TIEMPOS +
                        " (" +
                        CAMPO_TIEMPOS_CEDULA + "," +
                        CAMPO_TIEMPOS_FECHA + "," +
                        CAMPO_TIEMPOS_PRUEBA + "," +
                        CAMPO_TIEMPOS_METROS + "," +
                        CAMPO_TIEMPOS_TIEMPO +
                        ")values" + "\n" + sql.getInsertInto(TABLA_TIEMPOS) + "\n"+

                        "INSERT INTO competencia (nombre,fecha)values"+sql.getInsertInto("competencia") + "\n"+
                        "INSERT INTO evento (id_competencia,numero,prueba,categoria,genero)values"+sql.getInsertInto("evento") + "\n"+
                        "INSERT INTO institucion (nombre)values"+sql.getInsertInto("institucion") + "\n"+
                        "INSERT INTO institucion_nadador (id_nadador,id_institucion,id_competencia,categoria,configCheck)values"+sql.getInsertInto("institucion_nadador") + "\n"+
                        "INSERT INTO serie (id_evento,numero,cedula,nadador,institucion,tiempo,descalificado,puntos,lugar,premiado)values"+sql.getInsertInto("serie") + "\n";


        return msg;


    }
    public void createSql(String texto) {
        String nombreArchivo = "natacion.sql";
        File directorioBD = context.getApplicationContext().getDatabasePath(nombreArchivo);

        // Verificar si el directorio de la base de datos existe, si no, crearlo
        if (!directorioBD.getParentFile().exists()) {
            directorioBD.getParentFile().mkdirs();
        }

        String rutaArchivo = directorioBD.getAbsolutePath();

        try {
            FileWriter archivo = new FileWriter(rutaArchivo);
            archivo.write(texto);
            archivo.close();
            System.out.println("Archivo creado correctamente en la carpeta de bases de datos de la aplicación.");
        } catch (IOException e) {
            System.out.println("Se produjo un error al crear el archivo.");
            e.printStackTrace();
        }
    }

}


