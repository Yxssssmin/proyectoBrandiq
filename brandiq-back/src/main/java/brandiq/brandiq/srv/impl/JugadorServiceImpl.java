package brandiq.brandiq.srv.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<JugadorInfo> getTopJugadores() {
        List<JugadorDb> topJugadoresDb = jugadorRepository.findTop10ByOrderByPuntosTotalesDescVictoriasDesc();
        return topJugadoresDb.stream()
                .map(JugadorMapper.INSTANCE::jugadorDbToJugadorInfo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<JugadorDb> getJugadorDbByNickname(String nickname) {
        Optional<JugadorDb> jugadorDb = jugadorRepository.findById(nickname);

        if (jugadorDb.isPresent()) {
            return Optional.of(jugadorDb.get());

        }
        return Optional.empty();
    }

}
