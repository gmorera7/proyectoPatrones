package edu.gpc.controller;

import edu.gpc.controller.util.Database;
import edu.gpc.entities.Cliente;
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

@ManagedBean(name = "listaClientes")
@ViewScoped
public class ExportarClienteController {

    private Long nit;
    Cliente cliente;

    private List<Cliente> clientes = new ArrayList<Cliente>();

    public void generarReporte2(Cliente cliente) {
        if (cliente == null) {
            generarReporte(null);
        } else {
            this.cliente = cliente;
            generarReporte(Long.valueOf(cliente.getNit()));
        }
    }

    public void generarReporte() {
        generarReporte(nit);
    }

    public void generarReporte(Long nit) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/ListadoClientes.jasper");

            System.out.println(nit + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nit", (nit));
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
                dscargar(datosReporte, (nit == null) ? "Listado de Clientes" : nit.toString());

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

    public ExportarClienteController() {
    }

    public List<Cliente> getClientes() {
        Cliente cliente = new Cliente();
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

}

//				exporter = new JRPdfExporter();
//	
//				exporter = new JRHtmlExporter();
//			
//				exporter = new JRXlsExporter();
//			
//				exporter = new JRTextExporter();
//			
//				exporter = new JRCsvExporter();
//			
//				exporter = new JRDocxExporter();

