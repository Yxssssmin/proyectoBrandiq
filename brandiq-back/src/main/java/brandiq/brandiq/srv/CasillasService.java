package brandiq.brandiq.srv;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.dto.CasillasList;
import jakarta.validation.Valid;

@Service
public interface CasillasService {

    CasillasEdit save(CasillasEdit casillasEdit);

    public CasillasDb crearAllCasillasDb(Integer id_tablero) throws IOException;
   
}
