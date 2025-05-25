package uniandes.edu.co.demo.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;


@ToString
public class OrdenServicio {

    @Id
    private Integer id_Orden;

    private TipoOrden tipo_orden;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime fecha;

    private EstadoOrden estado;
    private Integer cantidad;
    private String descripcion;

    private String medico;

    private String servicio;

    
    public OrdenServicio(LocalDateTime fecha, EstadoOrden estado, String descripcion, String doctor,TipoOrden tipo, String Servicios_ID,Integer cantidad) {
        this.fecha = fecha;
        this.estado = estado;
        this.tipo_orden = tipo;
        this.descripcion = descripcion;
        this.medico = doctor;
        this.servicio = Servicios_ID;
        this.cantidad = cantidad;
    }

    public OrdenServicio(){}

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


    public LocalDateTime getFecha() {
        return fecha;
    }


    public void setFecha(LocalDateTime fecha) {
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


    public String getMedico() {
        return medico;
    }


    public void setMedico(String medico) {
        this.medico = medico;
    }


    public String getServicio() {
        return servicio;
    }


    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    
    
}