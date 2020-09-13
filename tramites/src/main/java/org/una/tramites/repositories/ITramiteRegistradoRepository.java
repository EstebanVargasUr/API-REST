/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author acer
 */
public interface ITramiteRegistradoRepository extends JpaRepository<TramiteRegistrado, Long> {
    
  //   public Optional<List<TramiteRegistrado>> findByTramiteTipoId(Long id);
   //  public Optional<List<TramiteRegistrado>> findByClienteId(Long id);
}
