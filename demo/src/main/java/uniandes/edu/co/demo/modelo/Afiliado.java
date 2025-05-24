package uniandes.edu.co.demo.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@Document(collection = "afiliados")
@ToString
public class Afiliado {

    @Id
    private String numero_documento;

    private String nombre;
    private String tipo_documento;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate  fecha_nacimiento;
    private String direccion;
    private String  telefono;

    //Parentesco
    private String parentesco;
    
    private TipoRelacion relacion;

    //Orden Servicio
    private List<OrdenServicio> ordenesServicios;

    public Afiliado(String nombre, String tipo_documento, String numero_documento, LocalDate  fecha_nacimiento, String direccion, String  telefono, String parentesco_numero_documento, TipoRelacion relacion){
        this.nombre = nombre;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.parentesco = parentesco_numero_documento;
        this.relacion = relacion;
    }

    public Afiliado(){}


    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
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

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public TipoRelacion getRelacion() {
        return relacion;
    }

    public void setRelacion(TipoRelacion relacion) {
        this.relacion = relacion;
    }

    public List<OrdenServicio> getOrdenesServicios() {
        return ordenesServicios;
    }

    public void setOrdenesServicios(List<OrdenServicio> ordenesServicios) {
        this.ordenesServicios = ordenesServicios;
    }    

    
    
    
}
