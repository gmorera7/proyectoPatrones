package edu.gpc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByCodigo", query = "SELECT p FROM Producto p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByCantidad", query = "SELECT p FROM Producto p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Producto.findByPrecioCompra", query = "SELECT p FROM Producto p WHERE p.precioCompra = :precioCompra"),
    @NamedQuery(name = "Producto.findByPrecioVenta", query = "SELECT p FROM Producto p WHERE p.precioVenta = :precioVenta"),
    @NamedQuery(name = "Producto.findByEstado", query = "SELECT p FROM Producto p WHERE p.estado = :estado"),
    @NamedQuery(name = "Producto.findByGanancia", query = "SELECT p FROM Producto p WHERE p.ganancia = :ganancia")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)    
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "precio_compra")
    private Integer precioCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_venta")
    private int precioVenta;
    @Basic(optional = false)
    
    @Size(min = 1, max = 10)
    @Column(name = "estado")
    private String estado;
    @Column(name = "ganancia")
    private Integer ganancia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCodigo")
    private List<DetalleVenta> detalleVentaList;
   
    @JoinColumn(name = "categoria_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Categoria categoriaCodigo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCodigo")
    private List<DetalleCompra> detalleCompraList;

    public Producto() {
    }

    public Producto(Integer codigo) {
        this.codigo = codigo;
    }

    public Producto(Integer codigo, String nombre, int cantidad, int precioVenta, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getGanancia() {
        return ganancia;
    }

    public void setGanancia(Integer ganancia) {
        this.ganancia = ganancia;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public Categoria getCategoriaCodigo() {
        return categoriaCodigo;
    }

    public void setCategoriaCodigo(Categoria categoriaCodigo) {
        this.categoriaCodigo = categoriaCodigo;
    }
   
}
