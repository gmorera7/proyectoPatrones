package javeriana.edu.co.modelo.aerolinea;

import javeriana.edu.co.modelo.busqueda.Busqueda;

/**
 *
 * @author javeriana.edu.co
 */
public interface AccionRutas {

    public void buscarRuta(Busqueda busqueda);
    
    public void consultarRutaPorId(Integer idRuta);

    public void cargarCiudadesOrigen();

    public void cargarCiudadesDestino();

}
