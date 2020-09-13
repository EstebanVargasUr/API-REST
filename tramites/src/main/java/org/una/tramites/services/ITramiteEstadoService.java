
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteEstado;


/**
 *
 * @author acer
 */
public interface ITramiteEstadoService {
    public Optional<List<TramiteEstado>> findAll();
    
    public Optional<TramiteEstado> findById(Long id);
    
    public Optional<List<TramiteEstado>> findByNombreContainingIgnoreCase(String nombreCompleto);
    
    public TramiteEstado create(TramiteEstado tramite_estado);

    public Optional<TramiteEstado> update(TramiteEstado tramite_estado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
