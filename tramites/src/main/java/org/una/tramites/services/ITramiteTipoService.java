
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteTipo;

/**
 *
 * @author acer
 */
public interface ITramiteTipoService {
    public Optional<List<TramiteTipo>> findAll();

    public Optional<TramiteTipo> findById(Long id);

    public Optional<List<TramiteTipo>> findByEstado(boolean estado);
    
    public Optional<List<TramiteTipo>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin);

    public Optional<List<TramiteTipo>>  findByDepartamentoId(Long id);

    public TramiteTipo create(TramiteTipo tramite_tipo);

    public Optional<TramiteTipo> update(TramiteTipo tramite_tipo, Long id);

    public void delete(Long id);

    public void deleteAll();


    
}
