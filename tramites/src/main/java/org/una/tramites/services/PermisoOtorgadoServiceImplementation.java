package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;

@Service
public class PermisoOtorgadoServiceImplementation implements IPermisoOtorgadoService {

    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadoRepository;

    
   @Override
   @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioId(Long usuarioId) {
        return permisoOtorgadoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoId(Long permisoId) {
        return permisoOtorgadoRepository.findByPermisoId(permisoId);
    }

   @Override
   @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado) {
        return permisoOtorgadoRepository.findByUsuarioIdAndEstado(usuarioId, estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoIdAndEstado(Long permisoId, boolean estado) {
      return permisoOtorgadoRepository.findByPermisoIdAndEstado(permisoId, estado);
    }

   
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return permisoOtorgadoRepository.findByFechaRegistroBetween(startDate, endDate);
    }

    @Override
    public PermisoOtorgadoDTO create(PermisoOtorgadoDTO permisoOtorgado) {
         return permisoOtorgadoRepository.save(permisoOtorgado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoOtorgadoDTO> update(PermisoOtorgadoDTO permisoOtorgado, Long id) {
       if (permisoOtorgadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisoOtorgadoRepository.save(permisoOtorgado));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoOtorgadoDTO> findById(Long usuarioId) {
      return permisoOtorgadoRepository.findById(usuarioId);
    }

    @Override
    public void delete(Long id) {
        permisoOtorgadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
       permisoOtorgadoRepository.deleteAll();
    }
 
}

