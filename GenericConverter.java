import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Enilson Filho
 */
@FacesConverter("generic")
public class GenericConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null && !"".equals(value)) {
            Sample entity = (Sample) value;
            this.addAttribute(component, entity);
            Long codigo = entity.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
    }

    protected void addAttribute(UIComponent component, Sample o) {
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}
