package brandiq.brandiq.srv;

import java.util.Optional;

import brandiq.brandiq.model.dto.JugadorSalaInfo;

public interface JugadorSalaService {

    public Optional<JugadorSalaInfo> getJugadorSalaInfoById(Integer id);
    
}
