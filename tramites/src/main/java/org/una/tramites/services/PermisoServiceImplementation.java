package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.repositories.IPermisoRepository;

@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findAll() {
        return Optional.ofNullable(permisoRepository.findAll());
    }

  
    @Override
    public PermisoDTO create(PermisoDTO permiso) {
      return permisoRepository.save(permiso);
    }

    @Override
    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id) {
        if (permisoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisoRepository.save(permiso));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
       return permisoRepository.findByFechaRegistroBetween(startDate, endDate);
    }

    @Override
    public Optional<PermisoDTO> findById(Long id) {
        return permisoRepository.findById(id);
    }
    
    @Override
    public Optional<PermisoDTO> findByCodigo(String codigo) {
        return permisoRepository.findByCodigo(codigo);
    }

    @Override
    public Optional<List<PermisoDTO>> findByEstado(boolean estado) {
        return permisoRepository.findByEstado(estado);
    }

    @Override
    public void delete(Long id) {
         permisoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
         permisoRepository.deleteAll();
    }

    @Override
    public Long countByEstado(boolean estado) {
        return permisoRepository.countByEstado(estado);
    }
    
  
 
}

