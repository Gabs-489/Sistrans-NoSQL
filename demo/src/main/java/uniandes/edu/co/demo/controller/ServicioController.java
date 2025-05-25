package uniandes.edu.co.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.demo.modelo.Prestacion;
import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.repository.CitasDisponiblesRepository;
import uniandes.edu.co.demo.repository.ServicioRepository;
import uniandes.edu.co.demo.repository.ServiciosSolicitadosRepository;


@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping("/new/save")
    public ResponseEntity<Map<String, String>>  crearServicio (@RequestBody Servicio servicio) {
        try {

            servicioRepository.insertarServicios(servicio);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Servicio creado exitosamente");

            response.put("servicio_id", servicio.getId_servicio());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Error al crear el servicio: " + e.getMessage());
            response.put("servicio_id", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarServicio(@PathVariable("id") String id, @RequestBody Servicio servicio) {
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
    public ResponseEntity<List<Servicio>> obtenerServicio(@PathVariable("id") String id) {
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

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> afiliadoEliminar(@PathVariable("id") String id) {
        try {
            servicioRepository.eliminarServicio(id);
            return new ResponseEntity<>("Servicio eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/prestaciones")
    public ResponseEntity<List<Prestacion>> obtenerDisponibilidad(@PathVariable("id") String id) {
        try {
            List<Servicio> servicios = servicioRepository.buscarServicios(id);
            if (servicios == null || servicios.isEmpty()) {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            } 
            Servicio servicio = servicios.get(0);
            LocalDateTime actual = LocalDateTime.now();
            LocalDateTime enCuatroSemanas = actual.plusWeeks(4);


            List<Prestacion> disponibles = new ArrayList<>();
            for (Prestacion p : servicio.getPrestaciones()) {
            if (!p.getFecha().isBefore(actual) && !p.getFecha().isAfter(enCuatroSemanas)) {
            disponibles.add(p);
            }
        }
            return ResponseEntity.ok(disponibles);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    
    }
    @PostMapping("/{id}/prestaciones/{fecha_hora}/agendar/{afiliado}")
    public ResponseEntity<String> AgendarSinOrden(@PathVariable("id") String id, @PathVariable("fecha_hora") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime fecha_hora, @PathVariable("afiliado") String afiliado) {
        try{
            List<Servicio> servicios = servicioRepository.buscarServicios(id);
            if (servicios == null || servicios.isEmpty()) {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servicio no encontrado con ID: " + id);
            } 
            Servicio servicio = servicios.get(0);
            String tipo = servicio.getTipo_servicio().name();
            if (!tipo.equalsIgnoreCase("CONSULTAMEDICOGENERAL") &&
            !tipo.equalsIgnoreCase("CONSULTAURGENCIA")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El servicio necesita una orden de servicio");
            }

            List<Prestacion> prestaciones = servicio.getPrestaciones();
            for (Prestacion p : prestaciones) {
                if (fecha_hora.equals(p.getFecha()) &&
                    !p.getFinalizado() &&
                    (p.getAfiliado() == null || p.getAfiliado().isEmpty())) {

                    p.setAfiliado(afiliado);
                    servicioRepository.save(servicio);

                    return ResponseEntity.ok("Prestación agendada correctamente: " + p.getId_prestacion());
                }
            }
            
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error al agendar la prestación: " + e.getMessage());
    }
        return null;
    }
    
    @Autowired
    private CitasDisponiblesRepository CitasDisponiblesRepository;

    @GetMapping("/RFC1/{id}")
    public ResponseEntity<List<Document>> obtenerCitasDisponibles(@PathVariable("id") String id) {
        try {
            List<Document> citas = CitasDisponiblesRepository.findCitasDisponibles(id);
            if (citas != null && !citas.isEmpty()) {
                return ResponseEntity.ok(citas);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Autowired
    private ServiciosSolicitadosRepository serviciosSolicitadosRepository;
    
    @GetMapping("/RFC2/{fechaInicio}/{fechaFin}")
    public ResponseEntity<List<Document>> obtenerServiciosSolicitados(
            @PathVariable("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") java.sql.Date fechaInicio,
            @PathVariable("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") java.sql.Date fechaFin) {
        try {
            List<Document> servicios = serviciosSolicitadosRepository.findServiciosSolicitados(fechaInicio, fechaFin);
            if (servicios != null && !servicios.isEmpty()) {
                return ResponseEntity.ok(servicios);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
