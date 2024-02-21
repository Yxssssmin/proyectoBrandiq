package brandiq.brandiq.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import java.util.List;

@Repository
public interface JugadorSalaEditRepository extends JpaRepository<JugadorSalaEditDb, Integer> {
        Optional<JugadorSalaEditDb> findById(Integer id);

        /* Optional<JugadorSalaEditDb> findById_jugador(String id_jugador); */

        /*
         * Optional<JugadorSalaEditDb> findById_jugadorAndId_tablero(String id_jugador,
         * Integer id_tablero);
         */
        @Query("SELECT j FROM JugadorSalaEditDb j WHERE j.id_jugador = :id_jugador AND j.id_tablero = :id_tablero")
        Optional<JugadorSalaEditDb> findByIdJugadorAndIdTablero(@Param("id_jugador") String id_jugador,
                        @Param("id_tablero") Integer id_tablero);

}
