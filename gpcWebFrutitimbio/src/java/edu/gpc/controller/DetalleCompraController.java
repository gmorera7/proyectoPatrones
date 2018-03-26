package edu.gpc.controller;

import edu.gpc.facade.CompraFacade;
import edu.gpc.entities.DetalleCompra;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import edu.gpc.facade.DetalleCompraFacade;
import edu.gpc.facade.ProductoFacade;
import edu.gpc.facade.ProveedorFacade;
import edu.gpc.entities.Compra;
import edu.gpc.entities.Producto;
import edu.gpc.entities.Proveedor;
import edu.gpc.entities.TipoPago;
import edu.gpc.facade.TipoPagoFacade;
import edu.gpc.util.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "detalleCompraController")
@ViewScoped
public class DetalleCompraController implements Serializable {

    private Proveedor provedor;
    private Compra compraSelect;
    private DetalleCompra detalle;
    private DetalleCompra current;
    private DataModel items = null;
    private DetalleCompra detCompraSelect;

    private List<Proveedor> listProvSelect;
    private List<Compra> listCompra;
    private List<Producto> listProducto;
    private List<DetalleCompra> agregaDetCompra;
    private List<TipoPago> listTipoPago;

    //objets 
    private Producto productoSel;
    private Proveedor proveedorFilter;

    //boolean
    private boolean renderedImprimir;

    private Date fechaActual;

    @EJB
    private edu.gpc.facade.CompraFacade ejbFacadeCompra;
    @EJB
    private edu.gpc.facade.ProveedorFacade ejbFacadeProveedor;
    @EJB
    private edu.gpc.facade.DetalleCompraFacade ejbFacade;
    @EJB
    private edu.gpc.facade.ProductoFacade ejbFacadeProducto;
    @EJB
    private TipoPagoFacade ejbFacadeTipoPago;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    // Boolean
    private boolean desocultarTp;

    public DetalleCompraController() {
        agregaDetCompra = new ArrayList<DetalleCompra>();
    }

    @PostConstruct
    public void postConstruct() {

        listTipoPago = ejbFacadeTipoPago.findAll();
        listProvSelect = ejbFacadeProveedor.findAll();
        listProducto = ejbFacadeProducto.findAll();

        obtenerObjectoLista();
    }

    /* *****--------------------------------Inicializar tipo de pago-------------------------------*******/
    public TipoPago obtenerObjectoLista() {
        TipoPago tipoPago = null;
        final String codigoTipoPago = "2";

        for (TipoPago elementTipoPago : listTipoPago) {

            if (elementTipoPago.getCodigo().equals(codigoTipoPago)) {
                System.out.println("elemento en objeto en if:::: " + listTipoPago.size());
                getSelected().getCompraCodigoCompra().setTipoPagoIdtipoPago(elementTipoPago);
            }
        }
        return tipoPago;
    }

    public void agregarCarrito() {
        System.out.print("=== AÑADIR PROBALO");
        // VALIDA AQUI
        if (validarExistencia(getSelected().getProductoCodigo())) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CompraProductoExistenet"));
            limpiar();
        } else {
            final DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setProductoCodigo(getSelected().getProductoCodigo());
            detalleCompra.setCompraCodigoCompra(getSelected().getCompraCodigoCompra());

            detalleCompra.setPrecioUnitario(getSelected().getPrecioUnitario());

            System.out.print("=== precio unitario en agregar carrito:::: " + detalleCompra.getPrecioUnitario());
            detalleCompra.setTotal(getSelected().getTotal());
            detalleCompra.setSubtotal(getSelected().getSubtotal());
            detalleCompra.setCantidad(getSelected().getCantidad());
            agregaDetCompra.add(detalleCompra);

            detalleCompra.getCompraCodigoCompra().setTotal(getSelected().getSubtotal() + detalleCompra.getCompraCodigoCompra().getTotal());
            limpiar();
        }
    }

    private boolean validarExistencia(Producto producto) {
        for (DetalleCompra object : agregaDetCompra) {
            if (object.getProductoCodigo().getCodigo().equals(producto.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public void limpiar() {
        System.out.print("=== ENTRO AL METODO LIMPIAR:::: ");
        getSelected().setProductoCodigo(null);
        getSelected().setCantidad(0);
        getSelected().setPrecioUnitario(0);
        getSelected().setTotal(0);
        getSelected().setSubtotal(0);
        productoSel = null;
    }

    public void calcularPrecio() {
        System.out.print("=== ENTRO AL METODO CALCULAR:::: ");
        getSelected().setSubtotal(getSelected().getCantidad() * getSelected().getPrecioUnitario());
    }

    public void cancelarCompra() {
        System.out.print("=== ENTRO AL METODO LIMPIAR:::: ");
        getSelected().setCompraCodigoCompra(null);
        getSelected().setProductoCodigo(null);
        getSelected().setCantidad(0);
        getSelected().setPrecioUnitario(0);
        getSelected().setTotal(0);
        getSelected().setSubtotal(0);
    }

    //
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    public List<Producto> completeProducto(String query) {
        System.out.print("==== COMPLETE PRODUCT===" + query);
        List<Producto> filteredProducto = new ArrayList<Producto>();

        for (int i = 0; i < listProducto.size(); i++) {
            Producto skin = listProducto.get(i);
            System.out.print("==== TAMAÑO DE LA LISTA PRODUCT DESPUES DE FOR===" + skin.getNombre());

            if (skin.getNombre().toUpperCase().startsWith(query.toUpperCase())) {
                filteredProducto.add(skin);
                System.out.print("==== TAMAÑO DE LA LISTA PRODUCT2===" + filteredProducto.size());
            }
        }
        return filteredProducto;
    }

    // *******---------------------- Guardar Compra ---------------------------------******// 
    public void guardar() {
        if (!agregaDetCompra.isEmpty()) {
            
             System.out.print("NUMERO DE CUOTAS::::: "+ getSelected().getCompraCodigoCompra().getNumeroCuotas());
             System.out.print("VALOR CUOTAS::::: "+ getSelected().getCompraCodigoCompra().getValorCuota());
             
            getSelected().getCompraCodigoCompra().setValorCuota(getSelected().getCompraCodigoCompra().getValorCuota());
            getSelected().getCompraCodigoCompra().setNumeroCuotas(getSelected().getCompraCodigoCompra().getNumeroCuotas());
            getSelected().getCompraCodigoCompra().setTotal(getSelected().getTotal());
            getSelected().getCompraCodigoCompra().setEstadoCompra("C");
            getSelected().getCompraCodigoCompra().setTotal(calcularTotal(agregaDetCompra));
            ejbFacadeCompra.create(getSelected().getCompraCodigoCompra());

            ejbFacade.guardarLista(agregaDetCompra);

            System.out.println(" valor render antes de for:::: " + renderedImprimir);
            for (DetalleCompra det : agregaDetCompra) {
                det.getProductoCodigo().setCantidad(det.getProductoCodigo().getCantidad() + det.getCantidad());
                det.getProductoCodigo().setPrecioCompra(det.getPrecioUnitario());
                if (det.getProductoCodigo().getCantidad() >= 20) {
                    det.getProductoCodigo().setEstado("1");
                }

                ejbFacadeProducto.edit(det.getProductoCodigo());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CompraSatisfactoria"));
                renderedImprimir = true;
                desocultarTp = false;
                limpirCuotaNumeroCuota();
                System.out.println(" valor render despues de guardar :::: " + renderedImprimir);
            }
            System.out.print("==== tamaño lita antes ===" + agregaDetCompra.size());
            agregaDetCompra = new ArrayList<DetalleCompra>();
            getSelected().getCompraCodigoCompra().setTotal(0);
            System.out.print("==== tamaño lita despues ===" + agregaDetCompra == null ? null : "no nulo");
        }
    }
    
    /*
    * limpiar cuotas y num cuotas.
    */
    public void limpirCuotaNumeroCuota(){
        System.out.println(":::: ENTRO A LIMPIAR VALORES :::: ");
         getSelected().getCompraCodigoCompra().setValorCuota(null);
         getSelected().getCompraCodigoCompra().setNumeroCuotas(null);
            
    }

    /**
     * *****---------------------------------------------------------------*******
     */
    private int calcularTotal(List<DetalleCompra> listaDetCompra) {
        int total = 0;
        for (DetalleCompra elemento : listaDetCompra) {
            total += elemento.getSubtotal();
        }
        return total;
    }

    /**
     * *****---------------------------------------------------------------*******
     */
    public void desocultarOpcionesTipoPago() {
        System.out.print("== Codigo " + getSelected().getCompraCodigoCompra().getTipoPagoIdtipoPago().getCodigo());
        if (getSelected().getCompraCodigoCompra().getTipoPagoIdtipoPago().getCodigo().equals("1")) {
            desocultarTp = true;
        } else {
            desocultarTp = false;
        }
        System.out.print("==== desocultar antes===" + desocultarTp);

        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("prueba")
                .getClientId());
    }

    public void eventoTraerProductos() {
        System.out.print("==== fiterProveedor ===" + productoSel); // O CON ESTE
        for (Producto productoSelect : listProducto) {
            System.out.print("==== PRODUCTO ===" + listProducto.size());
            if (productoSelect.getNombre().equals(productoSel.getNombre())) {
                getSelected().setProductoCodigo(productoSelect); // Lo podes hacer con este 
                System.out.print("==== PRODUCTO agregado ===" + productoSelect.getNombre());
                break;
            }
        }
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public void eventoTraerProveedores() {
        System.out.print("==== Proveedor ===" + proveedorFilter);
        for (Proveedor provedorSelect : listProvSelect) {
            if (provedorSelect.getNumeroIdentificacion().equals(proveedorFilter.getNumeroIdentificacion())) {
                getSelected().getCompraCodigoCompra().setProveedorCodigo(provedorSelect);
                break;
            }
        }
    }

    public String prepareView() {
        current = (DetalleCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new DetalleCompra();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleCompraCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (DetalleCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleCompraUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (DetalleCompra) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleCompraDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public List<Proveedor> completeProveedor(String query) {
        List<Proveedor> filteredProveedor = new ArrayList<Proveedor>();
        for (int i = 0; i < listProvSelect.size(); i++) {
            Proveedor skin = listProvSelect.get(i);
            if (skin.getNumeroIdentificacion().startsWith(query)) {
                filteredProveedor.add(skin);
            }
        }
        return filteredProveedor;
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

// ******* elimina los productos agregados a la lista para ser comprados ******// 
    public void eliminarProducto(DetalleCompra detalleCompraSel) {
        System.out.println("Eliminar " + agregaDetCompra.size());
        agregaDetCompra.remove(detalleCompraSel);
        getSelected().getCompraCodigoCompra().setTotal(calcularTotal(agregaDetCompra));
        System.out.println("Eliminar " + agregaDetCompra.size());
    }

    /**
     * *****---------------------------------------------------------------
     *
     *******
     * @return
     */
    public DetalleCompra getSelected() {
        if (current == null) {
            current = new DetalleCompra();
            current.setCompraCodigoCompra(new Compra());
            selectedItemIndex = -1;
        }
        return current;
    }

    private DetalleCompraFacade getFacade() {
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

    public DetalleCompra getDetalleCompra(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public void setEjbFacadeProducto(ProductoFacade ejbFacadeProducto) {
        this.ejbFacadeProducto = ejbFacadeProducto;
    }

    public ProductoFacade getEjbFacadeProducto() {
        return ejbFacadeProducto;
    }

    @FacesConverter(forClass = DetalleCompra.class)
    public static class DetalleCompraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleCompraController controller = (DetalleCompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleCompraController");
            return controller.getDetalleCompra(getKey(value));
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
            if (object instanceof DetalleCompra) {
                DetalleCompra o = (DetalleCompra) object;
                return getStringKey(o.getIddetalleCompra());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleCompra.class.getName());
            }
        }

    }

    public void setProvedor(Proveedor provedor) {
        this.provedor = provedor;
    }

    public Proveedor getProvedor() {
        return provedor;
    }

    public void setCompraSelect(Compra compraSelect) {
        this.compraSelect = compraSelect;
    }

    public Compra getCompraSelect() {
        return compraSelect;
    }

    public void setListCompra(List<Compra> listCompra) {
        this.listCompra = listCompra;
    }

    public List<Compra> getListCompra() {
        return listCompra;
    }

    public void setListProducto(List<Producto> listProducto) {
        this.listProducto = listProducto;
    }

    public List<Producto> getListProducto() {
        return listProducto;
    }

    public void setListProvSelect(List<Proveedor> listProvSelect) {
        this.listProvSelect = listProvSelect;
    }

    public List<Proveedor> getListProvSelect() {
        return listProvSelect;
    }

    public void setEjbFacadeCompra(CompraFacade ejbFacadeCompra) {
        this.ejbFacadeCompra = ejbFacadeCompra;
    }

    public CompraFacade getEjbFacadeCompra() {
        return ejbFacadeCompra;
    }

    public void setEjbFacadeProveedor(ProveedorFacade ejbFacadeProveedor) {
        this.ejbFacadeProveedor = ejbFacadeProveedor;
    }

    public ProveedorFacade getEjbFacadeProveedor() {
        return ejbFacadeProveedor;
    }

    public void setAgregaDetCompra(List<DetalleCompra> agregaDetCompra) {
        agregaDetCompra.add(current);
    }

    public List<DetalleCompra> getAgregaDetCompra() {
        return agregaDetCompra;
    }

    public List<TipoPago> getListTipoPago() {
        return listTipoPago;
    }

    public void setListTipoPago(List<TipoPago> listTipoPago) {
        this.listTipoPago = listTipoPago;
    }

    public DetalleCompra getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleCompra detalle) {
        this.detalle = detalle;
    }

    public boolean isDesocultarTp() {
        return desocultarTp;
    }

    public DetalleCompra getDetCompraSelect() {
        return detCompraSelect;
    }

    public void setDetCompraSelect(DetalleCompra detCompraSelect) {
        this.detCompraSelect = detCompraSelect;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public void setRenderedImprimir(boolean renderedImprimir) {
        this.renderedImprimir = renderedImprimir;
    }

    public boolean isRenderedImprimir() {
        return renderedImprimir;
    }

    public Producto getProductoSel() {
        return productoSel;
    }

    public void setProductoSel(Producto productoSel) {
        this.productoSel = productoSel;
    }

    public void setProveedorFilter(Proveedor proveedorFilter) {
        this.proveedorFilter = proveedorFilter;
    }

    public Proveedor getProveedorFilter() {
        return proveedorFilter;
    }

}
