package com.example.cronoswim.data_bases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Metros;
import com.example.cronoswim.entidades.Nadador;
import com.example.cronoswim.entidades.Pruebas;
import com.example.cronoswim.entidades.Tiempos;
import com.example.cronoswim.funciones.Cronometro;
import com.example.cronoswim.funciones.Funciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class SentenciasSql {
    Context context;
    ArrayList<Cronometros> listaCronometros= new ArrayList<>();
    ArrayList<Cronometro> listaCronos= new ArrayList<>();
    public SentenciasSql(@Nullable Context context) {
        this.context=context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public int getCantidadCronometros() {
        Conexion_BD conn = new Conexion_BD(context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        int cantidad = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + Tablas_BD.TABLA_CRONO, null);
        if (cursor.moveToFirst()) {
            cantidad = cursor.getInt(0);
        }
        cursor.close();
        return cantidad;
    }

    public ArrayList<Cronometros> getListaCronometros() {
        Conexion_BD conn=new Conexion_BD(context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Cronometros registro = null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_CRONO+" ORDER BY "+Tablas_BD.CAMPO_CRONO_POSICION+" ASC",null);
        while (cursor.moveToNext()){
            registro=new Cronometros();
            registro.setId(cursor.getInt(0));
            registro.setNombre(cursor.getString(1));
            registro.setMetros(cursor.getString(2));
            registro.setPrueba(cursor.getString(3));
            registro.setConfig(cursor.getString(4));
//            registro.setRendimiento(cursor.getString(5));
            registro.setTiempo(cursor.getString(5));
            registro.setVuelta(cursor.getString(6));
            registro.setPosicion(cursor.getInt(7));
            registro.setCedula(cursor.getInt(8));
            listaCronometros.add(registro);
        }
        cursor.close();
        return listaCronometros;
    }
    public ArrayList<String> obtenerNadadores() {
        ArrayList<Nadador> listaNadadores= new ArrayList<>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Nadador registro = null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_NADADOR+" ORDER BY nadador ASC",null);
        while (cursor.moveToNext()){
            registro=new Nadador();
            registro.setCedula(cursor.getInt(0));
            registro.setNadador(cursor.getString(1));
            registro.setNombres(cursor.getString(2));
            registro.setApellidos(cursor.getString(3));
            registro.setFecha_nacimiento(cursor.getString(4));
            registro.setGenero(cursor.getString(5));
            registro.setGrupo(cursor.getInt(6));
            listaNadadores.add(registro);
        }
        cursor.close();
        ArrayList<String> lista=new ArrayList<String>();
        lista.add("Seleccione el Nadador");
        for(int i=0;i<listaNadadores.size();i++){
            lista.add(i+1+".-"+listaNadadores.get(i).getNadador()+".-"+listaNadadores.get(i).getCedula());
        }
        return lista;
    }
    public ArrayList<Nadador> getNadadores(int grupo) {
        ArrayList<Nadador> listaEntidad= new ArrayList<Nadador>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Nadador dato=null;
        listaEntidad=new ArrayList<Nadador>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_NADADOR+" WHERE grupo="+grupo,null);
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
        return listaEntidad;
    }
    public ArrayList<String> obtenerArrayPruebas() {
        ArrayList<Pruebas> listaPruebas= new ArrayList<>();
        ArrayList<String> lista;
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Pruebas registro = null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_PRUEBAS,null);
        while (cursor.moveToNext()){
            registro=new Pruebas();
            registro.setId(cursor.getInt(0));
            registro.setNombre(cursor.getString(1));
            listaPruebas.add(registro);
        }
        cursor.close();
        lista=new ArrayList<String>();
        lista.add("Seleccione la Prueba");

        for(int i=0;i<listaPruebas.size();i++){
            lista.add(i+1+".-"+listaPruebas.get(i).getNombre());
        }
        return lista;
    }
    public ArrayList<String> obtenerArrayMetros() {

        ArrayList<Metros> listaMetros= new ArrayList<>();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Metros registro = null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_METROS,null);
        while (cursor.moveToNext()){
            registro=new Metros();
            registro.setId(cursor.getInt(0));
            registro.setMetros(cursor.getString(1));
            listaMetros.add(registro);
        }
        cursor.close();
        ArrayList<String> lista=new ArrayList<String>();
        lista.add("Seleccione los Metros");

        for(int i=0;i<listaMetros.size();i++){
            lista.add(i+1+".-"+listaMetros.get(i).getMetros());
        }
        return lista;
    }
    public long insertarCrono(Cronometros crono){
        long id=0;
        Conexion_BD conn=new Conexion_BD(context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
            values.put(Tablas_BD.CAMPO_CRONO_NOMBRE,crono.getNombre());
            values.put(Tablas_BD.CAMPO_CRONO_METROS,crono.getMetros());
            values.put(Tablas_BD.CAMPO_CRONO_PRUEBA,crono.getPrueba());
            values.put(Tablas_BD.CAMPO_CRONO_CONFIG,crono.getConfig());
//            values.put(Tablas_BD.CAMPO_CRONO_RENDIMIENTO,rendimiento);
            values.put(Tablas_BD.CAMPO_CRONO_TIEMPO,"00:00:00,00");
            values.put(Tablas_BD.CAMPO_CRONO_POSICION,crono.getPosicion());
            values.put(Tablas_BD.CAMPO_CRONO_CEDULA,crono.getCedula());
            long idResultante=db.insert(Tablas_BD.TABLA_CRONO,Tablas_BD.CAMPO_CRONO_ID,values);
//            Toast.makeText(context,"Id: "+id,Toast.LENGTH_SHORT).show();
            db.close();
            return idResultante;
    }
    public boolean editCrono(Cronometros entidad){
        boolean correcto= false;
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={""+entidad.getId()};
        ContentValues values=new ContentValues();

        values.put(Tablas_BD.CAMPO_CRONO_NOMBRE,entidad.getNombre());
        values.put(Tablas_BD.CAMPO_CRONO_METROS,entidad.getMetros());
        values.put(Tablas_BD.CAMPO_CRONO_PRUEBA,entidad.getPrueba());
        values.put(Tablas_BD.CAMPO_CRONO_CONFIG,entidad.getConfig());
        values.put(Tablas_BD.CAMPO_CRONO_VUELTA,entidad.getVuelta());
//        values.put(Tablas_BD.CAMPO_CRONO_RENDIMIENTO,entidad.getRendimiento());
        values.put(Tablas_BD.CAMPO_CRONO_TIEMPO,entidad.getTiempo());
        values.put(Tablas_BD.CAMPO_CRONO_POSICION,entidad.getPosicion());
        values.put(Tablas_BD.CAMPO_CRONO_CEDULA,entidad.getCedula());
        db.update(Tablas_BD.TABLA_CRONO,values,Tablas_BD.CAMPO_CRONO_ID+"=?",parametros);
//        Toast.makeText(context,"Actualizado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
        return correcto;
    }
    public void editTime(Tiempos entidad){
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={""+entidad.getId()};
        ContentValues values=new ContentValues();
        values.put(Tablas_BD.CAMPO_TIEMPOS_PRUEBA,entidad.getPrueba());
        values.put(Tablas_BD.CAMPO_TIEMPOS_METROS,entidad.getMetros());
        values.put(Tablas_BD.CAMPO_TIEMPOS_TIEMPO,entidad.getTiempo());
        db.update(Tablas_BD.TABLA_TIEMPOS,values,Tablas_BD.CAMPO_TIEMPOS_ID+"=?",parametros);
//        Toast.makeText(context,"Actualizado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
    }
    public void deleteTime(int id){
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={""+id};
        db.delete(Tablas_BD.TABLA_TIEMPOS,Tablas_BD.CAMPO_TIEMPOS_ID+"=?",parametros);
//        Toast.makeText(context,"Eliminado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
    }
    public void editarPositionCrono(int cedula,int newPosition){
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={""+cedula};
        ContentValues values=new ContentValues();
        values.put(Tablas_BD.CAMPO_CRONO_POSICION,newPosition);
        db.update(Tablas_BD.TABLA_CRONO,values,Tablas_BD.CAMPO_CRONO_CEDULA+"=?",parametros);
//        Toast.makeText(context,"Actualizado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
    }
    public void saveTime(Cronometros entidad){
        Funciones funcion = new Funciones();
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Tablas_BD.CAMPO_TIEMPOS_FECHA,funcion.obtenerFechaActual());
        values.put(Tablas_BD.CAMPO_TIEMPOS_CEDULA,entidad.getCedula());
        values.put(Tablas_BD.CAMPO_TIEMPOS_METROS,entidad.getMetros());
        values.put(Tablas_BD.CAMPO_TIEMPOS_PRUEBA,entidad.getPrueba());
        values.put(Tablas_BD.CAMPO_TIEMPOS_TIEMPO,entidad.getTiempo());
        db.insert(Tablas_BD.TABLA_TIEMPOS,Tablas_BD.CAMPO_TIEMPOS_ID,values);
        db.close();
    }
    public boolean eliminarRegistro(int id){
        boolean correcto= false;
        Conexion_BD conn=new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={""+id};

        db.delete(Tablas_BD.TABLA_CRONO,Tablas_BD.CAMPO_CRONO_ID+"=?",parametros);
//        Toast.makeText(context,"Eliminado Correctamente",Toast.LENGTH_LONG).show();
        db.close();
        return correcto;
    }
    public void eliminarTodosRegistros(){
        Conexion_BD conn = new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.delete(Tablas_BD.TABLA_CRONO, null, null);
        db.close();
    }
    public String getTimeRecord(Cronometros entidad) {
        Conexion_BD conn = new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String record=null;

//        select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_TIEMPOS+
                " WHERE prueba='"+entidad.getPrueba()+"' AND metros='"+entidad.getMetros()+"' AND cedula=+"+entidad.getCedula()+
                " ORDER BY tiempos.tiempo DESC ",null);

        while (cursor.moveToNext()){
            record= cursor.getString(5);
        }
        return record;

    }
    public String getDateTimeREcord(Cronometros entidad) {
        Conexion_BD conn = new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String record=null;

//        select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Tablas_BD.TABLA_TIEMPOS+
                " WHERE prueba='"+entidad.getPrueba()+"' AND metros='"+entidad.getMetros()+"' AND cedula=+"+entidad.getCedula()+
                " ORDER BY tiempos.tiempo DESC ",null);

        while (cursor.moveToNext()){
            record= cursor.getString(2);
        }
        return record;

    }
    public ArrayList<String> getTiemposString(String conditions) {

        ArrayList<String> listaInformacion=new ArrayList<>();
        ArrayList<Tiempos> listaEntidad=new ArrayList<>();
        Conexion_BD conn = new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Tiempos dato=null;
        listaEntidad=new ArrayList<Tiempos>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT tiempos.*,nadador.nadador FROM "+ Tablas_BD.TABLA_TIEMPOS+
                        " LEFT JOIN nadador ON tiempos.cedula = nadador.cedula "+conditions+" ORDER BY tiempos.id Desc LIMIT 100"
                ,null);

        while (cursor.moveToNext()){
            dato=new Tiempos();
            dato.setId(cursor.getInt(0));
            dato.setCedula(cursor.getString(1));
            dato.setFecha(cursor.getString(2));
            dato.setPrueba(cursor.getString(3));
            dato.setMetros(cursor.getString(4));
            dato.setTiempo(cursor.getString(5));
            dato.setNadador(cursor.getString(6));

            listaEntidad.add(dato);
        }
        listaInformacion=new ArrayList<String>();
        for (int i=0; i<listaEntidad.size();i++){
            listaInformacion.add(i+"- ("+listaEntidad.get(i).getId()+")"+
                    listaEntidad.get(i).getFecha()+"\n"+
                    listaEntidad.get(i).getNadador()+" "+listaEntidad.get(i).getMetros()+
                    listaEntidad.get(i).getPrueba()+"\n"+
                    listaEntidad.get(i).getTiempo()
            );
        }
        return  listaInformacion;
    }
    public ArrayList<Tiempos> getTiempos(String conditions) {
        ArrayList<Tiempos> listaEntidad=new ArrayList<>();
        Conexion_BD conn = new Conexion_BD(this.context,Tablas_BD.NOMBRE_BASE_DATOS,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        Tiempos dato=null;
        listaEntidad=new ArrayList<Tiempos>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT tiempos.*,nadador.nadador FROM "+ Tablas_BD.TABLA_TIEMPOS+
                        " LEFT JOIN nadador ON tiempos.cedula = nadador.cedula "+conditions+"  ORDER BY tiempos.id DESC LIMIT 100 "
                ,null);

        while (cursor.moveToNext()){
            dato=new Tiempos();
            dato.setId(cursor.getInt(0));
            dato.setCedula(cursor.getString(1));
            dato.setFecha(cursor.getString(2));
            dato.setPrueba(cursor.getString(3));
            dato.setMetros(cursor.getString(4));
            dato.setTiempo(cursor.getString(5));
            dato.setNadador(cursor.getString(6));

            listaEntidad.add(dato);
        }

        return  listaEntidad;
    }
    public String getInsertInto(String tabla) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tabla, null);
        StringBuilder result = new StringBuilder();
        int totalRows = cursor.getCount();
        int currentRow = 0;
        while (cursor.moveToNext()) {
            int columnCount = cursor.getColumnCount();
            StringBuilder rowData = new StringBuilder();
            rowData.append("(");
            for (int i = 0; i < columnCount; i++) {
                if(!Objects.equals(tabla, "nadador")){
                    if(i!=0){
                        rowData.append("'");
                        rowData.append(cursor.getString(i));
                        if (i < columnCount - 1) {
                            rowData.append("',");
                        }
                    }
                }else{

                    rowData.append("'");
                    rowData.append(cursor.getString(i));
                    if (i < columnCount - 1) {
                        rowData.append("',");
                    }
                }
            }
            currentRow++;
            if (currentRow == totalRows) {
                rowData.append("'");
                rowData.append(")");
                rowData.append(";");
            } else {
                rowData.append("'");
                rowData.append(")");
                rowData.append(",");
            }
            result.append(rowData.toString());
            result.append("\n");
        }

        return result.toString();
    }
    public ArrayList<String> getList(String sentencia) {
//        Toast.makeText(context,"Edgar",Toast.LENGTH_SHORT).show();
        ArrayList<String> lista = new ArrayList<>();
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        Cursor cursor = db.rawQuery(sentencia, null);
        int count=1;
        while (cursor.moveToNext()) {
            lista.add(count+".-"+cursor.getString(0));
            count++;
        }
        cursor.close();
        return lista;
    }
    // Método para vaciar una tabla específica
    public void vaciarTabla(String nombreTabla) {
        Conexion_BD conn = new Conexion_BD(this.context, Tablas_BD.NOMBRE_BASE_DATOS, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            db.delete(nombreTabla, null, null);
//            Toast.makeText(context, "Tabla " + nombreTabla + " vaciada exitosamente", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al vaciar la tabla " + nombreTabla + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
    // Método para vaciar todas las tablas
    public void vaciarTodasLasTablas() {
        vaciarTabla(Tablas_BD.TABLA_NADADOR);
        vaciarTabla(Tablas_BD.TABLA_PRUEBAS);
        vaciarTabla(Tablas_BD.TABLA_METROS);
        vaciarTabla(Tablas_BD.TABLA_TIEMPOS);
        vaciarTabla(Tablas_BD.TABLA_CUENTA);
        vaciarTabla(Tablas_BD.TABLA_USUARIO);
        vaciarTabla(Tablas_BD.TABLA_ROLES);
        vaciarTabla(Tablas_BD.TABLA_COMPETENCIA);
        vaciarTabla(Tablas_BD.TABLA_SERIE);
        vaciarTabla(Tablas_BD.TABLA_EVENTO);
        vaciarTabla(Tablas_BD.TABLA_INSTITUCION_NADADOR);
        vaciarTabla(Tablas_BD.TABLA_INSTITUCION);
        vaciarTabla(Tablas_BD.TABLA_LOGS);
        // Añade aquí todas las demás tablas que necesites vaciar
    }






}
