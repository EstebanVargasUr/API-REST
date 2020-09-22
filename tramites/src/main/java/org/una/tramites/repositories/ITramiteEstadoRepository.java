package org.una.tramites.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;

public interface ITramiteEstadoRepository extends JpaRepository<TramiteEstado, Long> {

    public Optional<List<TramiteEstadoDTO>> findByNombreContainingIgnoreCase(String nombreCompleto);
}

