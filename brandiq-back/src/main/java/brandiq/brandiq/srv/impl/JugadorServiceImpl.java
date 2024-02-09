package brandiq.brandiq.srv.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.dto.JugadorInfo;
import brandiq.brandiq.repository.JugadorRepository;
import brandiq.brandiq.srv.JugadorService;
import brandiq.brandiq.srv.mapper.JugadorMapper;

@Service
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public Optional<JugadorInfo> getJugadorInfoByNickname(String nickname) {
        Optional<JugadorDb> jugadorDb = jugadorRepository.findById(nickname);

        if (jugadorDb.isPresent()) {
            return Optional.of(JugadorMapper.INSTANCE.jugadorDbToJugadorInfo(jugadorDb.get()));

        }
        return Optional.empty();
    }

    @Override
    public void save(JugadorDb jugadorDb) {
        jugadorRepository.save(jugadorDb);
    }

}
