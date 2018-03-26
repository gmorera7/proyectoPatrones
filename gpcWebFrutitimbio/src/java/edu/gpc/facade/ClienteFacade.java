package edu.gpc.facade;

import edu.gpc.entities.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public List<Cliente> listaCliente() {
        final String query = "SELECT c FROM Cliente c WHERE c.estado = '1'";
        final TypedQuery<Cliente> tq = getEntityManager().createQuery(
                query, Cliente.class);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<Cliente>();
        }
    }

    public List<Cliente> listaClientesInactivos() {
        final String query = "SELECT c FROM Cliente c WHERE c.estado = '0'";
        final TypedQuery<Cliente> tq = getEntityManager().createQuery(
                query, Cliente.class);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<Cliente>();
        }
    }

    public boolean existeNit(Integer nit) {
        try {
            return ((Long) getEntityManager().
                    createQuery("SELECT count(c) FROM Cliente c WHERE c.nit=:nit", Long.class).
                    setParameter("nit", nit).getSingleResult()).intValue() > 0;

        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean existeNumeroTelefono(String numeroTelefono) {
        try {
            return ((Long) getEntityManager().
                    createQuery("SELECT count(c) FROM Cliente c WHERE c.numeroTelefono=:numeroTelefono", Long.class).
                    setParameter("numeroTelefono", numeroTelefono).getSingleResult()).intValue() > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    public void updateEstadoAct(Cliente cliente) {
        getEntityManager().merge(cliente);
    }

    public void updateEstadoActInac(Cliente cliente) {
        getEntityManager().merge(cliente);
    }

}
