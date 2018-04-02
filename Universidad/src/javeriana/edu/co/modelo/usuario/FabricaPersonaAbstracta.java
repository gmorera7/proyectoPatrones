package javeriana.edu.co.modelo.usuario;

/**
 *
 * @author javeriana.edu.co
 */
public abstract class FabricaPersonaAbstracta {

    public abstract Pasajero crearPasajero(String tipoDcumento, String numeroDocumento, String primerN, String segundoN, String primerA, String segundoA, String direccion, String correo, String telefono);

    public abstract Azafata crearAzafata();

    public abstract AnalistaReporte crearAnalistaReporte();

    public abstract Abastecedor crearAbastecedor();

}
