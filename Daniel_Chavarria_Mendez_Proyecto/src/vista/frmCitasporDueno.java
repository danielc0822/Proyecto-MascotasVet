/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Modelos.DuenoDAO;

/**
 *
 * @author Daniel
 */
public class frmCitasporDueno extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmCitasporDueño
     */
    public frmCitasporDueno() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbSeleccionarCedula = new javax.swing.JComboBox<>();
        lbseleccioneCedula = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDatosCitasDueno = new javax.swing.JTable();
        lbTitulo = new javax.swing.JLabel();
        btBuscar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cbSeleccionarCedula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Cedula" }));
        cbSeleccionarCedula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSeleccionarCedulaItemStateChanged(evt);
            }
        });
        cbSeleccionarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSeleccionarCedulaActionPerformed(evt);
            }
        });

        lbseleccioneCedula.setText("Seleccione la Fecha ");

        TableDatosCitasDueno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cita", "Asunto", "Fecha de Creacion", "Id Dueño", "Id Usuario", "Id Veterinario"
            }
        ));
        TableDatosCitasDueno.setRowMargin(4);
        TableDatosCitasDueno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDatosCitasDuenoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableDatosCitasDueno);

        lbTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbTitulo.setText("Reportes de Citas por Cedula");

        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbseleccioneCedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbSeleccionarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbseleccioneCedula)
                    .addComponent(cbSeleccionarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSeleccionarCedulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSeleccionarCedulaItemStateChanged
        // TODO add your handling code here:
        DuenoDAO cedula = new DuenoDAO();
        cedula.cargarCedula(cbSeleccionarCedula);
    }//GEN-LAST:event_cbSeleccionarCedulaItemStateChanged

    private void cbSeleccionarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSeleccionarCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSeleccionarCedulaActionPerformed

    private void TableDatosCitasDuenoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableDatosCitasDuenoMouseClicked

    }//GEN-LAST:event_TableDatosCitasDuenoMouseClicked

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCitasporDueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCitasporDueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCitasporDueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCitasporDueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCitasporDueno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TableDatosCitasDueno;
    public javax.swing.JButton btBuscar;
    public javax.swing.JComboBox<String> cbSeleccionarCedula;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbseleccioneCedula;
    // End of variables declaration//GEN-END:variables
}
