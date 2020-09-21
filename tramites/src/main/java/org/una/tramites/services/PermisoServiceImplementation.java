package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Permiso;
import org.una.tramites.repositories.IPermisoRepository;

@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findAll() {
        return Optional.ofNullable(permisoRepository.findAll());
    }

  
    @Override
    public Permiso create(Permiso permiso) {
      return permisoRepository.save(permiso);
    }

    @Override
    public Optional<Permiso> update(Permiso permiso, Long id) {
        if (permisoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisoRepository.save(permiso));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate) {
       return permisoRepository.findByFechaRegistroBetween(startDate, endDate);
    }

    @Override
    public Optional<Permiso> findById(Long id) {
        return permisoRepository.findById(id);
    }
    
    @Override
    public Optional<Permiso> findByCodigo(String codigo) {
        return permisoRepository.findByCodigo(codigo);
    }

    @Override
    public Optional<List<Permiso>> findByEstado(boolean estado) {
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

    @Override
    public Long countById(boolean Id) {
        return permisoRepository.countById(Id);
    }
    
  
 
}

