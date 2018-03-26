package edu.gpc.facade;

import edu.gpc.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anita
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean existeUsuario(String usuario) {
        try {
            return ((Long) getEntityManager().
                    createQuery("SELECT count(u) FROM Usuario u WHERE u.usuario=:usuario", Long.class).
                    setParameter("usuario", usuario).getSingleResult()).intValue() > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<Usuario> usuariosActivos() {
        final String query = "SELECT u FROM Usuario u WHERE u.estado = '1'";
        final TypedQuery<Usuario> tq = getEntityManager().createQuery(
                query, Usuario.class);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<Usuario>();
        }
    }

    public List<Usuario> usuariosInactivos() {
        final String query = "SELECT u FROM Usuario u WHERE u.estado = '0'";
        final TypedQuery<Usuario> tq = getEntityManager().createQuery(
                query, Usuario.class);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<Usuario>();
        }
    }

    public void activarUsuario(Usuario usuario2) {
        System.out.print("== ACTU ESTADO USUARIO en facade :::::" + usuario2.getEstado());
        getEntityManager().merge(usuario2);
    }

    public void updateEstadoAct(Usuario usuario) {
        getEntityManager().merge(usuario);
    }
    
    public void editUsuario(Usuario entity) {
        getEntityManager().merge(entity);
    }
    

}
