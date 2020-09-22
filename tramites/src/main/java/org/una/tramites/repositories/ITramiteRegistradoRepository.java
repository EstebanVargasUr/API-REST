package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author acer
 */
public interface ITramiteRegistradoRepository extends JpaRepository<TramiteRegistrado, Long> {
    
  //   @Query("SELECT u FROM TramiteRegistrado u LEFT JOIN u.tramite_tipo d WHERE d.id=:id")//
    public List<TramiteRegistradoDTO> findByTramiteTipoId(Long id);
     
    // @Query("SELECT u FROM TramiteRegistrado u LEFT JOIN u.cliente d WHERE d.id=:id")//
      public List<TramiteRegistradoDTO> findByClienteId(Long id);
}
