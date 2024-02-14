package brandiq.brandiq.security.service;

import java.util.Optional;

import brandiq.brandiq.security.dto.UsuarioEdit;

public interface UsuarioInterface {
    public Optional<UsuarioEdit> getUsuarioEditNickname(String nickname);

    public Optional<UsuarioEdit> update(UsuarioEdit usuarioEdit, String nickname);

    public String deleteByNicknameUsuario(String nickname);
}
