package brandiq.brandiq.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.dto.UsuarioPerfilInfo;
import brandiq.brandiq.srv.UsuarioPerfilService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class UsuarioController {

    private UsuarioPerfilService usuarioPerfilService;

    @Autowired
    public UsuarioController(UsuarioPerfilService usuarioPerfilService) {
        this.usuarioPerfilService = usuarioPerfilService;
    }

    @GetMapping("{nickname}/profile")
    public ResponseEntity<UsuarioPerfilInfo> getUsuarioPerfilInfoByNickname(
            @PathVariable(value = "nickname") String nickname) throws RuntimeException {
        Optional<UsuarioPerfilInfo> usuarioPerfilInfo = usuarioPerfilService.getUsuarioPerfilInfoByNickname(nickname);
        if (usuarioPerfilInfo.isPresent()) {
            return ResponseEntity.ok().body(usuarioPerfilInfo.get());
        } else {
            throw new ResourceNotFoundException("Jugador not found on :: " + nickname);
        }
    }

    

}
