package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.IPS;

public interface IPSRepository extends MongoRepository<IPS, String>{

    @Query(value = "{}")
    List<IPS> buscarIPSs();

    @Query("{_id:?0}")
    List<IPS> buscarIPS (String id);

    default void insertarIPS(IPS ips){
        save(ips);
    }

    @Query("{_id: ?0}")
    @Update("{ $set: { nombre: ?1,  direccion: ?2, telefono: ?3, servicios: ?4, medicos: ?5} }")
    void actualizarIPS (String NIT, String nombre, String direccion, String telefono, List<String> servicios, List<String> medicos);

    @Query(value = "{_id:?0}", delete = true)
    void eliminarIPS(String id);

}
