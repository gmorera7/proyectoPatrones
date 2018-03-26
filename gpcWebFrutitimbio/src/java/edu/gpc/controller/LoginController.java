package edu.gpc.controller;

import edu.gpc.facade.UserFacade;
import edu.gpc.util.Utilidad;
import edu.gpc.facade.RolLogueadoFacade;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;


@ManagedBean(name = "loginBean")
@SessionScoped

public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;

    private boolean logeado;
    private boolean resultRol;
    private boolean renderBooton;

    private RolLogueadoFacade rolDao;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void loginProject() {
        if (uname.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Advertencia!!!",
                            "Ingresa Nombre Usuario"));
        } else if (password.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Advertencia!!!",
                            "Ingresa la clave"));
        } else {
            String encriptMD5 = DigestUtils.md5Hex(password);
        
            System.out.println("pass: " + password);
            System.out.println("pass: " + encriptMD5);
            
            logeado = UserFacade.login(uname, encriptMD5);
            resultRol = rolDao.usuarioLogueado(uname);
             //System.out.println("usuario rol:::: " + getUname());
            //controlRol.compararRol();
            System.out.println("usuario: " + uname);
            System.out.println("pass: " + password);
            System.out.println("result rol: " + resultRol);
            if (logeado) {
                // get Http Session and store username

                HttpSession session = Utilidad.getSession();
                session.setAttribute("username", uname);
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("./menu.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia!!!",
                                "Por favor comuniquese con el administrador para cambiar su contraseña o ser activado en el sistema!"));
            }
            if (resultRol==true) {
                renderBooton = false;
                System.out.println("usuario rooool: " + resultRol);
            } else {
                renderBooton = true;
                System.out.println("usuario rol en else::::: " + resultRol);
            }
        }
    }

    public void logout() {
        try {
            System.out.println("LOGOUT ");
            HttpSession session = Utilidad.getSession();
            session.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
            logeado = false;
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public boolean isResultRol() {
        return resultRol;
    }

    public void setResultRol(boolean resultRol) {
        this.resultRol = resultRol;
    }

    public boolean isRenderBooton() {
        return renderBooton;
    }

    public void setRenderBooton(boolean renderBooton) {
        this.renderBooton = renderBooton;
    }

}
