/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas.moduloServicioStreaming;
import controladores.*;
import entidades.*;
import entidades.TipoServicioStreaming;
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
public class GUIInformeDetalladoPlan extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIRegistrar
     */
    //ControladorCliente controlador = new ControladorCliente();
    
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
            Logger.getLogger(GUIGenerarPlan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void limpiar(){
        
    }
    
    private void configurarEncabezadoJTable(){
        jTablePlanes.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        jTablePlanes.getTableHeader().setOpaque(false);
        jTablePlanes.getTableHeader().setForeground(Color.BLACK);
        
        jTableCuentas.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        jTableCuentas.getTableHeader().setOpaque(false);
        jTableCuentas.getTableHeader().setForeground(Color.BLACK);
    }
    
    private void llenarJTablePlanes(){
        DefaultTableModel dtm = new DefaultTableModel();
        
        ArrayList<Plan> planes = new ArrayList<>();
        ControladorServicioStreaming controladorServicioStreaming = new ControladorServicioStreaming();
        
        try {
            planes = controladorServicioStreaming.obtenerPlanes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Hubo un problema al cargar los datos de planes.");
        }
        
        String[] cabecera = {"Código plan", "Tiempo del plan (meses)", "Costo", "Plataforma asociada"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataPlan = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < planes.size(); i++) {
            dataPlan[0] = planes.get(i).getPlanId();
            dataPlan[1] = planes.get(i).getTiempoMeses();
            dataPlan[2] = "S/ " + planes.get(i).getCosto();
            dataPlan[3] = planes.get(i).getNombrePlataformaAsociada();
            dtm.addRow(dataPlan);
        }
        jTablePlanes.setModel(dtm);
    }
    
    private void llenarJTablePlanes(ArrayList<Plan> planes){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"Código plan", "Tiempo del plan (meses)", "Costo", "Plataforma asociada"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        Object[] dataPlan = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < planes.size(); i++) {
            dataPlan[0] = planes.get(i).getPlanId();
            dataPlan[1] = planes.get(i).getTiempoMeses();
            dataPlan[2] = "S/ " + planes.get(i).getCosto();
            dataPlan[3] = planes.get(i).getNombrePlataformaAsociada();
            dtm.addRow(dataPlan);
        }
        jTablePlanes.setModel(dtm);
    }
    
    private void llenarJTableCuentas(ArrayList<Cuenta> cuentas){
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] cabecera = {"ID cuenta", "Correo", "Contraseña"}; 
        dtm.setColumnIdentifiers(cabecera);
        dtm.setColumnCount(cabecera.length);
        
        Object[] dataCuenta = new Object[dtm.getColumnCount()];
        
        for (int i = 0; i < cuentas.size(); i++) {
            dataCuenta[0] = cuentas.get(i).getCuentaId();
            dataCuenta[1] = cuentas.get(i).getCorreo();
            dataCuenta[2] = cuentas.get(i).getContrasena();
            dtm.addRow(dataCuenta);
        }
        jTableCuentas.setModel(dtm);
    }
    
    private void establecerPorDefecto(){
        limpiar();
        llenarJTablePlanes();
        lblCodigoPlan.setText("0");
        cargarCboPlataformas();
    }
    
    public GUIInformeDetalladoPlan() {
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
        lblServicioStreaming = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePlanes = new javax.swing.JTable();
        lblCodigoPlan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCuentas = new javax.swing.JTable();
        cboPlataformas = new javax.swing.JComboBox<>();

        jPaneActualizar.setBackground(new java.awt.Color(255, 255, 153));
        jPaneActualizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Información detallada de plan");
        jPaneActualizar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 750, -1));

        lblServicioStreaming.setBackground(new java.awt.Color(204, 204, 204));
        lblServicioStreaming.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblServicioStreaming.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblServicioStreaming.setOpaque(true);
        jPaneActualizar.add(lblServicioStreaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 300, 20));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Plataforma asociada:");
        jPaneActualizar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 150, -1));

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Cuentas activas asociadas al plan:");
        jPaneActualizar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 240, -1));

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
        jLabel8.setText("Seleccionar plataforma de streaming:");
        jPaneActualizar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 260, -1));

        jTablePlanes.setModel(new javax.swing.table.DefaultTableModel(
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
        jTablePlanes.setFocusable(false);
        jTablePlanes.setGridColor(new java.awt.Color(0, 0, 0));
        jTablePlanes.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTablePlanes.setRowHeight(20);
        jTablePlanes.setShowGrid(true);
        jTablePlanes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePlanesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePlanes);

        jPaneActualizar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 770, 120));

        lblCodigoPlan.setBackground(new java.awt.Color(204, 204, 204));
        lblCodigoPlan.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCodigoPlan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodigoPlan.setOpaque(true);
        jPaneActualizar.add(lblCodigoPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 200, 20));

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Datos de plan:");
        jPaneActualizar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, -1));

        jTableCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCuentas.setGridColor(new java.awt.Color(0, 0, 0));
        jTableCuentas.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableCuentas.setShowGrid(true);
        jScrollPane2.setViewportView(jTableCuentas);

        jPaneActualizar.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 770, 140));

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
        jPaneActualizar.add(cboPlataformas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 200, -1));

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

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void jTablePlanesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePlanesMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = -1;
        //        String[] cabecera = {"Código plan", "Tiempo del plan (meses)", "Costo", "Plataforma asociada"}; 
        if(jTablePlanes.getSelectedRow() != -1){
            filaSeleccionada = jTablePlanes.getSelectedRow();
            int codigoPlan = Integer.parseInt(jTablePlanes.getValueAt(filaSeleccionada, 0)+ "");
            
            try {
                ControladorServicioStreaming controladorStreaming = new ControladorServicioStreaming();
                Plan objPlan;
                objPlan = controladorStreaming.obtenerPlan(codigoPlan);
                lblCodigoPlan.setText(objPlan.getPlanId() + "");
                lblServicioStreaming.setText(objPlan.getNombrePlataformaAsociada());
                
                ControladorCuenta controladorCuenta = new ControladorCuenta();
                
                llenarJTableCuentas(controladorCuenta.obtenerCuentas(objPlan.getPlanId(), true));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } 
        }
    }//GEN-LAST:event_jTablePlanesMouseClicked

    private void cboPlataformasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPlataformasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboPlataformasActionPerformed

    private void cboPlataformasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPlataformasItemStateChanged
        // TODO add your handling code here:
        if(cboPlataformas.getSelectedIndex() != -1){
            ControladorServicioStreaming controladorStreaming = new ControladorServicioStreaming();
            
            try {
                String item[] = cboPlataformas.getSelectedItem().toString().split("-");
                int plataformaId = Integer.parseInt(item[0].trim());
                
                llenarJTablePlanes(controladorStreaming.obtenerPlanes(plataformaId));
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_cboPlataformasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboPlataformas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPaneActualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCuentas;
    private javax.swing.JTable jTablePlanes;
    private javax.swing.JLabel lblCodigoPlan;
    private javax.swing.JLabel lblServicioStreaming;
    // End of variables declaration//GEN-END:variables
}
