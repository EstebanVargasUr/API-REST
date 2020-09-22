package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService{

    @Autowired
    private IDepartamentoRepository departamentoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        return Optional.ofNullable(departamentoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
        return departamentoRepository.findById(id);
    }
    
    @Override
    public Optional<List<DepartamentoDTO>> findByEstado(boolean estado) {
        return departamentoRepository.findByEstado(estado);
    }
    
    @Override
    @Transactional
    public DepartamentoDTO create(DepartamentoDTO departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id) {
        if (departamentoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(departamentoRepository.save(departamento));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

        departamentoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        departamentoRepository.deleteAll();
    }
}
