package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="servicios")
@ToString
public class Servicio {

    @Id
    private Integer id_servicio;

    private String nombre;
    private TipoServicio tipo_servicio;

    private List<Prestacion> prestaciones;
    
    public Servicio(Integer id_servicio, TipoServicio tipoServicio, String nombre){
        this.id_servicio = id_servicio;
        this.tipo_servicio = tipoServicio;
        this.nombre = nombre;
    }

    public Integer getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoServicio getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(TipoServicio tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public List<Prestacion> getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(List<Prestacion> prestaciones) {
        this.prestaciones = prestaciones;
    }   

    
    
}
