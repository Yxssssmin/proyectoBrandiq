package brandiq.brandiq.srv.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brandiq.brandiq.model.db.UsuarioPerfilDb;
import brandiq.brandiq.model.dto.UsuarioPerfilInfo;
import brandiq.brandiq.repository.UsuarioPerfilRepository;
import brandiq.brandiq.srv.UsuarioPerfilService;
import brandiq.brandiq.srv.mapper.UsuarioPerfilMapper;

@Service
public class UsuarioPerfilServiceImpl implements UsuarioPerfilService{
    
    private final UsuarioPerfilRepository usuarioPerfilRepository;
    
    @Autowired
    public UsuarioPerfilServiceImpl(UsuarioPerfilRepository usuarioPerfilRepository){
        this.usuarioPerfilRepository = usuarioPerfilRepository;
    }

    @Override
    public Optional<UsuarioPerfilInfo> getUsuarioPerfilInfoByNickname(String nickname) {
    
        Optional<UsuarioPerfilDb> usuarioPerfilDb = usuarioPerfilRepository.findByNickname(nickname);

        if (usuarioPerfilDb.isPresent()) {
            return Optional.of(UsuarioPerfilMapper.INSTANCE.usuarioPerfilDbToUsuarioPerfilInfo(usuarioPerfilDb.get()));
        }

        return Optional.empty();

    }

}
