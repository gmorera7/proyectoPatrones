package javeriana.edu.co.modelo.check;

import java.util.Date;

/**
 *
 * @author javeriana.edu.co
 */
public class FabricaCheck extends FabricaCheckAbstracta {

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
    public CheckIn crearCheckIn(Integer id , boolean confirmacion) {
        CheckIn checkIn = new CheckIn();
        checkIn.setConfirmacion(confirmacion);
        checkIn.setId(id);
        checkIn.setFecha(new Date());
        return checkIn;

    }

    @Override
    public CheckOut crearCheckOut(Integer id,boolean confirmacion) {
        CheckOut checkOut = new CheckOut();
        checkOut.setConfirmacion(confirmacion);
        checkOut.setId(id);
        checkOut.setFecha(new Date());
        return checkOut;

    }

    @Override
    public CheckFood crearCheckFood(Integer id,boolean confirmacion) {
        CheckFood checkFood = new CheckFood();
        checkFood.setConfirmacion(confirmacion);
        checkFood.setId(id);
        checkFood.setFecha(new Date());
        return checkFood;

    }

}
