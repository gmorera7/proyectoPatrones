package javeriana.edu.co.modelo.check;

/**
 *
 * @author javeriana.edu.co
 */
public abstract class FabricaCheckAbstracta {

    public abstract CheckIn crearCheckIn(Integer id,boolean confirmacion);

    public abstract CheckOut crearCheckOut(Integer id,boolean confirmacion);

    public abstract CheckFood crearCheckFood(Integer id,boolean confirmacion);
}
