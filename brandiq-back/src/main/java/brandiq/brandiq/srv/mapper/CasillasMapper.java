package brandiq.brandiq.srv.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEditDb;
import brandiq.brandiq.model.dto.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasInfo;
import brandiq.brandiq.model.dto.CasillasInfoNombre;
import brandiq.brandiq.model.dto.CasillasList;

@Mapper
public interface CasillasMapper {
  CasillasMapper INSTANCE = Mappers.getMapper(CasillasMapper.class);

  @Mapping(target = "id_tablero", source = "casillasDb.id")
  CasillasList casillasDbToCasillasList(CasillasDb casillasDb);

  List<CasillasList> casillasDbToCasillasList(List<CasillasDb> casillasDb);

  CasillasInfo casillasDbToCasillasInfo(CasillasDb casillasDb);

  Set<CasillasInfoNombre> casillasDbToCasillasInfoNombre(Set<CasillasInfoNombre> casillasInfoNombre);

  CasillasEdit casillasEditDbToCasillasEdit(CasillasEditDb casillasEditDb);

  CasillasEditDb casillasEditToCasillasEditDb(CasillasEdit casillasEdit);
}
