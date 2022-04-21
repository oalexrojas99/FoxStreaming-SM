/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas.moduloCliente;

import controladores.ControladorCliente;
import entidades.Cliente;
import excepcionesPropias.DatoNoValido;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexander
 */
public class GUIRegistrar extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIRegistrar
     */
    
    private void cargarCodigo(){
        ControladorCliente controlador = new ControladorCliente();
        try {
            lblCodigo.setText(controlador.obtenerNuevoCodigo()+ "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void limpiar(){
        txtNombreCompleto.setText("");
        txtNombreUsuario.setText("");
        txtCelular.setText("");
    }
    
    private void establecerPorDefecto(){
        cargarCodigo();
        limpiar();
        txtNombreCompleto.setFocusable(true);
    }
    
    public GUIRegistrar() {
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

        jPaneRegistrar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        txtNombreCompleto = new javax.swing.JTextField();
        txtNombreUsuario = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        jPaneRegistrar.setBackground(new java.awt.Color(111, 159, 195));
        jPaneRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registro de clientes");
        jPaneRegistrar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 10, 530, -1));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Número de celular:");
        jPaneRegistrar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 210, -1));

        lblCodigo.setBackground(new java.awt.Color(204, 204, 204));
        lblCodigo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodigo.setOpaque(true);
        jPaneRegistrar.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 210, 20));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nombre completo:");
        jPaneRegistrar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 210, -1));

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nombre de usuario:");
        jPaneRegistrar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 210, -1));

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Código (autogenerado) :");
        jPaneRegistrar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 210, -1));
        jPaneRegistrar.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 280, -1));
        jPaneRegistrar.add(txtNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 280, -1));
        jPaneRegistrar.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 280, -1));

        btnRegistrar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseClicked(evt);
            }
        });
        jPaneRegistrar.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 140, 50));

        btnSalir.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        jPaneRegistrar.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 140, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseClicked
        // TODO add your handling code here:
        try {
            Cliente objCliente = new Cliente();
            ControladorCliente controlador = new ControladorCliente();

            objCliente.setNombreCompleto(txtNombreCompleto.getText().trim());
            objCliente.setNombreUsuario(txtNombreUsuario.getText().trim());
            objCliente.setCelular(txtCelular.getText().trim());

            controlador.registrar(objCliente);
            
            JOptionPane.showMessageDialog(this, String.format("Cliente %s registrado exitosamente", objCliente.getNombreCompleto()));
            
            establecerPorDefecto();
        } catch (DatoNoValido | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarMouseClicked

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPaneRegistrar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}