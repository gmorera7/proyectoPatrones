package edu.gpc.entities;

import edu.gpc.entities.Municipio;
import edu.gpc.entities.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, String> numeroTelefono;
    public static volatile ListAttribute<Cliente, Venta> ventaList;
    public static volatile SingularAttribute<Cliente, String> estado;
    public static volatile SingularAttribute<Cliente, Municipio> municipio;
    public static volatile SingularAttribute<Cliente, String> email;
    public static volatile SingularAttribute<Cliente, String> departamento;
    public static volatile SingularAttribute<Cliente, Integer> nit;
    public static volatile SingularAttribute<Cliente, String> tipoTelefono;
    public static volatile SingularAttribute<Cliente, String> representanteLegal;

}