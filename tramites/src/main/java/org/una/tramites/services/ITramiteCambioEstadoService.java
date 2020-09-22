package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteCambioEstadoDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface ITramiteCambioEstadoService {
    
    public Optional<List<TramiteCambioEstadoDTO>> findAll();
    
    public Optional<TramiteCambioEstadoDTO> findById(Long id);
    
    public Optional<List<TramiteCambioEstadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO tramiteCambioEstado);

    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramiteCambioEstado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
