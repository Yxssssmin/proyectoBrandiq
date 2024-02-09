package brandiq.brandiq.security.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.security.dto.UsuarioEdit;
import brandiq.brandiq.security.entity.UsuarioDb;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioEdit usuarioDbToUsuarioEdit(UsuarioDb usuarioDb);

    UsuarioDb usuarioEditToUsuarioDb(UsuarioEdit usuarioEdit);

    void updateUsuarioDbFromUsuarioEdit(UsuarioEdit usuarioEdit, @MappingTarget UsuarioDb usuarioDb);
}
