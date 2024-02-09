package brandiq.brandiq.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import brandiq.brandiq.security.entity.UsuarioDb;
import brandiq.brandiq.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<UsuarioDb> getByNickname(String nickname) {
        return usuarioRepository.findByNickname(nickname);
    }

    public boolean existsByNickname(String nickname) {
        return usuarioRepository.existsByNickname(nickname);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void save(@NonNull UsuarioDb usuario) {
        usuarioRepository.save(usuario);
    }
}
