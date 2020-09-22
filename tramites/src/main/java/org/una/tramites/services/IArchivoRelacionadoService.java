package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface IArchivoRelacionadoService {
    
    public Optional<List<ArchivoRelacionadoDTO>> findAll();

    public Optional<ArchivoRelacionadoDTO> findById(Long id);
    
    public Optional<List<ArchivoRelacionadoDTO>> findByNombreAproximateIgnoreCase(String titulo);
    
    public Optional<List<ArchivoRelacionadoDTO>>  findByEstado(boolean estado);
    
    public Optional<List<ArchivoRelacionadoDTO>>  findByTipo(boolean tipo);
    
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<ArchivoRelacionadoDTO>>  findByTramiteRegistradoId(Long id);
    
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionado);

    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO archivoRelacionado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
