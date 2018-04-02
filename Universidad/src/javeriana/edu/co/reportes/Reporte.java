package javeriana.edu.co.reportes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.check.Check;
import javeriana.edu.co.modelo.check.CheckFood;
import javeriana.edu.co.modelo.check.HacerCheck;
import javeriana.edu.co.modelo.comida.ComidaEspecial;
import javeriana.edu.co.modelo.encuesta.Encuesta;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import javeriana.edu.co.modelo.reserva.Reserva;
import javeriana.edu.co.modelo.usuario.Persona;

/**
 *
 * @author javeriana.edu.co
 */
public class Reporte extends Observable implements HacerCheck, Observer ,HacerReporte {

    static Aerolinea aerolinea;

    Reserva reserva;

    private ArrayList revervasCheckInt = new ArrayList();
    private ArrayList revervasCheckOut = new ArrayList();
    private ArrayList revervasCheckFoodOK = new ArrayList();
    private ArrayList revervasCheckFoodNoOK = new ArrayList();
    private ArrayList revervasCheckF = new ArrayList();
    private ArrayList encuestas = new ArrayList();

    private static Reporte instance = null;

    protected Reporte() {

    }

    public static Reporte getInstance() {
        if (instance == null) {
            instance = new Reporte();
            aerolinea = Aerolinea.getInstance();
        }
        return instance;
    }

    @Override
    public void hacerCheckIn(Integer idReserva, boolean confirmacion) {
        aerolinea.hacerCheckIn(idReserva, confirmacion);
        if (confirmacion) {
            aerolinea.buscarReserva(idReserva);
            revervasCheckInt.add(reserva);
        }
    }

    public void hacerEncuesta(Integer idEncuesta, String calificacion) {
        Encuesta encuesta = new Encuesta();
        encuesta.setFecha(new Date());
        encuesta.setIdReserva(idEncuesta);
        encuesta.setCalificacion(calificacion);
        encuestas.add(encuesta);
        Aerolinea.getInstance().hacerEncuesta(encuesta);
    }

    @Override
    public void hacerCheckOut(Integer idReserva, boolean confirmacion) {
        aerolinea.hacerCheckOut(idReserva, confirmacion);
        if (confirmacion) {
            aerolinea.buscarReserva(idReserva);
            revervasCheckOut.add(reserva);
        }
    }

    @Override
    public void hacerCheckFood(Integer idReserva, boolean confirmacion) {
        aerolinea.hacerCheckFood(idReserva, confirmacion);
        aerolinea.buscarReserva(idReserva);
        revervasCheckF.add(reserva);
        if (confirmacion) {
            revervasCheckFoodOK.add(reserva);
        } else {
            revervasCheckFoodNoOK.add(reserva);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        Mensaje mensaje = (Mensaje) arg;

        if (mensaje.getAccion().equalsIgnoreCase("buscarReserva")) {
            reserva = (Reserva) mensaje.getObjeto();
        }

    }

    public void reporte2(Date fechaInicial, Date FechaFinal) {
        int contador = 0;
        int totalRango = 0;
        int porcentaje = 0;

        for (int i = 0; i < revervasCheckOut.size(); i++) {
            Reserva reservaLocal = (Reserva) revervasCheckOut.get(i);
            Date fechaSalida = reservaLocal.getRuta().getFechaSalida();
            if (fechaSalida.after(fechaInicial) && fechaSalida.before(FechaFinal)) {
                totalRango++;
                if (reservaLocal.getComida() instanceof ComidaEspecial) {
                    contador++;
                }
            }
        }
        if (totalRango > 0) {
            porcentaje = ((contador * 100) / totalRango);
        }
        enviarNotificacion("reporte2", porcentaje);
    }

    public void reporte1(Date fechaInicial, Date FechaFinal) {
        int contador = 0;
        int totalRango = revervasCheckFoodNoOK.size();
        int porcentaje = 0;
        for (int i = 0; i < revervasCheckFoodOK.size(); i++) {
            Reserva reservaLocal = (Reserva) revervasCheckFoodOK.get(i);
            Date fechaSalida = reservaLocal.getRuta().getFechaSalida();
            if (fechaSalida.after(fechaInicial) && fechaSalida.before(FechaFinal)) {
                totalRango++;
                if (reservaLocal.getComida() instanceof ComidaEspecial) {
                    contador++;
                }
            }
        }
        if (totalRango > 0) {
            porcentaje = ((contador * 100) / totalRango);
        }
        enviarNotificacion("reporte1", porcentaje);

    }

    public void reporte3(Date fechaInicial, Date FechaFinal) {
        int contador = 0;
        int totalRango = 0;
        int porcentaje = 0;
        Reserva reservaLocal = null;
        for (int i = 0; i < revervasCheckOut.size(); i++) {
            reservaLocal = (Reserva) revervasCheckOut.get(i);
            Date fechaSalida = reservaLocal.getRuta().getFechaSalida();

            if (fechaSalida.after(fechaInicial) && fechaSalida.before(FechaFinal)) {
                totalRango++;

                if (reservaLocal.getComida() instanceof ComidaEspecial) {

                    for (int l = 0; l < reservaLocal.getCheck().size(); l++) {
                        Check check = reservaLocal.getCheck().get(l);
                        if (check instanceof CheckFood && !check.getConfirmacion()) {
                            contador++;
                        }
                    }
                }
            }
        }
        if (totalRango > 0) {
            porcentaje = ((contador * 100) / totalRango);
        }
        enviarNotificacion("reporte3", porcentaje);
    }

    public void reporte4(Date fechaInicial, Date FechaFinal) {
        Reserva reservaLocal;
        Persona persona;
        List<Persona> pasajeros = Aerolinea.getInstance().getPasajeros();
        List<ReporteComida> reportes = new ArrayList<>();
        ReporteComida reporte;

        int veces = 0;
        for (Persona pasajero : pasajeros) {
            veces = 0;
            for (int i = 0; i < revervasCheckFoodNoOK.size(); i++) {
                reservaLocal = (Reserva) revervasCheckFoodNoOK.get(i);
                persona = reservaLocal.getPersona();
                if (pasajero.getTipoDocumento().equalsIgnoreCase(persona.getTipoDocumento())
                        && pasajero.getNumeroDocumento().equalsIgnoreCase(persona.getNumeroDocumento())) {
                    veces++;
                }
                if (veces > 1) {

                    reporte = new ReporteComida();
                    reporte.setDescripcion(reservaLocal.getComida().getDescripcion());
                    reporte.setDireccion(reservaLocal.getPersona().getDireccion());
                    reporte.setFecha(reservaLocal.getFecha());
                    reporte.setNombre(reservaLocal.getPersona().getNombreCompleto());
                    reportes.add(reporte);

                }
            }
        }
        enviarNotificacion("reporte4", reportes);
    }

    public void reporte5(Date fechaInicial, Date FechaFinal) {
        Reserva reservaLocal;
        List<ReporteComida> reportes = new ArrayList<>();
        ReporteComida reporte;

        for (int i = 0; i < revervasCheckF.size(); i++) {
            reservaLocal = (Reserva) revervasCheckF.get(i);

            for (int j = 0; j < encuestas.size(); j++) {
                Encuesta encuesta = (Encuesta) encuestas.get(j);
                if (encuesta.getIdReserva().intValue() == reservaLocal.getId().intValue()) {
                    if (Integer.parseInt(encuesta.getCalificacion()) < 5) {

                        reporte = new ReporteComida();
                        reporte.setDescripcion(reservaLocal.getComida().getDescripcion());
                        reporte.setDireccion(reservaLocal.getPersona().getDireccion());
                        reporte.setFecha(encuesta.getFecha());
                        reporte.setNombre(reservaLocal.getPersona().getNombreCompleto());
                        reportes.add(reporte);
                    }
                }
            }
        }
        enviarNotificacion("reporte5", reportes);
    }

    public void reporte6(Date fechaInicial, Date FechaFinal) {

        List<ReporteComidaProteina> lista = new ArrayList<>();
        ReporteComidaProteina reporteComidaProteina;
        Reserva reservaLocal;

        for (int i = 0; i < revervasCheckF.size(); i++) {
            reservaLocal = (Reserva) revervasCheckF.get(i);

            for (int j = 0; j < encuestas.size(); j++) {
                Encuesta encuesta = (Encuesta) encuestas.get(j);
                if (encuesta.getIdReserva().intValue() == reservaLocal.getId().intValue()) {
                    if (reservaLocal.getComida() instanceof ComidaEspecial
                            && reservaLocal.getComida().getDescripcion().equalsIgnoreCase("COMIDA BAJA EN PROTEINAS")) {
                        reporteComidaProteina = new ReporteComidaProteina();
                        reporteComidaProteina.setCalificacion(encuesta.getCalificacion());
                        reporteComidaProteina.setFecha(reservaLocal.getRuta().getFechaSalida());
                        reporteComidaProteina.setVuelo(reservaLocal.getRuta().getNoVuelo());
                        lista.add(reporteComidaProteina);
                    }
                }
            }
        }
        enviarNotificacion("reporte6", lista);
    }

    public void enviarNotificacion(String accion, Object objeto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setAccion(accion);
        mensaje.setObjeto(objeto);
        setChanged();
        notifyObservers(mensaje);
    }

    public void realizarEncuesta(Integer idEncuesta, String calificacion) {
        Encuesta encuesta = new Encuesta();
        encuesta.setFecha(new Date());
        encuesta.setIdReserva(idEncuesta);
        encuesta.setCalificacion(calificacion);
        Aerolinea.getInstance().hacerEncuesta(encuesta);

    }
}
