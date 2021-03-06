package javeriana.edu.co.vista;

import javeriana.edu.co.control.ControlPantallaAvion;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javeriana.edu.co.control.IControlPantallaAvion;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.reserva.Reserva;

/**
 *
 * @author javeriana.edu.co
 */
public class PantallaAvion extends javax.swing.JFrame implements Observer, IAccionesPantalla {

    private DefaultTableModel modelo;
    private ArrayList<Reserva> reservasRespuesta;
    private static PantallaAvion instance = null;
    private Aerolinea aerolinea = Aerolinea.getInstance();
    private IControlPantallaAvion controlPantallaAvion = ControlPantallaAvion.getInstance();

    protected PantallaAvion() {
    }

    public static PantallaAvion getInstance() {
        if (instance == null) {
            instance = new PantallaAvion();
            instance.initComponents();
            instance.iniciarComponentes();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConsultar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNumeroVuelo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        jLabel1.setText("Número Vuelo");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VUELO", "RESERVA", "PASAJERO", "SILLA", "COMIDA", "CHECKFOOD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnConsultar)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(26, 26, 26)
                        .addComponent(btnGuardar)
                        .addGap(300, 300, 300))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnConsultar)
                    .addComponent(txtNumeroVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        if (txtNumeroVuelo.getText() != null) {
            controlPantallaAvion.consultarReservasPorVuelo(txtNumeroVuelo.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese el número del vuelo");
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        boolean checkFood;
        String idReserva;
        for (int fila = 0; fila < reservasRespuesta.size(); fila++) {
            checkFood = false;
            idReserva = (String) tabla.getValueAt(fila, 1);
            if (tabla.getValueAt(fila, 5) != null) {
                checkFood = (boolean) tabla.getValueAt(fila, 5);
                controlPantallaAvion.realizarCheckFood(Integer.parseInt(idReserva), checkFood);
            }
        }
        limpiarCampos();
        PantallaAvion.getInstance().setVisible(false);
        PantallaAerolinea.getInstance().setVisible(true);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        PantallaAvion.getInstance().setVisible(false);
        PantallaAerolinea.getInstance().setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNumeroVuelo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {

        Mensaje mensaje = (Mensaje) arg;

        if (mensaje.getAccion().equalsIgnoreCase("busquedaReservasPorVuelo")) {
            modelo = (DefaultTableModel) tabla.getModel();
            reservasRespuesta = (ArrayList<Reserva>) mensaje.getObjeto();

            for (int i = 0; i < reservasRespuesta.size(); i++) {
                String pasajero = reservasRespuesta.get(i).getPersona().getNombreCompleto();
                String vuelo = reservasRespuesta.get(i).getRuta().getNoVuelo();
                String reserva = reservasRespuesta.get(i).getId() + "";
                String numeroSilla = reservasRespuesta.get(i).getNumeroSilla();
                String comida = reservasRespuesta.get(i).getComida().getDescripcion();
                JButton btn = new JButton("CHECKOUT");
                Object row[] = {vuelo, reserva, pasajero, numeroSilla, comida, false};

                modelo.insertRow(0, row);
            }
            tabla.setModel(modelo);
        }
    }

    @Override
    public void iniciarComponentes() {
        aerolinea.addObserver(PantallaAvion.getInstance());
    }

    @Override
    public void limpiarCampos() {
        txtNumeroVuelo.setText("");
        reservasRespuesta = new ArrayList<>();
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "VUELO", "RESERVA", "PASAJERO", "SILLA", "COMIDA", "CHECKFOOD"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
    }
}
