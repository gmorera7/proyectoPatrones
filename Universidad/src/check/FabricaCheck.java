package check;

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
    public CheckIn crearCheckIn(Integer id) {
        CheckIn checkIn = new CheckIn();
        checkIn.setConfirmacion("ok");
        checkIn.setId(id);
        return checkIn;

    }

    @Override
    public CheckOut crearCheckOut(Integer id) {
        CheckOut checkOut = new CheckOut();
        checkOut.setConfirmacion("ok");
        checkOut.setId(id);
        return checkOut;

    }

    @Override
    public CheckFood crearCheckFood(Integer id) {
        CheckFood checkFood = new CheckFood();
        checkFood.setConfirmacion("ok");
        checkFood.setId(id);
        return checkFood;

    }

}
