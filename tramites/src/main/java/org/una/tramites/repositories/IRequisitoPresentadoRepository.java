package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.RequisitoPresentado;

public interface IRequisitoPresentadoRepository extends JpaRepository<RequisitoPresentado, Long> {
    
   public Optional<List<RequisitoPresentado>> findByFechaRegistroBetween(Date startDate, Date endDate);
}

