package edu.gpc.facade;

import edu.gpc.entities.Rolmenu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anita
 */
@Stateless
public class RolmenuFacade extends AbstractFacade<Rolmenu> {
    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolmenuFacade() {
        super(Rolmenu.class);
    }
    
}
