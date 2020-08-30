package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Optional<List<Requisito>> findAll() {
        return Optional.ofNullable(requisitoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Requisito> findById(Long id) {
        return requisitoRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Requisito>> findByVariacionId(Long id) {
        return Optional.ofNullable(requisitoRepository.findByVariacionId(id));
    }
    
    @Override
    public Optional<List<Requisito>> findByEstado(boolean estado) {
        return requisitoRepository.findByEstado(estado);
    }
    
    @Override
    public Optional<List<Requisito>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return requisitoRepository.findByFechaRegistroBetween(startDate, endDate);
    }
    
    @Override
    @Transactional
    public Requisito create(Requisito requisito) {
        return requisitoRepository.save(requisito);
    }

    @Override
    @Transactional
    public Optional<Requisito> update(Requisito requisito, Long id) {
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
