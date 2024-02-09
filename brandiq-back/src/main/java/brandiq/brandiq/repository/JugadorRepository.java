package brandiq.brandiq.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brandiq.brandiq.model.db.JugadorDb;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorDb, String> {
    Optional<JugadorDb> findById(String id); // El id es el nickname del usuario

    List<JugadorDb> findTop10ByOrderByPuntosTotalesDescVictoriasDesc();

}
