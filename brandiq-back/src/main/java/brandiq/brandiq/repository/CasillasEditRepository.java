package brandiq.brandiq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import brandiq.brandiq.model.db.CasillasEditDb;

@Repository
public interface CasillasEditRepository extends JpaRepository<CasillasEditDb, Integer> {
    Optional<CasillasEditDb> findById(Integer id);

    @Query("SELECT c FROM CasillasEditDb c WHERE c.posicionX = :posicionX AND c.posicionY = :posicionY AND c.id_tablero = :id_tablero")
    Optional<CasillasEditDb> findCasillasByPosicionAndTablero(
            @Param("posicionX") int posicionX,
            @Param("posicionY") int posicionY,
            @Param("id_tablero") int id_tablero);
}
