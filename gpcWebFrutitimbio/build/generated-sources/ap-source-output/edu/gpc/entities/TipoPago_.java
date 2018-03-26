package edu.gpc.entities;

import edu.gpc.entities.Compra;
import edu.gpc.entities.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(TipoPago.class)
public class TipoPago_ { 

    public static volatile SingularAttribute<TipoPago, String> codigo;
    public static volatile SingularAttribute<TipoPago, Integer> idtipoPago;
    public static volatile SingularAttribute<TipoPago, String> destipoPago;
    public static volatile ListAttribute<TipoPago, Venta> ventaList;
    public static volatile ListAttribute<TipoPago, Compra> compraList;

}