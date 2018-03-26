package edu.gpc.controller;

import edu.gpc.controller.util.Database;
import edu.gpc.util.Utilidad;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean(name = "generadorReportes")
@ViewScoped

public class GeneradorReportesController {

    private Date fechaDesde;
    private Date fechaHasta;
    private String campoPrueba;

    private boolean desocultarPanelRepFechas = false;
    private boolean desocultarPanelRepFechasComp = false;
    private boolean desocultarPanelRepMayorCompra = false;
    private boolean desocultarPanelRepMayorVenta = false;

    public GeneradorReportesController() {
    }

    public void desocultarPanelVentasPorFecha() {
        System.out.println("Entro a cambiar boolean:::::: ");
        System.out.println("desocultarPanelRepFechas antes::::: " + desocultarPanelRepFechas);
        desocultarPanelRepFechas = true;
        desocultarPanelRepFechasComp = false;
        desocultarPanelRepMayorCompra = false;
        desocultarPanelRepMayorVenta = false;
        
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasVentas").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasPanel").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasMayor").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasMayorVenta").getClientId());
    
        System.out.println("desocultarPanelRepFechas despues::::: " + desocultarPanelRepFechas);
    }

    public void desocultarPanelComprasPorFecha() {
        System.out.println("Entro a cambiar boolean compras:::::: ");
        System.out.println("desocultarPanelRepFechasComp antes::::: " + desocultarPanelRepFechas);
        desocultarPanelRepFechasComp = true;
        desocultarPanelRepFechas = false;
        desocultarPanelRepMayorCompra = false;
        desocultarPanelRepMayorVenta = false;

        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasVentas").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasPanel").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasMayor").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasMayorVenta").getClientId());
    
        System.out.println("desocultarPanelRepFechasComp despues::::: " + desocultarPanelRepFechasComp);

    }

    public void desocultarPanelMayorCompraPorFecha() {
        System.out.println("Entro a cambiar boolean compras:::::: ");
        System.out.println("desocultarPanelRepFechas antes::::: " + desocultarPanelRepFechas);
        desocultarPanelRepMayorCompra = true;
        desocultarPanelRepFechasComp = false;
        desocultarPanelRepFechas = false;
        desocultarPanelRepMayorVenta = false;

        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasVentas").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasPanel").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasMayor").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasMayorVenta").getClientId());
    
    }

    public void desocultarPanelMayorVentaPorFecha() {
        System.out.println("Entro a cambiar boolean compras:::::: ");
        System.out.println("desocultarPanelRepFechas antes::::: " + desocultarPanelRepFechas);
        desocultarPanelRepMayorVenta = true;
        desocultarPanelRepFechasComp = false;
        desocultarPanelRepFechas = false;
        desocultarPanelRepMayorCompra = false;
    
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasVentas").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasPanel").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasComprasMayor").getClientId());
        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("rangoFechasMayorVenta").getClientId());
            
    }

    public GeneradorReportesController(Date fechaDesde, Date fechaHasta) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public void generarReporte2(Date fechaInicio, Date fechaFin) {
        this.fechaDesde = fechaInicio;
        this.fechaHasta = fechaFin;

        System.out.println("fecha desde:::: " + fechaDesde);
        System.out.println("fecha hasta:::: " + fechaHasta);
        generarReporte(fechaInicio, fechaFin);
    }

    public void generarReporte() {
        generarReporte(fechaDesde, fechaHasta);
    }

    public void generarReporte(Date fechaInicio, Date fechaFin) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/reporteVentas.jasper");

            System.out.println(fechaInicio + "inputStreamJR" + inputStreamJR);
            System.out.println(fechaFin + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fecha_inicio", (fechaInicio));
            parametros.put("fecha_fin", (fechaFin));

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                    Database.getConnection());
            byte[] datosReporte = JasperExportManager.exportReportToPdf(print);

            try {
                descargar(datosReporte, ("Listado de Ventas"));

            } catch (IOException ex) {
                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //--------generar factura compras
    public void generarReporteCompras2(Date fechaInicio, Date fechaFin) {
        this.fechaDesde = fechaInicio;
        this.fechaHasta = fechaFin;

        System.out.println("fecha desde:::: " + fechaDesde);
        System.out.println("fecha hasta:::: " + fechaHasta);
        generarReporteCompras(fechaInicio, fechaFin);
    }

    public void generarReporteCompras() {
        generarReporteCompras(fechaDesde, fechaHasta);
    }

    public void generarReporteCompras(Date fechaInicio, Date fechaFin) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/reporteCompras.jasper");

            System.out.println(fechaInicio + "inputStreamJR" + inputStreamJR);
            System.out.println(fechaFin + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fecha_inicio", (fechaInicio));
            parametros.put("fecha_fin", (fechaFin));

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                    Database.getConnection());
            byte[] datosReporte = JasperExportManager.exportReportToPdf(print);

            try {
                descargar(datosReporte, ("Listado de Compras"));

            } catch (IOException ex) {
                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------
    //--------generar factura compras
    public void generarReporteMayorCompra2(Date fechaInicio, Date fechaFin) {
        this.fechaDesde = fechaInicio;
        this.fechaHasta = fechaFin;

        System.out.println("fecha desde:::: " + fechaDesde);
        System.out.println("fecha hasta:::: " + fechaHasta);
        generarReporteMayorCompra(fechaInicio, fechaFin);
    }

    public void generarReporteMayorCompra() {
        generarReporteMayorCompra(fechaDesde, fechaHasta);
    }

    public void generarReporteMayorCompra(Date fechaInicio, Date fechaFin) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/reporteMayorCompra.jasper");

            System.out.println(fechaInicio + "inputStreamJR" + inputStreamJR);
            System.out.println(fechaFin + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fecha_inicio", (fechaInicio));
            parametros.put("fecha_fin", (fechaFin));

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                    Database.getConnection());
            byte[] datosReporte = JasperExportManager.exportReportToPdf(print);

            try {
                descargar(datosReporte, ("Listado de Mayores Compras"));

            } catch (IOException ex) {
                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void generarReporteMayorVenta2(Date fechaInicio, Date fechaFin) {
        this.fechaDesde = fechaInicio;
        this.fechaHasta = fechaFin;

        System.out.println("fecha desde:::: " + fechaDesde);
        System.out.println("fecha hasta:::: " + fechaHasta);
        generarReporteMayorVenta(fechaInicio, fechaFin);
    }

    public void generarReporteMayorVenta() {
        generarReporteMayorVenta(fechaDesde, fechaHasta);
    }

    public void generarReporteMayorVenta(Date fechaInicio, Date fechaFin) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/reporteMayorVentas.jasper");

            System.out.println(fechaInicio + "inputStreamJR" + inputStreamJR);
            System.out.println(fechaFin + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fecha_inicio", (fechaInicio));
            parametros.put("fecha_fin", (fechaFin));

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                    Database.getConnection());
            byte[] datosReporte = JasperExportManager.exportReportToPdf(print);

            try {
                descargar(datosReporte, ("Listado de Mayores Ventas"));

            } catch (IOException ex) {
                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void descargar(byte[] file, String nomReporte) throws IOException {

        ExternalContext econtext = null;
        HttpServletResponse response = null;
        econtext = FacesContext.getCurrentInstance().getExternalContext();
        response = (HttpServletResponse) econtext.getResponse();
        response.setHeader(
                "Content-disposition",
                "attachment; filename="
                + nomReporte
                + ".pdf"
        );
        ServletOutputStream out = response.getOutputStream();
        out.write(file);
        out.flush();
        out.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    //get y set
    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getCampoPrueba() {
        return campoPrueba;
    }

    public void setCampoPrueba(String campoPrueba) {
        this.campoPrueba = campoPrueba;
    }

    public boolean isDesocultarPanelRepFechas() {
        return desocultarPanelRepFechas;
    }

    public void setDesocultarPanelRepFechas(boolean desocultarPanelRepFechas) {
        this.desocultarPanelRepFechas = desocultarPanelRepFechas;
    }

    public boolean isDesocultarPanelRepFechasComp() {
        return desocultarPanelRepFechasComp;
    }

    public void setDesocultarPanelRepFechasComp(boolean desocultarPanelRepFechasComp) {
        this.desocultarPanelRepFechasComp = desocultarPanelRepFechasComp;
    }

    public boolean isDesocultarPanelRepMayorVenta() {
        return desocultarPanelRepMayorVenta;
    }

    public void setDesocultarPanelRepMayorVenta(boolean desocultarPanelRepMayorVenta) {
        this.desocultarPanelRepMayorVenta = desocultarPanelRepMayorVenta;
    }

    public boolean isDesocultarPanelRepMayorCompra() {
        return desocultarPanelRepMayorCompra;
    }

    public void setDesocultarPanelRepMayorCompra(boolean desocultarPanelRepMayorCompra) {
        this.desocultarPanelRepMayorCompra = desocultarPanelRepMayorCompra;
    }

}
