package javeriana.edu.co.vista;

import java.text.SimpleDateFormat;
import javeriana.edu.co.control.ControlSeleccionVuelo;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import javeriana.edu.co.modelo.busqueda.Busqueda;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.reserva.Ruta;

/**
 *
 * @author javeriana.edu.co
 */
public class PantallaSeleccionVuelo extends javax.swing.JFrame implements Observer, AccionesPantalla {

    private static PantallaSeleccionVuelo instance = null;
    private static Aerolinea aerolinea = Aerolinea.getInstance();
    private Busqueda busqueda;

    protected PantallaSeleccionVuelo() {
    }

    public static PantallaSeleccionVuelo getInstance() {
        if (instance == null) {
            instance = new PantallaSeleccionVuelo();
            instance.initComponents();
            instance.iniciarComponentes();
        }
        return instance;
    }

    @Override
    public void iniciarComponentes() {
        aerolinea.addObserver(PantallaSeleccionVuelo.getInstance());
    }

    @Override
    public void limpiarCampos() {

    }

    public void cargarRutas() {
        ControlSeleccionVuelo.getInstance().buscarRutas(busqueda);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        rutas = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rutas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(rutas);

        jLabel1.setText("Seleccione el vuelo");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(479, Short.MAX_VALUE)
                        .addComponent(btnContinuar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnContinuar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        PantallaSeleccionVuelo.getInstance().setVisible(false);
        PantallaMenuPrincipal.getInstance().setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed

        if (rutas.getSelectedValue() != null) {
            String seleccion = (String) rutas.getSelectedValue();
            System.err.println("ruta " + rutas.getSelectedValue());
            if (rutas.getSelectedValue() != null) {
                String[] parts = seleccion.split("--");

                if (parts != null && parts.length > 0) {
                    PantallaDatosBasicos.getInstance().setIdRuta(Integer.parseInt(parts[1]));
                    PantallaSeleccionVuelo.getInstance().setVisible(false);
                    PantallaDatosBasicos.getInstance().setVisible(true);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un vuelo");
        }

    }//GEN-LAST:event_btnContinuarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList rutas;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the busqueda
     */
    public Busqueda getBusqueda() {
        return busqueda;
    }

    /**
     * @param busqueda the busqueda to set
     */
    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
    }

    @Override
    public void update(Observable o, Object arg) {
        Mensaje mensaje = (Mensaje) arg;
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<Ruta> rutasRespuesta = new ArrayList<>();

        if (mensaje.getAccion().equalsIgnoreCase("busquedaRuta")) {

            rutasRespuesta = (ArrayList<Ruta>) mensaje.getObjeto();

            for (int i = 0; i < rutasRespuesta.size(); i++) {
                Ruta ruta = rutasRespuesta.get(i);

                String cadena = "id --" + ruta.getId() + "-- Origen --" + ruta.getOrigen()
                        + "-- Destino --" + ruta.getDestino() + "--" + " Fecha Salida: " + new SimpleDateFormat("MM-dd-yyyy hh:mm").format(ruta.getFechaSalida());
                model.addElement(cadena);
            }
            rutas.setModel(model);
        }
    }
}
