
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteEstadoDTO;


/**
 *
 * @author acer
 */
public interface ITramiteEstadoService {
    public Optional<List<TramiteEstadoDTO>> findAll();
    
    public Optional<TramiteEstadoDTO> findById(Long id);
    
    public Optional<List<TramiteEstadoDTO>> findByNombreContainingIgnoreCase(String nombreCompleto);
    
    public TramiteEstadoDTO create(TramiteEstadoDTO tramite_estado);

    public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramite_estado, Long id);
    
}
