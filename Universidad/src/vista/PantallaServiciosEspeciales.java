package vista;

import Mensaje.Mensaje;
import Usuario.Pasajero;
import comida.Comida;
import comida.ComidaEspecial;
import comida.ComidaRegular;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import reserva.Reserva;
import reserva.Ruta;

/**
 *
 * @author javeriana.edu.co
 */
public class PantallaServiciosEspeciales extends javax.swing.JFrame implements AccionesPantalla, Observer {

    private Integer idRuta;
    private Ruta ruta;
    private Reserva reserva;
    private Pasajero pasajero;

    private static PantallaServiciosEspeciales instance = null;

    protected PantallaServiciosEspeciales() {
    }

    public static PantallaServiciosEspeciales getInstance() {
        if (instance == null) {
            instance = new PantallaServiciosEspeciales();
            instance.initComponents();
            instance.iniciarComponentes();
        }
        return instance;
    }

    @Override
    public void limpiarCampos() {
    }

    @Override
    public void iniciarComponentes() {
        txtNumeroSillas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        txtTipoComida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"REGULAR", "ESPECIAL"}));
        txtComidaEspecial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"CHINO", "POLLO"}));
        txtComidaEspecial.setVisible(false);
        jLabel4.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroSillas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtTipoComida = new javax.swing.JComboBox<>();
        btnContinuar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtComidaEspecial = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Servicios Especiales");

        jLabel2.setText("Número Silla");

        txtNumeroSillas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Tipo Comida");

        txtTipoComida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtTipoComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoComidaActionPerformed(evt);
            }
        });

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtComidaEspecial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Comida Especial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btnContinuar)
                .addGap(33, 33, 33)
                .addComponent(btnCancelar)
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtComidaEspecial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumeroSillas, 0, 158, Short.MAX_VALUE)
                    .addComponent(txtTipoComida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumeroSillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipoComida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComidaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        
        Control.ControlServiciosEspeciales.getInstance().consultarRutaPorId(idRuta);

        String comidaSeleccionada = txtTipoComida.getSelectedItem().toString();
        Comida comida = (comidaSeleccionada.equalsIgnoreCase("REGULAR")) ? new ComidaRegular() : new ComidaEspecial();

        reserva = new Reserva();
        reserva.setComida(comida);
        reserva.setEstado("ACTIVA");
        reserva.setFecha(new Date());
        reserva.setNumeroSilla(txtNumeroSillas.getSelectedItem().toString());
        reserva.setPersona(pasajero);
        reserva.setRuta(ruta);

        Control.ControlServiciosEspeciales.getInstance().crearReserva(reserva);
        PantallaServiciosEspeciales.getInstance().setVisible(false);
        PantallaConfirmacionReserva.getInstance().setReserva(reserva);
        PantallaConfirmacionReserva.getInstance().setVisible(true);
        
        
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        PantallaServiciosEspeciales.getInstance().setVisible(false);
        PantallaMenuPrincipal.getInstance().setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTipoComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoComidaActionPerformed
        if (txtTipoComida.getSelectedItem().toString().equalsIgnoreCase("ESPECIAL")) {
            txtComidaEspecial.setVisible(true);
            jLabel4.setVisible(true);
        } else {
            txtComidaEspecial.setVisible(false);
            jLabel4.setVisible(false);

        }
    }//GEN-LAST:event_txtTipoComidaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> txtComidaEspecial;
    private javax.swing.JComboBox<String> txtNumeroSillas;
    private javax.swing.JComboBox<String> txtTipoComida;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the pasajero
     */
    public Pasajero getPasajero() {
        return pasajero;
    }

    /**
     * @param pasajero the pasajero to set
     */
    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    /**
     * @return the idRuta
     */
    public Integer getIdRuta() {
        return idRuta;
    }

    /**
     * @param idRuta the idRuta to set
     */
    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    @Override
    public void update(Observable o, Object arg) {
        Mensaje mensaje = (Mensaje) arg;

        if (mensaje.getAccion().equalsIgnoreCase("busquedaRutaPorId")) {
            ruta = (Ruta) mensaje.getObjeto();
        }

        if (mensaje.getAccion().equalsIgnoreCase("hacerReserva")) {
            reserva = (Reserva) mensaje.getObjeto();
        }
    }
}
