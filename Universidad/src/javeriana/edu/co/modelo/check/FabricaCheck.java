package javeriana.edu.co.modelo.check;

import java.util.Date;

/**
 *
 * @author javeriana.edu.co
 */
public class FabricaCheck implements IFabricaCheckAbstracta{

    private static FabricaCheck instance = null;

    protected FabricaCheck() {       
    }

    public static FabricaCheck getInstance() {
        if (instance == null) {
            instance = new FabricaCheck();
        }
        return instance;
    }

    @Override
    public Check crearCheckIn(Integer id , boolean confirmacion) {
        Check checkIn = new CheckIn();
        checkIn.setConfirmacion(confirmacion);
        checkIn.setId(id);
        checkIn.setFecha(new Date());
        return checkIn;

    }

    @Override
    public Check crearCheckOut(Integer id,boolean confirmacion) {
        Check checkOut = new CheckOut();
        checkOut.setConfirmacion(confirmacion);
        checkOut.setId(id);
        checkOut.setFecha(new Date());
        return checkOut;

    }

    @Override
    public Check crearCheckFood(Integer id,boolean confirmacion) {
        Check checkFood = new CheckFood();
        checkFood.setConfirmacion(confirmacion);
        checkFood.setId(id);
        checkFood.setFecha(new Date());
        return checkFood;

    }

}
