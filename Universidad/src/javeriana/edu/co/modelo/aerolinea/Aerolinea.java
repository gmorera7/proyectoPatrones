package javeriana.edu.co.modelo.aerolinea;

import javeriana.edu.co.modelo.mensaje.HacerNotificacion;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import javeriana.edu.co.modelo.busqueda.Busqueda;
import javeriana.edu.co.modelo.check.CheckFood;
import javeriana.edu.co.modelo.check.CheckOut;
import java.util.Observable;
import javeriana.edu.co.modelo.check.FabricaCheck;
import javeriana.edu.co.modelo.check.HacerCheck;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javeriana.edu.co.modelo.reserva.Reserva;
import javeriana.edu.co.modelo.reserva.Ruta;
import javeriana.edu.co.utilidades.Utilities;

/**
 *
 * @author javeriana.edu.co
 */
public class Aerolinea extends Observable implements AccionReserva, AccionRutas, HacerCheck, HacerNotificacion {

    private ArrayList reservas = new ArrayList<>();
    private ArrayList rutas = new ArrayList<>();

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
        enviarNotificacion("cargarCiudadesOrigen", CargaDatos.getInstance().cargarCiudadesOrigen());
    }

    @Override
    public void cargarCiudadesDestino() {
        enviarNotificacion("cargarCiudadesDestino", CargaDatos.getInstance().cargarCiudadesDestino());
    }

    @Override
    public void buscarRuta(Busqueda busqueda) {
        Ruta ruta;
        String fechaBusqueda, fechaRuta;
        ArrayList<Ruta> rutasEncontradas = new ArrayList<>();

        for (Iterator it = getRutas().iterator(); it.hasNext();) {

            ruta = (Ruta) it.next();
            fechaBusqueda = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(busqueda.getFecha());
            fechaRuta = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(ruta.getFechaSalida());

            if (ruta.getOrigen().equalsIgnoreCase(busqueda.getOrigen())
                    && ruta.getDestino().equalsIgnoreCase(busqueda.getDestino())
                    && fechaBusqueda.equalsIgnoreCase(fechaRuta)) {

                rutasEncontradas.add(ruta);
                System.err.println("encontro ruta.....");
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
        Reserva reserva;
        for (Iterator it = reservas.iterator(); it.hasNext();) {
            agregar = true;
            reserva = (Reserva) it.next();

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

    @Override
    public void cargarTiposDocumento() {
        enviarNotificacion("cargarTiposDocumento", CargaDatos.getInstance().cargarTiposDocumento());
    }

    @Override
    public void cargarMenuComidaEspecial() {
        enviarNotificacion("cargarMenuComidaEspecial", CargaDatos.getInstance().cargarMenuComidaEspecial());
    }

    @Override
    public void cargarMenuTipoComida() {
        enviarNotificacion("cargarMenuTipoComida", CargaDatos.getInstance().cargarMenuTipoComida());

    }

    @Override
    public void cargarNumeroSillas() {
        enviarNotificacion("cargarMenuNumeroSillas", CargaDatos.getInstance().cargarMenuSillas());
    }
}