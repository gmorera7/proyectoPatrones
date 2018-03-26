package edu.gpc.entities;

import edu.gpc.entities.Compra;
import edu.gpc.entities.Municipio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> apellido2;
    public static volatile SingularAttribute<Proveedor, String> direccion;
    public static volatile SingularAttribute<Proveedor, String> tipoIdentificacion;
    public static volatile SingularAttribute<Proveedor, String> apellido1;
    public static volatile SingularAttribute<Proveedor, String> numeroIdentificacion;
    public static volatile SingularAttribute<Proveedor, Integer> codigo;
    public static volatile SingularAttribute<Proveedor, String> numeroTelefono;
    public static volatile SingularAttribute<Proveedor, String> estado;
    public static volatile SingularAttribute<Proveedor, String> nombre2;
    public static volatile SingularAttribute<Proveedor, Municipio> municipio;
    public static volatile SingularAttribute<Proveedor, String> email;
    public static volatile SingularAttribute<Proveedor, String> nombre1;
    public static volatile SingularAttribute<Proveedor, String> departamento;
    public static volatile ListAttribute<Proveedor, Compra> compraList;
    public static volatile SingularAttribute<Proveedor, String> tipoTelefono;

}