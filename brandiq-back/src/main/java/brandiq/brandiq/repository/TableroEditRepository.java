package brandiq.brandiq.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import brandiq.brandiq.model.db.TableroEditDb;

@Repository
public interface TableroEditRepository extends JpaRepository<TableroEditDb,Integer> {

    // @Query("SELECT id FROM tableros WHERE id_jugador = 'Pepe' ORDER BY id DESC LIMIT 1")
    // @Query("SELECT MAX(t.id) FROM Tablero t WHERE t.idJugador = :nombreJugador")
    // Long findLastTableroIdByJugador(@Param("nombreJugador") String nombreJugador);


    Optional<TableroEditDb> findById(int id);
}
