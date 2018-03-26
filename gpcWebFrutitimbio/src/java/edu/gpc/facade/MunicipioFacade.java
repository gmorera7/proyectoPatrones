package edu.gpc.facade;

import edu.gpc.entities.Departamentos;
import edu.gpc.entities.Municipio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anita
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "InventarioFrutitimbioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public List<Municipio> listarMunicipiosPorDepartamento(Departamentos departamento) {
        // Typed query
        final TypedQuery<Municipio> query = em.createNamedQuery("Municipio.findByDepartamentoIddepartamento", Municipio.class);
        // Parametros
        query.setParameter("departamentoIddepartamento", departamento);
        // obetener consulta
        return query.getResultList();
    }

}
