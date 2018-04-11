package javeriana.edu.co.modelo.aerolinea;

import javeriana.edu.co.modelo.usuario.Pasajero;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javeriana.edu.co.modelo.comida.Comida;
import javeriana.edu.co.modelo.comida.ComidaRegular;
import javeriana.edu.co.modelo.comida.FabricaComidaEspecial;
import javeriana.edu.co.modelo.reserva.Reserva;
import javeriana.edu.co.modelo.reserva.Ruta;

/**
 *
 * @author javeriana.edu.co
 */
public class CargaDatos {

    private static CargaDatos instance = null;
    private Ruta ruta1, ruta2, ruta3, ruta4,ruta5,ruta6;
    private String[] tiposDocumento, ciudadesOrigen, ciudadesDestino, tipoComida, comidaEspecial, sillas;
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String stringFechaLlegada = "2018-04-17 09:00";
            String stringFechaSalida = "2018-04-17 08:00";
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
            ruta3.setId(3);
            ruta3.setDestino("CALI");
            ruta3.setDuracionVuelo("1");
            ruta3.setNoVuelo("V3");
            ruta3.setOrigen("TUNJA");
            ruta3.setPrecio(250000L);
            ruta3.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta3.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta3);

            ruta4 = new Ruta();
            ruta4.setId(4);
            ruta4.setDestino("BUCARAMANGA");
            ruta4.setDuracionVuelo("1");
            ruta4.setNoVuelo("V4");
            ruta4.setOrigen("CARACAS");
            ruta4.setPrecio(200000L);
            ruta4.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta4.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta4);
            
            stringFechaLlegada = "2018-04-16 11:00";
            stringFechaSalida = "2018-04-16 10:00";
                        
            ruta5 = new Ruta();
            ruta5.setId(5);
            ruta5.setDestino("CALI");
            ruta5.setDuracionVuelo("1");
            ruta5.setNoVuelo("V5");
            ruta5.setOrigen("ARMENIA");
            ruta5.setPrecio(300000L);
            ruta5.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta5.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta5);
            
            stringFechaLlegada = "2018-04-16 09:00";
            stringFechaSalida = "2018-04-16 08:00";
            
            ruta6 = new Ruta();
            ruta6.setId(6);
            ruta6.setDestino("CALI");
            ruta6.setDuracionVuelo("1");
            ruta6.setNoVuelo("V6");
            ruta6.setOrigen("ARMENIA");
            ruta6.setPrecio(300000L);
            ruta6.setFechaSalida(sdf.parse(stringFechaSalida));
            ruta6.setFechaLlegada(sdf.parse(stringFechaLlegada));
            rutas.add(ruta6);

        } catch (ParseException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }
        Aerolinea.getInstance().setRutas(rutas);

    }

    public String[] cargarCiudadesOrigen() {
        ciudadesOrigen = new String[4];
        ciudadesOrigen[0] = "ARMENIA";
        ciudadesOrigen[1] = "BÓGOTA";
        ciudadesOrigen[2] = "CARACAS";
        ciudadesOrigen[3] = "TUNJA";

        return ciudadesOrigen;

    }

    public String[] cargarCiudadesDestino() {
        ciudadesDestino = new String[4];
        ciudadesDestino[0] = "CALI";
        ciudadesDestino[1] = "PASTO";
        ciudadesDestino[2] = "MEDELLIN";
        ciudadesDestino[3] = "BUCARAMANGA";

        return ciudadesDestino;
    }

    public void cargarReservas() {
        Integer id1, id2, id3;
        
        Comida comida = new ComidaRegular();
        comida.setDescripcion("REGULAR");
        

        Pasajero pasajero1 = new Pasajero();
        pasajero1.setTipoDocumento("CEDULA");
        pasajero1.setNumeroDocumento("1");
        pasajero1.setPrimerNombre("A");
        pasajero1.setSegundoNombre("A");
        pasajero1.setPrimerApellido("A");
        pasajero1.setSegundoApellido("A");
        pasajero1.setTelefono("3268452");
        pasajero1.setDireccion("CALLE 54 65-85");
        pasajero1.setCorreoElectronico("gmorera1987@gmail.com");

        Pasajero pasajero2 = new Pasajero();
        pasajero2.setTipoDocumento("CEDULA");
        pasajero2.setNumeroDocumento("2");
        pasajero2.setPrimerNombre("B");
        pasajero2.setSegundoNombre("B");
        pasajero2.setPrimerApellido("B");
        pasajero2.setSegundoApellido("B");
        pasajero2.setTelefono("3269875");
        pasajero2.setDireccion("CALLE 12 25-14");
        pasajero2.setCorreoElectronico("gmorera1987@gmail.com");

        Pasajero pasajero3 = new Pasajero();
        pasajero3.setTipoDocumento("CEDULA");
        pasajero3.setNumeroDocumento("3");
        pasajero3.setPrimerNombre("C");
        pasajero3.setSegundoNombre("C");
        pasajero3.setPrimerApellido("C");
        pasajero3.setSegundoApellido("C");
        pasajero3.setTelefono("3269875");
        pasajero3.setDireccion("CALLE 12 25-14");
        pasajero3.setCorreoElectronico("gmorera1987@gmail.com");
        
        Pasajero pasajero4 = new Pasajero();
        pasajero4.setTipoDocumento("CEDULA");
        pasajero4.setNumeroDocumento("4");
        pasajero4.setPrimerNombre("D");
        pasajero4.setSegundoNombre("D");
        pasajero4.setPrimerApellido("D");
        pasajero4.setSegundoApellido("D");
        pasajero4.setTelefono("3268496");
        pasajero4.setDireccion("CALLE 58 25-14");
        pasajero4.setCorreoElectronico("gmorera1987@gmail.com");
        
        Pasajero pasajero5 = new Pasajero();
        pasajero5.setTipoDocumento("CEDULA");
        pasajero5.setNumeroDocumento("5");
        pasajero5.setPrimerNombre("E");
        pasajero5.setSegundoNombre("E");
        pasajero5.setPrimerApellido("E");
        pasajero5.setSegundoApellido("E");
        pasajero5.setTelefono("3269875");
        pasajero5.setDireccion("CALLE 12 25-14");
        pasajero5.setCorreoElectronico("gmorera1987@gmail.com");
        
        Pasajero pasajero6 = new Pasajero();
        pasajero6.setTipoDocumento("CEDULA");
        pasajero6.setNumeroDocumento("6");
        pasajero6.setPrimerNombre("F");
        pasajero6.setSegundoNombre("F");
        pasajero6.setPrimerApellido("F");
        pasajero6.setSegundoApellido("F");
        pasajero6.setTelefono("3269874");
        pasajero6.setDireccion("CALLE 50 25-14");
        pasajero6.setCorreoElectronico("gmorera1987@gmail.com");

        Reserva uno = new Reserva();
        uno.setPersona(pasajero1);
        uno.setComida(FabricaComidaEspecial.getInstance().comidaVetariana());
        uno.setRuta(ruta5);
        uno.setFecha(new Date());
        uno.setNumeroSilla("1");
        uno.setCheck(new ArrayList<>());
      
        Reserva dos = new Reserva();
        dos.setPersona(pasajero2);
        dos.setComida(FabricaComidaEspecial.getInstance().comidaBajaProteinas());
        dos.setRuta(ruta5);
        dos.setFecha(new Date());
        dos.setNumeroSilla("2");
        dos.setCheck(new ArrayList<>());
        
        Reserva tres = new Reserva();
        tres.setPersona(pasajero3);
        tres.setComida(comida);
        tres.setRuta(ruta5);
        tres.setFecha(new Date());
        tres.setNumeroSilla("3");
        tres.setCheck(new ArrayList<>());
        
        Reserva cuatro = new Reserva();
        cuatro.setPersona(pasajero4);
        cuatro.setComida(FabricaComidaEspecial.getInstance().comidaVetariana());
        cuatro.setRuta(ruta6);
        cuatro.setFecha(new Date());
        cuatro.setNumeroSilla("4");
        cuatro.setCheck(new ArrayList<>());
        
        
        Reserva cinco = new Reserva();
        cinco.setPersona(pasajero5);
        cinco.setComida(FabricaComidaEspecial.getInstance().comidaBajaProteinas());
        cinco.setRuta(ruta6);
        cinco.setFecha(new Date());
        cinco.setNumeroSilla("5");
        cinco.setCheck(new ArrayList<>());
        
        Reserva seis = new Reserva();
        seis.setPersona(pasajero6);
        seis.setComida(comida);
        seis.setRuta(ruta6);
        seis.setFecha(new Date());
        seis.setNumeroSilla("6");
        seis.setCheck(new ArrayList<>());
        
        Reserva siete = new Reserva();
        siete.setPersona(pasajero4);
        siete.setComida(FabricaComidaEspecial.getInstance().comidaMar());
        siete.setRuta(ruta4);
        siete.setFecha(new Date());
        siete.setNumeroSilla("1");
        siete.setCheck(new ArrayList<>());
        
        Aerolinea.getInstance().hacerReserva(uno);
        Aerolinea.getInstance().hacerReserva(dos);
        Aerolinea.getInstance().hacerReserva(tres);
        Aerolinea.getInstance().hacerReserva(cuatro);
        Aerolinea.getInstance().hacerReserva(cinco);
        Aerolinea.getInstance().hacerReserva(seis);
        Aerolinea.getInstance().hacerReserva(siete);

        Aerolinea.getInstance().hacerCheckIn(1, true);
        Aerolinea.getInstance().hacerCheckIn(2, true);
        Aerolinea.getInstance().hacerCheckIn(3, true);
        Aerolinea.getInstance().hacerCheckIn(4, true);
        Aerolinea.getInstance().hacerCheckIn(5, true);
        Aerolinea.getInstance().hacerCheckIn(6, true);
        Aerolinea.getInstance().hacerCheckIn(7, true);
    }

    public String[] cargarTiposDocumento() {
        tiposDocumento = new String[3];
        tiposDocumento[0] = "CEDULA";
        tiposDocumento[1] = "NIT";
        tiposDocumento[2] = "PASAPORTE";
        return tiposDocumento;
    }

    public String[] cargarMenuTipoComida() {
        tipoComida = new String[2];
        tipoComida[0] = "REGULAR";
        tipoComida[1] = "ESPECIAL";
        return tipoComida;
    }

    public String[] cargarMenuComidaEspecial() {
        comidaEspecial = new String[9];
        comidaEspecial[0] = "COMIDA VEGETARIANA";
        comidaEspecial[1] = "COMIDA DE MAR";
        comidaEspecial[2] = "COMIDA PARA DIABETICOS";
        comidaEspecial[3] = "COMIDA BAJA EN GRASA";
        comidaEspecial[4] = "COMIDA BAJA EN COLESTEROL";
        comidaEspecial[5] = "COMIDA BAJA EN PROTEINAS";
        comidaEspecial[6] = "COMIDA BAJA EN CALORIAS";
        comidaEspecial[7] = "COMIDA LIBRE DE LACTOSA";
        comidaEspecial[8] = "COMIDA PARA NIÑOS";
        return comidaEspecial;
    }

    public String[] cargarMenuSillas() {
        sillas = new String[30];
        for (int i = 0; i < 30; i++) {
            sillas[i] = (i + 1) + "";
        }
        return sillas;
    }

}
