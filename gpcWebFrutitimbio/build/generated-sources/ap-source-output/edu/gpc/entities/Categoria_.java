package edu.gpc.entities;

import edu.gpc.entities.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-03-23T01:26:28")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, Integer> codigo;
    public static volatile SingularAttribute<Categoria, String> descripcion;
    public static volatile ListAttribute<Categoria, Producto> productoList;

}