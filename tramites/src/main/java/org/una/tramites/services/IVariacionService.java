package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.VariacionDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface IVariacionService {
    
    public Optional<List<VariacionDTO>> findAll();

    public Optional<VariacionDTO> findById(Long id);
    
    public Optional<List<VariacionDTO>>  findByTramiteTipoId(Long id);

    public Optional<List<VariacionDTO>>  findByEstado(boolean estado);
    
    public Optional<List<VariacionDTO>>  findByGrupo(boolean grupo);
    
    public Optional<List<VariacionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public VariacionDTO create(VariacionDTO variacion);

    public Optional<VariacionDTO> update(VariacionDTO variacion, Long id);

    public void delete(Long id);

    public void deleteAll();

}
