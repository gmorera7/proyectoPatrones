package edu.gpc.controller;

import static edu.gpc.controller.ExportarClienteController.dscargar;
import edu.gpc.controller.util.Database;
import edu.gpc.entities.Compra;
import edu.gpc.entities.Venta;
import edu.gpc.facade.CompraFacade;
import edu.gpc.facade.VentaFacade;
import edu.gpc.util.JsfUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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

@ManagedBean(name = "generarFactura")
@ViewScoped
public class GenerarFacturaController {

    private Long codigo;
    private Long CodigoListaVentas;

    @EJB
    private CompraFacade comFacade;
    @EJB
    private VentaFacade ventFacade;

    private Compra compra;
    private Venta ListaVenta;
    private Venta ventaPorCodigo;

    private List<Compra> compras = new ArrayList<Compra>();

    public GenerarFacturaController() {

    }

//*******************generar factura compra obteniendo ultimo codigo de compra por consulta*********
    public void generarReporte2() {
        if (compra == null) {
//            this.compra = compra;
            Compra com = comFacade.obtenerCodigoCompra();
            if (com == null) {
                System.err.println("NO result exception");
            } else {
                System.out.println("compra no es nulo:::::: " + com.getCodigoCompra());
                generarReporte(Long.valueOf(com.getCodigoCompra()));
            }
        }
    }

    //*******************generar factura compra del listado de compras **************************
    public void generarFactura(Compra compra) {
        if (compra == null) {
            System.out.println("Factura null");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CompraVacia"));
        } else {
            System.out.println("Factura no null");
            this.compra = compra;
            generarReporte(Long.valueOf(compra.getCodigoCompra()));
        }
    }

    //*******************generar factura venta del listado de ventas **************************
    public void generarFacturaVenta(Venta ventas) {
        System.out.println("Factura null");
        if (ventas == null) {
            System.out.println("Factura null");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaVacia"));
        } else {
            System.out.println("Factura no null");
            this.ListaVenta = ventas;
            generarFacturaVentas(Long.valueOf(ventas.getCodigo()));
        }
    }

    //*******************generar factura venta obteniendo ultimo codigo de venta por consulta*********
    public void generarFacturaVentaPorCodigo() {
        if (ventaPorCodigo == null) {
            Venta ventas = new Venta();

            System.out.println("VENTA ES NULL:::::: ");
//            this.compra = compra;
            ventas = ventFacade.obtenerCodigoVenta();
            System.out.println("valor devuelto por consulta::::: " + ventas.getCodigo());

            if (ventas == null) {
                System.err.println("NO result exception");
            } else {
                System.out.println("venta no es nulo:::::: " + ventas.getCodigo());
                generarFacturaVentas(Long.valueOf(ventas.getCodigo()));
            }
        }
    }

    /**
     * ************************* Generar Reporte Principal Venta
     */
    public void generarReporteVenta() {
        generarFacturaVentas(CodigoListaVentas);
    }

    public void generarFacturaVentas(Long codigo) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/jcReportFactura.jasper");
            System.out.println(codigo + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("codigo", (codigo));

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                    Database.getConnection());

            byte[] datosReporte = JasperExportManager.exportReportToPdf(print);
            try {
                dscargar(datosReporte, (codigo == null) ? "todos" : codigo.toString());

            } catch (IOException ex) {
                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * ************************* Generar Reporte Principal
     */
    public void generarReporte() {
        generarReporte(codigo);
    }

    public void generarReporte(Long codigo) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/Factura_Compra.jasper");
            System.out.println(codigo + "inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("codigo", (codigo));

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                    Database.getConnection());

            byte[] datosReporte = JasperExportManager.exportReportToPdf(print);
            try {
                dscargar(datosReporte, (codigo == null) ? "todos" : codigo.toString());

            } catch (IOException ex) {
                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * ************************* Descargar Reporte
     */
    public static void dscargar(byte[] file, String nomReporte) throws IOException {

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

    /**
     * ************************* Getter and Setter
     */
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoListaVentas() {
        return CodigoListaVentas;
    }

    public void setCodigoListaVentas(Long CodigoListaVentas) {
        this.CodigoListaVentas = CodigoListaVentas;
    }

    public VentaFacade getVentFacade() {
        return ventFacade;
    }

    public void setVentFacade(VentaFacade ventFacade) {
        this.ventFacade = ventFacade;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Venta getListaVenta() {
        return ListaVenta;
    }

    public void setListaVenta(Venta ListaVenta) {
        this.ListaVenta = ListaVenta;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public CompraFacade getComFacade() {
        return comFacade;
    }

    public void setComFacade(CompraFacade comFacade) {
        this.comFacade = comFacade;
    }

    public Venta getVentaPorCodigo() {
        return ventaPorCodigo;
    }

    public void setVentaPorCodigo(Venta ventaPorCodigo) {
        this.ventaPorCodigo = ventaPorCodigo;
    }       
}