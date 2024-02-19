package brandiq.brandiq.srv.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.repository.JugadorRepository;
import brandiq.brandiq.repository.JugadorSalaEditRepository;
import brandiq.brandiq.repository.JugadorSalaRepository;
import brandiq.brandiq.srv.JugadorSalaService;
import brandiq.brandiq.srv.mapper.JugadorSalaMapper;
import brandiq.brandiq.srv.mapper.TableroMapper;

@Service
public class JugadorSalaServiceImpl implements JugadorSalaService{
    
    private final JugadorSalaRepository jugadorSalaRepository;
    private final JugadorSalaEditRepository jugadorSalaEditRepository;

    public JugadorSalaServiceImpl(JugadorSalaRepository jugadorSalaRepository, JugadorSalaEditRepository jugadorSalaEditRepository) {
        this.jugadorSalaRepository = jugadorSalaRepository;
        this.jugadorSalaEditRepository = jugadorSalaEditRepository;
    }
    

    @Override
public Optional<JugadorSalaInfo> getJugadorSalaInfoById(Integer id) {
    Optional<JugadorSalaDb> jugadorSalaDb = jugadorSalaRepository.findById(id);

    if (jugadorSalaDb.isPresent()) {
        return Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaDbToJugadorSalaInfo(jugadorSalaDb.get()));
    }

    return Optional.empty();
}

    @Override
    public void save(JugadorSalaDb jugadorSalaDb) {
        jugadorSalaRepository.save(jugadorSalaDb);
    }

    @Override
    public JugadorSalaEdit save(JugadorSalaEdit jugadorSalaEdit) {
        return JugadorSalaMapper.INSTANCE.jugadorSalaEditDbToJugadorSalaEdit(
                jugadorSalaEditRepository.save(JugadorSalaMapper.INSTANCE.jugadorSalaEditToJugadorSalaEditDb(jugadorSalaEdit)));
    }

}
