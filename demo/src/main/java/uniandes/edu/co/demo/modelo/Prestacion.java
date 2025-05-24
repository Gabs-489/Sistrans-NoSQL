package uniandes.edu.co.demo.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@ToString
public class Prestacion {

    @Id
    private String id_prestacion;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime fecha;

    private Boolean finalizado;

    private String afiliado;

    private String orden;

    private String medico;

    private String ips;


    public Prestacion(LocalDateTime fecha, Boolean finalizado, String afiliado, String orden, String medico, String ips) {
        this.fecha = fecha;
        this.finalizado = finalizado;
        this.afiliado = afiliado;
        this.orden = orden;
        this.medico = medico;
        this.ips = ips;
    }

    public Prestacion(){}


    public String getId_prestacion() {
        return id_prestacion;
    }


    public void setId_prestacion(String id_prestacion) {
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

    public String getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(String afiliado) {
        this.afiliado = afiliado;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    
    
}