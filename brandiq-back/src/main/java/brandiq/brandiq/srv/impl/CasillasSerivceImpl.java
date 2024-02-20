package brandiq.brandiq.srv.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.dto.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasInfo;
import brandiq.brandiq.repository.CasillasEditRepository;
import brandiq.brandiq.repository.CasillasRepository;
import brandiq.brandiq.srv.CasillasService;
import brandiq.brandiq.srv.mapper.CasillasMapper;

@Service
public class CasillasSerivceImpl implements CasillasService {

    private final CasillasRepository casillasRepository;
    private final CasillasEditRepository casillasEditRepository;

    public CasillasSerivceImpl(CasillasRepository casillasRepository, CasillasEditRepository casillasEditRepository) {
        this.casillasRepository = casillasRepository;
        this.casillasEditRepository = casillasEditRepository;
    }

    @Override
    public Optional<CasillasInfo> getCasillasInfoById(Integer id) {
        Optional<CasillasDb> casillasDb = casillasRepository.findById(id);

        if (casillasDb.isPresent()) {
            return Optional.of(CasillasMapper.INSTANCE.casillasDbToCasillasInfo(casillasDb.get()));
        }
        return Optional.empty();
    }

    @Override
    public void save(CasillasDb casillasDb) {
        casillasRepository.save(casillasDb);
    }

    @Override
    public CasillasEdit save(CasillasEdit CasillasEdit) {
        return CasillasMapper.INSTANCE.casillasEditDbToCasillasEdit(
                casillasEditRepository.save(CasillasMapper.INSTANCE.casillasEditToCasillasEditDb(CasillasEdit)));
    }

}
