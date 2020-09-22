package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface IRequisitoService {
    
    public Optional<List<RequisitoDTO>> findAll();

    public Optional<RequisitoDTO> findById(Long id);
    
    public Optional<List<RequisitoDTO>>  findByVariacionId(Long id);

    public Optional<List<RequisitoDTO>>  findByEstado(boolean estado);
    
    public Optional<List<RequisitoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public RequisitoDTO create(RequisitoDTO requisito);

    public Optional<RequisitoDTO> update(RequisitoDTO requisito, Long id);

    public void delete(Long id);

    public void deleteAll();
}
