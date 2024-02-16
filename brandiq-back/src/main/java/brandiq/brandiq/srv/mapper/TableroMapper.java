package brandiq.brandiq.srv.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.db.TableroNombreDb;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroInfoNombre;
import brandiq.brandiq.model.dto.TableroList;

@Mapper(uses = JugadorSalaMapper.class)
public interface TableroMapper {
    TableroMapper INSTANCE = Mappers.getMapper(TableroMapper.class);

    TableroEdit tableroDbToTableroEdit(TableroDb tableroDb);

    TableroEdit tableroEditDbToTableroEdit(TableroEditDb tableroEditDb);

    TableroEditDb tableroEditToTableroEditDb(TableroEdit tableroEdit);

    TableroDb tableroEditToTableroDb(TableroEdit tableroEdit);

    TableroList tableroEditDbToTablerList(TableroEditDb tableroEditDb);

    List<TableroList> tablerosEditDbToTablerosList(List<TableroEditDb> tablerosEditDb);

    Set<TableroInfoNombre> tablerosDbToTablerosInfoNombre(Set<TableroDb> tablerosDb);

    TableroInfoNombre tableroNombreDbToTableroInfoNombre(TableroNombreDb tableroNombreDb);

    void updateTableroEditDbFromTableroEdit(TableroEdit tableroEdit, @MappingTarget TableroEditDb tableroEditDb);

    @Mapping(target = "jugadoresSalaInfoNombres", source = "jugadoresSalaNombresDb")
    TableroInfo tableroDbToTableroInfo(TableroDb tableroDb);
}
