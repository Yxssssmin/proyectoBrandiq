package brandiq.brandiq.srv;

import java.util.List;

import org.springframework.stereotype.Service;

import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasList;
import jakarta.validation.Valid;

@Service
public interface CasillasService {
 
    public List<CasillasList> findAllCasillasDb(Long id_tablero);

    public CasillasEdit save(@Valid CasillasEdit casillasEdit);
    
    
}
