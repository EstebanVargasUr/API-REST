package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Nota;

/**
 *
 * @author Esteban Vargas
 */
public interface INotaService {
    
    public Optional<List<Nota>> findAll();

    public Optional<Nota> findById(Long id);
    
    public Optional<List<Nota>> findByTituloAproximateIgnoreCase(String titulo);
    
    public Optional<List<Nota>>  findByEstado(boolean estado);
    
    public Optional<List<Nota>>  findByTipo(boolean tipo);
    
    public Optional<List<Nota>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<Nota>>  findByTramiteRegistradoId(Long id);
    
    public Nota create(Nota nota);

    public Optional<Nota> update(Nota nota, Long id);

    public void delete(Long id);

    public void deleteAll();
}
