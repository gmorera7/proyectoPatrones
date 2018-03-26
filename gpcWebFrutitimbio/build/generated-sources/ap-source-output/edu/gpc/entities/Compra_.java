package edu.gpc.entities;

import edu.gpc.entities.DetalleCompra;
import edu.gpc.entities.Proveedor;
import edu.gpc.entities.TipoPago;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Integer> total;
    public static volatile SingularAttribute<Compra, String> estadoCompra;
    public static volatile ListAttribute<Compra, DetalleCompra> detalleCompraList;
    public static volatile SingularAttribute<Compra, Date> fechaCompra;
    public static volatile SingularAttribute<Compra, Proveedor> proveedorCodigo;
    public static volatile SingularAttribute<Compra, Integer> codigoCompra;
    public static volatile SingularAttribute<Compra, Integer> valorCuota;
    public static volatile SingularAttribute<Compra, Integer> numeroCuotas;
    public static volatile SingularAttribute<Compra, TipoPago> tipoPagoIdtipoPago;

}