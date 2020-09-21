package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.Variacion;

/**
 *
 * @author Esteban Vargas
 */
public interface IVariacionRepository extends JpaRepository<Variacion, Long>{
    
    public Optional<List<Variacion>>  findByEstado(boolean estado);
    
    public Optional<List<Variacion>>  findByGrupo(boolean grupo);
    
    public Optional<List<Variacion>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    @Query("SELECT u FROM Variacion u LEFT JOIN u.tramiteTipo d WHERE d.id=:id")
    public Variacion findByTramite_tipoId(Long id);
}
