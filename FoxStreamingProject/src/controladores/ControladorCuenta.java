/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.*;
import excepcionesPropias.DatoNoValido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import jdbc.ConexionBD;

/**
 *
 * @author Alexander
 */
public class ControladorCuenta {
    
    /**/
    public void registrar(Cuenta objCuenta) throws SQLException, DatoNoValido{
        //Validar no repitencia de correo electrónico
        ArrayList<Cuenta> cuentas = obtenerCuentas();
        
        for (int i = 0; i < cuentas.size(); i++) {
            if(cuentas.get(i).getCorreo().equals(objCuenta.getCorreo())){
                throw new DatoNoValido("Correo electrónico ya registrado al sistema.");
            }
        }
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Estableciendo la sentencia de inserción SQL
        String sql = "INSERT INTO Cuenta (planId, correo, contrasena, estaActiva, numActualUsuarios) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstm_insert = conn.prepareStatement(sql);
        
        //Asignando parámetros de la sentencia
        pstm_insert.setInt(1, objCuenta.getPlanId());
        pstm_insert.setString(2, objCuenta.getCorreo());
        pstm_insert.setString(3, objCuenta.getContrasena());
        pstm_insert.setInt(4, 1);
        pstm_insert.setInt(5, 0);
        
        //Manipulando, finalmente, la base de datos
        pstm_insert.executeUpdate();
        
        conn.close();
    }
    
    public void actualizar(Cuenta objCuenta) throws SQLException, DatoNoValido{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Validar no repitencia de correo electrónico a excepción del mismo objeto
        ArrayList<Cuenta> cuentas = obtenerCuentas();
        
        for (int i = 0; i < cuentas.size(); i++) {
            if(cuentas.get(i).getCuentaId()== objCuenta.getCuentaId()){
                cuentas.remove(cuentas.get(i));
                break;
            }
        }
        
        for (int i = 0; i < cuentas.size(); i++) {
            if(cuentas.get(i).getCorreo().equals(objCuenta.getCorreo())){
                throw new DatoNoValido("Correo electrónico ya registrado al sistema.");
            }
        }
        
        //Estableciendo la sentencia SQL para la actualización y asignando parámetros
        String sql_update = "UPDATE Cuenta SET planId = ?, correo = ?, contrasena = ?, estaActiva = ? "
                + "WHERE cuentaId = ?";
        PreparedStatement pstm_update = conn.prepareStatement(sql_update);
        
        pstm_update.setInt(1, objCuenta.getPlanId());
        pstm_update.setString(2, objCuenta.getCorreo());
        pstm_update.setString(3, objCuenta.getContrasena());
        
        if(objCuenta.getEstaActiva()){
            pstm_update.setInt(4, 1);
        }else{
            pstm_update.setInt(4, 0);
        }
        
        pstm_update.setInt(5, objCuenta.getCuentaId());
        
        //Manipulación en la BD
        pstm_update.executeUpdate();
        
        conn.close();
    }
    
    public void eliminar(Cuenta objCuenta) throws Exception{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Comprobar que la cuenta no tenga NINGUNA suscripción vigente*/
        int numSuscXCuenta = obtenerNumSuscripcionesXCuenta(objCuenta.getCuentaId());
        if(numSuscXCuenta > 0){
            throw new Exception("No se pudo proceder con la eliminación. "
                    + "Esto debido a que esta cuenta aún dispone de " + numSuscXCuenta + " cuentas ACTIVAS.");
        }
        
        //Estableciendo la sentencia SQL para consulta y asignando el parámetro necesario
        String sql_delete = "DELETE FROM Cuenta WHERE cuentaId = ?";
        PreparedStatement pstm = conn.prepareStatement(sql_delete);
        pstm.setInt(1, objCuenta.getCuentaId());
        
        //Manipulando datos en la base de datos
        pstm.executeUpdate();
        
        conn.close();
    }
    /*Obtiene el número de suscripciones ACTIVAS asociadas a una cuenta*/
    public int obtenerNumSuscripcionesXCuenta(int cuentaId) throws SQLException{
        
        int numSuscripciones = -1;
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql_contar = "SELECT COUNT(C.cuentaId) FROM Cuenta C " +
                            "INNER JOIN Suscripcion S ON C.cuentaId = S.cuentaId " +
                            "WHERE S.estaEnVigencia = 1 AND C.cuentaId = ?";

        PreparedStatement pstm = conn.prepareStatement(sql_contar);
        pstm.setInt(1, cuentaId);
        
        //Recuperando
        ResultSet result = pstm.executeQuery();
        
        while (result.next()) {            
            numSuscripciones = Integer.parseInt(result.getString(1));
        }
        
        return numSuscripciones;
    }
    
    /*Obtener todas las cuentas registradas en la BD*/
    public ArrayList<Cuenta> obtenerCuentas() throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta. Utilizamos createStatement()
        ya que, para la ejecución de la sentencia SQL, no se necesita parámetros.*/
        Statement stm = conn.createStatement();
        String sql = "SELECT * FROM Cuenta";
        
        //Recuperando clientes
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Cuenta objCuenta;
        
        while(rs.next()){
            objCuenta = new Cuenta();
            objCuenta.setCuentaId(Integer.parseInt(rs.getString(1)));
            objCuenta.setPlanId(Integer.parseInt(rs.getString(2)));
            objCuenta.setCorreo(rs.getString(3));
            objCuenta.setContrasena(rs.getString(4));
            
            if(rs.getString(5).equals("0")){
                objCuenta.setEstaActiva(false);
            }else{
                objCuenta.setEstaActiva(true);
            }
            
            objCuenta.setNumActualUsuarios(Integer.parseInt(rs.getString(6)));
                    
            cuentas.add(objCuenta);
        }
        
        return cuentas;
    }
    
    /*Obtener todas las cuentas registradas en la BD según su estado(activas o inactivas) asociadas a un cierto plan*/
    public ArrayList<Cuenta> obtenerCuentas(int planId, boolean estanActivas) throws SQLException{
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT * FROM Cuenta " +
                    "WHERE planId = ? AND estaActiva = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, planId);
        
        if(estanActivas){
            pstm.setInt(2, 1);
        } else{
            pstm.setInt(2, 0);
        }
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Cuenta objCuenta;
        
        while(rs.next()){
            objCuenta = new Cuenta();
            objCuenta.setCuentaId(Integer.parseInt(rs.getString(1)));
            objCuenta.setPlanId(Integer.parseInt(rs.getString(2)));
            objCuenta.setCorreo(rs.getString(3));
            objCuenta.setContrasena(rs.getString(4));
            
            if(rs.getString(5).equals("0")){
                objCuenta.setEstaActiva(false);
            }else{
                objCuenta.setEstaActiva(true);
            }
            
            objCuenta.setNumActualUsuarios(Integer.parseInt(rs.getString(6)));
                    
            cuentas.add(objCuenta);
        }
        
        return cuentas;
    }
    
    /*Obtener cuentas cuyo número actual de usuarios sea menor al la cantidad máxima
    asociada al tipo de servicioStreaming y según el estado de la cuenta. Estan cuentas pertenecerán
    a un plan específico, por tanto, también a la única plataforma de streaming.*/
    public ArrayList<Cuenta> obtenerCuentasMenorMaxUsuarios(int planId, boolean estanActivas) throws SQLException{
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT C.* FROM Cuenta C " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE C.numActualUsuarios < TS.maxUsuarios AND C.planId = ? AND C.estaActiva = ?;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, planId);
        
        if(estanActivas){
            pstm.setInt(2, 1);
        } else{
            pstm.setInt(2, 0);
        }
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Cuenta objCuenta;
        
        while(rs.next()){
            objCuenta = new Cuenta();
            objCuenta.setCuentaId(Integer.parseInt(rs.getString(1)));
            objCuenta.setPlanId(Integer.parseInt(rs.getString(2)));
            objCuenta.setCorreo(rs.getString(3));
            objCuenta.setContrasena(rs.getString(4));
            
            if(rs.getString(5).equals("0")){
                objCuenta.setEstaActiva(false);
            }else{
                objCuenta.setEstaActiva(true);
            }
            
            objCuenta.setNumActualUsuarios(Integer.parseInt(rs.getString(6)));
                    
            cuentas.add(objCuenta);
        }
        
        return cuentas;
    }
    
    /*Obtener cuentas segun su estado (activas o inactivas)*/
    public ArrayList<Cuenta> obtenerCuentasXEstado(boolean estanActivas) throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT C.* FROM Cuenta C " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE C.estaActiva = ?;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        if(estanActivas){
            pstm.setInt(1, 1);
        } else{
            pstm.setInt(1, 0);
        }
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Cuenta objCuenta;
        
        while(rs.next()){
            objCuenta = new Cuenta();
            objCuenta.setCuentaId(Integer.parseInt(rs.getString(1)));
            objCuenta.setPlanId(Integer.parseInt(rs.getString(2)));
            objCuenta.setCorreo(rs.getString(3));
            objCuenta.setContrasena(rs.getString(4));
            
            if(rs.getString(5).equals("0")){
                objCuenta.setEstaActiva(false);
            }else{
                objCuenta.setEstaActiva(true);
            }
            
            objCuenta.setNumActualUsuarios(Integer.parseInt(rs.getString(6)));
                    
            cuentas.add(objCuenta);
        }
        
        return cuentas;
    }
    
    /*Obtiene las cuentas según el texto contenido en el campo 'Correo' de la tabla Cuenta*/
    public  ArrayList<Cuenta> obtenerCuentas(String cadena_contenido) throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT C.* FROM Cuenta C " +
                    "INNER JOIN Plan P ON C.planId = P.planId " +
                    "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId " +
                    "WHERE C.correo LIKE ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, "%" + cadena_contenido + "%");
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Cuenta objCuenta;
        
        while(rs.next()){
            objCuenta = new Cuenta();
            objCuenta.setCuentaId(Integer.parseInt(rs.getString(1)));
            objCuenta.setPlanId(Integer.parseInt(rs.getString(2)));
            objCuenta.setCorreo(rs.getString(3));
            objCuenta.setContrasena(rs.getString(4));
            
            if(rs.getString(5).equals("0")){
                objCuenta.setEstaActiva(false);
            }else{
                objCuenta.setEstaActiva(true);
            }
            
            objCuenta.setNumActualUsuarios(Integer.parseInt(rs.getString(6)));
                    
            cuentas.add(objCuenta);
        }
        
        return cuentas;
    }
    
    /*Activar/Desactivar cuenta según su código.*/
    public void modificarEstadoCuenta(int cuentaId) throws SQLException, DatoNoValido{
        
        Cuenta objCuenta = obtenerCuenta(cuentaId);
        
        if(objCuenta.getEstaActiva() == true){
            objCuenta.setEstaActiva(false);
        } else{
            objCuenta.setEstaActiva(true);
        }
        
        actualizar(objCuenta);
    }
    
    /*Obtiene el nuevo código que se generará cuando se desea registrar una nueva cuenta*/
    public int obtenerNuevoCodigo() throws SQLException{
        int codigoGenerado = 0;
        
        Connection conn = ConexionBD.conectarMySQL();
        
        Statement stm = conn.createStatement();
        String sql = "SELECT MAX(cuentaId) FROM Cuenta";
        ResultSet result = stm.executeQuery(sql);
        
        while(result.next()){
            codigoGenerado = Integer.parseInt(result.getString(1));
        }
        
        codigoGenerado++;
        
        return codigoGenerado;
    }
    
    /*Obtener cuenta según su código*/
    public Cuenta obtenerCuenta(int cuentaId) throws SQLException{
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta.*/
        String sql = "SELECT * FROM Cuenta "
                + "WHERE cuentaId = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, cuentaId);
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        Cuenta objCuenta = new Cuenta();
        
        while(rs.next()){
            objCuenta.setCuentaId(Integer.parseInt(rs.getString(1)));
            objCuenta.setPlanId(Integer.parseInt(rs.getString(2)));
            objCuenta.setCorreo(rs.getString(3));
            objCuenta.setContrasena(rs.getString(4));
            
            if(rs.getString(5).equals("0")){
                objCuenta.setEstaActiva(false);
            }else{
                objCuenta.setEstaActiva(true);
            }
            
            objCuenta.setNumActualUsuarios(Integer.parseInt(rs.getString(6)));
        }
        
        return objCuenta;
    }
    
    /*Ordenar según su número actual de usuarios asociadados de una cuenta de forma ASCENDENTE*/
    public ArrayList<Cuenta> ordenarCuentasXCantActualUsuariosAsc(ArrayList<Cuenta> cuentas){
        Cuenta auxCuenta; //Para reservar el objeto actual
        int posMin; // Para reservar la posicion del objeto actual

        for(int i = 0; i < cuentas.size(); i++)
        {
            posMin = i;

            for(int j = i + 1; j < cuentas.size(); j++) //Para que recorra los demas objetos del arraylist
            {
                if(cuentas.get(j).getNumActualUsuarios()< cuentas.get(posMin).getNumActualUsuarios())
                {
                    posMin = j;
                }
            }

            auxCuenta = cuentas.get(i);
            cuentas.set(i, cuentas.get(posMin));
            cuentas.set(posMin, auxCuenta);
        }
        
        return cuentas;
    }
    
    /*Ordenar según su número actual de usuarios asociadados de una cuenta de forma DESCENDENTE*/
    public ArrayList<Cuenta> ordenarCuentasXCantActualUsuariosDesc(ArrayList<Cuenta> cuentas){
        cuentas = ordenarCuentasXCantActualUsuariosAsc(cuentas);
        Collections.reverse(cuentas);
        return cuentas;
    }
    
    /*Validar algunos de los campos de cuenta*/
    public void validarCuenta(Cuenta objCuenta){
        
    }
    
}
