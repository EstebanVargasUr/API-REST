package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.repositories.ITramiteCambioEstadoRepository;


@Service
public class TramiteCambioEstadoServiceImplementation implements ITramiteCambioEstadoService{

    @Autowired
    private ITramiteCambioEstadoRepository tramiteCambioEstadoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteCambioEstado>> findAll() {
        return Optional.ofNullable(tramiteCambioEstadoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteCambioEstado> findById(Long id) {
        return tramiteCambioEstadoRepository.findById(id);
    }
    
    @Override
    @Transactional
    public TramiteCambioEstado create(TramiteCambioEstado tramiteCambioEstado) {
        return tramiteCambioEstadoRepository.save(tramiteCambioEstado);
    }
    
    @Override
    @Transactional
    public Optional<TramiteCambioEstado> update(TramiteCambioEstado tramiteCambioEstado, Long id) {
        if (tramiteCambioEstadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramiteCambioEstadoRepository .save(tramiteCambioEstado));
        } else {
            return null;
        }

    }

    @Override
    public Optional<List<TramiteCambioEstado>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return tramiteCambioEstadoRepository.findByFechaRegistroBetween(startDate, endDate);
    }
    @Override
    @Transactional
    public void delete(Long id) {

        tramiteCambioEstadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramiteCambioEstadoRepository.deleteAll();
    }

  
}

