package javeriana.edu.co.vista;

import javeriana.edu.co.control.ControlReportes;
import javeriana.edu.co.control.IControlReportes;

/**
 *
 * @author javeriana.edu.co
 */
public class Encuesta extends javax.swing.JFrame implements AccionesPantalla{
    
    private IControlReportes controlReportes = ControlReportes.getInstance();
    private static Encuesta instance = null;
    
    public Encuesta() {
    }

    public static Encuesta getInstance() {
        if (instance == null) {
            instance = new Encuesta();

            instance.initComponents();
            instance.iniciarComponentes();

        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtReserva = new javax.swing.JTextField();
        txtCalificacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Número Reserva :");

        txtCalificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        txtCalificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalificacionActionPerformed(evt);
            }
        });

        jLabel2.setText("Calificación:");

        jLabel3.setText("Encuesta");

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCalificacion, 0, 132, Short.MAX_VALUE)
                                    .addComponent(txtReserva)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(jLabel3)))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Encuesta.getInstance().setVisible(false);
        PantallaMenuPrincipal.getInstance().setVisible(true);
        limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        controlReportes.registrarEncuesta(Integer.parseInt(txtReserva.getText()), txtCalificacion.getSelectedItem().toString());
        Encuesta.getInstance().setVisible(false);
        PantallaMenuPrincipal.getInstance().setVisible(true);
        limpiarCampos();        
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txtCalificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalificacionActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<String> txtCalificacion;
    private javax.swing.JTextField txtReserva;
    // End of variables declaration//GEN-END:variables

    @Override
    public void iniciarComponentes() {        
    }

    @Override
    public void limpiarCampos() {
        txtReserva.setText("");        
    }
}
