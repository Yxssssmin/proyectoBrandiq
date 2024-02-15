package brandiq.brandiq.repository;

import brandiq.brandiq.model.db.CasillasDb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasillasRepository extends JpaRepository<CasillasDb, Long> {
   
    List<CasillasDb> findByIdTablero(Long id_tablero);

}
