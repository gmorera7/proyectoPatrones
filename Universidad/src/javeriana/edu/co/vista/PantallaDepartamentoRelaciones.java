package javeriana.edu.co.vista;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javeriana.edu.co.control.ControlReportes;
import javeriana.edu.co.control.IControlReportes;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import javeriana.edu.co.reportes.Reporte;
import javeriana.edu.co.reportes.ReporteComida;

/**
 *
 * @author javeriana.edu.co
 */
public class PantallaDepartamentoRelaciones extends javax.swing.JFrame implements Observer, AccionesPantalla {

    private DefaultTableModel modelo;
    private DefaultTableModel modelo2;
    private static PantallaDepartamentoRelaciones instance = null;
    private static Reporte reporte = Reporte.getInstance();
    private IControlReportes controlReportes = ControlReportes.getInstance();

    protected PantallaDepartamentoRelaciones() {
    }

    public static PantallaDepartamentoRelaciones getInstance() {
        if (instance == null) {
            instance = new PantallaDepartamentoRelaciones();
            instance.initComponents();
            instance.iniciarComponentes();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFechaInicial = new com.toedter.calendar.JDateChooser();
        txtFechaFinal = new com.toedter.calendar.JDateChooser();
        btnConsultar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        jLabel1.setText("Fecha Inicial");

        jLabel2.setText("Fecha Final");

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NOMBRE", "DIRECCION", "FECHA", "COMIDA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla2);

        btnCancelar.setText("Aceptar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NOMBRE", "DIRECCION", "FECHA", "COMIDA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla);

        jLabel3.setText("pasajero con comida especial no cargada mas de una vez");

        jLabel4.setText("pasajero con calificacion menos que 5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConsultar))
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 492, Short.MAX_VALUE)
                        .addComponent(btnCancelar)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConsultar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(txtFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnCancelar)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        iniciarComponentes();
        if (txtFechaFinal.getDate() != null && txtFechaInicial.getDate() != null) {
            controlReportes.reporte4(txtFechaFinal.getDate(), txtFechaFinal.getDate());
            controlReportes.reporte5(txtFechaFinal.getDate(), txtFechaFinal.getDate());
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione las fechas del formulario");
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        PantallaMenuReporte.getInstance().setVisible(true);
        PantallaDepartamentoRelaciones.getInstance().setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla2;
    private com.toedter.calendar.JDateChooser txtFechaFinal;
    private com.toedter.calendar.JDateChooser txtFechaInicial;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {

        Mensaje mensaje = (Mensaje) arg;

        if (mensaje.getAccion().equalsIgnoreCase("reporte4")) {
            modelo = (DefaultTableModel) tabla.getModel();
            List<ReporteComida> reporte = (List<ReporteComida>) mensaje.getObjeto();

            for (int i = 0; i < reporte.size(); i++) {
                String nombre = reporte.get(i).getNombre();
                String direccion = reporte.get(i).getDireccion();
                Date fecha = reporte.get(i).getFecha();
                String comida = reporte.get(i).getDescripcion();

                Object row[] = {nombre, direccion, fecha, comida};
                modelo.insertRow(0, row);
            }
            tabla.setModel(modelo);
        }

        if (mensaje.getAccion().equalsIgnoreCase("reporte5")) {
            modelo2 = (DefaultTableModel) tabla2.getModel();
            List<ReporteComida> reporte = (List<ReporteComida>) mensaje.getObjeto();

            for (int i = 0; i < reporte.size(); i++) {
                String nombre = reporte.get(i).getNombre();
                String direccion = reporte.get(i).getDireccion();
                Date fecha = reporte.get(i).getFecha();
                String comida = reporte.get(i).getDescripcion();

                Object row[] = {nombre, direccion, fecha, comida};
                modelo2.insertRow(0, row);
            }
            tabla2.setModel(modelo2);
        }
    }

    @Override
    public void iniciarComponentes() {
        reporte.addObserver(PantallaDepartamentoRelaciones.getInstance());
    }

    @Override
    public void limpiarCampos() {
         tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NOMBRE", "DIRECCION", "FECHA", "COMIDA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
         
         tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NOMBRE", "DIRECCION", "FECHA", "COMIDA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
}
