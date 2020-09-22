package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.repositories.ITramiteTipoRepository;



@Service
public class TramiteTipoServiceImplementation implements ITramiteTipoService {
    @Autowired
    private ITramiteTipoRepository tramite_tipoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findAll() {
        return Optional.ofNullable(tramite_tipoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteTipoDTO> findById(Long id) {
        return tramite_tipoRepository.findById(id);
    }
    
    @Override
    @Transactional 
    public Optional<List<TramiteTipoDTO>> findByEstado(boolean estado) {
        return tramite_tipoRepository.findByEstado(estado);
    }
    
    @Override
    @Transactional 
    public Optional<List<TramiteTipoDTO>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin) {
        return tramite_tipoRepository.findByFechaRegistroBetween(fechaRegitro,fechafin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findByDepartamentoId(Long id) {
        return Optional.ofNullable(tramite_tipoRepository.findByDepartamentoId(id));
    }

    @Override
    @Transactional
    public TramiteTipoDTO create(TramiteTipoDTO tramite_tipo) {
        return tramite_tipoRepository.save(tramite_tipo);
    }

   @Override
    @Transactional
    public Optional<TramiteTipoDTO> update(TramiteTipoDTO tramite_tipo, Long id) {
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

