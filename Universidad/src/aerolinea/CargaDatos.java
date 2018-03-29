package aerolinea;

import Usuario.FabricaPersona;
import Usuario.Pasajero;
import comida.ComidaEspecial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import reserva.Reserva;
import reserva.Ruta;

/**
 *
 * @author javeriana.edu.co
 */
public class CargaDatos {

    private static CargaDatos instance = null;

    private Ruta ruta1, ruta2, ruta3, ruta4;

    private String[] ciudadesOrigen;
    private String[] ciudadesDestino;

    private ArrayList reservas = new ArrayList<>();

    protected CargaDatos() {

    }

    public static CargaDatos getInstance() {
        if (instance == null) {
            instance = new CargaDatos();
        }
        return instance;
    }

    public void cargarRutas() {
        ArrayList rutas = new ArrayList<>();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String stringFechaLlegada = "2018-03-21 07:00:00";
            String stringFechaSalida = "2018-03-21 08:00:00";
            ruta1 = new Ruta();
            ruta1.setId(1);
            ruta1.setDestino("ARMENIA");
            ruta1.setDuracionVuelo("1");
            ruta1.setNoVuelo("V1");
            ruta1.setOrigen("BÓGOTA");
            ruta1.setPrecio(300000L);
            ruta1.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta1.setFechaLlegada(sdf.parse(stringFechaLlegada));

            rutas.add(ruta1);
            ruta2 = new Ruta();
            ruta2.setId(2);
            ruta2.setDestino("PASTO");
            ruta2.setDuracionVuelo("1");
            ruta2.setNoVuelo("V2");
            ruta2.setOrigen("TUNJA");
            ruta2.setPrecio(350000L);
            ruta2.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta2.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta2);

            ruta3 = new Ruta();
            ruta3.setId(2);
            ruta3.setDestino("MEDELLIN");
            ruta3.setDuracionVuelo("1");
            ruta3.setNoVuelo("V3");
            ruta3.setOrigen("CALI");
            ruta3.setPrecio(250000L);
            ruta3.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta3.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta3);

            ruta4 = new Ruta();
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
        Aerolinea.getInstance().setRutas(rutas);

    }

    public void cargarCiudadesOrigen() {
        ciudadesOrigen = new String[4];
        ciudadesOrigen[0] = "ARMENIA";
        ciudadesOrigen[1] = "BÓGOTA";
        ciudadesOrigen[2] = "CARACAS";
        ciudadesOrigen[3] = "TUNJA";

        Aerolinea.getInstance().setCiudadesOrigen(ciudadesOrigen);

    }

    public void cargarCiudadesDestino() {
        ciudadesDestino = new String[4];
        ciudadesDestino[0] = "CALI";
        ciudadesDestino[1] = "PASTO";
        ciudadesDestino[2] = "MEDELLIN";
        ciudadesDestino[3] = "BUCARAMANGA";

        Aerolinea.getInstance().setCiudadesDestino(ciudadesDestino);
    }

    public void cargarReservas() {
        Integer id1, id2, id3;

        Pasajero pasajero1 = FabricaPersona.getInstance().crearPasajero();
        pasajero1.setTipoDocumento("CEDULA");
        pasajero1.setNumeroDocumento("1107045881");
        pasajero1.setPrimerNombre("CARLOS");
        pasajero1.setSegundoNombre("ANDRES");
        pasajero1.setPrimerApellido("ROJAS");
        pasajero1.setSegundoApellido("MORALES");
        pasajero1.setTelefono("3268452");
        pasajero1.setDireccion("CALLE 54 65-85");
        pasajero1.setCorreoElectronico("gmorera1987@gmail.com");

        Pasajero pasajero2 = FabricaPersona.getInstance().crearPasajero();
        pasajero2.setTipoDocumento("CEDULA");
        pasajero2.setNumeroDocumento("1107045882");
        pasajero2.setPrimerNombre("ANDREA");
        pasajero2.setSegundoNombre("CAROLINA");
        pasajero2.setPrimerApellido("MORALES");
        pasajero2.setSegundoApellido("RENDON");
        pasajero2.setTelefono("3269875");
        pasajero2.setDireccion("CALLE 12 25-14");
        pasajero2.setCorreoElectronico("gmorera1987@gmail.com");

        Pasajero pasajero3 = FabricaPersona.getInstance().crearPasajero();
        pasajero3.setTipoDocumento("CEDULA");
        pasajero3.setNumeroDocumento("1107045883");
        pasajero3.setPrimerNombre("ANDREA");
        pasajero3.setSegundoNombre("CAROLINA");
        pasajero3.setPrimerApellido("MORALES");
        pasajero3.setSegundoApellido("RENDON");
        pasajero3.setTelefono("3269875");
        pasajero3.setDireccion("CALLE 12 25-14");
        pasajero3.setCorreoElectronico("gmorera1987@gmail.com");

        Reserva uno = new Reserva();
        uno.setPersona(pasajero1);
        uno.setComida(new ComidaEspecial());
        uno.setRuta(ruta2);

        uno.setFecha(new Date());
        uno.setEstado("ACTIVA");
        uno.setNumeroSilla("1");
        uno.setCheck(new ArrayList<>());
        id1 = reservas.size() + 1;
        uno.setId(id1);
        reservas.add(uno);

        Reserva dos = new Reserva();
        dos.setPersona(pasajero2);
        dos.setComida(new ComidaEspecial());
        dos.setRuta(ruta2);
        dos.setFecha(new Date());
        dos.setNumeroSilla("2");
        dos.setEstado("ACTIVA");
        dos.setCheck(new ArrayList<>());
        id2 = reservas.size() + 1;
        dos.setId(id2);
        reservas.add(dos);

        Reserva tres = new Reserva();
        tres.setPersona(pasajero3);
        tres.setComida(new ComidaEspecial());
        tres.setRuta(ruta2);
        tres.setFecha(new Date());
        tres.setEstado("ACTIVA");
        tres.setNumeroSilla("3");
        tres.setCheck(new ArrayList<>());
        id3 = reservas.size() + 1;
        tres.setId(id3);
        reservas.add(tres);
        Aerolinea.getInstance().setReservas(reservas);

        Aerolinea.getInstance().hacerCheckIn(id1);
        Aerolinea.getInstance().hacerCheckIn(id2);
        Aerolinea.getInstance().hacerCheckIn(id3);

    }

}
