package brandiq.brandiq.security.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.security.dto.UsuarioEdit;
import brandiq.brandiq.security.entity.UsuarioDb;
import brandiq.brandiq.security.repository.UsuarioRepository;
import brandiq.brandiq.security.service.UsuarioInterface;
import brandiq.brandiq.security.service.mapper.UsuarioMapper;

@Service
public class UsuarioServiceImpl implements UsuarioInterface {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<UsuarioEdit> getUsuarioEditNickname(String nickname) {
        Optional<UsuarioDb> usuarioDb = usuarioRepository.findByNickname(nickname);

        if (usuarioDb.isPresent()) {
            return Optional.of(UsuarioMapper.INSTANCE.usuarioDbToUsuarioEdit(usuarioDb.get()));
        } else {
            return Optional.empty();
        }
    }

    /* @Override
    public Optional<UsuarioEdit> update(UsuarioEdit usuarioEdit) {
        Optional<UsuarioDb> usuarioDb = usuarioRepository.findByNickname(usuarioEdit.getNickname());

        if (usuarioDb.isPresent()) {
            return Optional.of(UsuarioMapper.INSTANCE.usuarioDbToUsuarioEdit(
                    usuarioRepository.save(UsuarioMapper.INSTANCE.usuarioEditToUsuarioDb(usuarioEdit))));
        } else {
            throw new ResourceNotFoundException("USUARIO_NOT_FOUND" + usuarioEdit.getNickname() + " not found");
        }
    } */

    @Override
    public Optional<UsuarioEdit> update(UsuarioEdit usuarioEdit, String nickname) {
    Optional<UsuarioDb> usuarioDb = usuarioRepository.findByNickname(nickname);

    if (usuarioDb.isPresent()) {
        UsuarioMapper.INSTANCE.updateUsuarioDbFromUsuarioEdit(usuarioEdit, usuarioDb.get());
        return Optional.of(UsuarioMapper.INSTANCE.usuarioDbToUsuarioEdit(usuarioRepository.save(usuarioDb.get())));
    } else {
        throw new ResourceNotFoundException("Usuario con apodo " + nickname + " no encontrado");
    }
    }

    @Override
    public String deleteByNicknameUsuario(String nickname) {
        Optional<UsuarioDb> usuarioDb = usuarioRepository.findByNickname(nickname);

        if (usuarioDb.isPresent()) {
            usuarioRepository.delete(usuarioDb.get());
            return "Usuario eliminado";
        } else {
            return "Usuario no encontrado";
        }
    }

}
