package edu.gpc.controller;

import edu.gpc.entities.Producto;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import edu.gpc.facade.ProductoFacade;
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

@ManagedBean(name = "productoController")
@ViewScoped
public class ProductoController implements Serializable {

    private Producto productoSelect;
    private Producto productoClone;
    private Producto current;
    private DataModel items = null;

    //Booleans
    private boolean verProducto;
    private boolean renderedForm;

    @EJB
    private edu.gpc.facade.ProductoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    //Lists
    private List<Producto> listProductos;
    private List<Producto> listFiltersProductos;

    @PostConstruct
    public void postConstruct() {
        listProductos = ejbFacade.findAll();
    }

    public void validarVerProducto() {
        System.out.print("== Vr ver producto ants " + verProducto);
        if (productoSelect == null) {
            verProducto = false;
            System.out.print("== Vr ver producto if " + verProducto);
            System.out.print("==  error no selecciono producto ");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DeseleccionProductoVer"));
        } else {
            renderedForm = true;
            verProducto = true;
            //renderedForm = false;
            // JsfUtil.ejecutarUpdate(JsfUtil.buscarHtmlComponete("widViewDialog").getClientId());
            System.out.print("== Vr ver producto else " + verProducto);
        }
    }

    public void prueba() {
        if (productoSelect != null) {
            renderedForm = true;
            System.out.print("==pruebaproducto");
            System.out.print("==pruebaproducto::::" + productoSelect.getCodigo());
            productoClone = new Producto();
            productoClone.setCodigo(productoSelect.getCodigo());
            productoClone.setNombre(productoSelect.getNombre());
            productoClone.setCantidad(productoSelect.getCantidad());
            productoClone.setPrecioCompra(productoSelect.getPrecioCompra());
            productoClone.setPrecioVenta(productoSelect.getPrecioVenta());
            productoClone.setEstado(productoSelect.getEstado());
            productoClone.setGanancia(productoSelect.getGanancia());

            System.out.print("==pruebaproducto clone::::" + productoClone.getCodigo());
        } else {
            System.out.print("==  error no selecciono ");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ViewProductoDeseleccionEditar"));
        }
    }

    public ProductoController() {
    }

    public List<Producto> getListProductos() {
        return listProductos;
    }

    public List<Producto> getListFiltersProductos() {
        return listFiltersProductos;
    }

    public void setListProductos(List<Producto> listProductos) {
        this.listProductos = listProductos;
    }

    public void setListFiltersProductos(List<Producto> listFiltersProductos) {
        this.listFiltersProductos = listFiltersProductos;
    }

    public Producto getSelected() {
        if (current == null) {
            current = new Producto();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProductoFacade getFacade() {
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

    public String prepareList() {
        recreateModel();
        return "listarProductos";
    }

    public String prepareView() {
        current = (Producto) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "detalleProducto";
    }

    public String prepareCreate() {
        current = new Producto();
        selectedItemIndex = -1;
        return "crearProducto";
    }

    public String create() {
        try {
            if (getFacade().existeProducto(current.getNombre())) {
                System.out.println("la identificacion ya existe::: ");
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductoExiste"));
            } else {
                getSelected().setEstado("0");
                getSelected().setPrecioCompra(0);
                getFacade().create(current);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductoCreated"));
            }
            return "listarProductos";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Producto) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "editarProducto";
    }

    public void update() {
        System.out.println("---- entro a actualizar--- " + productoSelect.getCodigo());
        System.out.println("---- entro a actualizar--- " + productoClone.getCodigo());
        try {
            if (productoClone.getCodigo().equals(productoSelect.getCodigo())) {
                System.out.print("== PASO IFFF ");
//                if (getFacade().existeProducto(productoSelect.getNombre())) {
//                    System.out.print("== PASO IFFF2 " + productoSelect.getNombre());
//                    System.out.println("la identificacion ya existe::: ");
//                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductoExiste"));
//                } else {
                    renderedForm = false;
                    getFacade().edit(productoSelect);
                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductoUpdated"));
                    actualizarLista(productoSelect);
                    JsfUtil.ejecutarUpdate(JsfUtil.buscarHtmlComponete("pnlGroupFormularioCategoria").getClientId());

                    productoClone = null;
                    productoSelect = null;
                }

             else {
                System.out.print("== ERROR ");
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        } catch (Exception e) {
            System.out.print("== ERROR ");
        }
    }

    private void actualizarLista(Producto producto) {
        System.out.print("== ACTU");
        for (Producto object : listProductos) {
            System.out.print("== ACTU 1");
            if (object.getCodigo().equals(producto.getCodigo())) {
                System.out.print("== ACTU 2");
                object.setCodigo(producto.getCodigo());
                object.setNombre(producto.getNombre());
                object.setCantidad(producto.getCantidad());
                object.setPrecioCompra(producto.getPrecioCompra());
                object.setPrecioVenta(producto.getPrecioVenta());
                object.setEstado(producto.getEstado());
                object.setGanancia(producto.getGanancia());
            }
        }
    }

    public void cancelarEditProducto() {
        System.out.print("== entro a cancelar producto");
        recreateModel();
        productoSelect = null;
        productoClone = null;
        renderedForm = false;
    }

    public String destroy() {
        current = (Producto) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "listarProductos";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "detalleProducto";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "listarProductos";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductoDeleted"));
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
        return "listarProductos";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "listarProductos";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Producto getProducto(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public Producto getProductoSelect() {
        return productoSelect;
    }

    public void setProductoSelect(Producto productoSelect) {
        this.productoSelect = productoSelect;
    }

    public Producto getProductoClone() {
        return productoClone;
    }

    public void setProductoClone(Producto productoClone) {
        this.productoClone = productoClone;
    }

    public void setRenderedForm(boolean renderedForm) {
        this.renderedForm = renderedForm;
    }

    public boolean isRenderedForm() {
        return renderedForm;
    }

    public void setVerProducto(boolean verProducto) {
        this.verProducto = verProducto;
    }

    public boolean isVerProducto() {
        return verProducto;
    }

    @FacesConverter(forClass = Producto.class)
    public static class ProductoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoController controller = (ProductoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productoController");
            return controller.getProducto(getKey(value));
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
            if (object instanceof Producto) {
                Producto o = (Producto) object;
                return getStringKey(o.getCodigo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Producto.class.getName());
            }
        }

    }

}
