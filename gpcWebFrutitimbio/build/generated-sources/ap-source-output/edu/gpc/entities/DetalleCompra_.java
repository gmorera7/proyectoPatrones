package edu.gpc.entities;

import edu.gpc.entities.Compra;
import edu.gpc.entities.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(DetalleCompra.class)
public class DetalleCompra_ { 

    public static volatile SingularAttribute<DetalleCompra, Integer> iddetalleCompra;
    public static volatile SingularAttribute<DetalleCompra, Integer> total;
    public static volatile SingularAttribute<DetalleCompra, Compra> compraCodigoCompra;
    public static volatile SingularAttribute<DetalleCompra, Integer> precioUnitario;
    public static volatile SingularAttribute<DetalleCompra, Producto> productoCodigo;
    public static volatile SingularAttribute<DetalleCompra, Integer> cantidad;
    public static volatile SingularAttribute<DetalleCompra, Integer> subtotal;

}