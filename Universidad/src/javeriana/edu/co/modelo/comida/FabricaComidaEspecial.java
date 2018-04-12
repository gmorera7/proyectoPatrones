package javeriana.edu.co.modelo.comida;

/**
 *
 * @author javeriana.edu.co
 */
public class FabricaComidaEspecial implements IFabricaComidaEspecialAbstracta {

    private static FabricaComidaEspecial instance = null;

    protected FabricaComidaEspecial() {
    }

    public static FabricaComidaEspecial getInstance() {
        if (instance == null) {
            instance = new FabricaComidaEspecial();
        }
        return instance;
    }

    @Override
    public Comida comidaVetariana() {
        Comida comidaVegetaria = new ComidaEspecial();
        comidaVegetaria.setDescripcion("COMIDA VEGETARIANA");
        return comidaVegetaria;
    }

    @Override
    public Comida comidaMar() {
        Comida comidaMar = new ComidaEspecial();
        comidaMar.setDescripcion("COMIDA DE MAR");
        return comidaMar;

    }

    @Override
    public Comida comidaDiabeticos() {
        Comida comidaDiabeticos = new ComidaEspecial();
        comidaDiabeticos.setDescripcion("COMIDA PARA DIABETICOS");
        return comidaDiabeticos;

    }

    @Override
    public Comida comidaBajaEnGrasa() {
        Comida comidaBajaEnGrasa = new ComidaEspecial();
        comidaBajaEnGrasa.setDescripcion("COMIDA BAJA EN GRASA");
        return comidaBajaEnGrasa;

    }

    @Override
    public Comida comidaBajaColesterol() {
        Comida comidaBajaColesterol = new ComidaEspecial();
        comidaBajaColesterol.setDescripcion("COMIDA BAJA EN COLESTEROL");
        return comidaBajaColesterol;
    }

    @Override
    public Comida comidaBajaProteinas() {
        Comida comidaBajaProteinas = new ComidaEspecial();
        comidaBajaProteinas.setDescripcion("COMIDA BAJA EN PROTEINAS");
        return comidaBajaProteinas;

    }

    @Override
    public Comida comidaBajaCalorias() {
        Comida comidaBajaCalorias = new ComidaEspecial();
        comidaBajaCalorias.setDescripcion("COMIDA BAJA EN CALORIAS");
        return comidaBajaCalorias;
    }

    @Override
    public Comida comidaLibreLactosa() {
        Comida comidaLibreLactosa = new ComidaEspecial();
        comidaLibreLactosa.setDescripcion("COMIDA LIBRE DE LACTOSA");
        return comidaLibreLactosa;
    }

    @Override
    public Comida comidaParaNinos() {
        Comida comidaParaNinos = new ComidaEspecial();
        comidaParaNinos.setDescripcion("COMIDA PARA NIÑOS");
        return comidaParaNinos;
    }

    @Override
    public Comida crearComidaPorDescripcion(String descripcion) {
        Comida comidaEspecial = null;
        if (descripcion.equalsIgnoreCase("COMIDA VEGETARIANA")) {
            comidaEspecial = comidaVetariana();
        } else if (descripcion.equalsIgnoreCase("COMIDA DE MAR")) {
            comidaEspecial = comidaMar();
        } else if (descripcion.equalsIgnoreCase("COMIDA PARA DIABETICOS")) {
            comidaEspecial = comidaMar();
        } else if (descripcion.equalsIgnoreCase("COMIDA BAJA EN GRASA")) {
            comidaEspecial = comidaBajaEnGrasa();
        } else if (descripcion.equalsIgnoreCase("COMIDA BAJA EN COLESTEROL")) {
            comidaEspecial = comidaBajaColesterol();
        } else if (descripcion.equalsIgnoreCase("COMIDA BAJA EN PROTEINAS")) {
            comidaEspecial = comidaBajaProteinas();
        } else if (descripcion.equalsIgnoreCase("COMIDA BAJA EN CALORIAS")) {
            comidaEspecial = comidaBajaCalorias();
        } else if (descripcion.equalsIgnoreCase("COMIDA LIBRE DE LACTOSA")) {
            comidaEspecial = comidaLibreLactosa();
        } else if (descripcion.equalsIgnoreCase("COMIDA PARA NIÑOS")) {
            comidaEspecial = comidaParaNinos();
        }

        return comidaEspecial;
    }

}
