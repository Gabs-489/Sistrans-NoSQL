package uniandes.edu.co.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uniandes.edu.co.demo.modelo.IPS;
import uniandes.edu.co.demo.repository.IPSRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/IPS")
public class IPSController {

    @Autowired
    private IPSRepository ipsRepository;

    @PostMapping("/new/save")
    public ResponseEntity<Map<String, String>>  crearIPS (@RequestBody IPS ips) {
        try {

            ipsRepository.insertarIPS(ips);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Medico creado exitosamente");
            response.put("nit", ips.getNit());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Error al crear el medico: " + e.getMessage());
            response.put("nit", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarIPS(@PathVariable("id") String id, @RequestBody IPS ips) {
        try {
            
            ipsRepository.actualizarIPS(
                id, 
                ips.getNombre(),
                ips.getDireccion(),
                ips.getTelefono(),
                ips.getServicios(),
                ips.getMedicos());
            return new ResponseEntity<>("IPS actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la IPS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<IPS>> obtenerIPSs() {
        try {
            List<IPS> ipss = ipsRepository.buscarIPSs();
            return ResponseEntity.ok(ipss);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<IPS>> obtenerIPS(@PathVariable("id") String id) {
        try {
            List<IPS> ips = ipsRepository.buscarIPS(id);
            if (ips != null && !ips.isEmpty()) {
                return ResponseEntity.ok(ips);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> ipsEliminar(@PathVariable("id") String id) {
        try {
            ipsRepository.eliminarIPS(id);
            return new ResponseEntity<>("IPS eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la IPS", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
