package uniandes.edu.co.demo.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@ToString
public class Prestacion {

    @Id
    private Integer id_prestacion;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime fecha;

    private Boolean finalizado;

    @DBRef
    private Afiliado afiliado;

    @DBRef
    private OrdenServicio orden;

    @DBRef
    private Medico medico;

    @DBRef
    private IPS ips;


    public Prestacion(LocalDateTime fechat, Boolean finalizadot, Afiliado afiliadot, OrdenServicio ordent, Medico medicot, IPS ipst) {
        this.fecha = fechat;
        this.finalizado = finalizadot;
        this.afiliado = afiliadot;
        this.orden = ordent;
        this.medico = medicot;
        this.ips = ipst;
    }


    public Integer getId_prestacion() {
        return id_prestacion;
    }


    public void setId_prestacion(Integer id_prestacion) {
        this.id_prestacion = id_prestacion;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }


    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    public Boolean getFinalizado() {
        return finalizado;
    }


    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }


    public Afiliado getAfiliado() {
        return afiliado;
    }


    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }


    public OrdenServicio getOrden() {
        return orden;
    }


    public void setOrden(OrdenServicio orden) {
        this.orden = orden;
    }


    public Medico getMedico() {
        return medico;
    }


    public void setMedico(Medico medico) {
        this.medico = medico;
    }
 
}