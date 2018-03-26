package edu.gpc.controller;

import edu.gpc.controller.util.Database;
import edu.gpc.entities.Proveedor;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@ManagedBean(name = "exportProv")
@ViewScoped
public class ExportarProveedorController {

    private String Identificacion;
    Proveedor proveedor;

    private List<Proveedor> proveedores = new ArrayList<Proveedor>();

    public void generarReporte2(Proveedor proveedor) {
        if (proveedor == null) {
            generarReporte(null);
        } else {
            this.proveedor = proveedor;
            generarReporte(proveedor.getNumeroIdentificacion());
        }
    }

    public void generarReporte() {
        generarReporte(Identificacion);
    }

    public void generarReporte(String Identificacion) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/ListadoProveedores.jasper");

            System.out.println(Identificacion + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Identificacion", (Identificacion));
//            parametros.put("prueba", cliente.getNombre());

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                    Database.getConnection());

            JRXlsxExporter exporter = new JRXlsxExporter();
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
            byte[] datosReporte = baos.toByteArray();

            try {
                dscargar(datosReporte, (Identificacion == null) ? "Listado de Proveedores" : Identificacion.toString());

            } catch (IOException ex) {
                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void dscargar(byte[] file, String nomReporte) throws IOException {

        ExternalContext econtext = null;
        HttpServletResponse response = null;
        econtext = FacesContext.getCurrentInstance().getExternalContext();
        response = (HttpServletResponse) econtext.getResponse();
        response.setHeader(
                "Content-disposition",
                "attachment; filename="
                + nomReporte
                + ".xls"
        );
        ServletOutputStream out = response.getOutputStream();
        out.write(file);
        out.flush();
        out.close();
        FacesContext.getCurrentInstance().responseComplete();

    }
    
    public ExportarProveedorController() {
    }

    public List<Proveedor> getProveedores() {
        Proveedor proveedor = new Proveedor();
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
    }

}
