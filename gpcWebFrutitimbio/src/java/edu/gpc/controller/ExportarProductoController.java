package edu.gpc.controller;

import static edu.gpc.controller.ExportarClienteController.dscargar;
import edu.gpc.controller.util.Database;
import edu.gpc.entities.Producto;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@ManagedBean(name = "expProd")
@ViewScoped
public class ExportarProductoController {

    private Long codigo;
    Producto producto;

    private List<Producto> productos = new ArrayList<Producto>();

    public void generarReporte2(Producto producto) {
        System.out.println("== producto "+(producto == null));
        if (producto == null) {
            generarReporte(null);
        } else {
            this.producto = producto;
            generarReporte(Long.valueOf(producto.getCodigo()));
        }
    }

    public void generarReporte() {
        generarReporte(codigo);
    }
    
        public void generarReporte(Long codigo) {
        try {
            final InputStream inputStreamJR = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream("/resources/jaspers/ListadoProducto.jasper");
            System.out.println(codigo +"inputStreamJR" + inputStreamJR);

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Codigo", (codigo));

            final JasperPrint print = JasperFillManager.fillReport(
                    inputStreamJR, parametros,
                   Database.getConnection());
            
       JRXlsxExporter exporter  =new    JRXlsxExporter();
       java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
		 byte[] datosReporte=	baos.toByteArray();         
            try {
                dscargar(datosReporte,(codigo==null)?"todos":codigo.toString());
         
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

    public ExportarProductoController() {
    }

    public List<Producto> getProductos() {
        Producto producto = new Producto();
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

}


//    public void generarReporte(Long codigo) {
//        try {
//            final InputStream inputStreamJR = FacesContext
//                    .getCurrentInstance()
//                    .getExternalContext()
//                    .getResourceAsStream("/resources/jaspers/ListadoProducto.jasper");
//
//            System.out.println(codigo + "inputStreamJR" + inputStreamJR);
//
//            Map<String, Object> parametros = new HashMap<String, Object>();
//            parametros.put("codigo", Long.valueOf(codigo));
//            parametros.put("prueba", producto.getNombre());
//
//            final JasperPrint print = JasperFillManager.fillReport(
//                    inputStreamJR, parametros,
//                    Database.getConnection());
////
//            JRXlsxExporter exporter = new JRXlsxExporter();
//            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
////			exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 180);
////			exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 40);
//            exporter.exportReport();
//            byte[] datosReporte = baos.toByteArray();
////
////            byte[] datosReporte = JasperExportManager.exportReport(print);
//
//            try {
//                dscargar(datosReporte, (codigo == null) ? "todos" : codigo.toString());
//
//            } catch (IOException ex) {
//                Logger.getLogger(ExportarClienteController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (JRException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

//    public static void dscargar(byte[] file,String nomReporte) throws IOException{
//       
//			ExternalContext econtext = null;
//			HttpServletResponse response = null;
//			econtext = FacesContext.getCurrentInstance().getExternalContext();
//			response = (HttpServletResponse) econtext.getResponse();
//			response.setHeader(
//					"Content-disposition",
//					"attachment; filename="
//							+ nomReporte
//							+ ".pdf"
//							);
//			ServletOutputStream out = response.getOutputStream();
//			out.write(file);
//			out.flush();
//			out.close();
//			FacesContext.getCurrentInstance().responseComplete();
//		
//        } 