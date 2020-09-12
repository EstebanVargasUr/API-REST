package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.repositories.ITramiteTipoRepository;



@Service
public class TramiteTipoServiceImplementation implements ITramiteTipoService {
    @Autowired
    private ITramiteTipoRepository tramite_tipoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipo>> findAll() {
        return Optional.ofNullable(tramite_tipoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteTipo> findById(Long id) {
        return tramite_tipoRepository.findById(id);
    }
    
    @Override
    @Transactional 
    public Optional<List<TramiteTipo>> findByEstado(boolean estado) {
        return tramite_tipoRepository.findByEstado(estado);
    }
    
    @Override
    @Transactional 
    public Optional<List<TramiteTipo>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin) {
        return tramite_tipoRepository.findByFechaRegistroBetween(fechaRegitro,fechafin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipo>> findByDepartamentoId(Long id) {
        return Optional.ofNullable(tramite_tipoRepository.findByDepartamentoId(id));
    }

    @Override
    @Transactional
    public TramiteTipo create(TramiteTipo tramite_tipo) {
        return tramite_tipoRepository.save(tramite_tipo);
    }

   @Override
    @Transactional
    public Optional<TramiteTipo> update(TramiteTipo tramite_tipo, Long id) {
        if (tramite_tipoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramite_tipoRepository.save(tramite_tipo));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

         tramite_tipoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramite_tipoRepository.deleteAll();
    }


    
}

