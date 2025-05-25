package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Prestacion;
import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.modelo.TipoServicio;

public interface ServicioRepository extends MongoRepository<Servicio, String>{

    @Query(value = "{}")
    List<Servicio> buscarServicios();

    @Query("{_id:?0}")
    List<Servicio> buscarServicios (String id);

    default void insertarServicios(Servicio servicio){
        save(servicio);
    }

    @Query("{_id: ?0}")
    @Update("{ $set: { tipoServicio: ?1,  nombre: ?2, prestaciones: ?3} }")
    void actualizarServicio (String id_servicio, TipoServicio tipoServicio, String nombre, List<Prestacion> prestaciones);

    @Query(value = "{_id:?0}", delete = true)
    void eliminarServicio(String id);


    //Obtener prestaciones
    @Query (value = "{_id: ?0}")
    List<Prestacion> obtenerPrestaciones(String id);
    
} 