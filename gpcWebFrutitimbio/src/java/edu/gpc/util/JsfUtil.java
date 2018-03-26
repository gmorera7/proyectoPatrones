package edu.gpc.util;

import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static Throwable getRootCause(Throwable cause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    /**
     * Obtiene el id del componente Html y lo actualiza los valores
     *
     * @param id, identificador en el componente DOM
     */
    public static void ejecutarUpdate(String id) {
        final RequestContext context = RequestContext.getCurrentInstance();
        context.update(id);
    }

    /**
     * Busca el componente en el DOM, dependiendo del parametro de entrada
     *
     * @param idComponete, componente de entrada
     * @return {@value UIComponent} Componente encontrado, null caso contrario
     */
    public static UIComponent buscarHtmlComponete(String idComponete) {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (null != context) {
            return buscarHtmlComponete(context.getViewRoot(), idComponete);
        }
        return null;
    }

    /**
     * Busca el componente en el DOM, dependiendo del parametro de entrada
     *
     * @param parent, componente padre {@value UIComponent}
     * @param idComponete, componente de entrada
     * @return {@value UIComponent} Componente encontrado, null caso contrario
     */
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
}
