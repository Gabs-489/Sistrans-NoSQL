package uniandes.edu.co.demo.modelo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;


@ToString
public class OrdenServicio {

    @Id
    private Integer id_Orden;

    private TipoOrden tipo_orden;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;

    private EstadoOrden estado;
    private Integer cantidad;
    private String descripcion;
    
    @DBRef
    private Medico medico;

    @DBRef
    private Servicio servicio;

    
    public OrdenServicio(LocalDate fecha, EstadoOrden estado, String descripcion, Medico doctor,TipoOrden tipo, Servicio Servicios_ID, Afiliado afiliados_numero_documento, Integer cantidad) {
        this.fecha = fecha;
        this.estado = estado;
        this.tipo_orden = tipo;
        this.descripcion = descripcion;
        this.medico = doctor;
        this.servicio = Servicios_ID;
        this.cantidad = cantidad;
    }


    public Integer getId_Orden() {
        return id_Orden;
    }


    public void setId_Orden(Integer id_Orden) {
        this.id_Orden = id_Orden;
    }


    public TipoOrden getTipo_orden() {
        return tipo_orden;
    }


    public void setTipo_orden(TipoOrden tipo_orden) {
        this.tipo_orden = tipo_orden;
    }


    public LocalDate getFecha() {
        return fecha;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public EstadoOrden getEstado() {
        return estado;
    }


    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }


    public Integer getCantidad() {
        return cantidad;
    }


    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Medico getMedico() {
        return medico;
    }


    public void setMedico(Medico medico) {
        this.medico = medico;
    }


    public Servicio getServicio() {
        return servicio;
    }


    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    
    
}