package javeriana.edu.co.modelo.check;

/**
 *
 * @author javeriana.edu.co
 */
public interface IFabricaCheckAbstracta {
    
    public Check crearCheckIn(Integer id, boolean confirmacion);

    public Check crearCheckOut(Integer id, boolean confirmacion);

    public Check crearCheckFood(Integer id, boolean confirmacion);
    
}
