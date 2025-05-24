package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Prestacion;
import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.modelo.TipoServicio;

public interface ServicioRepository extends MongoRepository<Servicio, Integer>{

    @Query(value = "{}")
    List<Servicio> buscarServicios();

    @Query("{_id:?0}")
    List<Servicio> buscarServicios (Integer id);

    default void insertarServicios(Servicio servicio){
        save(servicio);
    }

    @Query("{id: ?0}")
    @Update("{ $set: { tipoServicio: ?1,  nombre: ?2, prestaciones: ?3} }")
    void actualizarAfiliado (Integer id_servicio, TipoServicio tipoServicio, String nombre, List<Prestacion> prestaciones);

    @Query(value = "{_id:?0}", delete = true)
    void eliminarServicio(Integer id);


    //Obtener Ordenes de servicio
    @Query (value = "{_id: ?0}")
    List<Prestacion> obtenerPrestaciones(Integer id);
    
} 