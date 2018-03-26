package edu.gpc.entities;

import edu.gpc.entities.Cliente;
import edu.gpc.entities.DetalleVenta;
import edu.gpc.entities.TipoPago;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> codigo;
    public static volatile SingularAttribute<Venta, Integer> total;
    public static volatile SingularAttribute<Venta, Date> fecha;
    public static volatile SingularAttribute<Venta, String> estadoVenta;
    public static volatile ListAttribute<Venta, DetalleVenta> detalleVentaList;
    public static volatile SingularAttribute<Venta, Integer> valorCuota;
    public static volatile SingularAttribute<Venta, Cliente> clienteNit;
    public static volatile SingularAttribute<Venta, Integer> numeroCuotas;
    public static volatile SingularAttribute<Venta, TipoPago> tipoPagoIdtipoPago;

}