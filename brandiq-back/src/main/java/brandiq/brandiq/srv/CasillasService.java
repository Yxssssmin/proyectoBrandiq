package brandiq.brandiq.srv;

import java.util.Optional;
import org.springframework.stereotype.Service;
import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.dto.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasInfo;

@Service
public interface CasillasService {

    public Optional<CasillasInfo> getCasillasInfoById(Integer id);

    public void save(CasillasDb casillasDb);

    public CasillasEdit save(CasillasEdit CasillasEdit);

}
