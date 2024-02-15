package brandiq.brandiq.srv.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasInfo;
import brandiq.brandiq.model.dto.CasillasList;
import brandiq.brandiq.repository.CasillasRepository;
import brandiq.brandiq.srv.CasillasService;
import brandiq.brandiq.srv.mapper.CasillasMapper;
import jakarta.validation.Valid;

@Service
public class CasillasSerivceImpl implements CasillasService{

    private CasillasRepository casillasRepository;

    private CasillasMapper casillasMapper;

    @Override
    public List<CasillasList> findAllCasillasDb(Long id_tablero) {
        return CasillasMapper.INSTANCE.casillasDbToCasillasList((List<CasillasDb>) casillasRepository.findByIdTablero(id_tablero));
    }

    @Override
    public CasillasEdit save(@Valid CasillasEdit casillasEdit) {
        CasillasDb casillasDb = CasillasMapper.INSTANCE.casillasEditToCasillasDb(casillasEdit);
        casillasDb = casillasRepository.save(casillasDb);
        return CasillasMapper.INSTANCE.casillasDbToCasillasEdit(casillasDb);
    }

    
    


    
   


    
}
