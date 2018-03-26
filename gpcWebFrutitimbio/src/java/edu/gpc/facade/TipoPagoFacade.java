package edu.gpc.facade;

import edu.gpc.entities.TipoPago;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anita
 */
@Stateless
public class TipoPagoFacade extends AbstractFacade<TipoPago> {
    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPagoFacade() {
        super(TipoPago.class);
    }
    
}
