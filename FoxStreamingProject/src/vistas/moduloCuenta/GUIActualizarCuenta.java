/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas.moduloCuenta;

import vistas.moduloServicioStreaming.*;
import vistas.moduloCliente.*;
import controladores.ControladorCliente;
import controladores.*;
import entidades.*;
import excepcionesPropias.DatoNoValido;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexander
 */
public class GUIActualizarCuenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIRegistrar
     */
    
    private void limpiar(){
        txtBusquedaCorreo.setText("");
        txtContrasena.setText("");
        txtCorreo.setText("");
    }
    
    private void configurarEncabezadoJTable(){
        jTableCuentas.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        jTableCuentas.getTableHeader().setOpaque(false);
        jTableCuentas.getTableHeader().setForeground(Color.BLACK);
        
    }
    
    private void llenarJTableCuentas(){
        DefaultTableModel dtm = new DefaultTableModel();
        
        ControladorCuenta controladorCuenta = new ControladorCuenta();
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        
        try {
            cuentas = controladorCuenta.obtenerCuentas();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Hubo un problema al cargar los datos.");
        }
        
        String[] cabecera = {"ID cuenta", "Correo", "Contrasena", "Nº actual de usuarios", "¿Está activa?"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataCuentas = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < cuentas.size(); i++) {
            dataCuentas[0] = cuentas.get(i).getCuentaId();
            dataCuentas[1] = cuentas.get(i).getCorreo();
            dataCuentas[2] = cuentas.get(i).getContrasena();
            dataCuentas[3] = cuentas.get(i).getNumActualUsuarios();
            
            if(cuentas.get(i).getEstaActiva()){
                dataCuentas[4] = "SÍ";
            } else{
                dataCuentas[4] = "NO";
            }
            
            dtm.addRow(dataCuentas);
        }
        jTableCuentas.setModel(dtm);
    }
    
    private void llenarJTableCuentas(ArrayList<Cuenta> cuentas){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"ID cuenta", "Correo", "Contrasena", "Nº actual de usuarios", "¿Está activa?"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataCuentas = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < cuentas.size(); i++) {
            dataCuentas[0] = cuentas.get(i).getCuentaId();
            dataCuentas[1] = cuentas.get(i).getCorreo();
            dataCuentas[2] = cuentas.get(i).getContrasena();
            dataCuentas[3] = cuentas.get(i).getNumActualUsuarios();
            
            if(cuentas.get(i).getEstaActiva()){
                dataCuentas[4] = "SÍ";
            } else{
                dataCuentas[4] = "NO";
            }
            
            dtm.addRow(dataCuentas);
        }
        jTableCuentas.setModel(dtm);
    }
    
    private void cargarCboPlanes(int plataformaId){
        cboPlanes.removeAllItems();
        ControladorServicioStreaming controlador = new ControladorServicioStreaming();
        ArrayList<Plan> planes;
        try {
            planes = controlador.obtenerPlanes(plataformaId);
            for (int i = 0; i < planes.size(); i++) {
                cboPlanes.addItem(planes.get(i).getPlanId()+ " - Plan de " + planes.get(i).getTiempoMeses() + " mes(es)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIRegistrarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void cargarCboPlataformas(){
        ControladorServicioStreaming controlador = new ControladorServicioStreaming();
        ArrayList<TipoServicioStreaming> plataformas;
        try {
            plataformas = controlador.obtenerServiciosStreaming();
            plataformas = controlador.ordenarTipoServiciosXNombreAsc(plataformas);
            for (int i = 0; i < plataformas.size(); i++) {
                cboPlataformas.addItem(plataformas.get(i).getTipoServicioId() + " - " + plataformas.get(i).getNombre());
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIRegistrarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void establecerPorDefecto(){
        //cargarCodigo();
        limpiar();
        txtBusquedaCorreo.setFocusable(true);
        llenarJTableCuentas();
        cargarCboPlataformas();
    }
    
    public GUIActualizarCuenta() {
        initComponents();
        establecerPorDefecto();
        configurarEncabezadoJTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPaneActualizar = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBusquedaCorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboPlataformas = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboPlanes = new javax.swing.JComboBox<>();
        btnSalir = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCuentas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboEstadoCuenta = new javax.swing.JComboBox<>();

        jPaneActualizar.setBackground(new java.awt.Color(255, 255, 255));
        jPaneActualizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setBackground(new java.awt.Color(204, 204, 204));
        lblCodigo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodigo.setOpaque(true);
        jPaneActualizar.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 210, 20));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Estado de actividad:");
        jPaneActualizar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 210, -1));

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Datos de cuenta:");
        jPaneActualizar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, -1));

        txtBusquedaCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaCorreoKeyPressed(evt);
            }
        });
        jPaneActualizar.add(txtBusquedaCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 250, -1));

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Plataforma asociada");
        jPaneActualizar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 380, 260, -1));

        cboPlataformas.setBackground(new java.awt.Color(204, 204, 204));
        cboPlataformas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPlataformasItemStateChanged(evt);
            }
        });
        jPaneActualizar.add(cboPlataformas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 230, -1));

        jLabel10.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Plan:");
        jPaneActualizar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 410, 260, -1));

        cboPlanes.setBackground(new java.awt.Color(204, 204, 204));
        jPaneActualizar.add(cboPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 230, -1));

        btnSalir.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        jPaneActualizar.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 140, 50));

        btnActualizar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });
        jPaneActualizar.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 140, 50));

        jLabel7.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Código:");
        jPaneActualizar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 210, -1));
        jPaneActualizar.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 280, -1));

        jLabel8.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Buscar correo electrónico de la cuenta:");
        jPaneActualizar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 270, -1));

        jTableCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCuentas.setFocusable(false);
        jTableCuentas.setGridColor(new java.awt.Color(0, 0, 0));
        jTableCuentas.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableCuentas.setRowHeight(20);
        jTableCuentas.setShowGrid(true);
        jTableCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCuentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCuentas);

        jPaneActualizar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 780, 190));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Actualización de cuenta");
        jPaneActualizar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, -1));

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Correo electrónico:");
        jPaneActualizar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 210, -1));
        jPaneActualizar.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 280, -1));

        jLabel11.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Contrasena:");
        jPaneActualizar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 210, -1));

        cboEstadoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cboEstadoCuenta.setSelectedIndex(-1);
        jPaneActualizar.add(cboEstadoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 160, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        // TODO add your handling code here:
        try {
            if(txtCorreo.getText().equals("") || txtContrasena.getText().equals("") || cboPlanes.getSelectedIndex() == -1 || cboEstadoCuenta.getSelectedIndex() == -1){
                throw new DatoNoValido("Datos incompletos.");
            }
            
            ControladorCuenta controladorCuenta = new ControladorCuenta();
            Cuenta objCuenta = new Cuenta();

            String itemPlanes[] = cboPlanes.getSelectedItem().toString().split("-");
            objCuenta.setCuentaId(Integer.parseInt(lblCodigo.getText()));
            objCuenta.setPlanId(Integer.parseInt(itemPlanes[0].trim()));
            objCuenta.setCorreo(txtCorreo.getText().trim());
            objCuenta.setContrasena(txtContrasena.getText().trim());

            if(cboEstadoCuenta.getSelectedIndex() == 0){
                objCuenta.setEstaActiva(true);
            } else{
                objCuenta.setEstaActiva(false);
            }
            controladorCuenta.actualizar(objCuenta);

            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");

            establecerPorDefecto();
        } catch (DatoNoValido | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error. Intente nuevamente.");
        }
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void jTableCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCuentasMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = 0;
        
        if(jTableCuentas.getSelectedRow() != -1){
            filaSeleccionada = jTableCuentas.getSelectedRow();
            
            ControladorCuenta controladorCuenta = new ControladorCuenta();
            int cuentaId = Integer.parseInt(jTableCuentas.getValueAt(filaSeleccionada, 0).toString());
            try {
                Cuenta objCuenta = controladorCuenta.obtenerCuenta(cuentaId);
                lblCodigo.setText(objCuenta.getCuentaId() + "");
                txtCorreo.setText(objCuenta.getCorreo());
                txtContrasena.setText(objCuenta.getContrasena());
            } catch (SQLException ex) {
                Logger.getLogger(GUIActualizarCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTableCuentasMouseClicked

    private void txtBusquedaCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaCorreoKeyPressed
        // TODO add your handling code here:

        try {
            ControladorCuenta controladorCuenta = new ControladorCuenta();
            llenarJTableCuentas(controladorCuenta.obtenerCuentas(txtBusquedaCorreo.getText().trim()));
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_txtBusquedaCorreoKeyPressed

    private void cboPlataformasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPlataformasItemStateChanged
        // TODO add your handling code here:
        if(cboPlataformas.getSelectedIndex() != -1){
            String item[] = cboPlataformas.getSelectedItem().toString().split("-");
            int planId = Integer.parseInt(item[0].trim());
            cargarCboPlanes(planId);
        }
    }//GEN-LAST:event_cboPlataformasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboEstadoCuenta;
    private javax.swing.JComboBox<String> cboPlanes;
    private javax.swing.JComboBox<String> cboPlataformas;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPaneActualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCuentas;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JTextField txtBusquedaCorreo;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
