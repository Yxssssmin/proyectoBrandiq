package brandiq.brandiq.srv;

import java.util.Optional;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.JugadorSalaInfo;

public interface JugadorSalaService {

    public Optional<JugadorSalaInfo> getJugadorSalaInfoById(Integer id);

    public void save(JugadorSalaDb jugadorSalaDb);

    public JugadorSalaEdit save(JugadorSalaEdit jugadorSalaEdit);

    public Optional<JugadorSalaEdit> getJugadorSalaEditById(Integer id);
}
