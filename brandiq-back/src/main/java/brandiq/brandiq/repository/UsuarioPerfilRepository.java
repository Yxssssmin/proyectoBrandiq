package brandiq.brandiq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brandiq.brandiq.model.db.UsuarioPerfilDb;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfilDb, Long> {
    
    Optional<UsuarioPerfilDb> findByNickname(String nickname); 

}
