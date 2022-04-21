/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas.moduloCliente;

import controladores.ControladorCliente;
import controladores.ControladorServicioStreaming;
import controladores.ControladorSuscripcion;
import entidades.Cliente;
import entidades.Suscripcion;
import entidades.TipoServicioStreaming;
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
public class GUIInformeCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIRegistrar
     */
    ControladorCliente controlador = new ControladorCliente();
    
    private void limpiar(){
        txtBusquedaCliente.setText("");
    }
    
    private void configurarEncabezadoJTable(){
        jTableClientes.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        jTableClientes.getTableHeader().setOpaque(false);
        jTableClientes.getTableHeader().setForeground(Color.BLACK);
        
        jTableSuscripciones.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        jTableSuscripciones.getTableHeader().setOpaque(false);
        jTableSuscripciones.getTableHeader().setForeground(Color.BLACK);
    }
    
    private void llenarJTableClientes(){
        DefaultTableModel dtm = new DefaultTableModel();
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            clientes = controlador.obtenerClientes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Hubo un problema al cargar los datos de clientes.");
        }
        
        String[] cabecera = {"Código", "Nombre completo", "Nombre de usuario", "Celular"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataCliente = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < clientes.size(); i++) {
            dataCliente[0] = clientes.get(i).getClienteId();
            dataCliente[1] = clientes.get(i).getNombreCompleto();
            dataCliente[2] = clientes.get(i).getNombreUsuario();
            dataCliente[3] = clientes.get(i).getCelular();
            dtm.addRow(dataCliente);
        }
        jTableClientes.setModel(dtm);
    }
    
    private void llenarJTableClientes(ArrayList<Cliente> clientes){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"Código", "Nombre completo", "Nombre de usuario", "Celular"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataCliente = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < clientes.size(); i++) {
            dataCliente[0] = clientes.get(i).getClienteId();
            dataCliente[1] = clientes.get(i).getNombreCompleto();
            dataCliente[2] = clientes.get(i).getNombreUsuario();
            dataCliente[3] = clientes.get(i).getCelular();
            dtm.addRow(dataCliente);
        }
        jTableClientes.setModel(dtm);
    }
    
    private void llenarJTableSuscripciones(ArrayList<Suscripcion> suscripciones){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"Código de suscripción", "Plataforma", "Correo cuenta", "Fecha de suscripción"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        
        Object[] dataSuscripcion = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < suscripciones.size(); i++) {
            dataSuscripcion[0] = suscripciones.get(i).getSucripcionID();
            dataSuscripcion[1] = suscripciones.get(i).getPlataformaStreamingAsociada();
            dataSuscripcion[2] = suscripciones.get(i).getCorreoCuentaAsociada();
            dataSuscripcion[3] = suscripciones.get(i).getFechaInicio();
            dtm.addRow(dataSuscripcion);
        }
        jTableSuscripciones.setModel(dtm);
    }
    
    private void cargarCboPlataformas(){
        ControladorServicioStreaming controladorTipoServicioStreaming = new ControladorServicioStreaming();
        ArrayList<TipoServicioStreaming> plataformas;
        
        try {
            plataformas = controladorTipoServicioStreaming.obtenerServiciosStreaming();
            plataformas = controladorTipoServicioStreaming.ordenarTipoServiciosXNombreAsc(plataformas);
            
            for (int i = 0; i < plataformas.size(); i++) {
                cboPlataformas.addItem(plataformas.get(i).getTipoServicioId() + " - " + plataformas.get(i).getNombre());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        
    }
    
    private void establecerPorDefecto(){
        limpiar();
        txtBusquedaCliente.setFocusable(true);
        llenarJTableClientes();
        lblCodigo.setText("0");
        cargarCboPlataformas();
    }
    
    public GUIInformeCliente() {
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNombreCompleto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBusquedaCliente = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        lblCelular = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSuscripciones = new javax.swing.JTable();
        cboPlataformas = new javax.swing.JComboBox<>();

        jPaneActualizar.setBackground(new java.awt.Color(111, 159, 195));
        jPaneActualizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Información de cliente");
        jPaneActualizar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 10, 630, -1));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Número de celular:");
        jPaneActualizar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 130, -1));

        lblNombreCompleto.setBackground(new java.awt.Color(204, 204, 204));
        lblNombreCompleto.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNombreCompleto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombreCompleto.setOpaque(true);
        jPaneActualizar.add(lblNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 300, 20));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nombre completo:");
        jPaneActualizar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 130, -1));

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Nombre de usuario:");
        jPaneActualizar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 130, -1));

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Sucripciones activas asociadas según tipo de plataforma:");
        jPaneActualizar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 380, -1));

        txtBusquedaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaClienteKeyTyped(evt);
            }
        });
        jPaneActualizar.add(txtBusquedaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 280, -1));

        btnSalir.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        jPaneActualizar.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 490, 140, 50));

        jLabel7.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Código:");
        jPaneActualizar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 60, -1));

        jLabel8.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Buscar nombre de cliente:");
        jPaneActualizar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, -1));

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
        jScrollPane1.setViewportView(jTableClientes);

        jPaneActualizar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 770, 120));

        lblCelular.setBackground(new java.awt.Color(204, 204, 204));
        lblCelular.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCelular.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCelular.setOpaque(true);
        jPaneActualizar.add(lblCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 200, 20));

        lblCodigo.setBackground(new java.awt.Color(204, 204, 204));
        lblCodigo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodigo.setOpaque(true);
        jPaneActualizar.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 200, 20));

        lblNombreUsuario.setBackground(new java.awt.Color(204, 204, 204));
        lblNombreUsuario.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombreUsuario.setOpaque(true);
        jPaneActualizar.add(lblNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 200, 20));

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Datos de cliente:");
        jPaneActualizar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, -1));

        jTableSuscripciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableSuscripciones.setGridColor(new java.awt.Color(0, 0, 0));
        jTableSuscripciones.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableSuscripciones.setShowGrid(true);
        jScrollPane2.setViewportView(jTableSuscripciones);

        jPaneActualizar.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 770, 110));

        cboPlataformas.setSelectedItem("");
        cboPlataformas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPlataformasItemStateChanged(evt);
            }
        });
        cboPlataformas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPlataformasActionPerformed(evt);
            }
        });
        jPaneActualizar.add(cboPlataformas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBusquedaClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaClienteKeyPressed
        // TODO add your handling code here:
        
        try {
            llenarJTableClientes(controlador.obtenerClientes(txtBusquedaCliente.getText().trim()));
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_txtBusquedaClienteKeyPressed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void txtBusquedaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaClienteKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtBusquedaClienteKeyTyped

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = 0;
        
        if(jTableClientes.getSelectedRow() != -1){
            filaSeleccionada = jTableClientes.getSelectedRow();
            lblCodigo.setText(jTableClientes.getValueAt(filaSeleccionada, 0) + "");
            lblNombreCompleto.setText(jTableClientes.getValueAt(filaSeleccionada, 1) + "");
            lblNombreUsuario.setText(jTableClientes.getValueAt(filaSeleccionada, 2) + "");
            lblCelular.setText(jTableClientes.getValueAt(filaSeleccionada, 3) + "");
            
            ControladorSuscripcion controladorSuscripciones = new ControladorSuscripcion();
            try {
                llenarJTableSuscripciones(controladorSuscripciones.obtenerSuscripciones(Integer.parseInt(lblCodigo.getText()), true));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void cboPlataformasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPlataformasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboPlataformasActionPerformed

    private void cboPlataformasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPlataformasItemStateChanged
        // TODO add your handling code here:
        if(cboPlataformas.getSelectedIndex() != -1){
            ControladorSuscripcion controladorSuscripcion = new ControladorSuscripcion();
            
            try {
                String item[] = cboPlataformas.getSelectedItem().toString().split("-");
                int plataformaId = Integer.parseInt(item[0].trim());
                int clienteId = Integer.parseInt(lblCodigo.getText());
                llenarJTableSuscripciones(controladorSuscripcion.obtenerSuscripciones(clienteId, true, plataformaId));
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_cboPlataformasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboPlataformas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPaneActualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableSuscripciones;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombreCompleto;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTextField txtBusquedaCliente;
    // End of variables declaration//GEN-END:variables
}
