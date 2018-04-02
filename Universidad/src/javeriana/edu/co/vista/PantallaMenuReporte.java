package javeriana.edu.co.vista;

/**
 *
 * @author javariana.edu.co
 */
public class PantallaMenuReporte extends javax.swing.JFrame {

    private static PantallaMenuReporte instance = null;

    protected PantallaMenuReporte() {
    }

    public static PantallaMenuReporte getInstance() {
        if (instance == null) {
            instance = new PantallaMenuReporte();
            instance.initComponents();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPorcentajes = new javax.swing.JButton();
        btnDepartamentoRelaciones = new javax.swing.JButton();
        btnAbastecedor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPorcentajes.setText("Analista de reportes");
        btnPorcentajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorcentajesActionPerformed(evt);
            }
        });

        btnDepartamentoRelaciones.setText("Departamento Relaciones");
        btnDepartamentoRelaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartamentoRelacionesActionPerformed(evt);
            }
        });

        btnAbastecedor.setText("Abastecedor externo");
        btnAbastecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbastecedorActionPerformed(evt);
            }
        });

        jLabel1.setText("Reportes");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAbastecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDepartamentoRelaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPorcentajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(156, 156, 156))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPorcentajes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbastecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDepartamentoRelaciones)
                    .addComponent(btnRegresar))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPorcentajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorcentajesActionPerformed
        PantallaMenuReporte.getInstance().setVisible(false);
        PantallaReportePorcentaje.getInstance().setVisible(true);
    }//GEN-LAST:event_btnPorcentajesActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        PantallaMenuReporte.getInstance().setVisible(false);
        PantallaAerolinea.getInstance().setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnDepartamentoRelacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepartamentoRelacionesActionPerformed
        PantallaMenuReporte.getInstance().setVisible(false);
        PantallaDepartamentoRelaciones.getInstance().setVisible(true);
    }//GEN-LAST:event_btnDepartamentoRelacionesActionPerformed

    private void btnAbastecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbastecedorActionPerformed
        PantallaMenuReporte.getInstance().setVisible(false);
        PantallaAbastecedor.getInstance().setVisible(true);
    }//GEN-LAST:event_btnAbastecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbastecedor;
    private javax.swing.JButton btnDepartamentoRelaciones;
    private javax.swing.JButton btnPorcentajes;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
