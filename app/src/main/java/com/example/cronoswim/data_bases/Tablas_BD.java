package com.example.cronoswim.data_bases;

public class Tablas_BD {
    public static final String NOMBRE_BASE_DATOS="crono_swim.db";

    //Constantes campos tabla nadador
    public static final String TABLA_NADADOR="nadador";
    public static final String CAMPO_NADADOR_CEDULA="cedula";
    public static final String CAMPO_NADADOR_NOMBRE="nadador";
    public static final String CAMPO_NADADOR_NOMBRES="nombres";
    public static final String CAMPO_NADADOR_APELLIDOS="apellidos";
    public static final String CAMPO_NADADOR_FECHA_NACIMIENTO="fecha_nacimiento";
    public static final String CAMPO_NADADOR_GENERO="genero";
    public static final String CAMPO_NADADOR_GRUPO="grupo";

    public static final String CREAR_TABLA_NADADOR="CREATE TABLE " +
            ""+TABLA_NADADOR+ " ("+
            CAMPO_NADADOR_CEDULA+" BIGINT PRIMARY KEY, "+
            CAMPO_NADADOR_NOMBRE+" TEXT, "+
            CAMPO_NADADOR_NOMBRES+" TEXT, "+
            CAMPO_NADADOR_APELLIDOS+" TEXT, "+
            CAMPO_NADADOR_FECHA_NACIMIENTO+" DATE, "+
            CAMPO_NADADOR_GENERO+" TEXT, "+
            CAMPO_NADADOR_GRUPO+" INTEGER "+
            ")";

    //Constantes campos tabla cronometro
    public static final String TABLA_CRONO="cronometro";
    public static final String CAMPO_CRONO_ID="id";
    public static final String CAMPO_CRONO_NOMBRE="nombre";
    public static final String CAMPO_CRONO_METROS="metros";
    public static final String CAMPO_CRONO_PRUEBA="prueba";
    public static final String CAMPO_CRONO_CONFIG ="config";
//    public static final String CAMPO_CRONO_RENDIMIENTO="rendimiento";
    public static final String CAMPO_CRONO_TIEMPO="tiempo";
    public static final String CAMPO_CRONO_VUELTA="vuelta";
    public static final String CAMPO_CRONO_POSICION="posicion";
    public static final String CAMPO_CRONO_CEDULA="cedula";


    public static final String CREAR_TABLA_CRONO="CREATE TABLE " +
            ""+TABLA_CRONO+" ("+CAMPO_CRONO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_CRONO_NOMBRE+" TEXT, "+
            CAMPO_CRONO_METROS+" TEXT, "+
            CAMPO_CRONO_PRUEBA+" TEXT, "+
            CAMPO_CRONO_CONFIG +" TEXT, "+
//            CAMPO_CRONO_RENDIMIENTO+" TEXT, "+
            CAMPO_CRONO_TIEMPO+" TEXT, "+
            CAMPO_CRONO_VUELTA+" TEXT, "+
            CAMPO_CRONO_POSICION+" INTEGER, "+
            CAMPO_CRONO_CEDULA+" BIGINT "+
            ")";
    //Constantes campos tabla pruebas
    public static final String TABLA_PRUEBAS="pruebas";
    public static final String CAMPO_PRUEBAS_ID="id";
    public static final String CAMPO_PRUEBAS_NOMBRE="nombre";

    public static final String CREAR_TABLA_PRUEBAS="CREATE TABLE " +
            ""+TABLA_PRUEBAS+" ("+CAMPO_PRUEBAS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_PRUEBAS_NOMBRE+" TEXT "+
            ")";

    //Constantes campos tabla metros
    public static final String TABLA_METROS="metros";
    public static final String CAMPO_METROS_ID="id";
    public static final String CAMPO_METROS_NOMBRE="metros";

    public static final String CREAR_TABLA_METROS="CREATE TABLE " +
            ""+TABLA_METROS+" ("+CAMPO_METROS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_METROS_NOMBRE+" TEXT "+
            ")";


    //Constantes campos tabla tiempos
    public static final String TABLA_TIEMPOS="tiempos";
    public static final String CAMPO_TIEMPOS_ID="id";
    public static final String CAMPO_TIEMPOS_CEDULA="cedula";
    public static final String CAMPO_TIEMPOS_FECHA="fecha";
    public static final String CAMPO_TIEMPOS_PRUEBA="prueba";
    public static final String CAMPO_TIEMPOS_METROS="metros";
    public static final String CAMPO_TIEMPOS_TIEMPO="tiempo";

    public static final String CREAR_TABLA_TIEMPOS="CREATE TABLE " +
            ""+TABLA_TIEMPOS+" ("+CAMPO_TIEMPOS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_TIEMPOS_CEDULA+" BIGINT, "+
            CAMPO_TIEMPOS_FECHA+" DATE, "+
            CAMPO_TIEMPOS_PRUEBA+" TEXT, "+
            CAMPO_TIEMPOS_METROS+" TEXT, "+
            CAMPO_TIEMPOS_TIEMPO+" TEXT "+
            ")";

    //Constantes campos tabla CUENTA
    public static final String TABLA_CUENTA="cuenta";
    public static final String CAMPO_CUENTA_ID="id";
    public static final String CAMPO_CUENTA_USERNAME="username";
    public static final String CAMPO_CUENTA_PASSWORD="password";
    public static final String CAMPO_CUENTA_IDUSUARIO="idUsuario";
    public static final String CAMPO_CUENTA_ROL="rol";

    public static final String CREAR_TABLA_CUENTA="CREATE TABLE " +
            ""+TABLA_CUENTA+" ("+CAMPO_CUENTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_CUENTA_USERNAME+" TEXT, "+
            CAMPO_CUENTA_PASSWORD+" TEXT, "+
            CAMPO_CUENTA_IDUSUARIO+" INTEGER, "+
            CAMPO_CUENTA_ROL+" INTEGER "+
            ")";

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_USUARIO_ID="id";
    public static final String CAMPO_USUARIO_CEDULA="cedula";
    public static final String CAMPO_USUARIO_PRIMER_NOMBRE="primer_nombre";
    public static final String CAMPO_USUARIO_SEGUNDO_NOMBRE="segundo_nombre";
    public static final String CAMPO_USUARIO_PRIMER_APELIDO="primer_apellido";
    public static final String CAMPO_USUARIO_SEGUNDO_APELLIDO="segundo_apellido";
    public static final String CAMPO_USUARIO_FECHA_NACIMIENTO="fecha_nacimiento";
    public static final String CAMPO_USUARIO_GENERO="genero";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_USUARIO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_USUARIO_CEDULA+" BIGINT, "+
            CAMPO_USUARIO_PRIMER_NOMBRE+" TEXT, "+
            CAMPO_USUARIO_SEGUNDO_NOMBRE+" TEXT, "+
            CAMPO_USUARIO_PRIMER_APELIDO+" TEXT, "+
            CAMPO_USUARIO_SEGUNDO_APELLIDO+" TEXT, "+
            CAMPO_USUARIO_FECHA_NACIMIENTO+" DATE, "+
            CAMPO_USUARIO_GENERO+" TEXT "+
            ")";

    //Constantes campos tabla ROLES
    public static final String TABLA_ROLES="roles";
    public static final String CAMPO_ROLES_ID="id";
    public static final String CAMPO_ROLES_NAME="name";
    public static final String CREAR_TABLA_ROLES="CREATE TABLE " +
            ""+TABLA_ROLES+" ("+CAMPO_ROLES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_ROLES_NAME+" TEXT "+
            ")";
    //Constantes campos tabla COMPETENCIA
    public static final String TABLA_COMPETENCIA="competencia";
    public static final String CAMPO_COMPETENCIA_ID="id";
    public static final String CAMPO_COMPETENCIA_NOMBRE="nombre";
    public static final String CAMPO_COMPETENCIA_FECHA="fecha";

    public static final String CREAR_TABLA_COMPETENCIA="CREATE TABLE " +
            ""+TABLA_COMPETENCIA+" ("+CAMPO_COMPETENCIA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_COMPETENCIA_NOMBRE+" TEXT, "+
            CAMPO_COMPETENCIA_FECHA+" DATE "+
            ")";


    //Constantes campos tabla SERIE
    public static final String TABLA_SERIE="serie";
    public static final String CAMPO_SERIE_ID="id";
    public static final String CAMPO_SERIE_ID_EVENTO="id_evento";
    public static final String CAMPO_SERIE_NUMERO="numero";
    public static final String CAMPO_SERIE_CARRIL="carril";
    public static final String CAMPO_SERIE_CEDULA="cedula";
    public static final String CAMPO_SERIE_NADADOR="nadador";
    public static final String CAMPO_SERIE_ID_INSTITUCION="id_institucion";
    public static final String CAMPO_SERIE_TIEMPO="tiempo";
    public static final String CAMPO_SERIE_DESCALIFICADO="descalificado";
    public static final String CAMPO_SERIE_PUNTOS="puntos";
    public static final String CAMPO_SERIE_LUGAR="lugar";
    public static final String CAMPO_SERIE_PREMIADO="premiado";

    public static final String CREAR_TABLA_SERIE="CREATE TABLE " +
            ""+TABLA_SERIE+" ("+CAMPO_SERIE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_SERIE_ID_EVENTO+" INTEGER, "+
            CAMPO_SERIE_NUMERO+" INTEGER, "+
            CAMPO_SERIE_CARRIL+" INTEGER, "+
            CAMPO_SERIE_CEDULA+" BIGINT, "+
            CAMPO_SERIE_NADADOR+" TEXT, "+
            CAMPO_SERIE_ID_INSTITUCION+" INTEGER, "+
            CAMPO_SERIE_TIEMPO+" TEXT, "+
            CAMPO_SERIE_DESCALIFICADO+" INTEGER, "+
            CAMPO_SERIE_PUNTOS+" INTEGER, "+
            CAMPO_SERIE_LUGAR+" INTEGER, "+
            CAMPO_SERIE_PREMIADO+" INTEGER "+
            ")";

    //Constantes campos tabla EVENTO
    public static final String TABLA_EVENTO="evento";
    public static final String CAMPO_EVENTO_ID="id";
    public static final String CAMPO_EVENTO_ID_COMPETENCIA="id_competencia";
    public static final String CAMPO_EVENTO_NUMERO="numero";
    public static final String CAMPO_EVENTO_NAME="name";
    public static final String CAMPO_EVENTO_PRUEBA="prueba";
    public static final String CAMPO_EVENTO_METROS="metros";
    public static final String CAMPO_EVENTO_CATEGORIA="categoria";
    public static final String CAMPO_EVENTO_GENERO="genero";

    public static final String CREAR_TABLA_EVENTO="CREATE TABLE " +
            ""+TABLA_EVENTO+" ("+CAMPO_EVENTO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_EVENTO_ID_COMPETENCIA+" INTEGER, "+
            CAMPO_EVENTO_NUMERO+" INTEGER, "+
            CAMPO_EVENTO_NAME+" TEXT, "+
            CAMPO_EVENTO_PRUEBA+" TEXT, "+
            CAMPO_EVENTO_METROS+" TEXT, "+
            CAMPO_EVENTO_CATEGORIA+" TEXT, "+
            CAMPO_EVENTO_GENERO+" TEXT "+
            ")";

    //Constantes campos tabla institucion_nadador
    public static final String TABLA_INSTITUCION_NADADOR="institucion_nadador";
    public static final String CAMPO_INSTITUCION_NADADOR_ID="id";
    public static final String CAMPO_INSTITUCION_NADADOR_ID_NADADOR="id_nadador";
    public static final String CAMPO_INSTITUCION_NADADOR_ID_INSTITUCION="id_institucion";
    public static final String CAMPO_INSTITUCION_NADADOR_ID_COMPETENCIA="id_competencia";
    public static final String CAMPO_INSTITUCION_NADADOR_CATEGORIA="categoria";
    public static final String CAMPO_INSTITUCION_NADADOR_CONFIGCHECK="configCheck";

    public static final String CREAR_TABLA_INSTITUCION_NADADOR="CREATE TABLE " +
            ""+TABLA_INSTITUCION_NADADOR+" ("+CAMPO_INSTITUCION_NADADOR_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_INSTITUCION_NADADOR_ID_NADADOR+" BIGINT, "+
            CAMPO_INSTITUCION_NADADOR_ID_INSTITUCION+" INTEGER, "+
            CAMPO_INSTITUCION_NADADOR_ID_COMPETENCIA+" INTEGER, "+
            CAMPO_INSTITUCION_NADADOR_CATEGORIA+" TEXT, "+
            CAMPO_INSTITUCION_NADADOR_CONFIGCHECK+" TEXT "+
            ")";


    //Constantes campos tabla institucion
    public static final String TABLA_INSTITUCION="institucion";
    public static final String CAMPO_INSTITUCION_ID="id";
    public static final String CAMPO_INSTITUCION_NOMBRE="nombre";

    public static final String CREAR_TABLA_INSTITUCION="CREATE TABLE " +
            ""+TABLA_INSTITUCION+" ("+CAMPO_INSTITUCION_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_INSTITUCION_NOMBRE+" TEXT"+
            ")";




    //Constantes campos tabla LOGS
    public static final String TABLA_LOGS="logs";
    public static final String CAMPO_LOGS_ID="id";
    public static final String CAMPO_LOGS_METHOD="httpMethod";
    public static final String CAMPO_LOGS_ACTION="action";
    public static final String CAMPO_LOGS_ENDPOINT="endPoint";
    public static final String CAMPO_LOGS_DESCRIPTION="description";
    public static final String CAMPO_LOGS_DATE="date";

    public static final String CREAR_TABLA_LOGS="CREATE TABLE " +
            ""+TABLA_LOGS+" ("+CAMPO_LOGS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_LOGS_METHOD+" TEXT, "+
            CAMPO_LOGS_ACTION+" TEXT, "+
            CAMPO_LOGS_ENDPOINT+" TEXT, "+
            CAMPO_LOGS_DESCRIPTION+" TEXT, "+
            CAMPO_LOGS_DATE+" DATE "+
            ")";

}
