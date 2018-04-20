/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: Clase que representa la vista en un modelo MVC
 */
package vista;

import control.Control;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import modelo.OpenFile;

public class Pantalla extends javax.swing.JFrame {

    private static Pantalla instance = null;
    private JFileChooser fileCoChooser = new JFileChooser();

    protected Pantalla() {
    }

    public static Pantalla getInstance() {
        if (instance == null) {
            instance = new Pantalla();
            instance.initComponents();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnContar = new javax.swing.JButton();
        button1 = new java.awt.Button();
        jButton2 = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Seleccione el directorio del proyecto:");

        btnContar.setText("Contar");
        btnContar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContarActionPerformed(evt);
            }
        });

        button1.setLabel("button1");

        jButton2.setText("jButton2");

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jLabel2.setText("Contador de Código");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(btnSeleccionar)
                .addGap(37, 37, 37)
                .addComponent(btnContar)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnContar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     /**
     * @Descripcion: metodo que activa la accion para contar por medio del control
     * @param evt evento de acccion realizada en pantalla
     */
    private void btnContarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContarActionPerformed
        txtArea.setText("");
        Control.getInstance().contarLineas(OpenFile.getInstance().getFile());
        txtArea.setText(Control.getInstance().imprimirRepuesta());
    }//GEN-LAST:event_btnContarActionPerformed

    /**
     * @Descripcion: metodo encargado de abrir el selector de files
     * @param evt evento de acccion realizada en pantalla
     */
    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        try {
            OpenFile file = OpenFile.getInstance();
            file.PickMe();
        } catch (Exception ex) {
            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContar;
    private javax.swing.JButton btnSeleccionar;
    private java.awt.Button button1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
