package edu.gpc.facade;

import edu.gpc.entities.DetalleCompra;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DetalleCompraFacade extends AbstractFacade<DetalleCompra> {

    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;
    
    @EJB
    private CompraFacade ejbFacadeCompra;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCompraFacade() {
        super(DetalleCompra.class);
    }

    public void guardarLista(List<DetalleCompra> listaDetalleCompra) {
        if(!listaDetalleCompra.isEmpty()){
            System.out.print("== ANTES DE GUARDAR");
            for (DetalleCompra detalleCompra : listaDetalleCompra) {
                create(detalleCompra);
            }
            System.out.print("== DESPUES ");
        }
    }

}
