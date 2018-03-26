package edu.gpc.util;

import java.util.Iterator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Rigo
 */
public class Utilidad {
    
    	public final static int CERO=0;
	public final static int UNO=1;
	public final static int DOS=2;	

    public static void buscarHtmlComponente() {

    }
    
/**************************    metodos para login   *******************************/
     public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("username").toString();
      }
       
      public static String getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userid");
        else
            return null;
      }
/**********************************************************************************/

//    public static MethodExpression crearMethodExpression(
//            String valueExpression, Class<?> valueType,
//            Class<?>[] expectedParamTypes) {
//        final FacesContext facesContext = FacesContext.getCurrentInstance();
//        return facesContext
//                .getApplication()
//                .getExpressionFactory()
//                .createMethodExpression(facesContext.getELContext(),
//                        valueExpression, valueType, expectedParamTypes);
//    }

//    public static ValueExpression crearValueExpression(String valueExpression,
//            Class<?> expectedParamTypes) {
//        final FacesContext facesContext = FacesContext.getCurrentInstance();
//        return facesContext
//                .getApplication()
//                .getExpressionFactory()
//                .createValueExpression(facesContext.getELContext(),
//                        valueExpression, expectedParamTypes);
//    }
//
//    public static void ejecutarJavaScript(String js) {
//        final RequestContext context = RequestContext.getCurrentInstance();
//        context.execute(js);
//    }
//
    public static void ejecutarUpdate(String id) {
        final RequestContext context = RequestContext.getCurrentInstance();
        context.update(id);
    }

    public static UIComponent buscarHtmlComponete(String idComponete) {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (null != context) {
            return buscarHtmlComponete(context.getViewRoot(), idComponete);
        }
        return null;
    }

    public static UIComponent buscarHtmlComponete(UIComponent parent,
            String idComponete) {
        if (idComponete.equals(parent.getId())) {
            return parent;
        }
        final Iterator<UIComponent> kids = parent.getFacetsAndChildren();
        while (kids.hasNext()) {
            final UIComponent kid = kids.next();
            final UIComponent found = buscarHtmlComponete(kid, idComponete);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * Funciones que permiten lanzar mensajes del archivo comun
     * 
     * @param mensaje
     *            etiqueta del mensaje a mostrar ejm: comun.label.mensaje.prueba
     * @author Carlos Cepeda
     */
    /**
    public static void lanzaMensajeError(String mensaje) {
        JsfMessageService.displayStaticMessage(
                JsfMessageService.SourceBundle.COMUN,
                JsfMessageService.MessageSeverity.SEVERITY_ERROR, mensaje,
                mensaje);
    }

    public static void lanzaMensajeInfo(String mensaje) {
        JsfMessageService.displayStaticMessage(
                JsfMessageService.SourceBundle.COMUN,
                JsfMessageService.MessageSeverity.SEVERITY_INFO, mensaje,
                mensaje);
    }

    public static void lanzaMensajeWarn(String mensaje) {
        JsfMessageService.displayStaticMessage(
                JsfMessageService.SourceBundle.COMUN,
                JsfMessageService.MessageSeverity.SEVERITY_WARN, mensaje,
                mensaje);
    }
    
    public static boolean validarString(String cadena){
    	if(cadena!=null && !cadena.trim().isEmpty()){
    		return true;
    	}
    	return false;
    }

    **
     * Funcion que permite validar si una entidad u objeto es nulo
     * 
     * @param entidad
     * @return boolean
     *
    public static <T> boolean validaNulos(T entidad) {
        if (entidad == null) {
            return false;
        }
        return true;
    }

    /**
     * Funcion que permite validar si una lista esta vacia
     * 
     * @param lista
     * @param cadena
     * @return
     *
    public static <E> boolean validaVacios(List<E> lista) {
        if (validaNulos(lista)) {
            if (lista.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    /**
     * 
     * @param listaMapa
     * @return
     *
    public static boolean validarMap(Map<String, String> listaMapa){
        if(listaMapa==null){
            return false;
        }
        return true;
    }

    public static String calcularEdad(Date fechaNacimiento) {
        return obtenerEdad(fechaNacimiento, false);
    }

    public static String calcularEdad(Date fechaNacimiento,
            boolean textoCompleto) {
        return obtenerEdad(fechaNacimiento, textoCompleto);
    }

    private static String obtenerEdad(Date fechaNacimiento,
            boolean textoCompleto) {
        final Date fechaActual = new Date();
        int anios = 0;
        int meses = 0;
        int dias = 0;
        if (fechaNacimiento != null) {
            anios = fechaActual.getYear() - fechaNacimiento.getYear();
            meses = fechaActual.getMonth() - fechaNacimiento.getMonth();
            dias = fechaActual.getDate() - fechaNacimiento.getDate();

            if (dias < 0) {
                meses = meses - 1;
                // valida cada mes para sumarle los días
                // meses en los que hay 30 días
                if (fechaNacimiento.getMonth() == 4
                        || fechaNacimiento.getMonth() == 5
                        || fechaNacimiento.getMonth() == 8
                        || fechaNacimiento.getMonth() == 10) {
                    dias = 30 + dias;
                } // febrero
                else if (fechaNacimiento.getMonth() == 1) {
                    if ((fechaActual.getYear() % 4 == 0)
                            && ((fechaActual.getYear() % 100 != 0) || (fechaActual
                                    .getYear() % 400 == 0))) {
                        dias = 29 + dias;
                    } else {
                        dias = 28 + dias;
                    }
                } // meses en los que hay 31 dias
                else {
                    dias = 31 + dias;
                }
            }
            if (meses < 0) {
                anios = anios - 1;
                meses = meses + 12;
            }
        }

        if (textoCompleto) {

            return anios + "A," + meses + "M," + dias + "D";

        } else {
            if (anios > 0) {
                return anios + "A";
            } else if (meses > 0) {
                return meses + "M";
            } else if (dias > 0) {
                return dias + "D";
            } else {
                return "";
            }
        }
    }

    /**
     * Calcula la edad en anios.
     * 
     * @param fechaNacimiento
     * @return la edad en anios
     *
    public static int calcularEdadAnios(Date fechaNacimiento) {
        final Date fechaActual = new Date();
        int anios = 0;
        int meses;
        int dias;
        final Calendar dateNow = Calendar.getInstance();
        dateNow.setTime(fechaActual);
        if (fechaNacimiento == null) {
            Log.info("FECHA ES NULA");
        } else {
            final Calendar fechaNacim = Calendar.getInstance();
            fechaNacim.setTime(fechaNacimiento);
            anios = dateNow.get(Calendar.YEAR) - fechaNacim.get(Calendar.YEAR);
            meses = dateNow.get(Calendar.MONTH)
                    - fechaNacim.get(Calendar.MONTH);
            dias = dateNow.get(Calendar.DATE) - fechaNacim.get(Calendar.DATE);
            if (anios == 0) {
                return anios;
            }

            if (dias < 0) {
                meses = meses - 1;
            }
            if (meses < 0) {
                anios = anios - 1;
                // meses = meses + 12;
            }
        }

        return anios;

    }
    /**
     * Metodo para convertir Date en String
     * @param fecha
     * @return
     *
    public static String convertirFecha(Object fecha) {
        final DateFormat formato = DateFormat.getDateInstance();
        return formato.format(fecha);

    }

    /*
     * Escribe los datos en un archivo dado
     * 
     * @param fichero archivo a escribir los datos
     * 
     * @param bytesArchivoExportado datos del archivo
     * 
     * @throws IOException error si no puede guardar la informacion en el
     * archivo
     *
    public static void escribirDatosEnArchivo(File fichero,
            byte[] bytesArchivoExportado) throws IOException {
        FileOutputStream fos = new FileOutputStream(fichero);
        try {
            fos.write(bytesArchivoExportado);
            fos.flush();
        } finally {
            fos.close();
        }
    }
    public static boolean isValidoMap(Map<String, String> map){
        if(map==null){
            return true;
        }
        return false;
                
    }
    public static <T> boolean esNulo(T entidad){
    	return entidad==null;
    }
    
    /**
     * Valida un cadena de texto es un correo electronico
     * 
     * @param correo
     * @return
     *
    public static boolean esUnCorreoElectronico(String correo) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern
                .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    */
}
