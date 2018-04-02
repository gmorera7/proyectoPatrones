package javeriana.edu.co.modelo.comida;

/**
 *
 * @author javeriana.edu.co
 */
public abstract class FabricaComidaEspecialAbstracta {

    public abstract ComidaEspecial comidaVetariana();

    public abstract ComidaEspecial comidaMar();

    public abstract ComidaEspecial comidaDiabeticos();

    public abstract ComidaEspecial comidaBajaEnGrasa();

    public abstract ComidaEspecial comidaBajaColesterol();

    public abstract ComidaEspecial comidaBajaProteinas();

    public abstract ComidaEspecial comidaBajaCalorias();

    public abstract ComidaEspecial comidaLibreLactosa();

    public abstract ComidaEspecial comidaParaNinos();

    public abstract ComidaEspecial crearComidaPorDescripcion(String descripcion);

}
