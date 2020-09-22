package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    @Autowired
    private IRequisitoRepository requisitoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoDTO>> findAll() {
        return Optional.ofNullable(requisitoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoDTO> findById(Long id) {
        return requisitoRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoDTO>> findByVariacionId(Long id) {
        return Optional.ofNullable(requisitoRepository.findByVariacionId(id));
    }
    
    @Override
    public Optional<List<RequisitoDTO>> findByEstado(boolean estado) {
        return requisitoRepository.findByEstado(estado);
    }
    
    @Override
    public Optional<List<RequisitoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return requisitoRepository.findByFechaRegistroBetween(startDate, endDate);
    }
    
    @Override
    @Transactional
    public RequisitoDTO create(RequisitoDTO requisito) {
        return requisitoRepository.save(requisito);
    }

    @Override
    @Transactional
    public Optional<RequisitoDTO> update(RequisitoDTO requisito, Long id) {
        if (requisitoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(requisitoRepository.save(requisito));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

        requisitoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        requisitoRepository.deleteAll();
    }
}
