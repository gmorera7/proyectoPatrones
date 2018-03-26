package edu.gpc.controller;

import edu.gpc.entities.Departamentos;
import edu.gpc.entities.Municipio;
import edu.gpc.entities.Proveedor;
import edu.gpc.facade.DepartamentosFacade;
import edu.gpc.facade.MunicipioFacade;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import edu.gpc.facade.ProveedorFacade;
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

@ManagedBean(name = "proveedorController")
@ViewScoped
public class ProveedorController implements Serializable {

    private Proveedor proveedorClone;
    private Proveedor proveedorSelect;
    private Proveedor current;
    private Departamentos departamentos;
    private DataModel items = null;

    private List<Departamentos> ListDeptos;
    private List<Proveedor> listProveedores;
    private List<Proveedor> listProvInactivos;
    private List<Proveedor> listProveedorFilters;
    private List<Municipio> ListMunicipios;
    private List<Municipio> ListMunicipiosUpdate;

    //booleans
    private boolean renderedForm;
    private boolean verProveedor;

    @EJB
    private edu.gpc.facade.ProveedorFacade ejbFacade;
    @EJB
    private DepartamentosFacade deptosFacade;
    @EJB
    private MunicipioFacade munFacade;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    @PostConstruct
    public void postConstruct() {
        listProveedores = ejbFacade.listaProvActivos();
        listProvInactivos = ejbFacade.listaProvInactivos();
        ListDeptos = deptosFacade.findAll();
        current = new Proveedor();
        current.setMunicipio(new Municipio());
        current.getMunicipio().setDepartamentoIddepartamento(new Departamentos());
        getSelected().setTipoIdentificacion("CC");
    }

    public ProveedorController() {

    }

    public void prueba() {
        if (proveedorSelect != null) {
            renderedForm = true;
            System.out.print("==  PRUEBA ");
            proveedorClone = new Proveedor();
            proveedorClone.setCodigo(proveedorSelect.getCodigo());
            proveedorClone.setTipoIdentificacion(proveedorSelect.getTipoIdentificacion());
            proveedorClone.setNumeroIdentificacion(proveedorSelect.getNumeroIdentificacion());
            proveedorClone.setNombre1(proveedorSelect.getNombre1());
            proveedorClone.setNombre2(proveedorSelect.getNombre2());
            proveedorClone.setApellido1(proveedorSelect.getApellido1());
            proveedorClone.setApellido2(proveedorSelect.getApellido2());
            proveedorClone.setDepartamento(proveedorSelect.getDepartamento());
            proveedorClone.setMunicipio(proveedorSelect.getMunicipio());
            proveedorClone.setDireccion(proveedorSelect.getDireccion());
            proveedorClone.setTipoTelefono(proveedorSelect.getTipoTelefono());
            proveedorClone.setNumeroTelefono(proveedorSelect.getNumeroTelefono());
            proveedorClone.setEmail(proveedorSelect.getEmail());
            proveedorClone.setEstado(proveedorSelect.getEstado());

            System.out.print("==  PRUEBA ?" + proveedorClone.getCodigo());
            System.out.print("==  PRUEBA " + renderedForm);

            eventoTraerMunicipiosUpdate();

            proveedorSelect.setMunicipio(
                    obtenerObjectoLista(proveedorSelect.getMunicipio()));

        } else {
            System.out.print("==  error no selecciono ");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ValidEditProveedor"));
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

    public void validarVerProveedor() {
        System.out.print("== Vr ver Cliente ants " + verProveedor);
        if (proveedorSelect == null) {
            verProveedor = false;
            System.out.print("== Vr ver Cliente if " + verProveedor);
            System.out.print("==  error no selecciono ");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VerProveedor"));
        } else {
            renderedForm = true;
            verProveedor = true;
            System.out.print("== Vr ver Cliente else " + verProveedor);
        }
    }

//    if (getSelected().getMunicipio().getDepartamentoIddepartamento() != null) {
//            ListMunicipios = munFacade.listarMunicipiosPorDepartamento(getSelected().getMunicipio().getDepartamentoIddepartamento());
//        }
    public void eventoTraerMunicipios() {
        System.out.print("==== Entro al evento traer municipios:::.===" + (getSelected().getMunicipio().getDepartamentoIddepartamento() == null ? null : "NO NULL"));
        if (getSelected().getMunicipio().getDepartamentoIddepartamento() != null) {
            System.out.print("==== Entro al evento traer municipios::: esta en el if.=== ");
            ListMunicipios = munFacade.listarMunicipiosPorDepartamento(getSelected().getMunicipio().getDepartamentoIddepartamento());

            System.out.print("==== tamaño de la lista municipios === " + ListMunicipios.size());
        }

    }

    public void eventoTraerMunicipiosAct() {
        System.out.print("==== Entro al evento traer municipios:::.===" + (proveedorSelect.getMunicipio().getDepartamentoIddepartamento() == null ? null : "NO NULL"));
        if (getSelected().getMunicipio().getDepartamentoIddepartamento() != null) {
            System.out.print("==== Entro al evento traer municipios::: esta en el if.=== ");
            ListMunicipiosUpdate = munFacade.listarMunicipiosPorDepartamento(proveedorSelect.getMunicipio().getDepartamentoIddepartamento());
            System.out.print("==== tamaño de la lista municipios update=== " + ListMunicipiosUpdate.size());
        }

    }

    public void updateEstadoAct(Proveedor proveedor) {
        if (proveedorSelect != null) {
            System.out.print("== ACTU ESTADO PROVEEDOR");
            System.out.print("== VALOR ESTADO ANTES ESTADO PROVEEDOR::: " + proveedor.getEstado());
            proveedor.setEstado("0");
            System.out.print("== VALOR ESTADO DESPUES ESTADO PROVEEDOR:::: " + proveedor.getEstado());
            getFacade().updateEstadoAct(proveedor);
            listProveedores.remove(proveedor);
            System.out.print("== ACTU ESTADO " + proveedor);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProveedorDesactivado"));
        } else {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DesactivarProveedor"));
        }
    }

    public void updateEstadoActInact(Proveedor proveedor2) {
        if (proveedorSelect != null) {
            System.out.print("== ACTU ESTADO");
            proveedor2.setEstado("1");
            getFacade().activarProveedor(proveedor2);
            listProvInactivos.remove(proveedor2);
            System.out.print("== ACTU ESTADO " + proveedor2);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProveedorActivado"));
        } else {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ActivarProveedor"));
        }
    }

    public void eventoTraerMunicipiosUpdate() {
        System.out.print("==== municipio 3===" + (getSelected().getMunicipio().getDepartamentoIddepartamento() == null ? null : "NO NULL"));
        if (proveedorSelect.getMunicipio().getDepartamentoIddepartamento() != null) {
            ListMunicipiosUpdate = munFacade.listarMunicipiosPorDepartamento(proveedorSelect.getMunicipio().getDepartamentoIddepartamento());
        }
    }

    public Proveedor getSelected() {
        if (current == null) {
            current = new Proveedor();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProveedorFacade getFacade() {
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
        return "listarproveedores";
    }

    public String prepareView() {
        current = (Proveedor) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "detalleproveedor";
    }

    public String prepareCreate() {
        current = new Proveedor();
        selectedItemIndex = -1;
        return "crearproveedor";
    }

    public String create() {
        try {
            if (getFacade().existeIdentificacion(current.getNumeroIdentificacion())) {
                System.out.println("la identificacion ya existe::: ");
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProveedorExiste"));
            } else {
                System.out.println("entro a guardar ::::::  ");
                getFacade().create(current);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                return "listarproveedores";
            }
            return "crearproveedor";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void update() {
        try {
            if (proveedorClone.getCodigo().equals(proveedorSelect.getCodigo())) {
//                if (getFacade().existeIdentificacion(proveedorSelect.getNumeroIdentificacion())) {
//                    System.out.println("la identificacion ya existe::: " +proveedorSelect.getNumeroIdentificacion());
//                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProveedorExiste"));
//                } else {
                renderedForm = false;
                System.out.print(proveedorSelect.getCodigo());
                getFacade().edit(proveedorSelect);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProveedorUpdated"));
                actualizarLista(proveedorSelect);
                JsfUtil.ejecutarUpdate(JsfUtil.buscarHtmlComponete("pnlGroupFormularioProveedores").getClientId());
                proveedorSelect = null;
                proveedorClone = null;

            } else {
                System.out.print("== ERROR ");
//            return "detallecliente";
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));

        }
    }

    private void actualizarLista(Proveedor proeveedor) {
        System.out.print("== ACTU");
        for (Proveedor object : listProveedores) {
            System.out.print("== ACTU 1");
            if (object.getCodigo().equals(proeveedor.getCodigo())) {
                System.out.print("== ACTU 2");
                object.setCodigo(proeveedor.getCodigo());
                object.setCodigo(proeveedor.getCodigo());
            }
        }
    }

    public String destroy() {
        current = (Proveedor) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "listaproveedores";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "detalleproveedor";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "listaproveedores";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProveedorDeleted"));
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
        return "listaproveedores";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "listaproveedores";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Proveedor getProveedor(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Proveedor.class)
    public static class ProveedorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProveedorController controller = (ProveedorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proveedorController");
            return controller.getProveedor(getKey(value));
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
            if (object instanceof Proveedor) {
                Proveedor o = (Proveedor) object;
                return getStringKey(o.getCodigo());
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo " + object.getClass().getName() + "; Tipo esperado: " + Proveedor.class.getName());
            }
        }

    }

    public void setListProveedores(List<Proveedor> listProveedores) {
        this.listProveedores = listProveedores;
    }

    public List<Proveedor> getListProveedores() {
        return listProveedores;
    }

    public void setListProveedorFilters(List<Proveedor> listProveedorFilters) {
        this.listProveedorFilters = listProveedorFilters;
    }

    public List<Proveedor> getListProveedorFilters() {
        return listProveedorFilters;
    }

    public void setProveedorSelect(Proveedor proveedorSelect) {
        this.proveedorSelect = proveedorSelect;
    }

    public Proveedor getProveedorSelect() {
        return proveedorSelect;
    }

    public void setProveedorClone(Proveedor proveedorClone) {
        this.proveedorClone = proveedorClone;
    }

    public Proveedor getProveedorClone() {
        return proveedorClone;
    }

    public void setRenderedForm(boolean renderedForm) {
        this.renderedForm = renderedForm;
    }

    public boolean isRenderedForm() {
        return renderedForm;
    }

    public void setVerProveedor(boolean verProveedor) {
        this.verProveedor = verProveedor;
    }

    public boolean isVerProveedor() {
        return verProveedor;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public void setListDeptos(List<Departamentos> ListDeptos) {
        this.ListDeptos = ListDeptos;
    }

    public List<Departamentos> getListDeptos() {
        return ListDeptos;
    }

    public void setListMunicipios(List<Municipio> ListMunicipios) {
        this.ListMunicipios = ListMunicipios;
    }

    public List<Municipio> getListMunicipios() {
        return ListMunicipios;
    }

    public MunicipioFacade getMunFacade() {
        return munFacade;
    }

    public DepartamentosFacade getDeptosFacade() {
        return deptosFacade;
    }

    public void setDeptosFacade(DepartamentosFacade deptosFacade) {
        this.deptosFacade = deptosFacade;
    }

    public List<Municipio> getListMunicipiosUpdate() {
        return ListMunicipiosUpdate;
    }

    public void setListMunicipiosUpdate(List<Municipio> ListMunicipiosUpdate) {
        this.ListMunicipiosUpdate = ListMunicipiosUpdate;
    }

    public List<Proveedor> getListProvInactivos() {
        return listProvInactivos;
    }

    public void setListProvInactivos(List<Proveedor> listProvInactivos) {
        this.listProvInactivos = listProvInactivos;
    }

}
