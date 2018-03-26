package edu.gpc.facade;

import edu.gpc.entities.Compra;
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
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

    public Compra obtenerCodigoCompra() {
        final String query = "SELECT c FROM Compra c ORDER BY c.codigoCompra DESC";
        final TypedQuery<Compra> tq = getEntityManager().createQuery(
                query, Compra.class);
        try {
            tq.setMaxResults(1);
            return tq.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
