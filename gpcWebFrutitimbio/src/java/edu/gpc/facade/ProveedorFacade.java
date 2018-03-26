package edu.gpc.facade;

import edu.gpc.entities.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrador
 */
@Stateless
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }

    public List<Proveedor> listaProvInactivos() {
        final String query = "SELECT c FROM Proveedor c WHERE c.estado = '0'";
        final TypedQuery<Proveedor> tq = getEntityManager().createQuery(
                query, Proveedor.class);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<Proveedor>();
        }
    }

    public List<Proveedor> listaProvActivos() {
        final String query = "SELECT c FROM Proveedor c WHERE c.estado = '1'";
        final TypedQuery<Proveedor> tq = getEntityManager().createQuery(
                query, Proveedor.class);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<Proveedor>();
        }
    }
    
    public boolean existeIdentificacion(String numeroIdentificacion) {
        try {
            return ((Long) getEntityManager().
                    createQuery("SELECT count(p) FROM Proveedor p WHERE p.numeroIdentificacion=:numeroIdentificacion", Long.class).
                    setParameter("numeroIdentificacion", numeroIdentificacion).getSingleResult()).intValue() > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    public void activarProveedor(Proveedor proveedor2) {
        System.out.print("== ACTU ESTADO PROVEEDOR en facade :::::" + proveedor2.getEstado());
        getEntityManager().merge(proveedor2);
    }

    public void updateEstadoAct(Proveedor proveedor) {
        System.out.print("== VALOR ESTADO FACADE ANTES ESTADO PROVEEDOR::: " + proveedor.getEstado());
        getEntityManager().merge(proveedor);
        System.out.print("== VALOR ESTADO FACADE DESPUES ESTADO PROVEEDOR::: " + proveedor.getEstado());
    }
}
