package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ParametroGeneral;

/**
 *
 * @author Esteban Vargas
 */
public interface IParametroGeneralService {
    
    public Optional<List<ParametroGeneral>> findAll();

    public Optional<ParametroGeneral> findById(Long id);
    
    public Optional<List<ParametroGeneral>> findByNombreAproximateIgnoreCase(String nombre);
    
    public Optional<List<ParametroGeneral>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public ParametroGeneral create(ParametroGeneral parametroGeneral);

    public Optional<ParametroGeneral> update(ParametroGeneral parametroGeneral, Long id);

    public void delete(Long id);

    public void deleteAll();
}
