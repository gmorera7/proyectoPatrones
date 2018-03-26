
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


public class ConvertAutocomplete implements Converter{
    

     private static Map<Object, String> entities = new ConcurrentHashMap<Object, String>();

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object entity) {
            System.out.println("CONVERTETR "+entities);
            // TODO : Fix
            if(entity == null)
                return "";

            synchronized (entities) {
                if (!entities.containsKey(entity)) {
                    String uuid = UUID.randomUUID().toString();
                    entities.put(entity, uuid);
                    return uuid;
                } else {
                    return entities.get(entity);
                }
            }
        }

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
            for (Map.Entry<Object, String> entry : entities.entrySet()) {
                if (entry.getValue().equals(uuid)) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }