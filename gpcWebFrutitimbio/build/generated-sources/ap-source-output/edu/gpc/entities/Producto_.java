package edu.gpc.entities;

import edu.gpc.entities.Categoria;
import edu.gpc.entities.DetalleCompra;
import edu.gpc.entities.DetalleVenta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Integer> codigo;
    public static volatile SingularAttribute<Producto, String> nombre;
    public static volatile SingularAttribute<Producto, Categoria> categoriaCodigo;
    public static volatile SingularAttribute<Producto, String> estado;
    public static volatile ListAttribute<Producto, DetalleCompra> detalleCompraList;
    public static volatile SingularAttribute<Producto, Integer> ganancia;
    public static volatile SingularAttribute<Producto, Integer> cantidad;
    public static volatile ListAttribute<Producto, DetalleVenta> detalleVentaList;
    public static volatile SingularAttribute<Producto, Integer> precioVenta;
    public static volatile SingularAttribute<Producto, Integer> precioCompra;

}