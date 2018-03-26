package edu.gpc.controller;

import edu.gpc.entities.Cliente;
import edu.gpc.entities.Venta;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import edu.gpc.facade.VentaFacade;
import edu.gpc.util.Utilidad;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "ventaController")
@ViewScoped
public class VentaController implements Serializable {

    private Venta current;
    private DataModel items = null;

    private boolean desocultarTp;

    private List<Cliente> listClientSelect;
    private List<Venta> ventaFltros;
    private Venta ventaSelect;

    @EJB
    private edu.gpc.facade.VentaFacade ejbFacade;
    @EJB
    private edu.gpc.facade.ClienteFacade ejbFacadeCliente;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public VentaController() {
    }

    @PostConstruct
    public void postConstruct() {
        System.out.print("==  postCOn Venta");
        listClientSelect = ejbFacadeCliente.findAll();
    }

    public Venta getSelected() {
        if (current == null) {
            current = new Venta();
            selectedItemIndex = -1;
        }
        return current;
    }

    private VentaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public void desocultarOpcionesTipoPago() {
        System.out.print("== Codigo " + getSelected().getTipoPagoIdtipoPago().getCodigo());
        if (getSelected().getTipoPagoIdtipoPago().getCodigo().equals("1")) {
            desocultarTp = true;
        } else {
            desocultarTp = false;
        }
        System.out.print("==== desocultar antes===" + desocultarTp);

        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("prueba")
                .getClientId());
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Venta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Venta();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Venta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Venta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
//      public void onProductoSelect(SelectEvent event) {
//        ventaSelect = (Venta) event.getObject();
////        System.out.println("selectedProducto = " + ventaSelect.());
//    }
//
//    public void onProductoUnselect(UnselectEvent event) {
//        ventaSelect = null;
//    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Venta getVenta(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Venta.class)
    public static class VentaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VentaController controller = (VentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ventaController");
            return controller.getVenta(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Venta) {
                Venta o = (Venta) object;
                return getStringKey(o.getCodigo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Venta.class.getName());
            }
        }

    }

    public List<Cliente> getListClientSelect() {
        return listClientSelect;
    }

    public void setListClientSelect(List<Cliente> listClientSelect) {
        this.listClientSelect = listClientSelect;
    }

    public void setDesocultarTp(boolean desocultarTp) {
        this.desocultarTp = desocultarTp;
    }

    public boolean isDesocultarTp() {
        return desocultarTp;
    }

    public List<Venta> getVentaFltros() {
        return ventaFltros;
    }

    public void setVentaFltros(List<Venta> ventaFltros) {
        this.ventaFltros = ventaFltros;
    }

    public Venta getVentaSelect() {
        return ventaSelect;
    }

    public void setVentaSelect(Venta ventaSelect) {
        this.ventaSelect = ventaSelect;
    }
}
