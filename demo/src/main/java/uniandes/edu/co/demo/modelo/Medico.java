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
    private String numero_registro;
    private List<String> especialidades;

    public Medico(String nombre, String tipoDocumento, String numeroDocumento, String numeroRegistro, List<String> especialidadest){
        this.nombre = nombre;
        this.tipo_documento = tipoDocumento;
        this.numero_documento = numeroDocumento;
        this.numero_registro = numeroRegistro;
        this.especialidades = especialidadest;
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

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(String numero_registro) {
        this.numero_registro = numero_registro;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    
}
