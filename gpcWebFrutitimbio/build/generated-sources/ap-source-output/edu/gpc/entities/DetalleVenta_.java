package edu.gpc.entities;

import edu.gpc.entities.Producto;
import edu.gpc.entities.Venta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(DetalleVenta.class)
public class DetalleVenta_ { 

    public static volatile SingularAttribute<DetalleVenta, Integer> precioUnitario;
    public static volatile SingularAttribute<DetalleVenta, Date> fecha;
    public static volatile SingularAttribute<DetalleVenta, Producto> productoCodigo;
    public static volatile SingularAttribute<DetalleVenta, Integer> idDetalleVenta;
    public static volatile SingularAttribute<DetalleVenta, Integer> cantidad;
    public static volatile SingularAttribute<DetalleVenta, Venta> ventaCodigo;
    public static volatile SingularAttribute<DetalleVenta, Integer> subtotal;

}