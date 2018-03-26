package edu.gpc.facade;

import edu.gpc.entities.Venta;
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
public class VentaFacade extends AbstractFacade<Venta> {
    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }
    
       public Venta obtenerCodigoVenta() {
        final String query = "SELECT v FROM Venta v ORDER BY v.codigo DESC";
        final TypedQuery<Venta> tq = getEntityManager().createQuery(
                query, Venta.class);
        try {
            tq.setMaxResults(1);
            return tq.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
