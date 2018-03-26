package edu.gpc.facade;

import edu.gpc.entities.DetalleVenta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DetalleVentaFacade extends AbstractFacade<DetalleVenta> {
    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

     @EJB
    private VentaFacade ejbFacadeCompra;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleVentaFacade() {
        super(DetalleVenta.class);
    }
    
        public void guardarLista(List<DetalleVenta> listaDetalleVenta) {
        if(!listaDetalleVenta.isEmpty()){
            
            System.out.print("== ANTES DE GUARDAR");
            for (DetalleVenta detalleVenta : listaDetalleVenta) {
                System.out.print("== ANTES DE GUARDAR FOR ::: "+ listaDetalleVenta.size());
                create(detalleVenta);
            }
            System.out.print("== DESPUES ");
        }
    }
    
}
