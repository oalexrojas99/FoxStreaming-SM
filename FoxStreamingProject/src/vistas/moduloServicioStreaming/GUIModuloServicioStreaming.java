/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.moduloServicioStreaming;

import vistas.moduloCliente.*;
import java.awt.Color;
import java.awt.Font;
import vistas.principal.GUIMenuPrincipal;

/**
 *
 * @author Alexander
 */
public class GUIModuloServicioStreaming extends javax.swing.JFrame {

    /**
     * Creates new form GUIModuloCliente
     */
    public GUIModuloServicioStreaming() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPaneModuloCliente = new javax.swing.JPanel();
        dsktModuloServiciosStreaming = new javax.swing.JDesktopPane();
        lblGenerarPlan = new javax.swing.JLabel();
        lblEliminacionStreaming = new javax.swing.JLabel();
        lblReporteStreaming = new javax.swing.JLabel();
        lblActualizarServicioSteraming = new javax.swing.JLabel();
        lblRegistrarTipoServicio = new javax.swing.JLabel();
        lblInformeDetalladoPlan = new javax.swing.JLabel();
        lblSalir = new javax.swing.JLabel();
        lblFondoModuloServcioStreaming = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPaneModuloCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dsktModuloServiciosStreamingLayout = new javax.swing.GroupLayout(dsktModuloServiciosStreaming);
        dsktModuloServiciosStreaming.setLayout(dsktModuloServiciosStreamingLayout);
        dsktModuloServiciosStreamingLayout.setHorizontalGroup(
            dsktModuloServiciosStreamingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        dsktModuloServiciosStreamingLayout.setVerticalGroup(
            dsktModuloServiciosStreamingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        jPaneModuloCliente.add(dsktModuloServiciosStreaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 800, 580));

        lblGenerarPlan.setBackground(new java.awt.Color(255, 255, 255));
        lblGenerarPlan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGenerarPlan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGenerarPlan.setText("Registro de nuevo plan");
        lblGenerarPlan.setOpaque(true);
        lblGenerarPlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGenerarPlanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGenerarPlanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblGenerarPlanMouseExited(evt);
            }
        });
        jPaneModuloCliente.add(lblGenerarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 300, 50));

        lblEliminacionStreaming.setBackground(new java.awt.Color(255, 255, 255));
        lblEliminacionStreaming.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEliminacionStreaming.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEliminacionStreaming.setText("Eliminación streaming");
        lblEliminacionStreaming.setOpaque(true);
        lblEliminacionStreaming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminacionStreamingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEliminacionStreamingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEliminacionStreamingMouseExited(evt);
            }
        });
        jPaneModuloCliente.add(lblEliminacionStreaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 300, 50));

        lblReporteStreaming.setBackground(new java.awt.Color(255, 255, 255));
        lblReporteStreaming.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblReporteStreaming.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReporteStreaming.setText("Informe general streaming");
        lblReporteStreaming.setOpaque(true);
        lblReporteStreaming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReporteStreamingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblReporteStreamingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblReporteStreamingMouseExited(evt);
            }
        });
        jPaneModuloCliente.add(lblReporteStreaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 300, 50));

        lblActualizarServicioSteraming.setBackground(new java.awt.Color(255, 255, 255));
        lblActualizarServicioSteraming.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblActualizarServicioSteraming.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblActualizarServicioSteraming.setText("Actualización streaming");
        lblActualizarServicioSteraming.setToolTipText("");
        lblActualizarServicioSteraming.setOpaque(true);
        lblActualizarServicioSteraming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblActualizarServicioSteramingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblActualizarServicioSteramingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblActualizarServicioSteramingMouseExited(evt);
            }
        });
        jPaneModuloCliente.add(lblActualizarServicioSteraming, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 300, 50));

        lblRegistrarTipoServicio.setBackground(new java.awt.Color(255, 255, 255));
        lblRegistrarTipoServicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRegistrarTipoServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistrarTipoServicio.setText("Registro streaming");
        lblRegistrarTipoServicio.setOpaque(true);
        lblRegistrarTipoServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarTipoServicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRegistrarTipoServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRegistrarTipoServicioMouseExited(evt);
            }
        });
        jPaneModuloCliente.add(lblRegistrarTipoServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 50));

        lblInformeDetalladoPlan.setBackground(new java.awt.Color(255, 255, 255));
        lblInformeDetalladoPlan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblInformeDetalladoPlan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformeDetalladoPlan.setText("Información detallada de plan");
        lblInformeDetalladoPlan.setOpaque(true);
        lblInformeDetalladoPlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInformeDetalladoPlanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInformeDetalladoPlanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInformeDetalladoPlanMouseExited(evt);
            }
        });
        jPaneModuloCliente.add(lblInformeDetalladoPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 300, 50));

        lblSalir.setBackground(new java.awt.Color(255, 255, 255));
        lblSalir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSalir.setText("Salir al menú principal");
        lblSalir.setOpaque(true);
        lblSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSalirMouseExited(evt);
            }
        });
        jPaneModuloCliente.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 300, 50));
        jPaneModuloCliente.add(lblFondoModuloServcioStreaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneModuloCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneModuloCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblRegistrarTipoServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarTipoServicioMouseClicked
        // TODO add your handling code here:
        this.dsktModuloServiciosStreaming.removeAll();
        GUIRegistrarServicioStreaming guiRegistro = new GUIRegistrarServicioStreaming();
        dsktModuloServiciosStreaming.add(guiRegistro);
        guiRegistro.setVisible(true);
    }//GEN-LAST:event_lblRegistrarTipoServicioMouseClicked

    private void lblRegistrarTipoServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarTipoServicioMouseEntered
        // TODO add your handling code here:
        lblRegistrarTipoServicio.setBackground(new Color(220, 164, 25));
    }//GEN-LAST:event_lblRegistrarTipoServicioMouseEntered

    private void lblRegistrarTipoServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarTipoServicioMouseExited
        // TODO add your handling code here:
        lblRegistrarTipoServicio.setBackground(Color.WHITE);
    }//GEN-LAST:event_lblRegistrarTipoServicioMouseExited

    private void lblActualizarServicioSteramingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizarServicioSteramingMouseClicked
        // TODO add your handling code here:
        this.dsktModuloServiciosStreaming.removeAll();
        GUIActualizarServicioStreaming gui = new GUIActualizarServicioStreaming();
        dsktModuloServiciosStreaming.add(gui);
        gui.setVisible(true);
    }//GEN-LAST:event_lblActualizarServicioSteramingMouseClicked

    private void lblActualizarServicioSteramingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizarServicioSteramingMouseExited
        // TODO add your handling code here:
        lblActualizarServicioSteraming.setBackground(Color.WHITE);
    }//GEN-LAST:event_lblActualizarServicioSteramingMouseExited

    private void lblActualizarServicioSteramingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizarServicioSteramingMouseEntered
        // TODO add your handling code here:
        lblActualizarServicioSteraming.setBackground(new Color(220, 164, 25));
    }//GEN-LAST:event_lblActualizarServicioSteramingMouseEntered

    private void lblEliminacionStreamingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEliminacionStreamingMouseClicked
        // TODO add your handling code here:
        this.dsktModuloServiciosStreaming.removeAll();
        GUIEliminacionServicioStreaming gui = new GUIEliminacionServicioStreaming();
        dsktModuloServiciosStreaming.add(gui);
        gui.setVisible(true);
    }//GEN-LAST:event_lblEliminacionStreamingMouseClicked

    private void lblEliminacionStreamingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEliminacionStreamingMouseEntered
        // TODO add your handling code here:
        lblEliminacionStreaming.setBackground(new Color(220, 164, 25));
    }//GEN-LAST:event_lblEliminacionStreamingMouseEntered

    private void lblEliminacionStreamingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEliminacionStreamingMouseExited
        // TODO add your handling code here:
        lblEliminacionStreaming.setBackground(Color.WHITE);
    }//GEN-LAST:event_lblEliminacionStreamingMouseExited

    private void lblGenerarPlanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenerarPlanMouseClicked
        // TODO add your handling code here:
        this.dsktModuloServiciosStreaming.removeAll();
        GUIGenerarPlan gui = new GUIGenerarPlan();
        dsktModuloServiciosStreaming.add(gui);
        gui.setVisible(true);
        
    }//GEN-LAST:event_lblGenerarPlanMouseClicked

    private void lblGenerarPlanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenerarPlanMouseEntered
        // TODO add your handling code here:
        lblGenerarPlan.setBackground(new Color(220, 164, 25));
    }//GEN-LAST:event_lblGenerarPlanMouseEntered

    private void lblGenerarPlanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenerarPlanMouseExited
        // TODO add your handling code here:
        lblGenerarPlan.setBackground(Color.WHITE);
    }//GEN-LAST:event_lblGenerarPlanMouseExited

    private void lblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalirMouseClicked
        // TODO add your handling code here:
        GUIMenuPrincipal guiPrincipal = new GUIMenuPrincipal();
        this.setVisible(false);
        guiPrincipal.setVisible(true);
    }//GEN-LAST:event_lblSalirMouseClicked

    private void lblSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalirMouseEntered
        // TODO add your handling code here:
        lblSalir.setBackground(new Color(220, 164, 25));
    }//GEN-LAST:event_lblSalirMouseEntered

    private void lblSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalirMouseExited
        // TODO add your handling code here:
        lblSalir.setBackground(Color.WHITE);
    }//GEN-LAST:event_lblSalirMouseExited

    private void lblReporteStreamingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReporteStreamingMouseClicked
        // TODO add your handling code here:
        this.dsktModuloServiciosStreaming.removeAll();
        GUIReporteServiciosStreaming gui = new GUIReporteServiciosStreaming();
        dsktModuloServiciosStreaming.add(gui);
        gui.setVisible(true);
        
    }//GEN-LAST:event_lblReporteStreamingMouseClicked

    private void lblReporteStreamingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReporteStreamingMouseEntered
        // TODO add your handling code here:
        lblReporteStreaming.setBackground(new Color(220, 164, 25));
    }//GEN-LAST:event_lblReporteStreamingMouseEntered

    private void lblReporteStreamingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReporteStreamingMouseExited
        // TODO add your handling code here:
        lblReporteStreaming.setBackground(Color.WHITE);
    }//GEN-LAST:event_lblReporteStreamingMouseExited

    private void lblInformeDetalladoPlanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInformeDetalladoPlanMouseClicked
        // TODO add your handling code here:
        this.dsktModuloServiciosStreaming.removeAll();
        GUIInformeDetalladoPlan gui = new GUIInformeDetalladoPlan();
        dsktModuloServiciosStreaming.add(gui);
        gui.setVisible(true);
    }//GEN-LAST:event_lblInformeDetalladoPlanMouseClicked

    private void lblInformeDetalladoPlanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInformeDetalladoPlanMouseEntered
        // TODO add your handling code here:
        lblInformeDetalladoPlan.setBackground(new Color(220, 164, 25));
    }//GEN-LAST:event_lblInformeDetalladoPlanMouseEntered

    private void lblInformeDetalladoPlanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInformeDetalladoPlanMouseExited
        // TODO add your handling code here:
        lblInformeDetalladoPlan.setBackground(Color.WHITE);
    }//GEN-LAST:event_lblInformeDetalladoPlanMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dsktModuloServiciosStreaming;
    private javax.swing.JPanel jPaneModuloCliente;
    private javax.swing.JLabel lblActualizarServicioSteraming;
    private javax.swing.JLabel lblEliminacionStreaming;
    private javax.swing.JLabel lblFondoModuloServcioStreaming;
    private javax.swing.JLabel lblGenerarPlan;
    private javax.swing.JLabel lblInformeDetalladoPlan;
    private javax.swing.JLabel lblRegistrarTipoServicio;
    private javax.swing.JLabel lblReporteStreaming;
    private javax.swing.JLabel lblSalir;
    // End of variables declaration//GEN-END:variables
}
