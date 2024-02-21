package brandiq.brandiq.srv.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.JugadorSalaNombreDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.model.dto.JugadorSalaInfoNombre;
import brandiq.brandiq.model.dto.JugadorSalaList;

@Mapper
public interface JugadorSalaMapper {
    JugadorSalaMapper INSTANCE = Mappers.getMapper(JugadorSalaMapper.class);

    @Mapping(target = "id_jugador", source = "jugadorDb.id")
    JugadorSalaList jugadorSalaDbToJugadorSalaList(JugadorSalaDb jugadorSalaDb);

    List<JugadorSalaList> jugadoresSalaDbToJugadoresSalaList(List<JugadorSalaDb> jugadorSalaDb);

    // Devuelve un objeto de tipo 'JugadorSalaInfo' a partir de un objeto de tipo
    // 'JugadorSalaDb'
    JugadorSalaInfo jugadorSalaDbToJugadorSalaInfo(JugadorSalaDb jugadorSalaDb);

    JugadorSalaEdit jugadorSalaDbToJugadorSalaEdit(JugadorSalaDb jugadorSalaDb);

    Set<JugadorSalaInfoNombre> jugadoresSalaDbToJugadoresSalaInfoNombre(Set<JugadorSalaNombreDb> jugadoresSalaNombreDb);

    JugadorSalaEdit jugadorSalaEditDbToJugadorSalaEdit(Optional<JugadorSalaEditDb> jugadorSalaEditDb);

    JugadorSalaEdit jugadorSalaEditDbToJugadorSalaEdit(JugadorSalaEditDb jugadorSalaEditDb);

    JugadorSalaEditDb jugadorSalaEditToJugadorSalaEditDb(JugadorSalaEdit jugadorSalaEdit);

    void updateJugadorSalaEditDbFromJugadorSalaEdit(JugadorSalaEdit jugadorSalaEdit,
            @MappingTarget JugadorSalaEditDb jugadorSalaEditDb);
}
