package brandiq.brandiq.repository;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.model.db.TableroDb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasillasRepository extends JpaRepository<CasillasDb, Integer> {

    CasillasEdit save(CasillasEdit casillasEditToCasillasDb);

    

/*
    List<CasillasDb> findByTableroDbId(int id_tablero);

    CasillasEdit findByIdTableroAndPosicionXAndPosicionY(TableroDb tableroDb, int posX, int posY);

    CasillasEdit save(CasillasEdit casillasEdit);
*/
  
}
