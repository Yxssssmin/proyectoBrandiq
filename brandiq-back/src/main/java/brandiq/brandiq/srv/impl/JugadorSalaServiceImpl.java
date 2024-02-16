package brandiq.brandiq.srv.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.repository.JugadorRepository;
import brandiq.brandiq.repository.JugadorSalaRepository;
import brandiq.brandiq.srv.JugadorSalaService;
import brandiq.brandiq.srv.mapper.JugadorSalaMapper;

@Service
public class JugadorSalaServiceImpl implements JugadorSalaService{
    
    private final JugadorSalaRepository jugadorSalaRepository;

    public JugadorSalaServiceImpl(JugadorSalaRepository jugadorSalaRepository) {
        this.jugadorSalaRepository = jugadorSalaRepository;
    }

    @Override
public Optional<JugadorSalaInfo> getJugadorSalaInfoById(Integer id) {
    Optional<JugadorSalaDb> jugadorSalaDb = jugadorSalaRepository.findById(id);

    if (jugadorSalaDb.isPresent()) {
        return Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaDbToJugadorSalaInfo(jugadorSalaDb.get()));
    }

    return Optional.empty();
}


}
