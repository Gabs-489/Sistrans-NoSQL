package uniandes.edu.co.demo.repository;

import uniandes.edu.co.demo.modelo.Afiliado;
import uniandes.edu.co.demo.modelo.OrdenServicio;
import uniandes.edu.co.demo.modelo.TipoRelacion;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface AfiliadoRepository extends MongoRepository<Afiliado, String> {

    @Query(value = "{}")
    List<Afiliado> buscarAfiliados();

    @Query("{_id:?0}")
    List<Afiliado> buscarAfiliado (String id);

    default void insertarAfiliado(Afiliado afiliado){
        save(afiliado);
    }

    @Query("{_id: ?0}")
    @Update("{ $set: { nombre: ?1,  tipo_documento: ?2, fecha_nacimiento: ?3, direccion: ?4, telefono: ?5, parentesco: ?6, tipo_relacion: ?7, ordenesServicios: ?8} }")
    void actualizarAfiliado (String id, String nombre, String tipo_documento, LocalDate  fecha_nacimiento, String direccion, String  telefono, String parentesco_numero_documento, TipoRelacion tipo_relacion, List<OrdenServicio> ordenesServicio);

    @Query(value = "{_id:?0}", delete = true)
    void eliminarAfiliado(String id);


    //Obtener Ordenes de servicio
    @Query (value = "{_id: ?0}")
    List<OrdenServicio> obtenerOrdenesServicioAfiliado(String id);

}   
