package brandiq.brandiq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.dto.JugadorInfo;
import brandiq.brandiq.srv.JugadorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class JugadorController {
    private JugadorService jugadorService;

    private JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping("{nickname}/estadisticas")
    public ResponseEntity<JugadorInfo> getJugadorInfoByNickname(@PathVariable(value = "nickname") String nickname) {
        Optional<JugadorInfo> jugadorInfo = jugadorService.getJugadorInfoByNickname(nickname);
        if (jugadorInfo.isPresent()) {
            return ResponseEntity.ok().body(jugadorInfo.get());
        } else {
            throw new ResourceNotFoundException("Jugador not found on ::" + nickname);
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<JugadorInfo>> getTopJugadores() {
        List<JugadorInfo> topJugadores = jugadorService.getTopJugadores();
        if (!topJugadores.isEmpty()) {
            return ResponseEntity.ok(topJugadores);
        } else {
            return ResponseEntity.noContent().build(); // Código 204 si la lista está vacía
        }
    }
}
