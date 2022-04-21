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
public class ControladorServicioStreaming {
    
    /*--------------------SERVICIO DE STREAMIING------------------------*/
    /*Registrar nueva plataforma de Streaming*/
    public void registrarTipoServicio(TipoServicioStreaming objTipoServicio) throws DatoNoValido, SQLException{
        
       
        //Validar no repitencia de nombre de plataforma
        ArrayList<TipoServicioStreaming> totalPlataformas = obtenerServiciosStreaming();
        
        for (int i = 0; i < totalPlataformas.size(); i++) {
            if(totalPlataformas.get(i).getNombre().equals(objTipoServicio.getNombre())){
                throw new DatoNoValido("Nombre de plataforma ya registrado al sistema.");
            }
        }
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Estableciendo la sentencia de inserción SQL
        String sql = "INSERT INTO TipoServicioStreaming (nombre, maxUsuarios) VALUES (?, ?)";
        PreparedStatement pstm_insert = conn.prepareStatement(sql);
        
        //Asignando parámetros de la sentencia
        pstm_insert.setString(1, objTipoServicio.getNombre());
        pstm_insert.setInt(2, objTipoServicio.getMaxUsuarios());
        
        //Manipulando, finalmente, la base de datos
        pstm_insert.executeUpdate();
        
        conn.close();
    }
    
    /*Actualizar una plataforma de Streaming*/
    public void actualizarTipoServicio(TipoServicioStreaming objTipoServicio) throws DatoNoValido, SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        //Validar no repitencia de nombre de cliente ni de celular a excepción del mismo objeto
        ArrayList<TipoServicioStreaming> totalPlataformas = obtenerServiciosStreaming();
        
        for (int i = 0; i < totalPlataformas.size(); i++) {
            if(totalPlataformas.get(i).getTipoServicioId()== objTipoServicio.getTipoServicioId()){
                totalPlataformas.remove(totalPlataformas.get(i));
                break;
            }
        }
        
        for (int i = 0; i < totalPlataformas.size(); i++) {
            if(totalPlataformas.get(i).getNombre().equals(objTipoServicio.getNombre())){
                throw new DatoNoValido("Nombre de plataforma ya registrado al sistema.");
            }
        }
        
        //Estableciendo la sentencia SQL para la actualización y asignando parámetros
        String sql_update = "UPDATE TipoServicioStreaming SET nombre = ?, maxUsuarios = ? "
                + "WHERE tipoServicioId = ?";
        PreparedStatement pstm_update = conn.prepareStatement(sql_update);
        
        pstm_update.setString(1, objTipoServicio.getNombre());
        pstm_update.setInt(2, objTipoServicio.getMaxUsuarios());
        pstm_update.setInt(3, objTipoServicio.getTipoServicioId());
        
        //Manipulación en la BD
        pstm_update.executeUpdate();
        
        conn.close();
    }
    
    /*Eliminar una plataforma de Streaming*/
    public void eliminarTipoServicio(TipoServicioStreaming objTipoServicio) throws SQLException, Exception{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Comprobar que el tipo de servicio no tenga NINGUNA cuenta activa que pertenece a un tipo
        de plan asociado*/
        if(obtenerNumCuentasXPlataforma(objTipoServicio.getTipoServicioId()) > 0){
            throw new Exception("No se pudo proceder con la eliminación. "
                    + "Esto debido a que esta plataforma dispone aún de cuentas ACTIVAS.");
        }
        
        //Estableciendo la sentencia SQL para consulta y asignando el parámetro necesario
        String sql_delete = "DELETE FROM TipoServicioStreaming WHERE tipoServicioId = ?";
        PreparedStatement pstm = conn.prepareStatement(sql_delete);
        pstm.setInt(1, objTipoServicio.getTipoServicioId());
        
        //Manipulando datos en la base de datos
        pstm.executeUpdate();
        
        conn.close();
    }
    
    /*Obtener todos las plataformas de streaming*/
    public ArrayList<TipoServicioStreaming> obtenerServiciosStreaming() throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta. Utilizamos createStatement()
        ya que, para la ejecución de la sentencia SQL, no se necesita parámetros.*/
        Statement stm = conn.createStatement();
        String sql = "SELECT * FROM TipoServicioStreaming";
        
        //Recuperando clientes
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<TipoServicioStreaming> plataformas = new ArrayList<>();
        TipoServicioStreaming objPlataforma;
        
        while(rs.next()){
            objPlataforma = new TipoServicioStreaming();
            objPlataforma.setTipoServicioId(Integer.parseInt(rs.getString(1)));
            objPlataforma.setNombre(rs.getString(2));
            objPlataforma.setMaxUsuarios(Integer.parseInt(rs.getString(4)));
            plataformas.add(objPlataforma);
        }
        
        return plataformas;
    }
    
    /*Obtener el número de cuentas ACTIVAS que pertenecen a un tipo de servicio de streaming.
    Método útil para la eliminación de tipo de servicio de streaming.*/
    public int obtenerNumCuentasXPlataforma(int plataformaId) throws SQLException{
        int numCuentas = -1;
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql_contar = "SELECT COUNT(TS.tipoServicioId) FROM TipoServicioStreaming TS " +
                            "INNER JOIN Plan P ON TS.tipoServicioId = P.tipoServicioId " +
                            "INNER JOIN Cuenta C ON P.planId = C.planId " +
                            "WHERE TS.tipoServicioId = ? AND C.estaActiva = 1";

        PreparedStatement pstm = conn.prepareStatement(sql_contar);
        pstm.setInt(1, plataformaId);
        
        //Recuperando
        ResultSet result = pstm.executeQuery();
        
        while (result.next()) {            
            numCuentas = Integer.parseInt(result.getString(1));
        }
        
        return numCuentas;
    }
    
    /*Obtiene todas las pataformas según el texto contenido en el campo 'Nombre' de la tabla TipoServicioStreaming*/
    public  ArrayList<TipoServicioStreaming> obtenerServiciosStreaming(String cadena_contenido) throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT * FROM TipoServicioStreaming where nombre LIKE ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, "%" + cadena_contenido + "%");
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<TipoServicioStreaming> plataformas = new ArrayList<>();
        TipoServicioStreaming objTipoServicioStreaming;
        
        while(rs.next()){
            objTipoServicioStreaming = new TipoServicioStreaming();
            objTipoServicioStreaming.setTipoServicioId(Integer.parseInt(rs.getString(1)));
            objTipoServicioStreaming.setNombre(rs.getString(2));
            objTipoServicioStreaming.setMaxUsuarios(Integer.parseInt(rs.getString(4)));
            plataformas.add(objTipoServicioStreaming);
        }
        
        return plataformas;
    }
    
    /*Obtener una plataforma de streaming según su código*/
    public TipoServicioStreaming obtenerServicioStreaming(int plataformaId) throws SQLException{
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT * FROM TipoServicioStreaming " +
                    "WHERE tipoServicioId = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, plataformaId);
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        TipoServicioStreaming objTipoServicioStreaming = new TipoServicioStreaming();
        
        while(rs.next()){
            objTipoServicioStreaming = new TipoServicioStreaming();
            objTipoServicioStreaming.setTipoServicioId(Integer.parseInt(rs.getString(1)));
            objTipoServicioStreaming.setNombre(rs.getString(2));
            objTipoServicioStreaming.setMaxUsuarios(Integer.parseInt(rs.getString(4)));
        }
        
        return objTipoServicioStreaming;
    }
    
    /*Ordenar, por nombre o descripción, todos los tipos de servicio de streaming de forma ASCENDENTE*/
    public ArrayList<TipoServicioStreaming> ordenarTipoServiciosXNombreAsc(ArrayList<TipoServicioStreaming> plataformas){
        
        TipoServicioStreaming auxTipoServicioStreaming; //Para reservar el objeto actual
        int posMin; // Para reservar la posicion del objeto actual

        for(int i = 0; i < plataformas.size(); i++)
        {
            posMin = i;

            for(int j = i + 1; j < plataformas.size(); j++) //Para que recorra los demas objetos del arraylist
            {
                if(plataformas.get(j).getNombre().compareToIgnoreCase(plataformas.get(posMin).getNombre()) < 0)
                {
                    posMin = j;
                }
            }

            auxTipoServicioStreaming = plataformas.get(i);
            plataformas.set(i, plataformas.get(posMin));
            plataformas.set(posMin, auxTipoServicioStreaming);
        }
        
        return plataformas;
    }
    
    /*Ordenar, por nombre o descripción, todos los tipos de servicio de streaming de forma DESCENDENTE*/
    public ArrayList<TipoServicioStreaming> ordenarTipoServiciosXNombreDesc(ArrayList<TipoServicioStreaming> plataformas){
        plataformas = ordenarTipoServiciosXNombreAsc(plataformas);
        Collections.reverse(plataformas);
        return plataformas;
    }
    
    /*Validar datos de los objetos TipoServicioStreaming*/
    public void validarTipoServicio(TipoServicioStreaming objTipoServicio) throws DatoNoValido, SQLException{
        if(!objTipoServicio.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚÑñ0-9+ ]{2,}$")){
            throw new DatoNoValido("El nombre del servicio de streaming debe estar conformado, por lo menos, por 2 caracteres.");
        }
    }
    
    /*--------------------Plan DE SERVICIO DE STREAMIING-------------------------*/
    /*Generar detalle segun tipo de servicio*/
    public void generarPlan(Plan objPlan) throws SQLException, DatoNoValido{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
       
        /*Verificar si el tiempo de suscripción en meses del plan NO se repita con otro
        asociado a la misma plataforma de streaming*/
        ArrayList<Plan> planes = obtenerPlanes(objPlan.getTipoServicioId());
        
        for (int i = 0; i < planes.size(); i++) {
            if(planes.get(i).getTiempoMeses() == objPlan.getTiempoMeses()){
                throw new DatoNoValido("Ya hay un plan con el mismo tiempo de suscripción"
                        + " asociada a una misma plataforma en el sistema.");
            }
        }
        
        //Estableciendo la sentencia de inserción SQL
        String sql = "INSERT INTO Plan (tipoServicioId, tiempoSuscripcionMeses, costo) VALUES (?, ?, ?)";
        PreparedStatement pstm_insert = conn.prepareStatement(sql);
        
        //Asignando parámetros de la sentencia
        pstm_insert.setInt(1, objPlan.getTipoServicioId());
        pstm_insert.setInt(2, objPlan.getTiempoMeses());
        pstm_insert.setDouble(3, objPlan.getCosto());
        
        //Manipulando, finalmente, la base de datos
        pstm_insert.executeUpdate();
        
        conn.close();
    }
    
    /*Actualizar Plan segun su código*/
    public void actualizarPlan(Plan objDetalle){
        
    }
    
    /*Eliminar Plan de servicio Streaming*/
    public void eliminarPlan(Plan objDetalle){
        
    }
    
    /*Obtener todos los planes*/
    public ArrayList<Plan> obtenerPlanes() throws SQLException{
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta. Utilizamos createStatement()
        ya que, para la ejecución de la sentencia SQL, no se necesita parámetros.*/
        Statement stm = conn.createStatement();
        String sql = "SELECT P.*, TS.nombre FROM Plan P "
                + "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId";
        
        //Recuperando clientes
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Plan> tiposOferta = new ArrayList<>();
        Plan objTipoOferta;
        
        while(rs.next()){
            objTipoOferta = new Plan();
            objTipoOferta.setPlanId(Integer.parseInt(rs.getString(1)));
            objTipoOferta.setTipoServicioId(Integer.parseInt(rs.getString(2)));
            objTipoOferta.setTiempoMeses(Integer.parseInt(rs.getString(3)));
            objTipoOferta.setCosto(Double.parseDouble(rs.getString(4)));
            objTipoOferta.setNombrePlataformaAsociada(rs.getString(5));
            objTipoOferta.setNumCuentasAsociadas(obtenerNumCuentasPorPlan(objTipoOferta.getPlanId()));
            tiposOferta.add(objTipoOferta);
        }
        
        return tiposOferta;
    }
    
    /*Obtener todos los planes de servicios según la plataforma a la que pertenece*/
    public ArrayList<Plan> obtenerPlanes(int tipoServicioId) throws SQLException{
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta.*/
        
        String sql = "SELECT P.*, TS.nombre FROM Plan P "
                + "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId "
                + "WHERE P.tipoServicioId = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, tipoServicioId);
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        ArrayList<Plan> planes = new ArrayList<>();
        Plan objPlan;
        
        while(rs.next()){
            objPlan = new Plan();
            objPlan.setPlanId(Integer.parseInt(rs.getString(1)));
            objPlan.setTipoServicioId(Integer.parseInt(rs.getString(2)));
            objPlan.setTiempoMeses(Integer.parseInt(rs.getString(3)));
            objPlan.setCosto(Double.parseDouble(rs.getString(4)));
            objPlan.setNombrePlataformaAsociada(rs.getString(5));
            objPlan.setNumCuentasAsociadas(obtenerNumCuentasPorPlan(objPlan.getPlanId()));
            planes.add(objPlan);
        }
        
        return planes;
    }
    
    /*Obtener un plan de servicio de streaming según su código*/
    public Plan obtenerPlan(int planId) throws SQLException{
        
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta.*/
        String sql = "SELECT P.*, TS.nombre FROM Plan P "
                + "INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId "
                + "WHERE P.planId = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, planId);
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        Plan objPlan = new Plan();
        
        while(rs.next()){
            objPlan.setPlanId(Integer.parseInt(rs.getString(1)));
            objPlan.setTipoServicioId(Integer.parseInt(rs.getString(2)));
            objPlan.setTiempoMeses(Integer.parseInt(rs.getString(3)));
            objPlan.setCosto(Double.parseDouble(rs.getString(4)));
            objPlan.setNombrePlataformaAsociada(rs.getString(5));
            objPlan.setNumCuentasAsociadas(obtenerNumCuentasPorPlan(objPlan.getPlanId()));
        }
        
        return objPlan;
    }
    
    /**/
    public int obtenerNumSuscripcionesPorPlan(int planId) throws SQLException{
        
        int numSuscripciones = -1;
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql_contar = "SELECT COUNT(*) FROM Plan P " +
                            "INNER JOIN Cuenta C ON P.planId = C.planId " +
                            "INNER JOIN Suscripcion S ON C.cuentaId = S.cuentaId " +
                            "WHERE P.planId = ? " +
                            "GROUP BY P.planId";
        
        PreparedStatement pstm = conn.prepareStatement(sql_contar);
        pstm.setInt(1, planId);
        
        //Recuperando
        ResultSet result = pstm.executeQuery();
        
        while (result.next()) {            
            numSuscripciones = Integer.parseInt(result.getString(1));
        }
        
        return numSuscripciones;
    }
    
    /**/
    public int obtenerNumCuentasPorPlan(int planId) throws SQLException{
        
        int numSuscripciones = -1;
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql_contar = "SELECT COUNT(*) FROM Plan P " +
                            "INNER JOIN Cuenta C ON P.planId = C.planId " +
                            "WHERE P.planId = ? " +
                            "GROUP BY P.planId";

        PreparedStatement pstm = conn.prepareStatement(sql_contar);
        pstm.setInt(1, planId);
        
        //Recuperando
        ResultSet result = pstm.executeQuery();
        
        while (result.next()) {            
            numSuscripciones = Integer.parseInt(result.getString(1));
        }
        
        return numSuscripciones;
    }
    
    public int obtenerNuevoCodigo() throws SQLException{
        int codigoGenerado = 0;
        
        Connection conn = ConexionBD.conectarMySQL();
        
        Statement stm = conn.createStatement();
        String sql = "SELECT MAX(tipoServicioId) FROM TipoServicioStreaming";
        ResultSet result = stm.executeQuery(sql);
        
        while(result.next()){
            codigoGenerado = Integer.parseInt(result.getString(1));
        }
        
        codigoGenerado++;
        
        return codigoGenerado;
    }
    
    public int obtenerNuevoCodigoPlan() throws SQLException{
        int codigoGenerado = 0;
        
        Connection conn = ConexionBD.conectarMySQL();
        
        Statement stm = conn.createStatement();
        String sql = "SELECT MAX(planId) FROM Plan";
        ResultSet result = stm.executeQuery(sql);
        
        while(result.next()){
            codigoGenerado = Integer.parseInt(result.getString(1));
        }
        
        codigoGenerado++;
        
        return codigoGenerado;
    }
    
    /*Obtener la cantidad de suscripciones según el tipo de servicio de streaming*/
    public int obtenerNumMaxUsuarios(int tipoServicioId) throws SQLException{
        int maxUsuarios = 0;
        
        //Creando la conexión
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta*/
        String sql = "SELECT maxUsuarios FROM TipoServicioStreaming" +
                    "WHERE tipoServicioId = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, tipoServicioId);
        
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        
        while(rs.next()){
            maxUsuarios = Integer.parseInt(rs.getString(1));
        }
        
        return maxUsuarios;
    }
    
    /**/
    public double obtenerMonto(int planId) throws SQLException{
        double monto = 0;
        
        Connection conn = ConexionBD.conectarMySQL();
        
        /*Estableciendo la sentencia SQL para consulta.*/
        String sql = "SELECT costo FROM Plan "
                + "WHERE planId = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, planId);
        //Recuperando clientes
        ResultSet rs = pstm.executeQuery();
        Cuenta objCuenta = new Cuenta();
        
        while(rs.next()){
            monto = Double.parseDouble(rs.getString(1));
        }
        
        return monto;
    }
    
    /*Ordenar, por costo, todos los planes de servicios de streaming de forma ASCENDENTE*/
    public ArrayList<Plan> ordenarPlanesXCostoAsc(ArrayList<Plan> detallesServicios){
        Plan auxDetalleServicio; //Para reservar el objeto actual
        int posMin; // Para reservar la posicion del objeto actual

        for(int i = 0; i < detallesServicios.size(); i++)
        {
            posMin = i;

            for(int j = i + 1; j < detallesServicios.size(); j++) //Para que recorra los demas objetos del arraylist
            {
                if(detallesServicios.get(j).getCosto()< detallesServicios.get(posMin).getCosto())
                {
                    posMin = j;
                }
            }

            auxDetalleServicio = detallesServicios.get(i);
            detallesServicios.set(i, detallesServicios.get(posMin));
            detallesServicios.set(posMin, auxDetalleServicio);
        }
        
        return detallesServicios;
    }
    
    /*Ordenar, por costo, todos los planes de servicios de streaming de forma DESCENDENTE*/
    public ArrayList<Plan> ordenarPlanesXCostoDesc(ArrayList<Plan> detallesServicios){
        detallesServicios = ordenarPlanesXCostoAsc(detallesServicios);
        Collections.reverse(detallesServicios);
        return detallesServicios;
    }
    
    /*Validar datos de los objetos DetalleServicio*/
    public void validarPlan(Plan objPlan){
        
    }
}
