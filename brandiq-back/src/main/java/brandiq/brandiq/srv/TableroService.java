package brandiq.brandiq.srv;

import java.util.List;
import java.util.Optional;

import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroList;

public interface TableroService {
    public TableroEdit save(TableroEdit tableroEdit);
    public Optional<TableroEdit> update(TableroEdit tableroEdit);
    public List<TableroList> findAllTableroList();
}
