package brandiq.brandiq.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import brandiq.brandiq.model.db.UsuarioPerfilDb;
import brandiq.brandiq.model.dto.UsuarioPerfilEdit;
import brandiq.brandiq.model.dto.UsuarioPerfilInfo;

@Mapper
public interface UsuarioPerfilMapper {
    UsuarioPerfilMapper INSTANCE= Mappers.getMapper(UsuarioPerfilMapper.class);

    //Devuelve un objeto de tipo 'UsuarioPerfilInfo' a partir de un objeto de tipo 'UsuarioPerfilDb'
    UsuarioPerfilInfo usuarioPerfilDbToUsuarioPerfilInfo(UsuarioPerfilDb usuarioPerfilDb);

    //Devuelve un objeto de tipo 'UsuarioPerfilEdit' a partir de un objeto de tipo 'UsuarioPerfilDb'
    UsuarioPerfilEdit usuarioPerfilDbToUsuarioPerfilEdit(UsuarioPerfilDb usuarioPerfilDb);

    //Devuelve un objeto de tipo 'UsuarioPerfilDb' a partir de un objeto de tipo 'UsuarioPerfilEdit'
    UsuarioPerfilDb ciudadEditToCiudadDb(UsuarioPerfilEdit usuarioPerfilEdit);
}
