/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import jdbc.ConexionBD;

/**
 *
 * @author Alexander
 */
public class ControladorSuscripcion {
    
    /**/
    public void generarSuscripcion(Suscripcion objSuscripcion) throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Estableciendo la sentencia de inserción SQL
        String sql = "INSERT INTO Suscripcion (clienteId, cuentaId, fechaInicio, estaEnVigencia, pagoEfectuado) "
                + "VALUES (?, ?, CURDATE(), 1, ?)";
        PreparedStatement pstm_insert = conn.prepareStatement(sql);
        
        //Asignando parámetros de la sentencia
        pstm_insert.setInt(1, objSuscripcion.getClienteID());
        pstm_insert.setInt(2, objSuscripcion.getCuentaID());
        pstm_insert.setDouble(3, objSuscripcion.getPagoEfectuado());
        
        //Manipulando, finalmente, la base de datos
        pstm_insert.executeUpdate();
        
        conn.close();
    }
    
    /**/
    public void actualizarDatosSuscripcion(Suscripcion objSuscripcion){
        /*Necesariamente un delete y un insert*/
        
    }
    
    /**/
    public void eliminarSuscripcion(int suscripcionId){
        /*Comprobar que no hay ninguna cuenta activa asociada a una pataforma de streaming*/
    }
            
    /*Obtener todas las suscripciones almacenadas en la BD*/
    public ArrayList<Suscripcion> obtenerSuscripciones() throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta. Utilizamos createStatement()
        ya que, para la ejecución de la sentencia SQL, no se necesita parámetros.*/
        Statement stm = conn.createStatement();
        String sql = "SELECT S.*, TS.nombre, C.correo, C.contrasena, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S " +
                    "INNER JOIN Cliente CL ON S.clienteId = CL.clienteId " +
                    "INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON DS.tipoServicioId = TS.tipoServicioId;";
        
        //Recuperando clientes
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Suscripcion> suscripciones = new ArrayList<>();
        Suscripcion objSuscripcion;
        
        while(rs.next()){
            objSuscripcion = new Suscripcion();
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
            objSuscripcion.setPlataformaStreamingAsociada(rs.getString(7));
            objSuscripcion.setCorreoCuentaAsociada(rs.getString(8));
            objSuscripcion.setContrasenaCuentaAsociada(rs.getString(9));
            objSuscripcion.setFechaFin(formatearFecha(rs.getString(10)));
            objSuscripcion.setNombreCliente(rs.getString(11));
                    
            suscripciones.add(objSuscripcion);
        }
        
        return suscripciones;
    }
    
    /*Obtener las suscripciones según el cliente a quien pertenece y en cuanto a su estado(activas/ inactivas)*/
    public ArrayList<Suscripcion> obtenerSuscripciones(int clienteId, boolean estanActivas) throws SQLException{
        
        int estadoSuscripcion;
        
        if(estanActivas){
            estadoSuscripcion = 1;
        } else{
            estadoSuscripcion = 0;
        }
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT S.*, TS.nombre, C.correo, C.contrasena, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S " +
                    "INNER JOIN Cliente CL ON S.clienteId = CL.clienteId " +
                    "INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE CL.clienteId = ? AND S.estaEnVigencia = ?;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, clienteId);
        pstm.setInt(2, estadoSuscripcion);
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Suscripcion> suscripciones = new ArrayList<>();
        Suscripcion objSuscripcion;
        
        while(rs.next()){
            objSuscripcion = new Suscripcion();
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
            objSuscripcion.setPlataformaStreamingAsociada(rs.getString(7));
            objSuscripcion.setCorreoCuentaAsociada(rs.getString(8));
            objSuscripcion.setContrasenaCuentaAsociada(rs.getString(9));
            objSuscripcion.setFechaFin(formatearFecha(rs.getString(10)));
            objSuscripcion.setNombreCliente(rs.getString(11));
                    
            suscripciones.add(objSuscripcion);
        }
        
        return suscripciones;
    }
    
    /*Obtener las suscripciones según el cliente a quien pertenece, en cuanto a su estado(activas/ inactivas) y a la plataforma a la que pertenecen*/
    public ArrayList<Suscripcion> obtenerSuscripciones(int clienteId, boolean estanActivas, int plataformaId) throws SQLException{
        int estadoSuscripcion;
        
        if(estanActivas){
            estadoSuscripcion = 1;
        } else{
            estadoSuscripcion = 0;
        }
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT S.*, TS.nombre, C.correo, C.contrasena , S.fechaInicio, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S " +
                    "INNER JOIN Cliente CL ON S.clienteId = CL.clienteId " +
                    "INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE CL.clienteId = ? AND S.estaEnVigencia = ? AND TS.tipoServicioId = ?;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, clienteId);
        pstm.setInt(2, estadoSuscripcion);
        pstm.setInt(3, plataformaId);
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Suscripcion> suscripciones = new ArrayList<>();
        Suscripcion objSuscripcion;
        
        while(rs.next()){
            objSuscripcion = new Suscripcion();
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
            objSuscripcion.setPlataformaStreamingAsociada(rs.getString(7));
            objSuscripcion.setCorreoCuentaAsociada(rs.getString(8));
            objSuscripcion.setContrasenaCuentaAsociada(rs.getString(9));
            objSuscripcion.setFechaFin(formatearFecha(rs.getString(10)));
            objSuscripcion.setNombreCliente(rs.getString(11));
                    
            suscripciones.add(objSuscripcion);
        }
        
        return suscripciones;
    }
    
    /*Obtener las suscripciones según el cliente a quien pertenece*/
    public ArrayList<Suscripcion> obtenerSuscripcionesXCliente(int clienteId) throws SQLException{
       
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT S.*, TS.nombre, C.correo, C.contrasena, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S " +
                    "INNER JOIN Cliente CL ON S.clienteId = CL.clienteId " +
                    "INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE CL.clienteId = ?;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, clienteId);
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Suscripcion> suscripciones = new ArrayList<>();
        Suscripcion objSuscripcion;
        
        while(rs.next()){
            objSuscripcion = new Suscripcion();
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
            objSuscripcion.setPlataformaStreamingAsociada(rs.getString(7));
            objSuscripcion.setCorreoCuentaAsociada(rs.getString(8));
            objSuscripcion.setContrasenaCuentaAsociada(rs.getString(9));
            objSuscripcion.setFechaFin(formatearFecha(rs.getString(10)));
            objSuscripcion.setNombreCliente(rs.getString(11));
                    
            suscripciones.add(objSuscripcion);
        }
        
        return suscripciones;
    }
    
    /*Obtener las suscripciones según su estado*/
    public ArrayList<Suscripcion> obtenerSuscripcionesXEstado(boolean estanActivas) throws SQLException{
        int estadoSuscripcion;
        
        if(estanActivas){
            estadoSuscripcion = 1;
        } else{
            estadoSuscripcion = 0;
        }
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT S.*, TS.nombre, C.correo, C.contrasena , S.fechaInicio, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S " +
                    "INNER JOIN Cliente CL ON S.clienteId = CL.clienteId " +
                    "INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE S.estaEnVigencia = ?;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, estadoSuscripcion);
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Suscripcion> suscripciones = new ArrayList<>();
        Suscripcion objSuscripcion;
        
        while(rs.next()){
            objSuscripcion = new Suscripcion();
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
            objSuscripcion.setPlataformaStreamingAsociada(rs.getString(7));
            objSuscripcion.setCorreoCuentaAsociada(rs.getString(8));
            objSuscripcion.setContrasenaCuentaAsociada(rs.getString(9));
            objSuscripcion.setFechaFin(formatearFecha(rs.getString(10)));
            objSuscripcion.setNombreCliente(rs.getString(11));
                    
            suscripciones.add(objSuscripcion);
        }
        
        return suscripciones;
    }
    
    /*Obtener suscripciones activas próximas a caducar en los proximos X dias*/
    public ArrayList<Suscripcion> obtenerSuscripcionesPorCaducar(int dias) throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT S.*, TS.nombre, C.correo, C.contrasena, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S " +
                    "INNER JOIN Cliente CL ON S.clienteId = CL.clienteId " +
                    "INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH) < DATE_ADD(CURRENT_DATE(), INTERVAL ? DAY)" +
                    "AND S.estaEnVigencia = 1;";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, dias);
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Suscripcion> suscripcionesPorCaducar = new ArrayList<>();
        Suscripcion objSuscripcion;
        
        while(rs.next()){
            objSuscripcion = new Suscripcion();
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
            objSuscripcion.setPlataformaStreamingAsociada(rs.getString(7));
            objSuscripcion.setCorreoCuentaAsociada(rs.getString(8));
            objSuscripcion.setContrasenaCuentaAsociada(rs.getString(9));
            objSuscripcion.setFechaFin(formatearFecha(rs.getString(10)));
            objSuscripcion.setNombreCliente(rs.getString(11));
                    
            suscripcionesPorCaducar.add(objSuscripcion);
        }
        
        return suscripcionesPorCaducar;
    }
    
    /*Obtener suscripciones ACTIVAS cuyo plazo de vencimiento terminó, por lo menos, ayer con respecto a la fecha de hoy*/
    public ArrayList<Suscripcion> obtenerSuscripcionesCaducadas() throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT S.*, TS.nombre, C.correo, C.contrasena, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S " +
                    "INNER JOIN Cliente CL ON S.clienteId = CL.clienteId " +
                    "INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE S.estaEnVigencia = 1 AND CURRENT_DATE() > DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH);";

        Statement stm = conn.createStatement();
        
        //Recuperando clientes
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Suscripcion> suscripcionesPorCaducar = new ArrayList<>();
        Suscripcion objSuscripcion;
        
        while(rs.next()){
            objSuscripcion = new Suscripcion();
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
            objSuscripcion.setPlataformaStreamingAsociada(rs.getString(7));
            objSuscripcion.setCorreoCuentaAsociada(rs.getString(8));
            objSuscripcion.setContrasenaCuentaAsociada(rs.getString(9));
            objSuscripcion.setFechaFin(formatearFecha(rs.getString(10)));
            objSuscripcion.setNombreCliente(rs.getString(11));
                    
            suscripcionesPorCaducar.add(objSuscripcion);
        }
        
        return suscripcionesPorCaducar;
    }
    
    /*Obtener el nuevo código que se genará cuando se desea realizar una nueva suscripción*/
    public int obtenerNuevoCodigo() throws SQLException{
        int codigoGenerado = 0;
        
        Connection conn = ConexionBD.conectarMySQL();
        
        Statement stm = conn.createStatement();
        String sql = "SELECT MAX(suscripcionId) FROM Suscripcion";
        ResultSet result = stm.executeQuery(sql);
        
        while(result.next()){
            codigoGenerado = Integer.parseInt(result.getString(1));
        }
        
        codigoGenerado++;
        
        return codigoGenerado;
    }
    
    /*Obtener una suscripción*/
    public Suscripcion obtenerSuscripcion(int suscripcionId) throws SQLException{
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta.*/
        String sql = "SELECT * FROM Cuenta "
                + "WHERE suscripcionId = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, suscripcionId);
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        Suscripcion objSuscripcion = new Suscripcion();
        
        while(rs.next()){
            objSuscripcion.setSucripcionID(Integer.parseInt(rs.getString(1)));
            objSuscripcion.setClienteID(Integer.parseInt(rs.getString(2)));
            objSuscripcion.setCuentaID(Integer.parseInt(rs.getString(3)));
            objSuscripcion.setFechaInicio(formatearFecha(rs.getString(4)));
            
            if(rs.getString(5).equals("0")){
                objSuscripcion.setEstaActiva(false);
            }else{
                 objSuscripcion.setEstaActiva(true);
            }
            
            objSuscripcion.setPagoEfectuado(Double.parseDouble(rs.getString(6)));
        }
        
        return objSuscripcion;
    }
    
    /**/
    public void actualizarEstadoSuscripcion(int suscripcionId) throws SQLException{
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Estableciendo la sentencia de inserción SQL
        String sql = "UPDATE Suscripcion SET estaEnVigencia = 0 "
                + "WHERE suscripcionId = ?";
        PreparedStatement pstm_insert = conn.prepareStatement(sql);
        
        //Asignando parámetros de la sentencia
        pstm_insert.setInt(1, suscripcionId);
        
        //Manipulando, finalmente, la base de datos
        pstm_insert.executeUpdate();
    }
    
    /*Convierte una fecha establecida en formato String a formato GregorianCalendar*/
    public GregorianCalendar formatearFecha(String fecha){
        //Recibe AAAA-MM-DD
        
        GregorianCalendar formato_simplificado;
        
        String partes_fecha[] = fecha.split("-");
        
        int anio = Integer.parseInt(partes_fecha[0]);
        int mes = Integer.parseInt(partes_fecha[1]);
        int dia = Integer.parseInt(partes_fecha[2]);
        
        formato_simplificado = new GregorianCalendar(anio, mes, dia);
        
        return formato_simplificado;
    }
}
