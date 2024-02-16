package brandiq.brandiq.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.model.dto.JugadorSalaList;

@Mapper
public interface JugadorSalaMapper {
    JugadorSalaMapper INSTANCE = Mappers.getMapper(JugadorSalaMapper.class);

    @Mapping(target = "id_jugador", source = "jugadorDb.id")
    JugadorSalaList jugadorSalaDbToJugadorSalaList(JugadorSalaDb jugadorSalaDb);

    List<JugadorSalaList> jugadoresSalaDbToJugadoresSalaList(List<JugadorSalaDb> jugadorSalaDb);

    //Devuelve un objeto de tipo 'JugadorSalaInfo' a partir de un objeto de tipo 'JugadorSalaDb'
	JugadorSalaInfo jugadorSalaDbToJugadorSalaInfo(JugadorSalaDb jugadorSalaDb);
}
