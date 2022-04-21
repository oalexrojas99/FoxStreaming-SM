/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.*;
import excepcionesPropias.DatoNoValido;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import jdbc.ConexionBD;

/**
 *
 * @author Alexander
 */
public class ControladorCliente {
    
    public void registrar(Cliente objCliente) throws DatoNoValido, SQLException{
        
        validarDatosCliente(objCliente);
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
       
        //Validar no repitencia de nombre de cliente ni de celular
        ArrayList<Cliente> totalClientes = obtenerClientes();
        
        for (int i = 0; i < totalClientes.size(); i++) {
            if(totalClientes.get(i).getNombreCompleto().equals(objCliente.getNombreCompleto())){
                throw new DatoNoValido("Nombre de cliente ya registrado al sistema.");
            }
            
            if(totalClientes.get(i).getCelular().equals(objCliente.getCelular())){
                throw new DatoNoValido("Número telefónico ya registrado al sistema.");
            }
        }
        
        //Estableciendo la sentencia de inserción SQL
        String sql = "INSERT INTO Cliente (nombreCompleto, nombreUsuario, celular) VALUES (?, ?, ?)";
        PreparedStatement pstm_insert = conn.prepareStatement(sql);
        
        //Asignando parámetros de la sentencia
        pstm_insert.setString(1, objCliente.getNombreCompleto());
        pstm_insert.setString(2, objCliente.getNombreUsuario());
        pstm_insert.setString(3, objCliente.getCelular());
        
        //Manipulando, finalmente, la base de datos
        pstm_insert.executeUpdate();
        
        //Cerramos la conexión con la base de datos
        conn.close();
    }
    
    public void actualizar(Cliente objCliente) throws DatoNoValido, SQLException{
        
        validarDatosCliente(objCliente);
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Validar no repitencia de nombre de cliente ni de celular a excepción del mismo objeto
        ArrayList<Cliente> totalClientes = obtenerClientes();
        
        for (int i = 0; i < totalClientes.size(); i++) {
            if(totalClientes.get(i).getClienteId() == objCliente.getClienteId()){
                totalClientes.remove(totalClientes.get(i));
                break;
            }
        }
        
        for (int i = 0; i < totalClientes.size(); i++) {
            if(totalClientes.get(i).getNombreCompleto().equals(objCliente.getNombreCompleto())){
                throw new DatoNoValido("Nombre de cliente ya registrado al sistema.");
            }
            
            if(totalClientes.get(i).getCelular().equals(objCliente.getCelular())){
                throw new DatoNoValido("Número telefónico ya registrado al sistema.");
            }
        }
        
        //Estableciendo la sentencia SQL para la actualización y asignando parámetros
        String sql_update = "UPDATE Cliente SET nombreCompleto = ?, nombreUsuario = ?,"
                + "celular = ? WHERE clienteId = ?";
        PreparedStatement pstm_update = conn.prepareStatement(sql_update);
        
        pstm_update.setString(1, objCliente.getNombreCompleto());
        pstm_update.setString(2, objCliente.getNombreUsuario());
        pstm_update.setString(3, objCliente.getCelular());
        pstm_update.setInt(4, objCliente.getClienteId());
        
        //Manipulación en la BD
        pstm_update.executeUpdate();
        
        conn.close();
    }
    
    public void eliminar(Cliente objCliente) throws SQLException, Exception{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Comprobar que el cliente no tenga ninguna suscripción activa
        if(obtenerNumSuscripcionesPorCliente(objCliente.getClienteId(), true) > 0){
            throw new Exception("No se pudo proceder con la eliminación. "
                    + "Esto debido a que el cliente aún mantiene suscripciones activas.");
        }
        
        //Estableciendo la sentencia SQL para consulta y asignando el parámetro necesario
        String sql_delete = "DELETE FROM Cliente WHERE clienteId = ?";
        PreparedStatement pstm = conn.prepareStatement(sql_delete);
        pstm.setInt(1, objCliente.getClienteId());
        
        //Manipulando datos en la base de datos
        pstm.executeUpdate();
        
        conn.close();
    }
    
    public Cliente obtenerCliente(int clienteId) throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Estableciendo la sentencia SQL para consulta y asignando el parámetro necesario
        String sql = "SELECT * FROM Cliente WHERE clienteId = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, clienteId);
        
        //Recuperando cliente
        Cliente objCliente = new Cliente();
        
        //Ejecución de la consulta a la BD
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
            //Obteniendo resultados
            objCliente.setClienteId(Integer.parseInt(rs.getString(1)));
            objCliente.setNombreCompleto(rs.getString(2));
            objCliente.setNombreUsuario(rs.getString(3));
            objCliente.setCelular(rs.getString(4));
            objCliente.setNumTotalSuscripciones(obtenerNumSuscripcionesPorCliente(clienteId));
        }
        
        conn.close();
        
        return objCliente;
    }
    
    public ArrayList<Cliente> obtenerClientes() throws SQLException{
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta. Utilizamos createStatement()
        ya que, para la ejecución de la sentencia SQL, no se necesita parámetros.*/
        Statement stm = conn.createStatement();
        String sql = "SELECT * FROM Cliente";
        
        //Recuperando clientes
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente objCliente;
        
        while(rs.next()){
            objCliente = new Cliente();
            objCliente.setClienteId(Integer.parseInt(rs.getString(1)));
            objCliente.setNombreCompleto(rs.getString(2));
            objCliente.setNombreUsuario(rs.getString(3));
            objCliente.setCelular(rs.getString(4));
            objCliente.setNumTotalSuscripciones(obtenerNumSuscripcionesPorCliente(objCliente.getClienteId()));
            clientes.add(objCliente);
        }
        
        return clientes;
    }
    
    public  ArrayList<Cliente> obtenerClientes(String cadena_contenido) throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT * FROM Cliente where nombreCompleto LIKE ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, "%" + cadena_contenido + "%");
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente objCliente;
        
        while(rs.next()){
            objCliente = new Cliente();
            objCliente.setClienteId(Integer.parseInt(rs.getString(1)));
            objCliente.setNombreCompleto(rs.getString(2));
            objCliente.setNombreUsuario(rs.getString(3));
            objCliente.setCelular(rs.getString(4));
            clientes.add(objCliente);
        }
        
        return clientes;
    }
    
    /*Para obtener el nuevo código que se generará al momento de 
    registrar un nuevo cliente*/
    public int obtenerNuevoCodigo() throws SQLException{
        int codigoGenerado = 0;
        
        Connection conn = ConexionBD.conectarMySQL();
        
        Statement stm = conn.createStatement();
        String sql = "SELECT MAX(clienteId) FROM Cliente";
        ResultSet result = stm.executeQuery(sql);
        
        while(result.next()){
            codigoGenerado = Integer.parseInt(result.getString(1));
        }
        
        codigoGenerado++;
        
        return codigoGenerado;
    }
    
    /*Obtiene el número total de suscripciones que cada cliente ha solicitado*/
    public int obtenerNumSuscripcionesPorCliente(int clienteId) throws SQLException{
        
        int numSuscripciones = -1;
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql_contar = "SELECT COUNT(*) FROM Cliente C, Suscripcion S WHERE C.clienteId = S.clienteId "
                + "AND S.clienteId = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql_contar);
        pstm.setInt(1, clienteId);
        
        //Recuperando
        ResultSet result = pstm.executeQuery();
        
        while (result.next()) {            
            numSuscripciones = Integer.parseInt(result.getString(1));
        }
        
        return numSuscripciones;
    }
    
    /*Obtiene el número suscripciones ACTIVAS que cada cliente ha solicitado*/
    public int obtenerNumSuscripcionesPorCliente(int clienteId, boolean cuentasActivas) throws SQLException{
        
        int numSuscripciones = -1;
        int estadoCuentas = -1;
        
        if(cuentasActivas){
            estadoCuentas = 1;
        } else{
            estadoCuentas = 0;
        }
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta para la obtención de resultados
        en función al código de cliente y si las suscripciones están activas o vigentes*/
        String sql_contar = "SELECT COUNT(*) FROM Cliente C, Suscripcion S WHERE C.clienteId = S.clienteId "
                + "AND S.estaEnVigencia = ? AND S.clienteId = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql_contar);
        pstm.setInt(1, estadoCuentas);
        pstm.setInt(2, clienteId);
        
        //Recuperando
        ResultSet result = pstm.executeQuery();
        
        while (result.next()) {            
            numSuscripciones = Integer.parseInt(result.getString(1));
        }
        
        return numSuscripciones;
    }
    
    /*Ordena los clientes según su nombre de forma ascedente*/
    public ArrayList<Cliente> ordenarClientesXNombresAsc(ArrayList<Cliente> clientes){
        Cliente auxCliente; //Para reservar el objeto actual
        int posMin; // Para reservar la posicion del objeto actual

        for(int i = 0; i < clientes.size(); i++)
        {
            posMin = i;

            for(int j = i + 1; j < clientes.size(); j++) //Para que recorra los demas objetos del arraylist
            {
                if(clientes.get(j).getNombreCompleto().compareToIgnoreCase(clientes.get(posMin).getNombreCompleto()) < 0)
                {
                    posMin = j;
                }
            }

            auxCliente = clientes.get(i);
            clientes.set(i, clientes.get(posMin));
            clientes.set(posMin, auxCliente);
        }
        
        return clientes;
    }
    
    /*Ordena los clientes según su nombre de forma descendente*/
    public ArrayList<Cliente> ordenarClientesXNombresDesc(ArrayList<Cliente> clientes){
        clientes = ordenarClientesXNombresAsc(clientes);
        Collections.reverse(clientes);
        return clientes;
    }
    
    /*Ordena los clientes en cuando al número de suscripciones realizadas de forma ascendente*/
    public ArrayList<Cliente> ordenarClientesXCantSuscripcionesAsc(ArrayList<Cliente> clientes){
        Cliente auxCliente; //Para reservar el objeto actual
        int posMin; // Para reservar la posicion del objeto actual

        for(int i = 0; i < clientes.size(); i++)
        {
            posMin = i;

            for(int j = i + 1; j < clientes.size(); j++) //Para que recorra los demas objetos del arraylist
            {
                if(clientes.get(j).getNumTotalSuscripciones()< clientes.get(posMin).getNumTotalSuscripciones())
                {
                    posMin = j;
                }
            }

            auxCliente = clientes.get(i);
            clientes.set(i, clientes.get(posMin));
            clientes.set(posMin, auxCliente);
        }
        
        return clientes;
    }
    
    /*Ordena los clientes en cuando al número de suscripciones realizadas de forma descendente*/
    public ArrayList<Cliente> ordenarClientesXCantSuscripcionesDesc(ArrayList<Cliente> clientes){
        ordenarClientesXCantSuscripcionesAsc(clientes);
        Collections.reverse(clientes);
        return clientes;
    }
    
    public void validarDatosCliente(Cliente objCliente) throws DatoNoValido, SQLException{
        
        if(!objCliente.getNombreCompleto().matches("^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]{3,}$")){
            throw new DatoNoValido("El nombre solo debe estar conformado por caracteres de letras.");
        }
        
        if(!objCliente.getNombreUsuario().matches("^[a-zA-ZáéíóúÁÉÍÓÚÑñ0-9 ]{1,}$")){
            throw new DatoNoValido("El nombre de usuario solo debe estar conformado por letras y/o números.");
        }
        
        if(!objCliente.getCelular().matches("^[0-9]{9}$")){
            throw new DatoNoValido("El número telefónico del cliente debe estar conformado solo por 9 dígitos.");
        } else if(!objCliente.getCelular().substring(0, 1).matches("^[9]{1}$")){
            throw new DatoNoValido("El número telefónico del cliente debe comenzar con el número '9'.");
        }
    }
}
