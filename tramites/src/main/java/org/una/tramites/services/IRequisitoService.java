package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Requisito;

/**
 *
 * @author Esteban Vargas
 */
public interface IRequisitoService {
    
    public Optional<List<Requisito>> findAll();

    public Optional<Requisito> findById(Long id);
    
    public Optional<List<Requisito>>  findByVariacionId(Long id);

    public Optional<List<Requisito>>  findByEstado(boolean estado);
    
    public Optional<List<Requisito>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Requisito create(Requisito requisito);

    public Optional<Requisito> update(Requisito requisito, Long id);

    public void delete(Long id);

    public void deleteAll();
}
