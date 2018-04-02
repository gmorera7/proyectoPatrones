package javeriana.edu.co.vista;

import javeriana.edu.co.control.ControlMenuPrinicipal;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import javeriana.edu.co.modelo.busqueda.Busqueda;
import java.util.Observable;
import java.util.Observer;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;

/**
 *
 * @author javeriana.edu.co
 */
public class PantallaMenuPrincipal extends javax.swing.JFrame implements Observer, AccionesPantalla {

    private static PantallaMenuPrincipal instance = null;
    private Aerolinea aerolinea = Aerolinea.getInstance();

    protected PantallaMenuPrincipal() {
    }

    public static PantallaMenuPrincipal getInstance() {
        if (instance == null) {
            instance = new PantallaMenuPrincipal();
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
        aerolinea.addObserver(PantallaMenuPrincipal.getInstance());
        ControlMenuPrinicipal.getInstance().cargarCatalogosCiudadesOrigen();
        ControlMenuPrinicipal.getInstance().cargarCatalogosCiudadesDestino();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel2 = new javax.swing.JLabel();
        btnCheckIn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        listaOrigen = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        listaDestino = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        fechaViaje = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        btnEncuesta = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCheckIn.setText("CheckIn");
        btnCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInActionPerformed(evt);
            }
        });

        jLabel1.setText("Gastronomic AIR");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        listaOrigen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listaOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaOrigenActionPerformed(evt);
            }
        });

        jLabel3.setText("Origen");

        jLabel4.setText("Destino");

        listaDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Fecha Viaje");

        jButton1.setText("Aerolinea");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnEncuesta.setText("Encuesta");
        btnEncuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncuestaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jButton1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(btnConsultar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCheckIn)
                                .addGap(18, 18, 18)
                                .addComponent(btnEncuesta))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(fechaViaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(133, 133, 133))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(156, 156, 156)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(listaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(listaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(fechaViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckIn)
                    .addComponent(btnConsultar)
                    .addComponent(jButton1)
                    .addComponent(btnEncuesta))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Busqueda busqueda = new Busqueda();
        busqueda.setOrigen(listaOrigen.getSelectedItem().toString());
        busqueda.setDestino(listaDestino.getSelectedItem().toString());
        busqueda.setFecha(fechaViaje.getDate());

        this.setVisible(false);
        PantallaSeleccionVuelo.getInstance().setBusqueda(busqueda);
        PantallaSeleccionVuelo.getInstance().cargarRutas();
        PantallaSeleccionVuelo.getInstance().setVisible(true);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void listaOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listaOrigenActionPerformed

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        PantallaMenuPrincipal.getInstance().setVisible(false);
        PantallaCheckIn.getInstance().setVisible(true);
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PantallaMenuPrincipal.getInstance().setVisible(false);
        PantallaAerolinea.getInstance().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEncuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncuestaActionPerformed
        Encuesta.getInstance().setVisible(true);
        PantallaMenuPrincipal.getInstance().setVisible(false);
    }//GEN-LAST:event_btnEncuestaActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEncuesta;
    private com.toedter.calendar.JDateChooser fechaViaje;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JComboBox listaDestino;
    private javax.swing.JComboBox listaOrigen;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable observable, Object arg) {
        Mensaje mensaje = (Mensaje) arg;

        if (mensaje.getAccion().equalsIgnoreCase("cargarCiudadesOrigen")) {
            listaOrigen.setModel(new javax.swing.DefaultComboBoxModel((String[]) mensaje.getObjeto()));
        }

        if (mensaje.getAccion().equalsIgnoreCase("cargarCiudadesDestino")) {
            listaDestino.setModel(new javax.swing.DefaultComboBoxModel((String[]) mensaje.getObjeto()));
        }
    }
}
