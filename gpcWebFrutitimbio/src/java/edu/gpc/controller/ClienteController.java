package edu.gpc.controller;

import edu.gpc.entities.Cliente;
import edu.gpc.entities.Departamentos;
import edu.gpc.entities.Municipio;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import edu.gpc.facade.ClienteFacade;
import edu.gpc.facade.DepartamentosFacade;
import edu.gpc.facade.MunicipioFacade;
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
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "clienteController")
@ViewScoped
public class ClienteController implements Serializable {

    private Cliente clienteSelect;
    private Cliente current;
    private Cliente clienteClone;
    private Cliente clienteDesact;
    private Departamentos departamentos;
    private DataModel items = null;
    @EJB
    private ClienteFacade ejbFacade;
    @EJB
    private MunicipioFacade munFacade;
    @EJB
    private DepartamentosFacade deptosFacade;

    JasperPrint jasperPrint;

    private PaginationHelper pagination;
    private int selectedItemIndex;

//lists
    private List<Cliente> listClientes;
    private List<Cliente> listClientesInactivos;
    private List<Cliente> ListClientesFilters;
    private List<Municipio> ListMunicipios;
    private List<Municipio> ListMunicipiosUpdate;
    private List<Departamentos> ListDeptos;

    //Booleans
    private boolean verClient;
    private boolean renderedForm;

    @PostConstruct
    public void postConstruct() {
        listClientes = ejbFacade.listaCliente();
        listClientesInactivos = ejbFacade.listaClientesInactivos();
        ListDeptos = deptosFacade.findAll();
        current = new Cliente();
        current.setMunicipio(new Municipio());
        current.getMunicipio().setDepartamentoIddepartamento(new Departamentos());
    }

       public void onClienteSelect(SelectEvent event) {
        clienteSelect = (Cliente) event.getObject();
        System.out.println("selectedProducto = " + clienteSelect.getNit());
    }
       
       public void onClienteUnselect(UnselectEvent event) {
           clienteSelect=null;
       }
    public void eventoTraerMunicipios() {
        System.out.print("==== municipio 3===" + (getSelected().getMunicipio().getDepartamentoIddepartamento() == null ? null : "NO NULL"));
        if (getSelected().getMunicipio().getDepartamentoIddepartamento() != null) {
            ListMunicipios = munFacade.listarMunicipiosPorDepartamento(getSelected().getMunicipio().getDepartamentoIddepartamento());
        }
    }  

    public ClienteController() {
        clienteSelect = new Cliente();
    }

    public void validarVerCliente() {
        System.out.print("== Vr ver Cliente ants " + verClient);
        if (clienteSelect == null) {
            verClient = false;
            System.out.print("== Vr ver Cliente if " + verClient);
            System.out.print("==  error no selecciono ");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DeseleccionClienteVer"));
        } else {
            renderedForm = true;
            verClient = true;
            //renderedForm = false;
            // JsfUtil.ejecutarUpdate(JsfUtil.buscarHtmlComponete("widViewDialog").getClientId());
            System.out.print("== Vr ver Cliente else " + verClient);
        }
    }
    
     public void eventoTraerMunicipiosUpdate() {
        if (clienteSelect.getMunicipio().getDepartamentoIddepartamento() != null) {
            ListMunicipiosUpdate = munFacade.listarMunicipiosPorDepartamento(clienteSelect.getMunicipio().getDepartamentoIddepartamento());
        }
    }

    public void prepareEditar() {
        if (clienteSelect != null) {
            renderedForm = true;
            System.out.print("==  PRUEBA ");
            clienteClone = new Cliente();
            clienteClone.setNit(clienteSelect.getNit());
            clienteClone.setNombre(clienteSelect.getNombre());
            clienteClone.setRepresentanteLegal(clienteSelect.getRepresentanteLegal());
            clienteClone.setDepartamento(clienteSelect.getDepartamento());
            clienteClone.setMunicipio(clienteSelect.getMunicipio());
            clienteClone.setDireccion(clienteSelect.getDireccion());
            clienteClone.setTipoTelefono(clienteSelect.getTipoTelefono());
            clienteClone.setNumeroTelefono(clienteSelect.getNumeroTelefono());
            clienteClone.setEmail(clienteSelect.getEmail());
            clienteClone.setEstado(clienteSelect.getEstado());
                        
            eventoTraerMunicipiosUpdate();
            
            clienteSelect.setMunicipio(
                    obtenerObjectoLista(clienteSelect.getMunicipio()));

            System.out.print("==  PRUEBA ?" + clienteClone.getNit());
            System.out.print("==  PRUEBA " + renderedForm);
        } else {
            System.out.print("==  error no selecciono ");
            System.out.print("==  PRUEBA else rederform::: " + renderedForm);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DeseleccionCliente"));
        }
    }
    
    public Municipio obtenerObjectoLista(Municipio municipio) {
        for (Municipio elementMunicipio : ListMunicipiosUpdate) {
            if (elementMunicipio.getIdmunicipio() == municipio.getIdmunicipio()) {
                municipio = elementMunicipio;
                for (Departamentos elementDepartamento : ListDeptos) {
                    if (municipio.getDepartamentoIddepartamento().getIddepartamento() == elementDepartamento.getIddepartamento()) {
                        municipio.setDepartamentoIddepartamento(elementDepartamento);
                    }
                }
            }
        }
        return municipio;
    }

    public void prepararDesactivar(Cliente c) {
        if (clienteSelect != null) {
            clienteDesact = new Cliente();
            clienteDesact.setNit(clienteSelect.getNit());
        } else {
            System.out.print("==  error no selecciono ");
        }
    }

    public void cancelarEditCliente() {
        System.out.print("== entro a cancelar");
        clienteSelect = null;
        clienteClone = null;
        renderedForm = false;
    }

    //Getter and Setter
    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public void setListClientesFilters(List<Cliente> ListClientesFilters) {
        this.ListClientesFilters = ListClientesFilters;
    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public List<Cliente> getListClientesFilters() {
        return ListClientesFilters;
    }

    private ClienteFacade getFacade() {
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

    public Cliente getSelected() {
        if (current == null) {
            current = new Cliente();
            selectedItemIndex = -1;
        }
        return current;
    }

    public String prepareList() {
        recreateModel();
        return "listaclientes";
    }

    public String prepareView() {
        current = (Cliente) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "detallecliente";
    }

    public String prepareCreate() {
        current = new Cliente();
        selectedItemIndex = -1;
        return "crearcliente";
    }

    public String create() {
        try {
            System.out.println("VALOR EXISTE usuario::::. " + getFacade().existeNit(current.getNit()));
            if ((getFacade().existeNit(current.getNit())) && (getFacade().existeNumeroTelefono(current.getNumeroTelefono()))) {
                System.out.println("entro a numero telefono y nit iguales existe::::. ");
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteNitTelefonoExiste"));

            } else {
                if (getFacade().existeNumeroTelefono(current.getNumeroTelefono())) {
                    System.out.println("entro a numero telefono existe::::. ");
                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteTelefonoExiste"));

                } else {
                    if (getFacade().existeNit(current.getNit())) {
                        System.out.println("entro a nit existe::::. ");
                        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteNitExiste"));

                    } else {
                        System.out.println("entro a crear usuario::::. ");
                        getFacade().create(current);
                        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
                        return "listaclientes";
                    }
                }
            }

            return "crearcliente";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Cliente) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "editarcliente";
    }

    public void update() {
        System.out.println("---- entro a actualizar--- " + clienteSelect.getNit());
        System.out.println("---- entro a actualizar--- " + clienteClone.getNit());
        try {
            if (clienteClone.getNit().equals(clienteSelect.getNit())) {
                renderedForm = false;
                getFacade().edit(clienteSelect);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteUpdated"));
                actualizarLista(clienteSelect);
                JsfUtil.ejecutarUpdate(JsfUtil.buscarHtmlComponete("pnlGroupFormularioClientes").getClientId());
                clienteSelect = null;
                clienteClone = null;
            } else {
              JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteNoActualizado"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void updateEstadoAct(Cliente cliente) {
        if (clienteSelect != null) {
            System.out.print("== ACTU ESTADO");
            cliente.setEstado("0");
            getFacade().updateEstadoAct(cliente);
            listClientes.remove(cliente);
            System.out.print("== ACTU ESTADO " + cliente);
        } else {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteInabilado"));
        }
    }

    public void updateEstadoActInact(Cliente cliente2) {
        if (clienteSelect != null) {
            System.out.print("== ACTU ESTADO");
            cliente2.setEstado("1");
            getFacade().updateEstadoActInac(cliente2);
            listClientesInactivos.remove(cliente2);
            System.out.print("== ACTU ESTADO " + cliente2);
        } else {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteActivar"));
        }
    }

    private void actualizarLista(Cliente cliente) {
        System.out.print("== ACTU");
        for (Cliente object : listClientes) {
            System.out.print("== ACTU 1");
            if (object.getNit().equals(cliente.getNit())) {
                System.out.print("== ACTU 2");
                object.setNit(cliente.getNit());
                object.setNit(cliente.getNit());
            }
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
        return "listaclientes";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "listaclientes";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Cliente getCliente(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Cliente.class)
    public static class ClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClienteController controller = (ClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clienteController");
            return controller.getCliente(getKey(value));
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
            if (object instanceof Cliente) {
                Cliente o = (Cliente) object;
                return getStringKey(o.getNit());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cliente.class.getName());
            }
        }

    }

    public Cliente getClienteSelect() {
        return clienteSelect;
    }

    public void setClienteSelect(Cliente clienteSelect) {
        this.clienteSelect = clienteSelect;
    }

    public void setVerClient(boolean verClient) {
        this.verClient = verClient;
    }

    public boolean isverClient() {
        return verClient;
    }

    public boolean isRenderedForm() {
        return renderedForm;
    }

    public void setRenderedForm(boolean renderedForm) {
        this.renderedForm = renderedForm;
    }

    public void setClienteClone(Cliente clienteClone) {
        this.clienteClone = clienteClone;
    }

    public Cliente getClienteClone() {
        return clienteClone;
    }

    public void setClienteDesact(Cliente clienteDesact) {
        this.clienteDesact = clienteDesact;
    }

    public Cliente getClienteDesact() {
        return clienteDesact;
    }

    public List<Cliente> getListClientesInactivos() {
        return listClientesInactivos;
    }

    public void setListClientesInactivos(List<Cliente> listClientesInactivos) {
        this.listClientesInactivos = listClientesInactivos;
    }

    public MunicipioFacade getMunFacade() {
        return munFacade;
    }

    public void setMunFacade(MunicipioFacade munFacade) {
        this.munFacade = munFacade;
    }

    public List<Municipio> getListMunicipios() {
        return ListMunicipios;
    }

    public void setListMunicipios(List<Municipio> ListMunicipios) {
        this.ListMunicipios = ListMunicipios;
    }

    public List<Departamentos> getListDeptos() {
//        if(getSelected() != null){
//            ListDeptos =  (List<Departamentos>) getSelected().getMunicipio().getDepartamentoIddepartamento();
//        }else{
//            return ListDeptos;
//        }
        return ListDeptos;
    }

    public void setListDeptos(List<Departamentos> ListDeptos) {
        this.ListDeptos = ListDeptos;
    }

    public DepartamentosFacade getDeptosFacade() {
        return deptosFacade;
    }

    public void setDeptosFacade(DepartamentosFacade deptosFacade) {
        this.deptosFacade = deptosFacade;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public List<Municipio> getListMunicipiosUpdate() {
        return ListMunicipiosUpdate;
    }

    public void setListMunicipiosUpdate(List<Municipio> ListMunicipiosUpdate) {
        this.ListMunicipiosUpdate = ListMunicipiosUpdate;
    }

}
