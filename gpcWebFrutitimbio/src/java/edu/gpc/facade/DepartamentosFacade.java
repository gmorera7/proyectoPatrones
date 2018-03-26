package edu.gpc.facade;

import edu.gpc.entities.Departamentos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anita
 */
@Stateless
public class DepartamentosFacade extends AbstractFacade<Departamentos> {
    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentosFacade() {
        super(Departamentos.class);
    }
    
}
