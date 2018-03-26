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
 * @author Anita
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByCodigo", query = "SELECT v FROM Venta v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Venta.findByNumeroCuotas", query = "SELECT v FROM Venta v WHERE v.numeroCuotas = :numeroCuotas"),
    @NamedQuery(name = "Venta.findByValorCuota", query = "SELECT v FROM Venta v WHERE v.valorCuota = :valorCuota"),
    @NamedQuery(name = "Venta.findByEstadoVenta", query = "SELECT v FROM Venta v WHERE v.estadoVenta = :estadoVenta")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "numero_cuotas")
    private Integer numeroCuotas;
    @Column(name = "valor_cuota")
    private Integer valorCuota;
    @Size(max = 45)
    @Column(name = "estado_venta")
    private String estadoVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaCodigo")
    private List<DetalleVenta> detalleVentaList;
    @JoinColumn(name = "tipo_pago_idtipo_pago", referencedColumnName = "idtipo_pago")
    @ManyToOne(optional = false)
    private TipoPago tipoPagoIdtipoPago;
    @JoinColumn(name = "cliente_nit", referencedColumnName = "nit")
    @ManyToOne(optional = false)
    private Cliente clienteNit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;

    public Venta() {
    }

    public Venta(Integer codigo) {
        this.codigo = codigo;
    }

    public Venta(Integer codigo, Date fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        fecha = new Date();
        return dateFormat.format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public TipoPago getTipoPagoIdtipoPago() {
        return tipoPagoIdtipoPago;
    }

    public void setTipoPagoIdtipoPago(TipoPago tipoPagoIdtipoPago) {
        this.tipoPagoIdtipoPago = tipoPagoIdtipoPago;
    }

    public Cliente getClienteNit() {
        return clienteNit;
    }

    public void setClienteNit(Cliente clienteNit) {
        this.clienteNit = clienteNit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
