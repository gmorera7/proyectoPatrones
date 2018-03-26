package edu.gpc.facade;

import edu.gpc.entities.Producto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {
    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
        public boolean existeProducto(String nombreProducto) {
        try {
            return ((Long) getEntityManager().
                    createQuery("SELECT count(p) FROM Producto p WHERE p.nombre=:nombreProducto", Long.class).
                    setParameter("nombreProducto", nombreProducto).getSingleResult()).intValue() > 0;
        } catch (NoResultException e) {
            return false;
        }
    }
}
