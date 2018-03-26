import java.util.List;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * permite convertir de un valor de f:selectItems a una entidad para ello la
 * entidad debe tener el atributo id
 *
 * @author Cesar Fajardo
 * @date 17/09/2014
 *
 */
public class ConvertEntity implements Converter {
    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        List<UIComponent> hijos = component.getChildren();
        UISelectItems items = null;
        for (UIComponent uiComponent: hijos) {
            if (uiComponent instanceof UISelectItems) {
                items = (UISelectItems) uiComponent;
                break;
            }
        }
        if (items != null) {
            ValueExpression valueExp = items.getValueExpression("value");
            List<Object> listaValores = (List<Object>) valueExp
                    .getValue(FacesContext.getCurrentInstance().getELContext());
            if (null != listaValores) {
                for (Object object: listaValores) {
                    if (object.toString().equals(value)) {
                        return object;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return (value == null ? null : value.toString());
    }

}