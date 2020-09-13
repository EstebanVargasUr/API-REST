package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteCambioEstado;

/**
 *
 * @author Esteban Vargas
 */
public interface ITramiteCambioEstadoService {
    
    public Optional<List<TramiteCambioEstado>> findAll();
    
    public Optional<TramiteCambioEstado> findById(Long id);
    
    public Optional<List<TramiteCambioEstado>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public TramiteCambioEstado create(TramiteCambioEstado tramiteCambioEstado);

    public Optional<TramiteCambioEstado> update(TramiteCambioEstado tramiteCambioEstado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
