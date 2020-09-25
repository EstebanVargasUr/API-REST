package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.TramiteTipo;

public interface ITramiteTipoRepository extends JpaRepository<TramiteTipo, Long> {

    public Optional<List<TramiteTipo>> findByEstado(boolean estado);
    
    public Optional<List<TramiteTipo>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin);
    
    public List<TramiteTipo> findByDepartamentoId(Long id);
}
