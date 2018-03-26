package edu.gpc.controller;

import edu.gpc.entities.Categoria;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import edu.gpc.facade.CategoriaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "categoriaController")
@ViewScoped
public class CategoriaController implements Serializable {

    private Categoria current;
    private Categoria categoriaSelect;
    private Categoria categoriaClone;
    private DataModel items = null;
    
    private boolean renderedForm;

    @EJB
    private edu.gpc.facade.CategoriaFacade ejbFacade;
    private List<Categoria> cat;
  //cat = ejbFacade.findAll();
    JasperPrint jasperPrint;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    // List's
    private List<Categoria> listCategoria;
    private List<Categoria> listCategoriaFilters;

    @PostConstruct
    public void postConstruct() {
        System.out.print("==  postCOn ");
        listCategoria = ejbFacade.findAll();
    }

    public void prueba() {
        System.out.print("==  entro a if:::: ");
        if (categoriaSelect != null){
            System.out.print("==  entro a if:::: " + categoriaSelect.getDescripcion());
            renderedForm = true;
        System.out.print("==  PRUEBA ");
        categoriaClone = new Categoria();
        categoriaClone.setCodigo(categoriaSelect.getCodigo());
        categoriaClone.setDescripcion(categoriaSelect.getDescripcion());
        System.out.print("==  PRUEBA ?" + categoriaClone.getCodigo());
        System.out.print("==  PRUEBA " + renderedForm);
        }else {
            System.out.print("==  error no selecciono ");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DeseleccionCategoria"));
        }
        
    }

    /// Setter's and Getter's
    public List<Categoria> getListCategoria() {
        return listCategoria;
    }

    public List<Categoria> getListCategoriaFilters() {
        return listCategoriaFilters;
    }

    public void setListCategoriaFilters(List<Categoria> listCategoriaFilters) {
        this.listCategoriaFilters = listCategoriaFilters;
    }

    public CategoriaController() {
        categoriaSelect = new Categoria();
    }

    public List<Categoria> getCat() {
//        cat = ejbFacade.findAll();
//        System.out.println("-- RGO -- tamaño lista get:" + cat.size());
        return cat;

    }

    public void setCat(List<Categoria> cat) {
        this.cat = cat;
    }

    public void init() throws JRException {

        cat = ejbFacade.findAll();
        System.out.println("-- RGO -- tamaño listaaaaaaaaa init:" + cat.size());
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(cat);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/jaspers/RepCategorias.jrxml");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
    }

    public void exportarCategoriaPdf() throws JRException, IOException {
        //System.out.println("-- RGO -- tamaño lista:" + cat.size());
        //System.out.println("-- RGO -- tamaño lista:" +  ejbFacade.findAll().size());

        init();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Lista de Categorias.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public Categoria getSelected() {
        if (current == null) {
            current = new Categoria();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CategoriaFacade getFacade() {
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
        return "listarcategoria";
    }

    public String prepareView() {
        current = (Categoria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "vercategoria";
    }

    public String prepareCreate() {
        String url = "nuevacategoria";
        System.out.println("retorna url::::" + url);
        current = new Categoria();
        selectedItemIndex = -1;
        return "nuevacategoria";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoriaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Categoria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "editarcategoria";
    }

    public void update() {
        System.out.println("---- entro a actualizar--- " + categoriaSelect.getCodigo());
        System.out.println("---- entro a actualizar--- " + categoriaClone.getCodigo());
        try {
            if (categoriaClone.getCodigo() == categoriaSelect.getCodigo()) {
                renderedForm = false;
                getFacade().edit(categoriaSelect);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoriaUpdated"));
                actualizarLista(categoriaSelect);
                //
                    // Restalece el select
                categoriaSelect = new Categoria();
                categoriaClone = new Categoria();
                JsfUtil.ejecutarUpdate(JsfUtil.buscarHtmlComponete("pnlGroupFormularioCategoria").getClientId());
            
            } else {
                System.out.print("== ERROR ");
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("codigo.no.editable"));
            }
        } catch (Exception e) {
            System.out.println( "---- hay una excepcion--- ");
            System.out.println("---- entro a actualizar--- " + categoriaClone.getCodigo());
        }
    }

    private void actualizarLista(Categoria categoria) {
        System.out.print("== ACTU");
        for (Categoria object : listCategoria) {
            System.out.print("== ACTU 1");
            if (object.getCodigo().equals(categoria.getCodigo())) {
                System.out.print("== ACTU 2");
                object.setCodigo(categoria.getCodigo());
                object.setDescripcion(categoria.getDescripcion());
            }
        }
    }

    public String destroy() {
        current = (Categoria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "listarcategoria";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "vercategoria";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "listarcategoria";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoriaDeleted"));
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
        return "listarcategoria";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "listarcategoria";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Categoria getCategoria(java.lang.Integer id) {
        return ejbFacade.find(id);

    }

    @FacesConverter(forClass = Categoria.class)
    public static class CategoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriaController controller = (CategoriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriaController");
            return controller.getCategoria(getKey(value));
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
            if (object instanceof Categoria) {
                Categoria o = (Categoria) object;
                return getStringKey(o.getCodigo());
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo " + object.getClass().getName() + "; Se espera de tipo: " + Categoria.class.getName());
            }
        }
    }

    public Categoria getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(Categoria categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }

    public boolean isRenderedForm() {
        return renderedForm;
    }

    public void setRenderedForm(boolean renderedForm) {
        this.renderedForm = renderedForm;
    }

}
