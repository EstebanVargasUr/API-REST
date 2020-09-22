package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.entities.ParametroGeneral;

/**
 *
 * @author Esteban Vargas
 */
public interface IParametroGeneralRepository extends JpaRepository<ParametroGeneral, Long>{
    
    public List<ParametroGeneralDTO> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<ParametroGeneralDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
}
