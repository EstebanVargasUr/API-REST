package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Variacion;

/**
 *
 * @author Esteban Vargas
 */
public interface IVariacionService {
    
    public Optional<List<Variacion>> findAll();

    public Optional<Variacion> findById(Long id);
    
  //  public Optional<List<Variacion>>  findByTramite_tipoId(Long id);

    public Optional<List<Variacion>>  findByEstado(boolean estado);
    
    public Optional<List<Variacion>>  findByGrupo(boolean grupo);
    
    public Optional<List<Variacion>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Variacion create(Variacion variacion);

    public Optional<Variacion> update(Variacion variacion, Long id);

    public void delete(Long id);

    public void deleteAll();

}
