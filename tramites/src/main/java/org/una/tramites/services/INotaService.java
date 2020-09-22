package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.NotaDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface INotaService {
    
    public Optional<List<NotaDTO>> findAll();

    public Optional<NotaDTO> findById(Long id);
    
    public Optional<List<NotaDTO>> findByTituloAproximateIgnoreCase(String titulo);
    
    public Optional<List<NotaDTO>>  findByEstado(boolean estado);
    
    public Optional<List<NotaDTO>>  findByTipo(boolean tipo);
    
    public Optional<List<NotaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<NotaDTO>>  findByTramiteRegistradoId(Long id);
    
    public NotaDTO create(NotaDTO nota);

    public Optional<NotaDTO> update(NotaDTO nota, Long id);

    public void delete(Long id);

    public void deleteAll();
}
