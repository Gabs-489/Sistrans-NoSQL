package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Medico;

public interface MedicoRepository extends MongoRepository<Medico, String>{

    @Query(value = "{}")
    List<Medico> buscarMedicos();

    @Query("{numero_documento:?0}")
    List<Medico> buscarMedicos (String id);

    default void insertarMedicos(Medico medico){
        save(medico);
    }

    @Query("{numero_documento: ?0}")
    @Update("{ $set: { nombre: ?1,  tipo_documento: ?2, num_registro: ?3, especialidades: ?4} }")
    void actualizarMedico (String numeroDocumento, String nombre, String tipoDocumento,  String numeroRegistro, List<String> especialidades);

    @Query(value = "{numero_documento:?0}", delete = true)
    void eliminarMedico(String id);
}
