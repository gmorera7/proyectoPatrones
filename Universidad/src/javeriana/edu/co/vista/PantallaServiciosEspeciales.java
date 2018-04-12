package javeriana.edu.co.vista;

import javeriana.edu.co.modelo.mensaje.Mensaje;
import javeriana.edu.co.modelo.usuario.Pasajero;
import javeriana.edu.co.modelo.comida.Comida;
import javeriana.edu.co.modelo.comida.ComidaRegular;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javeriana.edu.co.control.ControlServiciosEspeciales;
import javeriana.edu.co.control.IServiciosEspeciales;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.comida.FabricaComidaEspecial;
import javeriana.edu.co.modelo.comida.FabricaComidaEspecialAbstracta;
import javeriana.edu.co.modelo.reserva.Reserva;
import javeriana.edu.co.modelo.reserva.Ruta;
import javeriana.edu.co.modelo.usuario.Persona;

/**
 *
 * @author javeriana.edu.co
 */
public class PantallaServiciosEspeciales extends javax.swing.JFrame implements IAccionesPantalla, Observer {

    private IServiciosEspeciales controlServiciosEspeciales = ControlServiciosEspeciales.getInstance();
    private static FabricaComidaEspecialAbstracta fabricaComidaEspecial = FabricaComidaEspecial.getInstance();
    private static Aerolinea aerolinea = Aerolinea.getInstance();
    private Integer idRuta;
    private Ruta ruta;
    private Reserva reserva;
    private Persona pasajero;

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
        txtComidaEspecial.setVisible(false);
        jLabel4.setVisible(false);
    }

    @Override
    public void iniciarComponentes() {
        aerolinea.addObserver(PantallaServiciosEspeciales.getInstance());
        controlServiciosEspeciales.cargarMenuComidaEspecial();
        controlServiciosEspeciales.cargarMenuTipoComida();
        controlServiciosEspeciales.cargarNumeroSillas();
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

        jLabel3.setText("Tipo Comida");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addGap(151, 151, 151))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTipoComida, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroSillas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComidaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
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

        controlServiciosEspeciales.consultarRutaPorId(idRuta);
        String comidaSeleccionada = txtTipoComida.getSelectedItem().toString();
        Comida comida = (comidaSeleccionada.equalsIgnoreCase("REGULAR")) ? new ComidaRegular() : fabricaComidaEspecial.crearComidaPorDescripcion(txtComidaEspecial.getSelectedItem().toString());
        if (comida instanceof ComidaRegular) {
            comida.setDescripcion("COMIDA REGULAR");
        }
        reserva = new Reserva();
        reserva.setComida(comida);
        reserva.setFecha(new Date());
        reserva.setNumeroSilla(txtNumeroSillas.getSelectedItem().toString());
        reserva.setPersona(pasajero);
        reserva.setRuta(ruta);

        controlServiciosEspeciales.crearReserva(reserva);
        PantallaServiciosEspeciales.getInstance().limpiarCampos();
        PantallaServiciosEspeciales.getInstance().setVisible(false);
        PantallaConfirmacionReserva.getInstance().setReserva(reserva);
        PantallaConfirmacionReserva.getInstance().setVisible(true);

        limpiarCampos();
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
    public Persona getPasajero() {
        return pasajero;
    }

    /**
     * @param pasajero the pasajero to set
     */
    public void setPasajero(Persona pasajero) {
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

        if (mensaje.getAccion().equalsIgnoreCase("cargarMenuNumeroSillas")) {
            txtNumeroSillas.setModel(new javax.swing.DefaultComboBoxModel((String[]) mensaje.getObjeto()));
        }

        if (mensaje.getAccion().equalsIgnoreCase("cargarMenuTipoComida")) {
            txtTipoComida.setModel(new javax.swing.DefaultComboBoxModel((String[]) mensaje.getObjeto()));
        }

        if (mensaje.getAccion().equalsIgnoreCase("cargarMenuComidaEspecial")) {
            txtComidaEspecial.setModel(new javax.swing.DefaultComboBoxModel((String[]) mensaje.getObjeto()));
        }
    }
}
