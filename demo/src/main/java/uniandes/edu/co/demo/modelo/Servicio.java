package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="servicios")
@ToString
public class Servicio {

    @Id
    private String id_servicio;

    private String nombre;
    private TipoServicio tipo_servicio;

    private List<Prestacion> prestaciones;
    
    public Servicio(TipoServicio tipo_servicio, String nombre){
        this.tipo_servicio = tipo_servicio;
        this.nombre = nombre;
    }
    public Servicio(){}

    public String getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(String id_servicio) {
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
