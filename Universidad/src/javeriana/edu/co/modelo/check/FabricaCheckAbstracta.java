package javeriana.edu.co.modelo.check;

/**
 *
 * @author gerson
 */
public abstract class FabricaCheckAbstracta {

    public abstract CheckIn crearCheckIn(Integer id);

    public abstract CheckOut crearCheckOut(Integer id);

    public abstract CheckFood crearCheckFood(Integer id);
}
