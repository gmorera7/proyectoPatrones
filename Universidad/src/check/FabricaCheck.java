package check;

/**
 *
 * @author javeriana.edu.co
 */
public class FabricaCheck extends FabricaCheckAbstracta {

    private static FabricaCheck instance = null;

    protected FabricaCheck() {
        // Exists only to defeat instantiation.
    }

    public static FabricaCheck getInstance() {
        if (instance == null) {
            instance = new FabricaCheck();
        }
        return instance;
    }

    @Override
    public CheckIn crearCheckIn() {
        CheckIn checkIn = new CheckIn();
        checkIn.setConfirmacion("ok");
        checkIn.setId(1);
        return checkIn;

    }

    @Override
    public CheckOut crearCheckOut() {
        CheckOut checkOut = new CheckOut();
        checkOut.setConfirmacion("ok");
        checkOut.setId(1);
        return checkOut;

    }

    @Override
    public CheckFood crearCheckFood() {
        CheckFood checkFood = new CheckFood();
        checkFood.setConfirmacion("ok");
        checkFood.setId(1);
        return checkFood;

    }

}
