package org.una.tramites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author acer
 */
public interface ITramiteRegistradoRepository extends JpaRepository<TramiteRegistrado, Long> {
    
  /*   @Query("SELECT u FROM TramiteRegistrado u LEFT JOIN u.tramite_tipo d WHERE d.id=:id")
     public TramiteRegistrado findByTramiteTipoId(Long id);
     
     @Query("SELECT u FROM TramiteRegistrado u LEFT JOIN u.cliente d WHERE d.id=:id")
     public TramiteRegistrado findByClienteId(Long id);*/
}
