package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.TramiteCambioEstado;

public interface ITramiteCambioEstadoRepository extends JpaRepository<TramiteCambioEstado, Long> {
    
    public Optional<List<TramiteCambioEstado>> findByFechaRegistroBetween(Date startDate, Date endDate);
}
