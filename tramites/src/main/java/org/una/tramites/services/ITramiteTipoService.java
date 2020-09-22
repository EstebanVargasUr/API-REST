
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteTipoDTO;

/**
 *
 * @author acer
 */
public interface ITramiteTipoService {
    public Optional<List<TramiteTipoDTO>> findAll();

    public Optional<TramiteTipoDTO> findById(Long id);

    public Optional<List<TramiteTipoDTO>> findByEstado(boolean estado);
    
    public Optional<List<TramiteTipoDTO>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin);

    public Optional<List<TramiteTipoDTO>>  findByDepartamentoId(Long id);

    public TramiteTipoDTO create(TramiteTipoDTO tramite_tipo);

    public Optional<TramiteTipoDTO> update(TramiteTipoDTO tramite_tipo, Long id);

    public void delete(Long id);

    public void deleteAll();


    
}
