/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas.moduloSuscripcion;

import vistas.moduloCuenta.*;
import vistas.moduloServicioStreaming.*;
import controladores.*;
import entidades.*;
import excepcionesPropias.DatoNoValido;
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
public class GUIGenerarSuscripcion extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIRegistrar
     */
    
    private void cargarCodigo(){
        ControladorSuscripcion controlador = new ControladorSuscripcion();
        try {
            lblSuscripcionID.setText(controlador.obtenerNuevoCodigo()+ "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void llenarJTableClientes(){
        DefaultTableModel dtm = new DefaultTableModel();
        
        ControladorCliente controlador = new ControladorCliente();
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            clientes = controlador.obtenerClientes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Hubo un problema al cargar los datos de clientes.");
        }
        
        String[] cabecera = {"Cliente ID", "Nombre de cliente", "Nombre de usuario", "Celular"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataClientes = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < clientes.size(); i++) {
            dataClientes[0] = clientes.get(i).getClienteId();
            dataClientes[1] = clientes.get(i).getNombreCompleto();
            dataClientes[2] = clientes.get(i).getNombreUsuario();
            dataClientes[3] = clientes.get(i).getCelular();
            dtm.addRow(dataClientes);
        }
        jTableClientes.setModel(dtm);
    }
    
    private void llenarJTableClientes(ArrayList<Cliente> clientes){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"Cliente ID", "Nombre de cliente", "Nombre de usuario", "Celular"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataClientes = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < clientes.size(); i++) {
            dataClientes[0] = clientes.get(i).getClienteId();
            dataClientes[1] = clientes.get(i).getNombreCompleto();
            dataClientes[2] = clientes.get(i).getNombreUsuario();
            dataClientes[3] = clientes.get(i).getCelular();
            dtm.addRow(dataClientes);
        }
        jTableClientes.setModel(dtm);
    }
    
    private void llenarJTableCuentas(ArrayList<Cuenta> cuentas){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"Cuenta ID", "Correo electrónico", "Contraseña", "Nº actual de usuarios"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataCuentas = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < cuentas.size(); i++) {
            dataCuentas[0] = cuentas.get(i).getCuentaId();
            dataCuentas[1] = cuentas.get(i).getCorreo();
            dataCuentas[2] = cuentas.get(i).getContrasena();
            dataCuentas[3] = cuentas.get(i).getNumActualUsuarios();
            dtm.addRow(dataCuentas);
        }
        jTableCuentas.setModel(dtm);
    }
    
     private void vaciarJTableCuentas(){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"Cuenta ID", "Correo electrónico", "Contraseña", "Nº actual de usuarios"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataCuentas = new Object[dtm.getColumnCount()];
        
        jTableCuentas.setModel(dtm);
    }
    
    private void limpiar(){
        txtBusquedaCliente.setText("");
        lblClienteId.setText("");
        lblCuentaId.setText("");
        lblClienteId.setText("");
        vaciarJTableCuentas();
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
            Logger.getLogger(GUIGenerarSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            Logger.getLogger(GUIGenerarSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void establecerPorDefecto(){
        limpiar();
        cargarCodigo();
        cargarCboPlataformas();
        llenarJTableClientes();
    }
    
    public GUIGenerarSuscripcion() {
        initComponents();
        establecerPorDefecto();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPaneRegistrarSuscripcion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblCuentaId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGenerarSuscripcion = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboPlataformas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboPlanes = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtBusquedaCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCuentas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        lblSuscripcionID = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblClienteId = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblnombreCliente = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();

        jPaneRegistrarSuscripcion.setBackground(new java.awt.Color(255, 255, 255));
        jPaneRegistrarSuscripcion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Generar nueva suscripción");
        jPaneRegistrarSuscripcion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 680, -1));

        lblCuentaId.setBackground(new java.awt.Color(204, 204, 204));
        lblCuentaId.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCuentaId.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCuentaId.setOpaque(true);
        jPaneRegistrarSuscripcion.add(lblCuentaId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 180, 20));

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Búsqueda y selección de cliente:");
        jPaneRegistrarSuscripcion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 240, -1));

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Seleccionar plataforma:");
        jPaneRegistrarSuscripcion.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 170, -1));

        btnGenerarSuscripcion.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnGenerarSuscripcion.setText("Generar suscripción");
        btnGenerarSuscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenerarSuscripcionMouseClicked(evt);
            }
        });
        jPaneRegistrarSuscripcion.add(btnGenerarSuscripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 490, 240, 50));

        btnSalir.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        jPaneRegistrarSuscripcion.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 490, 140, 50));

        jLabel7.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Código (autogenerado) :");
        jPaneRegistrarSuscripcion.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 180, -1));

        cboPlataformas.setBackground(new java.awt.Color(204, 204, 204));
        cboPlataformas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPlataformasItemStateChanged(evt);
            }
        });
        jPaneRegistrarSuscripcion.add(cboPlataformas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 230, -1));

        jLabel8.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Seleccionar plan:");
        jPaneRegistrarSuscripcion.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 120, -1));

        cboPlanes.setBackground(new java.awt.Color(204, 204, 204));
        cboPlanes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPlanesItemStateChanged(evt);
            }
        });
        jPaneRegistrarSuscripcion.add(cboPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, 230, -1));

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Cuenta ID:");
        jPaneRegistrarSuscripcion.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 80, -1));

        txtBusquedaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBusquedaClienteMouseClicked(evt);
            }
        });
        txtBusquedaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaClienteKeyPressed(evt);
            }
        });
        jPaneRegistrarSuscripcion.add(txtBusquedaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 180, -1));

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

        jPaneRegistrarSuscripcion.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 750, 100));

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableClientes.setFocusable(false);
        jTableClientes.setGridColor(new java.awt.Color(0, 0, 0));
        jTableClientes.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableClientes.setRowHeight(20);
        jTableClientes.setShowGrid(true);
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableClientes);

        jPaneRegistrarSuscripcion.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 750, 100));

        jLabel10.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Seleccionar cuenta:");
        jPaneRegistrarSuscripcion.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, -1));

        lblSuscripcionID.setBackground(new java.awt.Color(204, 204, 204));
        lblSuscripcionID.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblSuscripcionID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSuscripcionID.setOpaque(true);
        jPaneRegistrarSuscripcion.add(lblSuscripcionID, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 180, 20));

        jLabel11.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Cliente ID:");
        jPaneRegistrarSuscripcion.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 160, -1));

        lblClienteId.setBackground(new java.awt.Color(204, 204, 204));
        lblClienteId.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteId.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblClienteId.setOpaque(true);
        jPaneRegistrarSuscripcion.add(lblClienteId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 180, 20));

        jLabel12.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Nombre cliente:");
        jPaneRegistrarSuscripcion.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 150, -1));

        lblnombreCliente.setBackground(new java.awt.Color(204, 204, 204));
        lblnombreCliente.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblnombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblnombreCliente.setOpaque(true);
        jPaneRegistrarSuscripcion.add(lblnombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 180, 20));

        jLabel13.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Precio de suscripción:");
        jPaneRegistrarSuscripcion.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 160, -1));

        lblMonto.setBackground(new java.awt.Color(204, 204, 204));
        lblMonto.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblMonto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMonto.setOpaque(true);
        jPaneRegistrarSuscripcion.add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 450, 180, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneRegistrarSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneRegistrarSuscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCuentasMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = 0;

        if(jTableCuentas.getSelectedRow() != -1){
            try {
                filaSeleccionada = jTableCuentas.getSelectedRow();
                int cuentaID = Integer.parseInt(jTableCuentas.getValueAt(filaSeleccionada, 0).toString());
                lblCuentaId.setText(cuentaID + "");
                ControladorServicioStreaming controlador = new ControladorServicioStreaming();

                String itemPlanes[] = cboPlanes.getSelectedItem().toString().split("-");
                double monto = controlador.obtenerMonto(Integer.parseInt(itemPlanes[0].trim()));
                lblMonto.setText(monto + "");
            } catch (SQLException ex) {
                Logger.getLogger(GUIGenerarSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTableCuentasMouseClicked

    private void cboPlataformasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPlataformasItemStateChanged
        // TODO add your handling code here:
        if(cboPlataformas.getSelectedIndex() != -1){
            vaciarJTableCuentas();
            lblCuentaId.setText("");
            lblMonto.setText("");
            String item[] = cboPlataformas.getSelectedItem().toString().split("-");
            int planId = Integer.parseInt(item[0].trim());
            cargarCboPlanes(planId);
        }
    }//GEN-LAST:event_cboPlataformasItemStateChanged

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnGenerarSuscripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarSuscripcionMouseClicked
        // TODO add your handling code here:
        try {
            if(lblClienteId.getText().equals("") || lblCuentaId.getText().equals("") || cboPlanes.getSelectedIndex() == -1 || lblMonto.getText().equals("")){
                throw new DatoNoValido("Selección incompleta de datos.");
            }
            
            Suscripcion objSuscripcion = new Suscripcion();
            ControladorSuscripcion controlador = new ControladorSuscripcion();
            
            objSuscripcion.setClienteID(Integer.parseInt(lblClienteId.getText()));
            objSuscripcion.setCuentaID(Integer.parseInt(lblCuentaId.getText()));
            objSuscripcion.setPagoEfectuado(Double.parseDouble(lblMonto.getText()));

            controlador.generarSuscripcion(objSuscripcion);

            JOptionPane.showMessageDialog(this, "Nueva suscripción registrada al sistema.");

            establecerPorDefecto();
        } catch (DatoNoValido | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnGenerarSuscripcionMouseClicked

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = 0;
        
        if(jTableClientes.getSelectedRow() != -1){
            
            filaSeleccionada = jTableClientes.getSelectedRow();
            lblClienteId.setText(jTableClientes.getValueAt(filaSeleccionada, 0) + "");
            lblnombreCliente.setText(jTableClientes.getValueAt(filaSeleccionada, 1) + "");
        }
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void txtBusquedaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBusquedaClienteMouseClicked
        try {
            if(lblClienteId.getText().equals("") || lblCuentaId.getText().equals("") || cboPlanes.getSelectedIndex() == -1){
                throw new DatoNoValido("Debe seleccionar datos de las tablas.");
            }
            
            Suscripcion objSuscripcion = new Suscripcion();
            ControladorSuscripcion controlador = new ControladorSuscripcion();
            
            objSuscripcion.setClienteID(Integer.parseInt(lblClienteId.getText()));
            objSuscripcion.setCuentaID(Integer.parseInt(lblCuentaId.getText()));
            objSuscripcion.setPagoEfectuado(Double.parseDouble(lblMonto.getText()));

            controlador.generarSuscripcion(objSuscripcion);

            JOptionPane.showMessageDialog(this, "Nueva suscripción registrada al sistema.");

            establecerPorDefecto();
        } catch (DatoNoValido | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_txtBusquedaClienteMouseClicked

    private void cboPlanesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPlanesItemStateChanged
        // TODO add your handling code here:
        if(cboPlanes.getSelectedIndex() != -1){
            String item[] = cboPlanes.getSelectedItem().toString().split("-");
            int planId = Integer.parseInt(item[0].trim());
            ControladorCuenta controladorCuenta = new ControladorCuenta();
            try {
                llenarJTableCuentas(controladorCuenta.obtenerCuentasMenorMaxUsuarios(planId, true));
            } catch (SQLException ex) {
                Logger.getLogger(GUIGenerarSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboPlanesItemStateChanged

    private void txtBusquedaClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaClienteKeyPressed
        // TODO add your handling code here:
        // TODO add your handling code here:
        ControladorCliente controlador = new ControladorCliente();
        try {
            llenarJTableClientes(controlador.obtenerClientes(txtBusquedaCliente.getText().trim()));
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_txtBusquedaClienteKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarSuscripcion;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboPlanes;
    private javax.swing.JComboBox<String> cboPlataformas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPaneRegistrarSuscripcion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableCuentas;
    private javax.swing.JLabel lblClienteId;
    private javax.swing.JLabel lblCuentaId;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblSuscripcionID;
    private javax.swing.JLabel lblnombreCliente;
    private javax.swing.JTextField txtBusquedaCliente;
    // End of variables declaration//GEN-END:variables
}
