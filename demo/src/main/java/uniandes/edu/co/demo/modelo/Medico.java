package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="medicos")
@ToString
public class Medico {

    private String nombre;
    private String tipo_documento;
    @Id
    private String numero_documento;
    private String num_registro;
    private List<String> especialidades;

    public Medico(String nombre, String tipo_documento, String numero_documento, String num_registro, List<String> especialidades){
        this.nombre = nombre;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.num_registro = num_registro;
        this.especialidades = especialidades;
    }

    public Medico(){}

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

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    public String getNum_registro() {
        return num_registro;
    }

    public void setNum_registro(String num_registro) {
        this.num_registro = num_registro;
    }

    
}
