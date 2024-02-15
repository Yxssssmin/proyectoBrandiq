package brandiq.brandiq.srv;

import java.util.List;
import java.util.Optional;

import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.dto.JugadorInfo;

public interface JugadorService {

    public Optional<JugadorDb> getJugadorDbByNickname(String nickname);

    public Optional<JugadorInfo> getJugadorInfoByNickname(String nickname);

    public void save(JugadorDb jugadorDb);

    List<JugadorInfo> getTopJugadores();

}
