package javeriana.edu.co.modelo.usuario;

/**
 *
 * @author javeriana.edu.co
 */
public class FabricaPersona implements IFabricaPersonaAbstracta {

    private static FabricaPersona instance = null;

    protected FabricaPersona() {

    }

    public static FabricaPersona getInstance() {
        if (instance == null) {
            instance = new FabricaPersona();
        }
        return instance;
    }

    @Override
    public Pasajero crearPasajero(String tipoDcumento, String numeroDocumento, String primerN, String segundoN, String primerA, String segundoA, String direccion, String correo, String telefono) {
        Pasajero pasajero = new Pasajero();
        pasajero.setTipoDocumento(tipoDcumento);
        pasajero.setNumeroDocumento(numeroDocumento);
        pasajero.setPrimerNombre(primerN);
        pasajero.setSegundoNombre(segundoN);
        pasajero.setPrimerApellido(primerA);
        pasajero.setSegundoApellido(segundoA);
        pasajero.setDireccion(direccion);
        pasajero.setCorreoElectronico(correo);
        pasajero.setTelefono(telefono);
        return pasajero;
    }

    @Override
    public Azafata crearAzafata() {
        return new Azafata();
    }

    @Override
    public AnalistaReporte crearAnalistaReporte() {
        return new AnalistaReporte();
    }

    @Override
    public Abastecedor crearAbastecedor() {
        return new Abastecedor();
    }

}
