package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author Esteban Vargas
 */
public interface IArchivoRelacionadoService {
    
    public Optional<List<ArchivoRelacionado>> findAll();

    public Optional<ArchivoRelacionado> findById(Long id);
    
    public Optional<List<ArchivoRelacionado>> findByNombreAproximateIgnoreCase(String titulo);
    
    public Optional<List<ArchivoRelacionado>>  findByEstado(boolean estado);
    
    public Optional<List<ArchivoRelacionado>>  findByTipo(boolean tipo);
    
    public Optional<List<ArchivoRelacionado>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<ArchivoRelacionado>>  findByTramiteRegistradoId(Long id);
    
    public ArchivoRelacionado create(ArchivoRelacionado archivoRelacionado);

    public Optional<ArchivoRelacionado> update(ArchivoRelacionado archivoRelacionado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
