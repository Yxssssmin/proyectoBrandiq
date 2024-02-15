package brandiq.brandiq.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasList;

import org.mapstruct.factory.Mappers;

@Mapper
public interface CasillasMapper {
        CasillasMapper INSTANCE = Mappers.getMapper(CasillasMapper.class);

        List<CasillasList> casillasDbToCasillasList(List<CasillasDb> casillasDbs);

        CasillasDb casillasEditToCasillasDb(CasillasEdit casillasEdit);

        CasillasEdit casillasDbToCasillasEdit(CasillasDb casillasDb);


}
