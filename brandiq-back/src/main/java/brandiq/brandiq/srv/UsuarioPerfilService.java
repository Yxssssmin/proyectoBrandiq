package brandiq.brandiq.srv;

import java.util.Optional;

import brandiq.brandiq.model.dto.UsuarioPerfilInfo;

public interface UsuarioPerfilService {
    
    public Optional<UsuarioPerfilInfo> getUsuarioPerfilInfoByNickname(String nickname);

}
