package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Permiso;

public interface IPermisoRepository extends JpaRepository<PermisoDTO, Long> {
    
public Optional<PermisoDTO> findByCodigo(String codigo);

public Optional<List<PermisoDTO>> findByEstado(boolean estado);

public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

public Long countByEstado(boolean estado);
    
public Long countById(boolean Id);
}
