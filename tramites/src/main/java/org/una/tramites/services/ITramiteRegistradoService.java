package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteRegistradoDTO;

/**
 *
 * @author acer
 */
public interface ITramiteRegistradoService {
    public Optional<List<TramiteRegistradoDTO>> findAll();

    public Optional<TramiteRegistradoDTO> findById(Long id);

   public Optional<List<TramiteRegistradoDTO>>  findByClienteId(Long id);
    
    public Optional<List<TramiteRegistradoDTO>>  findByTramiteTipoId(Long id);
     
    public TramiteRegistradoDTO create(TramiteRegistradoDTO usuario);

    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO usuario, Long id);
    
}
