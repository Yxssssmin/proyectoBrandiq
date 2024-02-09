package brandiq.brandiq.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brandiq.brandiq.security.entity.RolDb;
import brandiq.brandiq.security.enums.RolNombre;

public interface RolRepository extends JpaRepository<RolDb, Integer>{
    Optional<RolDb> findByNombre(RolNombre rolNombre);
}
