package edu.gpc.controller;

import edu.gpc.entities.Compra;
import edu.gpc.entities.TipoPago;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import edu.gpc.facade.CompraFacade;
import edu.gpc.entities.Proveedor;
import edu.gpc.facade.TipoPagoFacade;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "compraController")
@ViewScoped
public class CompraController implements Serializable {

    private Proveedor provedor;
    private Compra compraSelect;
    private Compra current;
    private DataModel items = null;
    private TipoPago tipoPago;

    private boolean desocultarTp;

    private List<Proveedor> listProvSelect;
    private List<Compra> listCompra;
    private List<Compra> compraFiltros;
    private List<TipoPago> listTipoPago;
    private List<TipoPago> listTipoPagoPrueba;

    @EJB
    private edu.gpc.facade.CompraFacade ejbFacade;
    @EJB
    private edu.gpc.facade.ProveedorFacade ejbFacadeProveedor;
    @EJB
    private TipoPagoFacade ejbFacadeTipoPago;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CompraController() {
//        compraSelect = new Compra();
    }

    @PostConstruct
    public void postConstruct() {
        System.out.print("==== postconstruct antes===");
        listProvSelect = ejbFacadeProveedor.findAll();
        listTipoPago = ejbFacadeTipoPago.findAll();
}

    private CompraFacade getFacade() {
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

    public void eventoTraerProveedores() {
        System.out.print("==== Proveedor ===" + (current.getProveedorCodigo() == null ? null : "NO NULL"));
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
        current = (Compra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Compra();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CompraCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Compra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CompraUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Compra) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CompraDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

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

    public void onProductoSelect(SelectEvent event) {
        compraSelect = (Compra) event.getObject();
        System.out.println("selectedProducto = " + compraSelect.getCodigoCompra());
    }

    public void onProductoUnselect(UnselectEvent event) {
        compraSelect = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Compra getCompra(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Compra.class)
    public static class CompraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CompraController controller = (CompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "compraController");
            return controller.getCompra(getKey(value));
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
            if (object instanceof Compra) {
                Compra o = (Compra) object;
                return getStringKey(o.getCodigoCompra());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Compra.class.getName());
            }
        }

    }

    public List<Compra> getListCompra() {
        return listCompra;
    }

    public void setListCompra(List<Compra> listCompra) {
        this.listCompra = listCompra;
    }

    public Compra getCompraSelect() {
        return compraSelect;
    }

    public void setCompraSelect(Compra compraSelect) {
        this.compraSelect = compraSelect;
    }

    public Proveedor getProvedor() {
        return provedor;
    }

    public void setProvedor(Proveedor provedor) {
        this.provedor = provedor;
    }

    public Compra getSelected() {
        if (current == null) {
            current = new Compra();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void stateChangeListener(ValueChangeEvent event) {
        System.out.println("cambio de lista ");
        if (event.getNewValue() != current) {
            current = null;
        }
    }

    public void metodoPrueba() {
        System.out.println("Entro al metodo prueba despues de seleccionar un proveedor ");
    }

    public List<Proveedor> getListProvSelect() {
        return listProvSelect;
    }

    public void setListProvSelect(List<Proveedor> listProvSelect) {
        this.listProvSelect = listProvSelect;
    }

    public List<TipoPago> getListTipoPago() {
        return listTipoPago;
    }

    public void setListTipoPago(List<TipoPago> listTipoPago) {
        this.listTipoPago = listTipoPago;
    }

    public void setDesocultarTp(boolean desocultarTp) {
        this.desocultarTp = desocultarTp;
    }

    public boolean isDesocultarTp() {
        return desocultarTp;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<Compra> getCompraFiltros() {
        return compraFiltros;
    }

    public void setCompraFiltros(List<Compra> compraFiltros) {
        this.compraFiltros = compraFiltros;
    }

    public List<TipoPago> getListTipoPagoPrueba() {
        return listTipoPagoPrueba;
    }

    public void setListTipoPagoPrueba(List<TipoPago> listTipoPagoPrueba) {
        this.listTipoPagoPrueba = listTipoPagoPrueba;
    }
}
