package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author acer
 */
public interface ITramiteRegistradoService {
    public Optional<List<TramiteRegistrado>> findAll();

    public Optional<TramiteRegistrado> findById(Long id);

   public Optional<List<TramiteRegistrado>>  findByClienteId(Long id);
    
    public Optional<List<TramiteRegistrado>>  findByTramiteTipoId(Long id);
     
    public TramiteRegistrado create(TramiteRegistrado usuario);

    public Optional<TramiteRegistrado> update(TramiteRegistrado usuario, Long id);

    public void delete(Long id);

    public void deleteAll();
}
