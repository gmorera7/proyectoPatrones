package javeriana.edu.co.vista;

import java.text.SimpleDateFormat;
import javeriana.edu.co.control.ControlCheckIn;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javeriana.edu.co.control.IControlCheckIn;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.reserva.Reserva;
import javeriana.edu.co.reportes.Reporte;

/**
 *
 * @author javeriana.edu.co
 */
public class PantallaCheckIn extends javax.swing.JFrame implements AccionesPantalla, Observer {

    private IControlCheckIn controlCheckIn = ControlCheckIn.getInstance();
    private static PantallaCheckIn instance = null;
    private Reserva reserva;
    private Aerolinea aerolinea = Aerolinea.getInstance();

    protected PantallaCheckIn() {
    }

    public static PantallaCheckIn getInstance() {
        if (instance == null) {
            instance = new PantallaCheckIn();
            instance.initComponents();
            instance.iniciarComponentes();
        }
        return instance;
    }

    @Override
    public void iniciarComponentes() {
        aerolinea.addObserver(Reporte.getInstance());
        aerolinea.addObserver(PantallaCheckIn.getInstance());
        //  txtNombrePasajero.setVisible(false);
        //  txtDestino.setVisible(false);
        //  txtOrigen.setVisible(false);
        //  txtFecha.setVisible(false);
        btnHacerCheck.setEnabled(false);
    }

    @Override
    public void limpiarCampos() {
        txtNumeroReserva.setText("");
        txtDestino.setText("");
        txtOrigen.setText("");
        txtNumeroReserva.setText("");
        txtNombrePasajero.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroReserva = new javax.swing.JTextField();
        bntConsultar = new javax.swing.JButton();
        btnHacerCheck = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtOrigen = new javax.swing.JTextField();
        txtNombrePasajero = new javax.swing.JTextField();
        txtDestino = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CHECK IN");

        jLabel2.setText("Número Reserva");

        bntConsultar.setText("Consultar");
        bntConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntConsultarActionPerformed(evt);
            }
        });

        btnHacerCheck.setText("Hacer Check");
        btnHacerCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHacerCheckActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtOrigen.setToolTipText("");

        jLabel3.setText("Origen");

        jLabel4.setText("Detino");

        jLabel5.setText("Nombre Pasajero");

        jLabel6.setText("Fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHacerCheck)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumeroReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bntConsultar)))
                .addGap(207, 207, 207))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDestino)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombrePasajero)
                    .addComponent(txtFecha))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntConsultar)
                    .addComponent(txtNumeroReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtNombrePasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHacerCheck)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHacerCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHacerCheckActionPerformed
        controlCheckIn.hacerCheckIn(reserva.getId(), true);
        PantallaCheckIn.getInstance().setVisible(false);
        PantallaMenuPrincipal.getInstance().setVisible(true);
        limpiarCampos();

    }//GEN-LAST:event_btnHacerCheckActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        PantallaCheckIn.getInstance().setVisible(false);
        PantallaMenuPrincipal.getInstance().setVisible(true);
        limpiarCampos();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void bntConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntConsultarActionPerformed

        if (txtNumeroReserva.getText() != null) {
            String numeroReserva = txtNumeroReserva.getText();
            controlCheckIn.consultarReserva(Integer.parseInt(numeroReserva));
            if (reserva != null) {
                txtNombrePasajero.setText(reserva.getPersona().getNombreCompleto());
                txtFecha.setText(new SimpleDateFormat("MM-dd-yyyy hh:mm").format(reserva.getFecha()));
                txtOrigen.setText(reserva.getRuta().getOrigen());
                txtDestino.setText(reserva.getRuta().getDestino());

                txtNombrePasajero.setVisible(true);
                txtDestino.setVisible(true);
                txtOrigen.setVisible(true);
                txtFecha.setVisible(true);
                btnHacerCheck.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro valores");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese el número de reserva");
        }
    }//GEN-LAST:event_bntConsultarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntConsultar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnHacerCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombrePasajero;
    private javax.swing.JTextField txtNumeroReserva;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        Mensaje mensaje = (Mensaje) arg;

        if (mensaje.getAccion().equalsIgnoreCase("buscarReserva")) {
            reserva = (Reserva) mensaje.getObjeto();
        }
    }
}
