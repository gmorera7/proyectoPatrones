package aerolinea;

import busqueda.Busqueda;

/**
 *
 * @author javeriana.edu.co
 */
public interface AccionRutas {

    public void buscarRuta(Busqueda busqueda);

    public void cargarRutas();

    public void cargarCiudadesOrigen();

    public void cargarCiudadesDestino();

}
