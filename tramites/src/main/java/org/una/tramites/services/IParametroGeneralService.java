package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ParametroGeneralDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface IParametroGeneralService {
    
    public Optional<List<ParametroGeneralDTO>> findAll();

    public Optional<ParametroGeneralDTO> findById(Long id);
    
    public Optional<List<ParametroGeneralDTO>> findByNombreAproximateIgnoreCase(String nombre);
    
    public Optional<List<ParametroGeneralDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneral);

    public Optional<ParametroGeneralDTO> update(ParametroGeneralDTO parametroGeneral, Long id);

    public void delete(Long id);

    public void deleteAll();
}
