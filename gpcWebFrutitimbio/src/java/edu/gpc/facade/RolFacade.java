package edu.gpc.facade;

import edu.gpc.entities.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anita
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {
    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
}
