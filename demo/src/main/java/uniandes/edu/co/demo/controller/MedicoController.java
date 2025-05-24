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

import uniandes.edu.co.demo.modelo.Medico;
import uniandes.edu.co.demo.repository.MedicoRepository;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/new/save")
    public ResponseEntity<String>  crearMedico (@RequestBody Medico medico) {
        try {

            medicoRepository.insertarMedicos(medico);;
            return new ResponseEntity<>("Medico creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear el medico: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarMedico (@PathVariable("id") String id, @RequestBody  Medico medico) {
        try {
            
            medicoRepository.actualizarMedico(
                id, 
                medico.getNombre(), 
                medico.getTipo_documento(), 
                medico.getNumero_registro(),
                medico.getEspecialidades());
            return new ResponseEntity<>("Medico actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el medico: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Medico>> obtenerMedicos() {
        try {
            List<Medico> medicos = medicoRepository.buscarMedicos();
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Medico>> obtenerMedico(@PathVariable("id") String id) {
        try {
            List<Medico> medico = medicoRepository.buscarMedico(id);
            if (medico != null && !medico.isEmpty()) {
                return ResponseEntity.ok(medico);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> medicoEliminar(@PathVariable("id") String id) {
        try {
            medicoRepository.eliminarMedico(id);
            return new ResponseEntity<>("Afiliado eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
