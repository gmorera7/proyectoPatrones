package javeriana.edu.co.notificacion;

import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javeriana.edu.co.modelo.aerolinea.Aerolinea;
import javeriana.edu.co.modelo.mensaje.Mensaje;
import javeriana.edu.co.modelo.reserva.Reserva;

/**
 *
 * @author javeriana.edu.co
 */
public class Notificacion implements INotificacion, Observer {

    private static Notificacion instance = null;
    private Reserva reserva;

    
    protected Notificacion() {
    }

    public static Notificacion getInstance() {
        if (instance == null) {
            instance = new Notificacion();
        }
        return instance;
    }

    @Override
    public void notificarEmail(Integer idReserva) {
        Aerolinea.getInstance().buscarReserva(idReserva);
        try {
            enviarCorreo(idReserva);
        } catch (MessagingException ex) {
            Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        Mensaje mensaje = (Mensaje) arg;

        if (mensaje.getAccion().equalsIgnoreCase("buscarReserva")) {
            reserva = (Reserva) mensaje.getObjeto();
            String email = reserva.getPersona().getCorreoElectronico();

        }
    }

    public static void enviarCorreo(Integer idReserva) throws NoSuchProviderException, MessagingException {
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", "gmorera1987@gmail.com");
        props.setProperty("mail.smtp.auth", "true");

        // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);

        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("gmorera1987@gmail.com"));
        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("gmorera1987@gmail.com"));
        message.setSubject("Reserva CheckFood Gastronomic AIR");
        message.setText(
                "Se realizo exitosamente el checkFood para la reserva: "+idReserva+" por favor tu calificacion la puedes realizar en nuestra aplicacion." );

        // Lo enviamos.
        Transport t = session.getTransport("smtp");
        t.connect("gmorera1987@gmail.com", "nicole7");
        t.sendMessage(message, message.getAllRecipients());

        // Cierre.
        t.close();

    }

}
