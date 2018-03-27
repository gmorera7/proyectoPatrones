package aerolinea;

import Mensaje.HacerNotificacion;
import Mensaje.Mensaje;
import busqueda.Busqueda;
import java.util.Observable;
import check.FabricaCheck;
import check.HacerCheck;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        cargarRutas();
    }

    public static Aerolinea getInstance() {
        if (instance == null) {
            instance = new Aerolinea();
        }
        return instance;
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

    @Override
    public void hacerReserva(Reserva reserva) {
        reserva.setFecha(new Date());
        reserva.setEstado("activa");
        reserva.setCheck(new ArrayList<>());
        reserva.setId(reservas.size() + 1);
        reservas.add(reservas.size(), reserva);
        enviarNotificacion("hacerReserva", reserva);
    }

    @Override
    public void buscarReserva(Integer idReserva) {
        Reserva reservaFinal = null;
        for (Iterator iterator = reservas.iterator(); iterator.hasNext();) {
            Reserva reservaLocal = (Reserva) iterator.next();
            if (reservaLocal.getId().intValue() == idReserva) {
                reservaFinal = reservaLocal;
            }
        }
        enviarNotificacion("buscarReserva", reservaFinal);
    }

    @Override
    public void hacerCheckIn(Integer idReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = (Reserva) reservas.get(i);
            if (reserva.getId().intValue() == idReserva) {
                reserva.getCheck().add(FabricaCheck.getInstance().crearCheckIn(reserva.getCheck().size()));
                reservas.set(i, reserva);
            }
        }
    }

    @Override
    public void hacerCheckOut(Integer idReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = (Reserva) reservas.get(i);
            if (reserva.getId().intValue() == idReserva) {
                reserva.getCheck().add(FabricaCheck.getInstance().crearCheckFood(reserva.getCheck().size()));
                reservas.set(i, reserva);
            }
        }
    }

    @Override
    public void cargarRutas() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String stringFechaLlegada = "2018-03-21 07:00:00";
            String stringFechaSalida = "2018-03-21 08:00:00";
            Ruta ruta1 = new Ruta();
            ruta1.setId(1);
            ruta1.setDestino("ARMENIA");
            ruta1.setDuracionVuelo("1");
            ruta1.setNoVuelo("V1");
            ruta1.setOrigen("BÓGOTA");
            ruta1.setPrecio(300000L);
            ruta1.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta1.setFechaLlegada(sdf.parse(stringFechaLlegada));

            rutas.add(ruta1);
            Ruta ruta2 = new Ruta();
            ruta2.setId(2);
            ruta2.setDestino("PASTO");
            ruta2.setDuracionVuelo("1");
            ruta2.setNoVuelo("V2");
            ruta2.setOrigen("TUNJA");
            ruta2.setPrecio(350000L);
            ruta2.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta2.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta2);

            Ruta ruta3 = new Ruta();
            ruta3.setId(2);
            ruta3.setDestino("MEDELLIN");
            ruta3.setDuracionVuelo("1");
            ruta3.setNoVuelo("V3");
            ruta3.setOrigen("CALI");
            ruta3.setPrecio(250000L);
            ruta3.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta3.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta3);

            Ruta ruta4 = new Ruta();
            ruta4.setId(2);
            ruta4.setDestino("BUCARAMANGA");
            ruta4.setDuracionVuelo("1");
            ruta4.setNoVuelo("V4");
            ruta4.setOrigen("CARACAS");
            ruta4.setPrecio(200000L);
            ruta4.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta4.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta4);

        } catch (ParseException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void cargarCiudadesOrigen() {
        ciudadesOrigen = new String[4];
        ciudadesOrigen[0] = "ARMENIA";
        ciudadesOrigen[1] = "BÓGOTA";
        ciudadesOrigen[2] = "CARACAS";
        ciudadesOrigen[3] = "TUNJA";

        enviarNotificacion("cargarCiudadesOrigen", ciudadesOrigen);
    }

    @Override
    public void cargarCiudadesDestino() {
        ciudadesDestino = new String[4];
        ciudadesDestino[0] = "CALI";
        ciudadesDestino[1] = "PASTO";
        ciudadesDestino[2] = "MEDELLIN";
        ciudadesDestino[3] = "BUCARAMANGA";

        enviarNotificacion("cargarCiudadesDestino", ciudadesDestino);
    }

    @Override
    public void buscarRuta(Busqueda busqueda) {
        System.err.println("comparando busqueda : " + busqueda.getDestino() + busqueda.getOrigen() + busqueda.getFecha());
        ArrayList<Ruta> rutasEncontradas = new ArrayList<>();
        for (Iterator it = rutas.iterator(); it.hasNext();) {
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
        for (Iterator iterator = rutas.iterator(); iterator.hasNext();) {
            Ruta reservaLocal = (Ruta) iterator.next();
            if (reservaLocal.getId().intValue() == idRuta) {
                rutaFinal = reservaLocal;
            }
        }
        enviarNotificacion("busquedaRutaPorId", rutaFinal);
    }

    @Override
    public void enviarNotificacion(String accion, Object objeto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setAccion(accion);
        mensaje.setObjeto(objeto);
        setChanged();
        notifyObservers(mensaje);
    }
}
