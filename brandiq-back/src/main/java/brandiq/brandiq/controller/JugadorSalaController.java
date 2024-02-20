package brandiq.brandiq.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.dto.JugadorInfo;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.srv.JugadorSalaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class JugadorSalaController {
    
    JugadorSalaService jugadorSalaService;

    private JugadorSalaController(JugadorSalaService jugadorSalaService) {
        this.jugadorSalaService = jugadorSalaService;
    }

    @GetMapping("/jugadorSala/{id}/info")
    public ResponseEntity<JugadorSalaInfo> getJugadorSalaInfoById(
    @PathVariable(value = "id") Integer id) throws RuntimeException {
       Optional<JugadorSalaInfo> jugadorSalaInfo = jugadorSalaService.getJugadorSalaInfoById(id);
        if (jugadorSalaInfo.isPresent()) {
            return ResponseEntity.ok().body(jugadorSalaInfo.get());
        } else {
            throw new ResourceNotFoundException("Jugador sala not found on :: "+id);
        }
    }

}
