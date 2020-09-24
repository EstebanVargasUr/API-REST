package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;

/**
 *
 * @author Esteban Vargas
 */
public interface IRequisitoRepository extends JpaRepository<RequisitoDTO, Long>{
    
    public Optional<List<RequisitoDTO>>  findByEstado(boolean estado);
    
    public Optional<List<RequisitoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<RequisitoDTO> findByVariacionId(Long id);
}
