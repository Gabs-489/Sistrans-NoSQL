package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uniandes.edu.co.demo.modelo.Afiliado;
import uniandes.edu.co.demo.modelo.OrdenServicio;
import uniandes.edu.co.demo.repository.AfiliadoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/afiliados")
public class AfiliadosController {


    @Autowired
    private AfiliadoRepository afiliadoRepository;

    @PostMapping("/new/save")
    public ResponseEntity<Map<String, String>>  crearAfiliado (@RequestBody Afiliado afiliado) {
        try {

            afiliadoRepository.insertarAfiliado(afiliado);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Afiliado creado exitosamente");
            response.put("numero_documento", afiliado.getNumero_documento());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {;
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Error al crear el afiliado: " + e.getMessage());
            response.put("numero_documento", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarAfiliado(@PathVariable("id") String id, @RequestBody Afiliado afiliado) {
        try {
            
            afiliadoRepository.actualizarAfiliado(
                id, 
                afiliado.getNombre(), 
                afiliado.getTipo_documento(),
                afiliado.getFecha_nacimiento(),
                afiliado.getDireccion(),
                afiliado.getTelefono(),
                afiliado.getParentesco(),
                afiliado.getRelacion(),
                afiliado.getOrdenesServicios());
            return new ResponseEntity<>("Afiliado actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el afiliado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("")
    public ResponseEntity<List<Afiliado>> obtenerAfiliados() {
        try {
            List<Afiliado> afiliados = afiliadoRepository.buscarAfiliados();
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Afiliado>> obtenerAfiliado(@PathVariable("id") String id) {
        try {
            List<Afiliado> afiliado = afiliadoRepository.buscarAfiliado(id);
            if (afiliado != null && !afiliado.isEmpty()) {
                return ResponseEntity.ok(afiliado);
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
            afiliadoRepository.eliminarAfiliado(id);
            return new ResponseEntity<>("Afiliado eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/ordenes")
    public ResponseEntity<String> agregarOrden( @PathVariable String id,@RequestBody OrdenServicio nuevaOrden) {
        try {
            List<Afiliado> afiliadolist = afiliadoRepository.buscarAfiliado(id);
            if (afiliadolist == null || afiliadolist.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Afiliado no encontrado con ID: " + id);
            } 
            Afiliado afiliado = afiliadolist.get(0);
            if (afiliado.getOrdenesServicios() == null) {
            afiliado.setOrdenesServicios(new ArrayList<>());
            }

            int maxId = afiliado.getOrdenesServicios().stream()
            .mapToInt(orden -> orden.getId_Orden() == null ? 0 : orden.getId_Orden())
            .max()
            .orElse(0);

            nuevaOrden.setId_Orden(maxId + 1);

            afiliado.getOrdenesServicios().add(nuevaOrden);
            afiliadoRepository.save(afiliado);
            return ResponseEntity.status(HttpStatus.CREATED).body("Orden registrada con éxito");
                
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la orden de servicio: " + e.getMessage());
        }


    }

    
    
    
}
