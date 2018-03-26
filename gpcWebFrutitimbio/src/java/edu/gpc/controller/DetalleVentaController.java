package edu.gpc.controller;

import edu.gpc.entities.DetalleVenta;
import edu.gpc.controller.util.JsfUtil;
import edu.gpc.controller.util.PaginationHelper;
import edu.gpc.entities.Cliente;
import edu.gpc.entities.Producto;
import edu.gpc.entities.TipoPago;
import edu.gpc.entities.Venta;
import edu.gpc.facade.DetalleVentaFacade;
import edu.gpc.facade.VentaFacade;
import edu.gpc.util.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
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

@ManagedBean(name = "detalleVentaController")
@ViewScoped
public class DetalleVentaController implements Serializable {

    private Cliente cliente;
    private Venta ventaPrueba;
    private DetalleVenta detalle;
    private DetalleVenta current;
    private DataModel items = null;

    private List<Cliente> listClientSelect;
    private List<Venta> listVenta;
    private List<Producto> listProducto;
    private List<DetalleVenta> agregaDetVenta;
    private List<TipoPago> listTipoPago;

    // Booleans
    private boolean desocultarTp;
    private boolean desocultarImprimirFactura;

    //objets
    private Cliente clienteFilter;
    private Producto productoFilter;

    private Integer numeroCuotas;
    private Integer valorCuota;

    @EJB
    private edu.gpc.facade.DetalleVentaFacade ejbFacade;
    @EJB
    private edu.gpc.facade.ProductoFacade ejbFacadeProducto;
    @EJB
    private edu.gpc.facade.VentaFacade ejbFacadeVenta;
    @EJB
    private edu.gpc.facade.ClienteFacade ejbFacadeCliente;
    @EJB
    private edu.gpc.facade.TipoPagoFacade ejbFacadeTipoPago;

    @PostConstruct
    public void postConstruct() {
        listClientSelect = ejbFacadeCliente.findAll();
        listProducto = ejbFacadeProducto.findAll();
        listTipoPago = ejbFacadeTipoPago.findAll();

        obtenerObjectoLista();
    }

    private DetalleVentaFacade getFacade() {
        return ejbFacade;
    }

    /* *****--------------------------------Inicializar tipo de pago-------------------------------*******/
    public TipoPago obtenerObjectoLista() {
        TipoPago tipoPago = null;
        final String codigoTipoPago = "2";

        for (TipoPago elementTipoPago : listTipoPago) {

            if (elementTipoPago.getCodigo().equals(codigoTipoPago)) {
                System.out.println("elemento en objeto en if:::: " + listTipoPago.size());
                getSelected().getVentaCodigo().setTipoPagoIdtipoPago(elementTipoPago);
            }
        }
        return tipoPago;
    }

    public List<Cliente> completeCliente(String query) {
        System.out.print("==== COMPLETE CLIENTE===");
        List<Cliente> filteredProveedor = new ArrayList<Cliente>();
        for (int i = 0; i < listClientSelect.size(); i++) {
            Cliente skin = listClientSelect.get(i);
            if (skin.getNit().toString().startsWith(query)) {
                filteredProveedor.add(skin);
            }
        }
        return filteredProveedor;
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

    public String onFlowProcess(FlowEvent event) {
        cliente = new Cliente();
        return event.getNewStep();
    }

    public void desocultarOpcionesTipoPago() {
        System.out.print("== Codigo " + getSelected().getVentaCodigo().getTipoPagoIdtipoPago().getCodigo());
        if (getSelected().getVentaCodigo().getTipoPagoIdtipoPago().getCodigo().equals("1")) {
            desocultarTp = true;
        } else {
            desocultarTp = false;
        }
        System.out.print("==== desocultar antes===" + desocultarTp);

        Utilidad.ejecutarUpdate(Utilidad.buscarHtmlComponete("prueba")
                .getClientId());
    }

    public void eventoTraerProductos() {
        System.out.print("==== PRODUCTO ===" + productoFilter);
        for (Producto productoSelect : listProducto) {
            System.out.print("==== PRODUCTO ===" + listProducto.size());
            if (productoSelect.getNombre().equals(productoFilter.getNombre())) {
                getSelected().setProductoCodigo(productoSelect);
                System.out.print("==== PRODUCTO agregado ===" + productoSelect.getNombre());
                break;
            }
        }
    }

    public void eventoTraerClientes() {
        System.out.print("==== Proveedor ===" + clienteFilter);
        for (Cliente provedorSelect : listClientSelect) {
            if (provedorSelect.getNit().equals(clienteFilter.getNit())) {
                getSelected().getVentaCodigo().setClienteNit(provedorSelect);
                break;
            }
        }
    }

    public void calcularPrecio() {
//        System.out.print("==== esta en calcular precio ====" + getSelected().getProductoCodigo().getPrecioVenta());
//        if (getSelected().getCantidad() <= getSelected().getProductoCodigo().getCantidad()) {
        if (getSelected().getProductoCodigo() == null) {
            edu.gpc.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaSinPrecio"));
            System.out.print("==== esta en calcular precio ====");

        } else {
            getSelected().setSubtotal(getSelected().getCantidad() * getSelected().getProductoCodigo().getPrecioVenta());
            edu.gpc.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaSatisfactoria"));
            System.out.print("==== esta en calcular precio ====");
        }

    }

    public void eliminarProducto(DetalleVenta detalleVentaSel) {
        System.out.println("Eliminar " + agregaDetVenta.size());
        agregaDetVenta.remove(detalleVentaSel);
        getSelected().getVentaCodigo().setTotal(calcularTotal(agregaDetVenta));
        System.out.println("Eliminar " + agregaDetVenta.size());
    }

    public void guardar() {
        if (!agregaDetVenta.isEmpty()) {
            System.out.println(":::: Numero de Cuotas::: " + getSelected().getVentaCodigo().getNumeroCuotas());
            System.out.println(":::: Valor de las Cuotas::: " + getSelected().getVentaCodigo().getValorCuota());
//            System.out.println(":::: Total::: " + getSelected().getVentaCodigo().getTotal());

            getSelected().getVentaCodigo().setNumeroCuotas(getSelected().getVentaCodigo().getNumeroCuotas());
            getSelected().getVentaCodigo().setValorCuota(getSelected().getVentaCodigo().getValorCuota());
            getSelected().getVentaCodigo().setTotal(getSelected().getVentaCodigo().getTotal());
            getSelected().getVentaCodigo().setEstadoVenta("V");
            getSelected().getVentaCodigo().setTotal(calcularTotal(agregaDetVenta));
            ejbFacadeVenta.create(getSelected().getVentaCodigo());
            System.out.println("tamaño de la lista a guardar::: " + agregaDetVenta.size());

            ejbFacade.guardarLista(agregaDetVenta);

            for (DetalleVenta det : agregaDetVenta) {
                det.getProductoCodigo().setCantidad(det.getProductoCodigo().getCantidad() - det.getCantidad());
                System.out.println("precio de de la venta::: " + det.getProductoCodigo().getPrecioVenta());
                getSelected().setPrecioUnitario(det.getProductoCodigo().getPrecioVenta());

                System.out.println("estado del producto antes del if::: " + det.getProductoCodigo().getEstado());
                if (det.getProductoCodigo().getCantidad() <= 20) {
                    det.getProductoCodigo().setEstado("0");
                    edu.gpc.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductoEstado") + det.getProductoCodigo().getNombre() + " se encuentra agotado");
                    System.out.println("estado del producto en if========= " + det.getProductoCodigo().getEstado());
                }
                System.out.println("estado del producto despues de if========= " + det.getProductoCodigo().getEstado());
                ejbFacadeProducto.edit(det.getProductoCodigo());
                edu.gpc.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaSatisfactoria"));
                desocultarImprimirFactura = true;
            }
            System.out.print("==== tamaño lita antes ===" + agregaDetVenta.size());
            agregaDetVenta = new ArrayList<DetalleVenta>();
            getSelected().getVentaCodigo().setTotal(0);
            desocultarTp = false;
            System.out.print("==== tamaño lita despues ===" + agregaDetVenta == null ? null : "no nulo");
            limpiarValoresTipoPago();
            agregaDetVenta = null;
        }
    }

    /*
     metodo para limpiar cuotas y numero e cuotas
     */
    public void limpiarValoresTipoPago() {
        System.out.println(":::: ENTRO A LIMPIAR ::: ");
        getSelected().getVentaCodigo().setValorCuota(null);
        getSelected().getVentaCodigo().setNumeroCuotas(null);
//        System.out.println(":::: Total::: " + ventaPrueba.getTotal());
    }

    private int calcularTotal(List<DetalleVenta> listaDetVenta) {
        int total = 0;
        for (DetalleVenta elemento : listaDetVenta) {
            total += elemento.getSubtotal();
        }
        return total;
    }

    public void agregarCarrito() {
        if (validarExistencia(getSelected().getProductoCodigo())) {
            edu.gpc.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentaProductoExistenet"));
            limpiar();
        } else {
            if (getSelected().getCantidad() <= getSelected().getProductoCodigo().getCantidad()) {
                System.out.println("esta en if::::");
                System.out.print("=== AÑADIR ");
                final DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setProductoCodigo(getSelected().getProductoCodigo());
                detalleVenta.setVentaCodigo(getSelected().getVentaCodigo());
                detalleVenta.setPrecioUnitario(getSelected().getProductoCodigo().getPrecioVenta());
                System.out.print("=== precio unitario en agragar a carrito " + detalleVenta.getPrecioUnitario());
                detalleVenta.setSubtotal(getSelected().getSubtotal());
                detalleVenta.setCantidad(getSelected().getCantidad());
                agregaDetVenta.add(detalleVenta);
                detalleVenta.getVentaCodigo().setTotal(getSelected().getSubtotal() + detalleVenta.getVentaCodigo().getTotal());
                limpiar();
            } else {
                System.out.print("=== ENTRO AL ELSE:::: ");
                edu.gpc.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductoCantidadAgotado"));
                final DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setProductoCodigo(getSelected().getProductoCodigo());

                limpiar();
                limpiarObjeto();
                System.out.print("=== ENTRO AL ELSE:::: ");
            }
        }
    }

    public void limpiarObjeto() {
        System.out.print("=== ENTRO A LIMPIAR OBJETO:::: ");
        final DetalleVenta detalleVenta = new DetalleVenta();
    }

    private boolean validarExistencia(Producto producto) {
        for (DetalleVenta object : agregaDetVenta) {
            if (object.getProductoCodigo().getCodigo().equals(producto.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public void limpiar() {
        System.out.print("=== ENTRO AL METODO LIMPIAR VENTA:::: ");
        getSelected().setProductoCodigo(null);
        getSelected().setCantidad(0);
        getSelected().setPrecioUnitario(0);
//        getSelected().setTotal(0);
        getSelected().setSubtotal(0);
        productoFilter = null;
    }

    public void cancelarVenta() {
        System.out.print("=== ENTRO AL METODO CANCELAR VENTA:::: ");
        getSelected().setProductoCodigo(null);
        getSelected().setVentaCodigo(null);
        getSelected().setCantidad(0);
        getSelected().setPrecioUnitario(0);
        getSelected().setSubtotal(0);
        productoFilter = null;
    }

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public DetalleVentaController() {
        agregaDetVenta = new ArrayList<DetalleVenta>();
        cliente = new Cliente();
    }

    public DetalleVenta getSelected() {
        if (current == null) {
            current = new DetalleVenta();
            current.setVentaCodigo(new Venta());
            selectedItemIndex = -1;
        }
        return current;
    }

    public String prepareView() {
        current = (DetalleVenta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new DetalleVenta();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle2").getString("DetalleVentaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (DetalleVenta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle2").getString("DetalleVentaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            return null;
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public DetalleVenta getDetalleVenta(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = DetalleVenta.class)
    public static class DetalleVentaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleVentaController controller = (DetalleVentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleVentaController");
            return controller.getDetalleVenta(getKey(value));
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
            if (object instanceof DetalleVenta) {
                DetalleVenta o = (DetalleVenta) object;
                return getStringKey(o.getIdDetalleVenta());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleVenta.class.getName());
            }
        }

    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setDetalle(DetalleVenta detalle) {
        this.detalle = detalle;
    }

    public DetalleVenta getDetalle() {
        return detalle;
    }

    public void setListClientSelect(List<Cliente> listClientSelect) {
        this.listClientSelect = listClientSelect;
    }

    public List<Cliente> getListClientSelect() {
        return listClientSelect;
    }

    public void setListProducto(List<Producto> listProducto) {
        this.listProducto = listProducto;
    }

    public List<Venta> getListVenta() {
        return listVenta;
    }

    public void setAgregaDetVenta(List<DetalleVenta> agregaDetVenta) {
        this.agregaDetVenta = agregaDetVenta;
    }

    public List<DetalleVenta> getAgregaDetVenta() {
        return agregaDetVenta;
    }

    public List<Producto> getListProducto() {
        return listProducto;
    }

    public void setDesocultarTp(boolean desocultarTp) {
        this.desocultarTp = desocultarTp;
    }

    public boolean isDesocultarTp() {
        return desocultarTp;
    }

    public void setDesocultarImprimirFactura(boolean desocultarImprimirFactura) {
        this.desocultarImprimirFactura = desocultarImprimirFactura;
    }

    public boolean isDesocultarImprimirFactura() {
        return desocultarImprimirFactura;
    }

    public VentaFacade getEjbFacadeVenta() {
        return ejbFacadeVenta;
    }

    public void setEjbFacadeVenta(VentaFacade ejbFacadeVenta) {
        this.ejbFacadeVenta = ejbFacadeVenta;
    }

    public List<TipoPago> getListTipoPago() {
        return listTipoPago;
    }

    public void setListTipoPago(List<TipoPago> listTipoPago) {
        this.listTipoPago = listTipoPago;
    }

    public Cliente getClienteFilter() {
        return clienteFilter;
    }

    public void setClienteFilter(Cliente clienteFilter) {
        this.clienteFilter = clienteFilter;
    }

    public Producto getProductoFilter() {
        return productoFilter;
    }

    public void setProductoFilter(Producto productoFilter) {
        this.productoFilter = productoFilter;
    }

    public Venta getVentaPrueba() {
        return ventaPrueba;
    }

    public void setVentaPrueba(Venta ventaPrueba) {
        this.ventaPrueba = ventaPrueba;
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public Integer getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(Integer valorCuota) {
        this.valorCuota = valorCuota;
    }

}
