package brandiq.brandiq.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import brandiq.brandiq.security.entity.RolDb;
import brandiq.brandiq.security.enums.RolNombre;
import brandiq.brandiq.security.repository.RolRepository;



@Service
@Transactional // Mantiene la coherencia de la BD si hay varios accesos
public class RolService {
    
    @Autowired
    RolRepository rolRepository;

    public Optional<RolDb> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByNombre(rolNombre);
    }

    public void save(@NonNull RolDb rol) {
        rolRepository.save(rol);
    }
}
