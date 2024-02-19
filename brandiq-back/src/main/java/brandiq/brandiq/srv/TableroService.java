package brandiq.brandiq.srv;

import java.util.List;
import java.util.Optional;

import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroList;

public interface TableroService {
    /* PARA HACER EL UPDATE DEL CONTROLLER */
    public Optional<TableroEdit> getTableroEditById(int id);
    
    public TableroEdit save(TableroEdit tableroEdit);
    public Optional<TableroEdit> update(TableroEdit tableroEdit);
    public List<TableroList> findAllTableroList();
    public Optional<TableroInfo> getTableroInfoById(Integer id);
}
