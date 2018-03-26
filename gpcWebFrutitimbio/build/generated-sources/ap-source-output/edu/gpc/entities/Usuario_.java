package edu.gpc.entities;

import edu.gpc.entities.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> usuariocreacion;
    public static volatile SingularAttribute<Usuario, Date> fechacreacion;
    public static volatile SingularAttribute<Usuario, String> estado;
    public static volatile SingularAttribute<Usuario, String> usuariomodificacion;
    public static volatile SingularAttribute<Usuario, Rol> rolId;
    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, Date> fechamodificacion;

}