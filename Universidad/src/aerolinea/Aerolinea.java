package aerolinea;

import Mensaje.HacerNotificacion;
import Mensaje.Mensaje;
import busqueda.Busqueda;
import check.CheckFood;
import check.CheckOut;
import java.util.Observable;
import check.FabricaCheck;
import check.HacerCheck;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import reserva.Reserva;
import reserva.Ruta;

/**
 *
 * @author javeriana.edu.co
 */
public class Aerolinea extends Observable implements AccionReserva, AccionRutas, HacerCheck, HacerNotificacion {

    private ArrayList reservas = new ArrayList<>();
    private ArrayList rutas = new ArrayList<>();
    private String[] ciudadesOrigen;
    private String[] ciudadesDestino;

    private static Aerolinea instance = null;

    protected Aerolinea() {

    }

    public static Aerolinea getInstance() {
        if (instance == null) {
            instance = new Aerolinea();
            CargaDatos.getInstance().cargarRutas();
            CargaDatos.getInstance().cargarReservas();
        }
        return instance;
    }

    @Override
    public void hacerReserva(Reserva reserva) {
        reserva.setFecha(new Date());
        reserva.setEstado("ACTIVA");
        reserva.setCheck(new ArrayList<>());
        reserva.setId(getReservas().size() + 1);
        getReservas().add(getReservas().size(), reserva);

        enviarNotificacion("hacerReserva", reserva);
    }

    @Override
    public void buscarReserva(Integer idReserva) {
        Reserva reservaFinal = null;
        for (Iterator iterator = getReservas().iterator(); iterator.hasNext();) {
            Reserva reservaLocal = (Reserva) iterator.next();
            if (reservaLocal.getId().intValue() == idReserva) {
                reservaFinal = reservaLocal;
            }
        }
        enviarNotificacion("buscarReserva", reservaFinal);
    }

    @Override
    public void cargarCiudadesOrigen() {
        CargaDatos.getInstance().cargarCiudadesOrigen();
        enviarNotificacion("cargarCiudadesOrigen", getCiudadesOrigen());
    }

    @Override
    public void cargarCiudadesDestino() {
        CargaDatos.getInstance().cargarCiudadesDestino();
        enviarNotificacion("cargarCiudadesDestino", getCiudadesDestino());
    }

    @Override
    public void buscarRuta(Busqueda busqueda) {
        System.err.println("comparando busqueda : " + busqueda.getDestino() + busqueda.getOrigen() + busqueda.getFecha());
        ArrayList<Ruta> rutasEncontradas = new ArrayList<>();
        for (Iterator it = getRutas().iterator(); it.hasNext();) {
            Ruta ruta = (Ruta) it.next();
            System.err.println("comparando ruta : " + ruta.getDestino() + ruta.getOrigen() + ruta.getFechaSalida());
            if (ruta.getOrigen().equalsIgnoreCase(busqueda.getOrigen())
                    && ruta.getDestino().equalsIgnoreCase(busqueda.getDestino())) {
                //&& ruta.getFechaLlegada().equals(busqueda.getFecha())) {
                rutasEncontradas.add(ruta);
                System.err.println("encontro ruta igual");
            }
        }
        enviarNotificacion("busquedaRuta", rutasEncontradas);
    }

    @Override
    public void consultarRutaPorId(Integer idRuta) {

        Ruta rutaFinal = null;
        for (Iterator iterator = getRutas().iterator(); iterator.hasNext();) {
            Ruta reservaLocal = (Ruta) iterator.next();
            if (reservaLocal.getId().intValue() == idRuta) {
                rutaFinal = reservaLocal;
            }
        }
        enviarNotificacion("busquedaRutaPorId", rutaFinal);
    }

    @Override
    public void consultarReservasPorVueloCheckFood(String numeroVuelo) {
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();
        boolean agregar;
        for (Iterator it = reservas.iterator(); it.hasNext();) {
            agregar = true;
            Reserva reserva = (Reserva) it.next();

            if (reserva.getRuta().getNoVuelo().equalsIgnoreCase(numeroVuelo)) {
                if (reserva.getCheck() == null || reserva.getCheck().isEmpty()) {
                    reservasEncontradas.add(reserva);
                } else {
                    for (int i = 0; i < reserva.getCheck().size(); i++) {
                        if (reserva.getCheck().get(i) instanceof CheckFood) {
                            agregar = false;
                        }
                    }
                    if (agregar) {
                        reservasEncontradas.add(reserva);
                    }
                }
            }
        }
        enviarNotificacion("busquedaReservasPorVuelo", reservasEncontradas);
    }

    @Override
    public void hacerCheckIn(Integer idReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = (Reserva) reservas.get(i);
            if (reserva.getId().intValue() == idReserva) {
                reserva.getCheck().add(FabricaCheck.getInstance().crearCheckIn(reserva.getCheck().size()));
                reservas.set(i, reserva);
                break;
            }
        }
    }

    @Override
    public void hacerCheckOut(Integer idReserva) {
        for (int i = 0; i < getReservas().size(); i++) {
            Reserva reserva = (Reserva) getReservas().get(i);
            if (reserva.getId().intValue() == idReserva) {
                reserva.getCheck().add(FabricaCheck.getInstance().crearCheckOut(reserva.getCheck().size()));
                getReservas().set(i, reserva);
                break;
            }
        }
    }

    @Override
    public void hacerCheckFood(Integer idReserva) {
        for (int i = 0; i < getReservas().size(); i++) {
            Reserva reserva = (Reserva) getReservas().get(i);
            if (reserva.getId().intValue() == idReserva) {
                reserva.getCheck().add(FabricaCheck.getInstance().crearCheckFood(reserva.getCheck().size()));
                getReservas().set(i, reserva);
                break;
            }
        }
    }

    @Override
    public void enviarNotificacion(String accion, Object objeto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setAccion(accion);
        mensaje.setObjeto(objeto);
        setChanged();
        notifyObservers(mensaje);
    }

    /**
     * @return the reserva
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReservas(ArrayList<Reserva> reserva) {
        this.reservas = reserva;
    }

    /**
     * @return the rutas
     */
    public ArrayList getRutas() {
        return rutas;
    }

    /**
     * @param rutas the rutas to set
     */
    public void setRutas(ArrayList rutas) {
        this.rutas = rutas;
    }

    /**
     * @return the ciudadesOrigen
     */
    public String[] getCiudadesOrigen() {
        return ciudadesOrigen;
    }

    /**
     * @param ciudadesOrigen the ciudadesOrigen to set
     */
    public void setCiudadesOrigen(String[] ciudadesOrigen) {
        this.ciudadesOrigen = ciudadesOrigen;
    }

    /**
     * @return the ciudadesDestino
     */
    public String[] getCiudadesDestino() {
        return ciudadesDestino;
    }

    /**
     * @param ciudadesDestino the ciudadesDestino to set
     */
    public void setCiudadesDestino(String[] ciudadesDestino) {
        this.ciudadesDestino = ciudadesDestino;
    }

    @Override
    public void consultarReservasPorVueloCheckOut(String numeroVuelo) {
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();
        boolean agregar;
        for (Iterator it = reservas.iterator(); it.hasNext();) {
            agregar = true;
            Reserva reserva = (Reserva) it.next();

            if (reserva.getRuta().getNoVuelo().equalsIgnoreCase(numeroVuelo)) {
                if (reserva.getCheck() == null || reserva.getCheck().isEmpty()) {
                    reservasEncontradas.add(reserva);
                } else {
                    for (int i = 0; i < reserva.getCheck().size(); i++) {
                        if (reserva.getCheck().get(i) instanceof CheckOut) {
                            agregar = false;
                        }
                    }
                    if (agregar) {
                        reservasEncontradas.add(reserva);
                    }
                }
            }
        }
        enviarNotificacion("busquedaReservasPorVuelo", reservasEncontradas);

    }
}
