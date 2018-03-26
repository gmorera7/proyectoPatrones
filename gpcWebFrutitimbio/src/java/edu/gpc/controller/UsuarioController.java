package edu.gpc.controller;

import edu.gpc.entities.Rol;
import edu.gpc.entities.Usuario;
import edu.gpc.facade.RolFacade;
import edu.gpc.facade.UsuarioFacade;
import edu.gpc.util.JsfUtil;
import edu.gpc.util.PaginationHelper;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    private List<Rol> ListRol;
    private List<Usuario> listUsuarios;
    private List<Usuario> listUsuariosInactivos;

    private boolean renderedForm;

    private Usuario current;
    private Usuario usuarioSelect;
    private Usuario usuarioClone;

    private DataModel items = null;
    @EJB
    private UsuarioFacade ejbFacade;
    @EJB
    private RolFacade rolFacade;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UsuarioController() {
    }

    @PostConstruct
    public void postConstructo() {
        System.out.println("PostConstruct Usuario.");
        ListRol = rolFacade.findAll();
        listUsuarios = ejbFacade.usuariosActivos();
        listUsuariosInactivos = ejbFacade.usuariosInactivos();
    }

    public void prepareEditar() {
        if (usuarioSelect != null) {
            renderedForm = true;
            System.out.print("==  PRUEBA ");
            usuarioClone = new Usuario();
            usuarioClone.setId(usuarioSelect.getId());
            usuarioClone.setClave(usuarioSelect.getClave());
            usuarioClone.setRolId(usuarioSelect.getRolId());
            usuarioClone.setEmail(usuarioSelect.getEmail());
            usuarioClone.setUsuario(usuarioSelect.getUsuario());

//            System.out.print("==  PRUEBA ?" + usuarioSelect.getUsuario();
            System.out.print("==  PRUEBA " + renderedForm);
        } else {
            System.out.print("==  error no selecciono ");
            System.out.print("==  PRUEBA else rederform::: " + renderedForm);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DeseleccionUsuario"));
        }
    }

    public void update() {
        System.out.println("---- entro a actualizar--- " + usuarioClone.getUsuario());
        System.out.println("---- entro a actualizar--- " + usuarioSelect.getUsuario());
        if (getFacade().existeUsuario(usuarioSelect.getUsuario())) {
            System.out.println("entro a usuario existe::::. ");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioExiste"));
        } else {
            renderedForm = false;
            String encriptMD5 = DigestUtils.md5Hex(usuarioSelect.getClave());
            usuarioSelect.setClave(encriptMD5);
            getFacade().editUsuario(usuarioSelect);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
            actualizarLista(usuarioSelect);
            JsfUtil.ejecutarUpdate(JsfUtil.buscarHtmlComponete("pnlGroupListUsers").getClientId());
            usuarioSelect = null;
            usuarioClone = null;
        }
    }

    public void activarUsuario(Usuario usuario2) {
        if (usuarioSelect != null) {
            System.out.print("== ACTU ESTADO USUARIO");
            System.out.print("== tamaño lista antes::::: " + listUsuariosInactivos.size());
            System.out.print("== ACTU ESTADO USUARIO estado antes de set:::: " + usuarioSelect.getEstado());
            usuario2.setEstado("1");
            System.out.print("== ACTU ESTADO USUARIO estado despues de set:::: " + usuarioSelect.getEstado());
            getFacade().activarUsuario(usuario2);
            listUsuariosInactivos.remove(usuario2);
            System.out.print("== tamaño lista despues de remove::::: " + listUsuariosInactivos.size());

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioActivo"));

            System.out.print("== ACTU ESTADO USUARIO" + usuarioSelect.getEstado());
        } else {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioActivar"));
        }
    }

    public void updateEstadoAct(Usuario usuario) {
        if (usuarioSelect != null) {
            System.out.print("== ACTU ESTADO");
            usuario.setEstado("0");
            getFacade().updateEstadoAct(usuario);
            listUsuarios.remove(usuario);
            System.out.print("== ACTU ESTADO " + usuario);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioInactivo"));
        } else {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioDesactivar"));
        }
    }

    public void cancelarEditUsuario() {
        System.out.print("== entro a cancelar");
        usuarioSelect = null;
        usuarioClone = null;
        renderedForm = true;
    }

    private void actualizarLista(Usuario usuario) {
        System.out.print("== ACTU");
        for (Usuario object : listUsuarios) {
            System.out.print("== ACTU 1");
            if (object.getId().equals(usuario.getId())) {
                System.out.print("== ACTU 2");
                object.setId(usuario.getId());
                object.setId(usuario.getId());
            }
        }
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UsuarioFacade getFacade() {
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
        return "listaUsuario";
    }

    private void recreateModel() {
        items = null;
    }

    public String prepareCreate() {
        current = new Usuario();
        selectedItemIndex = -1;
        return "crearUsuario";
    }

    public String create() {
        System.out.println("entro a guardar usuario::::. ");

        String encriptMD5 = DigestUtils.md5Hex(getSelected().getClave());
        System.out.println("md5:" + encriptMD5);
        try {
            System.out.println("VALOR EXISTE usuario::::. " + getFacade().existeUsuario(current.getUsuario()));
            if (getFacade().existeUsuario(current.getUsuario())) {
                System.out.println("entro a usuario existe::::. ");
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioExiste"));
            } else {
                System.out.println("entro a guardar usuario en else::::. ");
                getSelected().setClave(encriptMD5);
                System.out.println("valor encriptado::::. " + encriptMD5);
                getFacade().create(current);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
                return "listaUsuario";
            }
            return "crearUsuario";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Usuario getUsuario(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public void setListRol(List<Rol> ListRol) {
        this.ListRol = ListRol;
    }

    public List<Rol> getListRol() {
        return ListRol;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public Usuario getUsuarioSelect() {
        return usuarioSelect;
    }

    public void setUsuarioSelect(Usuario usuarioSelect) {
        this.usuarioSelect = usuarioSelect;
    }

    public boolean isRenderedForm() {
        return renderedForm;
    }

    public void setRenderedForm(boolean renderedForm) {
        this.renderedForm = renderedForm;
    }

    public Usuario getUsuarioClone() {
        return usuarioClone;
    }

    public void setUsuarioClone(Usuario usuarioClone) {
        this.usuarioClone = usuarioClone;
    }

    public List<Usuario> getListUsuariosInactivos() {
        return listUsuariosInactivos;
    }

    public void setListUsuariosInactivos(List<Usuario> listUsuariosInactivos) {
        this.listUsuariosInactivos = listUsuariosInactivos;
    }
}
