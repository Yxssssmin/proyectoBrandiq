package brandiq.brandiq.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.srv.JugadorSalaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
            throw new ResourceNotFoundException("Jugador sala not found on :: " + id);
        }
    }

    @PutMapping("/tirardado/{id_jugador}/{id_tablero}")
    public int updatePosicionesJugadores(@PathVariable(value = "id_jugador") String idJugador,
            @PathVariable(value = "id_tablero") Integer idTablero) {

        /*
         * Optional<JugadorSalaEditDb> jugadorSalaDb =
         * jugadorSalaService.findByIdJugadorAndIdTablero(idJugador,
         * idTablero);
         * 
         * System.out.println(jugadorSalaDb);
         */

        return jugadorSalaService.updatePosicionJugador(idJugador, idTablero);

    }

}
