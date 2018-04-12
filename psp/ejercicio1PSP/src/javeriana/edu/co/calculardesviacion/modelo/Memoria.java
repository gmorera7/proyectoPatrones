package javeriana.edu.co.calculardesviacion.modelo;

import java.util.LinkedList;

/**
 *
 * @author gersonmorera
 */
public class Memoria implements DesviacionEstandar {

    private LinkedList<Integer> lista = new LinkedList<Integer>();

    private static Memoria instance = null;

    protected Memoria() {
    }

    public static Memoria getInstance() {
        if (instance == null) {
            instance = new Memoria();
        }
        return instance;
    }

    @Override
    public void adicionarElemento(Integer valor) {
        lista.add(valor);
    }

    @Override
    public Double mediaAritmetica() {
        Integer suma = 0;
        for (int contador = 0; contador < lista.size(); contador++) {
            suma += lista.get(contador);
        }
        return (suma.doubleValue() / lista.size());
    }

    @Override
    public Double desviacionEstandar(Double media) {
        Double suma = 0.0;
        for (int contador = 0; contador < lista.size(); contador++) {
            Double resta = media - lista.get(contador);
            suma += Math.pow(resta, 2f);
        }
        Double dividendo = Math.sqrt(suma / (lista.size() - 1));
        return dividendo;
    }

}
