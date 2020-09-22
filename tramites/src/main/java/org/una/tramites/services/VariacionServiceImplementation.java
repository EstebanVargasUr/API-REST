package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.IVariacionRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class VariacionServiceImplementation implements IVariacionService{
    
    @Autowired
    private IVariacionRepository variacionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findAll() {
        return Optional.ofNullable(variacionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VariacionDTO> findById(Long id) {
        return variacionRepository.findById(id);
    }

    @Override
    public Optional<List<VariacionDTO>> findByEstado(boolean estado) {
        return variacionRepository.findByEstado(estado);
    }
    
    @Override
    public Optional<List<VariacionDTO>> findByGrupo(boolean grupo) {
        return variacionRepository.findByGrupo(grupo);
    }
    
    @Override
    public Optional<List<VariacionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return variacionRepository.findByFechaRegistroBetween(startDate, endDate);
    }
    
    @Override
    @Transactional
    public VariacionDTO create(VariacionDTO variacion) {
        return variacionRepository.save(variacion);
    }

    @Override
    @Transactional
    public Optional<VariacionDTO> update(VariacionDTO variacion, Long id) {
        if (variacionRepository.findById(id).isPresent()) {
            return Optional.ofNullable(variacionRepository.save(variacion));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

        variacionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        variacionRepository.deleteAll();
    }

    @Override
    public Optional<List<VariacionDTO>> findByTramite_tipoId(Long id) {
       return variacionRepository.findByTramite_tipoId(id);
    }

}
