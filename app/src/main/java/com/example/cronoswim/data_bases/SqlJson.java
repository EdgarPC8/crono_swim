package com.example.cronoswim.data_bases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.cronoswim.entidades.Competencia;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Cuenta;
import com.example.cronoswim.entidades.Evento;
import com.example.cronoswim.entidades.Institucion;
import com.example.cronoswim.entidades.InstitucionNadador;
import com.example.cronoswim.entidades.Logs;
import com.example.cronoswim.entidades.Metros;
import com.example.cronoswim.entidades.Nadador;
import com.example.cronoswim.entidades.Pruebas;
import com.example.cronoswim.entidades.Roles;
import com.example.cronoswim.entidades.Serie;
import com.example.cronoswim.entidades.Tiempos;
import com.example.cronoswim.entidades.Usuario;
import com.example.cronoswim.funciones.Cronometro;
import com.example.cronoswim.funciones.Funciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SqlJson {
    Context context;
    public SqlJson(@Nullable Context context) {
        this.context=context;
    }
    public JSONArray getPruebas() throws JSONException {
        ArrayList<Pruebas> listaEntidad = new ArrayList<Pruebas>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Pruebas dato=null;
        listaEntidad=new ArrayList<Pruebas>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_PRUEBAS,null);
        while (cursor.moveToNext()){
            dato=new Pruebas();
            dato.setId(cursor.getInt(0));
            dato.setNombre(cursor.getString(1));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Pruebas valor : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", valor.getId());
            jsonObject.put("nombre", valor.getNombre());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getMetros() throws JSONException {
        ArrayList<Metros> listaEntidad = new ArrayList<Metros>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Metros dato=null;
        listaEntidad=new ArrayList<Metros>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_METROS,null);
        while (cursor.moveToNext()){
            dato=new Metros();
            dato.setId(cursor.getInt(0));
            dato.setMetros(cursor.getString(1));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Metros valor : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", valor.getId());
            jsonObject.put("metros", valor.getMetros());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getNadadores() throws JSONException {
        ArrayList<Nadador> listaEntidad = new ArrayList<Nadador>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Nadador dato=null;
        listaEntidad=new ArrayList<Nadador>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_NADADOR,null);
        while (cursor.moveToNext()){
            dato=new Nadador();
            dato.setCedula(cursor.getInt(0));
            dato.setNadador(cursor.getString(1));
            dato.setNombres(cursor.getString(2));
            dato.setApellidos(cursor.getString(3));
            dato.setFecha_nacimiento(cursor.getString(4));
            dato.setGenero(cursor.getString(5));
            dato.setGrupo(cursor.getInt(6));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Nadador nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cedula", nadador.getCedula());
            jsonObject.put("nadador", nadador.getNadador());
            jsonObject.put("nombres", nadador.getNombres());
            jsonObject.put("apellidos", nadador.getApellidos());
            jsonObject.put("fecha_nacimiento", nadador.getFecha_nacimiento());
            jsonObject.put("genero", nadador.getGenero());
            jsonObject.put("grupo", nadador.getGrupo());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getTiempos() throws JSONException {
        ArrayList<Tiempos> listaEntidad = new ArrayList<Tiempos>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Tiempos dato=null;
        listaEntidad=new ArrayList<Tiempos>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_TIEMPOS,null);
        while (cursor.moveToNext()){
            dato=new Tiempos();
            dato.setId(cursor.getInt(0));
            dato.setCedula(cursor.getString(1));
            dato.setFecha(cursor.getString(2));
            dato.setPrueba(cursor.getString(3));
            dato.setMetros(cursor.getString(4));
            dato.setTiempo(cursor.getString(5));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Tiempos nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("cedula", nadador.getCedula());
            jsonObject.put("fecha", nadador.getFecha());
            jsonObject.put("prueba", nadador.getPrueba());
            jsonObject.put("metros", nadador.getMetros());
            jsonObject.put("tiempo", nadador.getTiempo());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getCuenta() throws JSONException {
        ArrayList<Cuenta> listaEntidad = new ArrayList<Cuenta>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Cuenta dato=null;
        listaEntidad=new ArrayList<Cuenta>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_CUENTA,null);
        while (cursor.moveToNext()){
            dato=new Cuenta();
            dato.setId(cursor.getInt(0));
            dato.setUsername(cursor.getString(1));
            dato.setPassword(cursor.getString(2));
            dato.setIdUsuario(cursor.getInt(3));
            dato.setRol(cursor.getInt(4));
            listaEntidad.add(dato);
        }

        JSONArray jsonArray = new JSONArray();

        for (Cuenta nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("rol", nadador.getRol());
            jsonObject.put("idUsuario", nadador.getIdUsuario());
            jsonObject.put("username", nadador.getUsername());
            jsonObject.put("password", nadador.getPassword());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getUsuario() throws JSONException {
        ArrayList<Usuario> listaEntidad = new ArrayList<Usuario>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario dato=null;
        listaEntidad=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_USUARIO,null);
        while (cursor.moveToNext()){
            dato=new Usuario();
            dato.setId(cursor.getInt(0));
            dato.setCedula(cursor.getInt(1));
            dato.setPrimer_nombre(cursor.getString(2));
            dato.setSegundo_nombre(cursor.getString(3));
            dato.setPrimer_apellido(cursor.getString(4));
            dato.setSegundo_apellido(cursor.getString(5));
            dato.setFecha_nacimiento(cursor.getString(6));
            dato.setGenero(cursor.getString(7));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Usuario nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("cedula", nadador.getCedula());
            jsonObject.put("primer_nombre", nadador.getPrimer_nombre());
            jsonObject.put("segundo_nombre", nadador.getSegundo_nombre());
            jsonObject.put("primer_apellido", nadador.getPrimer_apellido());
            jsonObject.put("segundo_apellido", nadador.getSegundo_apellido());
            jsonObject.put("fecha_nacimiento", nadador.getFecha_nacimiento());
            jsonObject.put("genero", nadador.getGenero());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getEvento() throws JSONException {
        ArrayList<Evento> listaEntidad = new ArrayList<Evento>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Evento dato=null;
        listaEntidad=new ArrayList<Evento>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_EVENTO,null);
        while (cursor.moveToNext()){
            dato=new Evento();
            dato.setId(cursor.getInt(0));
            dato.setId_competencia(cursor.getInt(1));
            dato.setNumero(cursor.getInt(2));
            dato.setName(cursor.getString(3));
            dato.setPrueba(cursor.getString(4));
            dato.setMetros(cursor.getString(5));
            dato.setCategoria(cursor.getString(6));
            dato.setGenero(cursor.getString(7));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Evento nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("id_competencia", nadador.getId_competencia());
            jsonObject.put("numero", nadador.getNumero());
            jsonObject.put("name", nadador.getName());
            jsonObject.put("prueba", nadador.getPrueba());
            jsonObject.put("metros", nadador.getMetros());
            jsonObject.put("categoria", nadador.getCategoria());
            jsonObject.put("genero", nadador.getGenero());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getSerie() throws JSONException {
        ArrayList<Serie> listaEntidad = new ArrayList<Serie>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Serie dato=null;
        listaEntidad=new ArrayList<Serie>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_SERIE,null);
        while (cursor.moveToNext()){
            dato=new Serie();
            dato.setId(cursor.getInt(0));
            dato.setId_evento(cursor.getInt(1));
            dato.setNumero(cursor.getInt(2));
            dato.setCarril(cursor.getInt(3));
            dato.setCedula(cursor.getInt(4));
            dato.setNadador(cursor.getString(5));
            dato.setId_institucion(cursor.getInt(6));
            dato.setTiempo(cursor.getString(7));
            dato.setDescalificado(cursor.getInt(8));
            dato.setPuntos(cursor.getInt(9));
            dato.setLugar(cursor.getInt(10));
            dato.setPremiado(cursor.getInt(11));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Serie nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("id_evento", nadador.getId_evento());
            jsonObject.put("numero", nadador.getNumero());
            jsonObject.put("carril", nadador.getCarril());
            jsonObject.put("cedula", nadador.getCedula());
            jsonObject.put("nadador", nadador.getNadador());
            jsonObject.put("id_institucion", nadador.getId_institucion());
            jsonObject.put("tiempo", nadador.getTiempo());
            jsonObject.put("descalificado", nadador.getDescalificado());
            jsonObject.put("puntos", nadador.getPuntos());
            jsonObject.put("lugar", nadador.getLugar());
            jsonObject.put("premiado", nadador.getPremiado());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getRoles() throws JSONException {
        ArrayList<Roles> listaEntidad = new ArrayList<Roles>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Roles dato=null;
        listaEntidad=new ArrayList<Roles>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_ROLES,null);
        while (cursor.moveToNext()){
            dato=new Roles();
            dato.setId(cursor.getInt(0));
            dato.setName(cursor.getString(1));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Roles nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("name", nadador.getName());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getInstitucionNadador() throws JSONException {
        ArrayList<InstitucionNadador> listaEntidad = new ArrayList<InstitucionNadador>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        InstitucionNadador dato=null;
        listaEntidad=new ArrayList<InstitucionNadador>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_INSTITUCION_NADADOR,null);
        while (cursor.moveToNext()){
            dato=new InstitucionNadador();
            dato.setId(cursor.getInt(0));
            dato.setId_nadador(cursor.getInt(1));
            dato.setId_institucion(cursor.getInt(2));
            dato.setId_competencia(cursor.getInt(3));
            dato.setCategoria(cursor.getString(4));
            dato.setConfigCheck(cursor.getString(5));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (InstitucionNadador nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("id_nadador", nadador.getId_nadador());
            jsonObject.put("id_institucion", nadador.getId_institucion());
            jsonObject.put("id_competencia", nadador.getId_competencia());
            jsonObject.put("categoria", nadador.getCategoria());
            jsonObject.put("configCheck", nadador.getConfigCheck());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getInstitucion() throws JSONException {
        ArrayList<Institucion> listaEntidad = new ArrayList<Institucion>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Institucion dato=null;
        listaEntidad=new ArrayList<Institucion>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_INSTITUCION,null);
        while (cursor.moveToNext()){
            dato=new Institucion();
            dato.setId(cursor.getInt(0));
            dato.setNombre(cursor.getString(1));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Institucion nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("nombre", nadador.getNombre());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getCompetencia() throws JSONException {
        ArrayList<Competencia> listaEntidad = new ArrayList<Competencia>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Competencia dato=null;
        listaEntidad=new ArrayList<Competencia>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_COMPETENCIA,null);
        while (cursor.moveToNext()){
            dato=new Competencia();
            dato.setId(cursor.getInt(0));
            dato.setNombre(cursor.getString(1));
            dato.setFecha(cursor.getString(2));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Competencia nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("nombre", nadador.getNombre());
            jsonObject.put("fecha", nadador.getFecha());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
    public JSONArray getLogs() throws JSONException {
        ArrayList<Logs> listaEntidad = new ArrayList<Logs>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Logs dato=null;
        listaEntidad=new ArrayList<Logs>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_LOGS,null);
        while (cursor.moveToNext()){
            dato=new Logs();
            dato.setId(cursor.getInt(0));
            dato.setHttpMethod(cursor.getString(1));
            dato.setAction(cursor.getString(2));
            dato.setEndPoint(cursor.getString(3));
            dato.setDescription(cursor.getString(4));
            dato.setDate(cursor.getString(5));
            listaEntidad.add(dato);
        }
        JSONArray jsonArray = new JSONArray();

        for (Logs nadador : listaEntidad) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", nadador.getId());
            jsonObject.put("httpMethod", nadador.getHttpMethod());
            jsonObject.put("action", nadador.getAction());
            jsonObject.put("endPoint", nadador.getEndPoint());
            jsonObject.put("description", nadador.getDescription());
            jsonObject.put("date", nadador.getDate());
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }

    public void setPruebas(JSONArray pruebas) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < pruebas.length(); i++) {
                JSONObject dato = pruebas.getJSONObject(i);
                String id = dato.getString("id");
                String nombre = dato.getString("nombre");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_PRUEBAS_NOMBRE, nombre);
                values.put(Tablas_BD.CAMPO_PRUEBAS_ID, id);
                db.insert(Tablas_BD.TABLA_PRUEBAS, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setMetros(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String nombre = dato.getString("metros");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_METROS_ID,id);
                values.put(Tablas_BD.CAMPO_METROS_NOMBRE,nombre);
                db.insert(Tablas_BD.TABLA_METROS, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setNadadores(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String cedula = dato.getString("cedula");
                String nadador = dato.getString("nadador");
                String nombres = dato.getString("nombres");
                String apellidos = dato.getString("apellidos");
                String fecha_nacimiento = dato.getString("fecha_nacimiento");
                String genero = dato.getString("genero");
                String grupo = dato.getString("grupo");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_NADADOR_CEDULA,cedula);
                values.put(Tablas_BD.CAMPO_NADADOR_NOMBRE,nadador);
                values.put(Tablas_BD.CAMPO_NADADOR_NOMBRES,nombres);
                values.put(Tablas_BD.CAMPO_NADADOR_APELLIDOS,apellidos);
                values.put(Tablas_BD.CAMPO_NADADOR_FECHA_NACIMIENTO,fecha_nacimiento);
                values.put(Tablas_BD.CAMPO_NADADOR_GENERO,genero);
                values.put(Tablas_BD.CAMPO_NADADOR_GRUPO,grupo);
                db.insert(Tablas_BD.TABLA_NADADOR, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setTiempos(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String cedula = dato.getString("cedula");
                String tiempo = dato.getString("tiempo");
                String metros = dato.getString("metros");
                String prueba = dato.getString("prueba");
                String fecha = dato.getString("fecha");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_TIEMPOS_ID,id);
                values.put(Tablas_BD.CAMPO_TIEMPOS_CEDULA,cedula);
                values.put(Tablas_BD.CAMPO_TIEMPOS_TIEMPO,tiempo);
                values.put(Tablas_BD.CAMPO_TIEMPOS_METROS,metros);
                values.put(Tablas_BD.CAMPO_TIEMPOS_PRUEBA,prueba);
                values.put(Tablas_BD.CAMPO_TIEMPOS_FECHA,fecha);
                db.insert(Tablas_BD.TABLA_TIEMPOS, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setCuenta(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String rol = dato.getString("rol");
                String idUsuario = dato.getString("idUsuario");
                String username = dato.getString("username");
                String password = dato.getString("password");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_CUENTA_ID,id);
                values.put(Tablas_BD.CAMPO_CUENTA_ROL,rol);
                values.put(Tablas_BD.CAMPO_CUENTA_IDUSUARIO,idUsuario);
                values.put(Tablas_BD.CAMPO_CUENTA_USERNAME,username);
                values.put(Tablas_BD.CAMPO_CUENTA_PASSWORD,password);
                db.insert(Tablas_BD.TABLA_CUENTA, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setUsuario(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String cedula = dato.getString("cedula");
                String primer_nombre = dato.getString("primer_nombre");
                String segundo_nombre = dato.getString("segundo_nombre");
                String primer_apellido = dato.getString("primer_apellido");
                String segundo_apellido = dato.getString("segundo_apellido");
                String fecha_nacimiento = dato.getString("fecha_nacimiento");
                String genero = dato.getString("genero");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_USUARIO_ID,id);
                values.put(Tablas_BD.CAMPO_USUARIO_CEDULA,cedula);
                values.put(Tablas_BD.CAMPO_USUARIO_PRIMER_NOMBRE,primer_nombre);
                values.put(Tablas_BD.CAMPO_USUARIO_SEGUNDO_NOMBRE,segundo_nombre);
                values.put(Tablas_BD.CAMPO_USUARIO_PRIMER_APELIDO,primer_apellido);
                values.put(Tablas_BD.CAMPO_USUARIO_SEGUNDO_APELLIDO,segundo_apellido);
                values.put(Tablas_BD.CAMPO_USUARIO_FECHA_NACIMIENTO,fecha_nacimiento);
                values.put(Tablas_BD.CAMPO_USUARIO_GENERO,genero);
                db.insert(Tablas_BD.TABLA_USUARIO, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setEvento(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String id_competencia = dato.getString("id_competencia");
                String numero = dato.getString("numero");
                String name = dato.getString("name");
                String prueba = dato.getString("prueba");
                String metros = dato.getString("metros");
                String categoria = dato.getString("categoria");
                String genero = dato.getString("genero");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_EVENTO_ID,id);
                values.put(Tablas_BD.CAMPO_EVENTO_ID_COMPETENCIA,id_competencia);
                values.put(Tablas_BD.CAMPO_EVENTO_NUMERO,numero);
                values.put(Tablas_BD.CAMPO_EVENTO_NAME,name);
                values.put(Tablas_BD.CAMPO_EVENTO_PRUEBA,prueba);
                values.put(Tablas_BD.CAMPO_EVENTO_METROS,metros);
                values.put(Tablas_BD.CAMPO_EVENTO_CATEGORIA,categoria);
                values.put(Tablas_BD.CAMPO_EVENTO_GENERO,genero);
                db.insert(Tablas_BD.TABLA_EVENTO, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setSerie(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String id_evento = dato.getString("id_evento");
                String numero = dato.getString("numero");
                String carril = dato.getString("carril");
                String cedula = dato.getString("cedula");
                String nadador = dato.getString("nadador");
                String id_institucion = dato.getString("id_institucion");
                String tiempo = dato.getString("tiempo");
                String descalificado = dato.getString("descalificado");
                String puntos = dato.getString("puntos");
                String lugar = dato.getString("lugar");
                String premiado = dato.getString("premiado");

                System.out.println(nadador);

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_SERIE_ID,id);
                values.put(Tablas_BD.CAMPO_SERIE_ID_EVENTO,id_evento);
                values.put(Tablas_BD.CAMPO_SERIE_NUMERO,numero);
                values.put(Tablas_BD.CAMPO_SERIE_CARRIL,carril);
                values.put(Tablas_BD.CAMPO_SERIE_CEDULA,cedula);
                values.put(Tablas_BD.CAMPO_SERIE_NADADOR,nadador);
                values.put(Tablas_BD.CAMPO_SERIE_ID_INSTITUCION,id_institucion);
                values.put(Tablas_BD.CAMPO_SERIE_TIEMPO,tiempo);
                values.put(Tablas_BD.CAMPO_SERIE_DESCALIFICADO,descalificado);
                values.put(Tablas_BD.CAMPO_SERIE_PUNTOS,puntos);
                values.put(Tablas_BD.CAMPO_SERIE_LUGAR,lugar);
                values.put(Tablas_BD.CAMPO_SERIE_PREMIADO,premiado);
                db.insert(Tablas_BD.TABLA_SERIE, null, values);
            }
            Toast.makeText(context, "Importación de series exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setRol(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String name = dato.getString("name");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_ROLES_ID,id);
                values.put(Tablas_BD.CAMPO_ROLES_NAME,name);
                db.insert(Tablas_BD.TABLA_ROLES, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setInstitucionNadador(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String id_nadador = dato.getString("id_nadador");
                String id_institucion = dato.getString("id_institucion");
                String id_competencia = dato.getString("id_competencia");
                String categoria = dato.getString("categoria");
                String configCheck = dato.getString("configCheck");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_INSTITUCION_NADADOR_ID,id);
                values.put(Tablas_BD.CAMPO_INSTITUCION_NADADOR_ID_NADADOR,id_nadador);
                values.put(Tablas_BD.CAMPO_INSTITUCION_NADADOR_ID_INSTITUCION,id_institucion);
                values.put(Tablas_BD.CAMPO_INSTITUCION_NADADOR_ID_COMPETENCIA,id_competencia);
                values.put(Tablas_BD.CAMPO_INSTITUCION_NADADOR_CATEGORIA,categoria);
                values.put(Tablas_BD.CAMPO_INSTITUCION_NADADOR_CONFIGCHECK,configCheck);
                db.insert(Tablas_BD.TABLA_INSTITUCION_NADADOR, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setInstitucion(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String nombre = dato.getString("nombre");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_INSTITUCION_ID,id);
                values.put(Tablas_BD.CAMPO_INSTITUCION_NOMBRE,nombre);
                db.insert(Tablas_BD.TABLA_INSTITUCION, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setCompetencia(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String nombre = dato.getString("nombre");
                String fecha = dato.getString("fecha");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_COMPETENCIA_ID,id);
                values.put(Tablas_BD.CAMPO_COMPETENCIA_NOMBRE,nombre);
                values.put(Tablas_BD.CAMPO_COMPETENCIA_FECHA,fecha);
                db.insert(Tablas_BD.TABLA_COMPETENCIA, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    public void setLogs(JSONArray data) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject dato = data.getJSONObject(i);
                String id = dato.getString("id");
                String httpMethod = dato.getString("httpMethod");
                String action = dato.getString("action");
                String endPoint = dato.getString("endPoint");
                String description = dato.getString("description");
                String date = dato.getString("date");

                ContentValues values = new ContentValues();
                values.put(Tablas_BD.CAMPO_LOGS_ID,id);
                values.put(Tablas_BD.CAMPO_LOGS_METHOD,httpMethod);
                values.put(Tablas_BD.CAMPO_LOGS_ACTION,action);
                values.put(Tablas_BD.CAMPO_LOGS_ENDPOINT,endPoint);
                values.put(Tablas_BD.CAMPO_LOGS_DESCRIPTION,description);
                values.put(Tablas_BD.CAMPO_LOGS_DATE,date);
                db.insert(Tablas_BD.TABLA_LOGS, null, values);
            }
//            Toast.makeText(context, "Importación de pruebas exitosa", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(context, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }






}
