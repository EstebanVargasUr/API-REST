package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.DepartamentoDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface IDepartamentoService {
    
    public Optional<List<DepartamentoDTO>> findAll();
    
    public Optional<DepartamentoDTO> findById(Long id);
    
    public Optional<List<DepartamentoDTO>>  findByEstado(boolean estado);
    
    public DepartamentoDTO create(DepartamentoDTO departamento);

    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id);

    public void delete(Long id);

    public void deleteAll();
}
