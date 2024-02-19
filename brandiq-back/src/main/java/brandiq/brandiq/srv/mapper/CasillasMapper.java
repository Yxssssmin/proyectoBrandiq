package brandiq.brandiq.srv.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasInfo;
import brandiq.brandiq.model.dto.CasillasList;

import org.mapstruct.factory.Mappers;

@Mapper
public interface CasillasMapper {
     
        CasillasMapper INSTANCE = Mappers.getMapper(CasillasMapper.class);

        CasillasEdit casillasEditToCasillasEditDb(CasillasEdit casillaseEdit);

        CasillasEdit cassillasEditDbToCasillasEdit(CasillasEdit save);

        /*
          CasillasDb casillasEditToCasillasDb(CasillasEdit casillasEdit);
          CasillasEdit casillasEditDbToCasillasEdit(CasillasDb casillasDb);
          CasillasEdit casillasDbToCasillasEdit(CasillasDb casillasDb);
        */
}
