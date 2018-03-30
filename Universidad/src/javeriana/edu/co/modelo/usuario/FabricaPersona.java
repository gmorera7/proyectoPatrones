package javeriana.edu.co.modelo.usuario;

/**
 *
 * @author javeriana.edu.co
 */
public class FabricaPersona extends FabricaPersonaAbstracta {

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
    public Pasajero crearPasajero() {
        return new Pasajero();
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
