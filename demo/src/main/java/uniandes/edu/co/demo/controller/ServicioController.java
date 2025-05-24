package uniandes.edu.co.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.repository.ServicioRepository;


@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping("/new/save")
    public ResponseEntity<String>  crearServicio (@RequestBody Servicio servicio) {
        try {

            servicioRepository.insertarServicios(servicio);;
            return new ResponseEntity<>("Servicio creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear el servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarServicio(@PathVariable("id") Integer id, @RequestBody Servicio servicio) {
        try {
            servicioRepository.actualizarServicio(
                id, 
                servicio.getTipo_servicio(), 
                servicio.getNombre(), 
                servicio.getPrestaciones());
            return new ResponseEntity<>("Servicio actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Servicio>> obtenerServicios() {
        try {
            List<Servicio> servicios = servicioRepository.buscarServicios();
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Servicio>> obtenerServicio(@PathVariable("id") Integer id) {
        try {
            List<Servicio> servicio = servicioRepository.buscarServicios(id);
            if (servicio != null && !servicio.isEmpty()) {
                return ResponseEntity.ok(servicio);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> afiliadoEliminar(@PathVariable("id") Integer id) {
        try {
            servicioRepository.eliminarServicio(id);
            return new ResponseEntity<>("Servicio eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
