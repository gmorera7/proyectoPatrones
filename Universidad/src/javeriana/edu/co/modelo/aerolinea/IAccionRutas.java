package javeriana.edu.co.modelo.aerolinea;

import javeriana.edu.co.modelo.busqueda.Busqueda;

/**
 *
 * @author javeriana.edu.co
 */
public interface IAccionRutas {

    public void buscarRuta(Busqueda busqueda);
    
    public void consultarRutaPorId(Integer idRuta);

    public void cargarCiudadesOrigen();

    public void cargarCiudadesDestino();

}
