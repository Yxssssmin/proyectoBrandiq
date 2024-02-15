package brandiq.brandiq.security.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.dto.Mensaje;
import brandiq.brandiq.security.dto.JwtDto;
import brandiq.brandiq.security.dto.LoginUsuario;
import brandiq.brandiq.security.dto.NuevoUsuario;
import brandiq.brandiq.security.dto.UsuarioEdit;
import brandiq.brandiq.security.entity.RolDb;
import brandiq.brandiq.security.entity.UsuarioDb;
import brandiq.brandiq.security.enums.RolNombre;
import brandiq.brandiq.security.jwt.JwtService;
import brandiq.brandiq.security.service.RolService;
import brandiq.brandiq.security.service.UsuarioInterface;
import brandiq.brandiq.security.service.UsuarioService;
import brandiq.brandiq.srv.JugadorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin // Al no poner nada más permitimos acceder desde cualquier origen
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtService jwtProvider;

    @Autowired
    JugadorService jugadorService;

    private UsuarioInterface usuarioInterface;

    public AuthController(UsuarioInterface usuarioInterface) {
        this.usuarioInterface = usuarioInterface;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Mensaje("Datos incorrectos o email inválido"));
        }
        if (usuarioService.existsByNickname(nuevoUsuario.getNickname())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El nickname del usuario ya existe"));
        }
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El email del usuario ya existe"));
        }
        UsuarioDb usuarioDb = new UsuarioDb(nuevoUsuario.getNombre(), nuevoUsuario.getNickname(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<RolDb> rolesDb = new HashSet<>();
        rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")) {
            rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuarioDb.setRoles(rolesDb);
        usuarioService.save(usuarioDb);

        /*
         * Aqui pongo un Objeto JugadorDb para que cada usuario que se cree se añada a
         * la tabla jugadores
         */
        JugadorDb jugadorDb = new JugadorDb(nuevoUsuario.getNickname(), usuarioDb, 0, 0, 0, 0);

        /* Y lo guardamos */
        jugadorService.save(jugadorDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario creado"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Datos incorrectos"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNickname(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }

    @PutMapping("update/{nickname}")
    public ResponseEntity<UsuarioEdit> updateUsuarioEdit(@PathVariable(value = "nickname") String nickname,
            @Valid @RequestBody UsuarioEdit usuarioEdit) throws RuntimeException {
        Optional<UsuarioEdit> usuarioEditOriginal = usuarioInterface.getUsuarioEditNickname(nickname);

        if (usuarioEditOriginal.isPresent()) {
            Optional<UsuarioEdit> usuarioEditModificado = usuarioInterface.update(usuarioEdit, nickname);
            return new ResponseEntity<>(usuarioEditModificado.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("USUARIO_NOT_FOUND" + nickname);
        }

    }

    @DeleteMapping("/delete/{nickname}")
    public ResponseEntity<Map<String, String>> deleteByIdUsuario(@PathVariable String nickname) {
        String result = usuarioInterface.deleteByNicknameUsuario(nickname);
        Map<String, String> response = new HashMap<>();

        if ("Usuario eliminado".equals(result)) {
            response.put("message", "Usuario eliminado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "No se pudo eliminar el usuario");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
