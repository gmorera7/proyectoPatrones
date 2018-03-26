package edu.gpc.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rigo
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByCodigoCompra", query = "SELECT c FROM Compra c WHERE c.codigoCompra = :codigoCompra"),
    @NamedQuery(name = "Compra.findByFechaCompra", query = "SELECT c FROM Compra c WHERE c.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "Compra.findByNumeroCuotas", query = "SELECT c FROM Compra c WHERE c.numeroCuotas = :numeroCuotas"),
    @NamedQuery(name = "Compra.findByValorCuota", query = "SELECT c FROM Compra c WHERE c.valorCuota = :valorCuota"),
    @NamedQuery(name = "Compra.findByEstadoCompra", query = "SELECT c FROM Compra c WHERE c.estadoCompra = :estadoCompra")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_compra")
    private Integer codigoCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @Column(name = "numero_cuotas")
    private Integer numeroCuotas;
    @Column(name = "valor_cuota")
    private Integer valorCuota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado_compra")
    private String estadoCompra;
    @JoinColumn(name = "tipo_pago_idtipo_pago", referencedColumnName = "idtipo_pago")
    @ManyToOne(optional = false)
    private TipoPago tipoPagoIdtipoPago;
    @JoinColumn(name = "proveedor_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Proveedor proveedorCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraCodigoCompra")
    private List<DetalleCompra> detalleCompraList;
    @Column(name = "total")
    private int total;

    public Compra() {
    }

    public Compra(Integer codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public Compra(Integer codigoCompra, Date fechaCompra, String estadoCompra) {
        this.codigoCompra = codigoCompra;
        this.fechaCompra = fechaCompra;
        this.estadoCompra = estadoCompra;
    }

    public Integer getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(Integer codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public String getFechaCompra() {
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        fechaCompra = new Date();
        return dateFormat.format(fechaCompra);
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public Integer getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(Integer valorCuota) {
        this.valorCuota = valorCuota;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public TipoPago getTipoPagoIdtipoPago() {
        return tipoPagoIdtipoPago;
    }

    public void setTipoPagoIdtipoPago(TipoPago tipoPagoIdtipoPago) {
        this.tipoPagoIdtipoPago = tipoPagoIdtipoPago;
    }

    public Proveedor getProveedorCodigo() {
        return proveedorCodigo;
    }

    public void setProveedorCodigo(Proveedor proveedorCodigo) {
        this.proveedorCodigo = proveedorCodigo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @XmlTransient
    public List<DetalleCompra> getDetalleCompraList() {
        return detalleCompraList;
    }

    public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
        this.detalleCompraList = detalleCompraList;
    }
}
