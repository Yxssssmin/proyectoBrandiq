package brandiq.brandiq.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.dto.JugadorInfo;

@Mapper(uses = TableroMapper.class)
public interface JugadorMapper {
    JugadorMapper INSTANCE = Mappers.getMapper(JugadorMapper.class);

    // Devuelve un objeto de tipo 'JugadorInfo' a partir de un objeto de tipo
    // 'JugadorDb'
    @Mapping(target = "nickname", source = "usuarioDb.nickname")
    @Mapping(target = "tablerosInfoNombres", source = "tablerosNombresDb")
    JugadorInfo jugadorDbToJugadorInfo(JugadorDb jugadorDb);
}
