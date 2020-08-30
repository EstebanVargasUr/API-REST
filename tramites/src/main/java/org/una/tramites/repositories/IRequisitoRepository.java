package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Requisito;

/**
 *
 * @author Esteban Vargas
 */
public interface IRequisitoRepository extends JpaRepository<Requisito, Long>{
    
    public Optional<List<Requisito>>  findByEstado(boolean estado);
    
    public Optional<List<Requisito>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<Requisito> findByVariacionId(Long id);
}
