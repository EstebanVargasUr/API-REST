package org.una.tramites.services;
        
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.PermisoDTO;


public interface IPermisoService {
    public Optional<List<PermisoDTO>> findAll();
 
    public Optional<PermisoDTO> findById(Long id);
     
    public Optional<PermisoDTO> findByCodigo(String codigo);

    public Optional<List<PermisoDTO>> findByEstado(boolean estado);
    
    public Long countByEstado(boolean estado);
    
    public Long countById(boolean Id);
    
    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public PermisoDTO create(PermisoDTO permiso);

    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id);

    public void delete(Long id);

    public void deleteAll();

    

}

