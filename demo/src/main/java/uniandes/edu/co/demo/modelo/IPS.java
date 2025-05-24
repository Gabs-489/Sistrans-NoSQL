package uniandes.edu.co.demo.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="ips")
@ToString
public class IPS {

    @Id
    private String nit;

    private String nombre;
    private String direccion;
    private String telefono;

    private List<String> servicios = new ArrayList<>();;

    private List<String> medicos = new ArrayList<>();;

    public IPS(String nit, String nombre, String direccion, String telefono){
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public IPS() {}

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<String> getServicios() {
        return servicios;
    }

    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    public List<String> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<String> medicos) {
        this.medicos = medicos;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    

}
