package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Tramite_Tipo;
import org.una.tramites.repositories.ITramite_TipoRepository;



@Service
public class Tramite_TipoServiceImplementation implements ITramite_TipoService {
    @Autowired
    private ITramite_TipoRepository tramite_tipoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Tramite_Tipo>> findAll() {
        return Optional.ofNullable(tramite_tipoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tramite_Tipo> findById(Long id) {
        return tramite_tipoRepository.findById(id);
    }
    
    @Override
    @Transactional 
    public Optional<List<Tramite_Tipo>> findByEstado(boolean estado) {
        return tramite_tipoRepository.findByEstado(estado);
    }
    
    @Override
    @Transactional 
    public Optional<List<Tramite_Tipo>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin) {
        return tramite_tipoRepository.findByFechaRegistroBetween(fechaRegitro,fechafin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Tramite_Tipo>> findByDepartamentoId(Long id) {
        return Optional.ofNullable(tramite_tipoRepository.findByDepartamentoId(id));
    }

    @Override
    @Transactional
    public Tramite_Tipo create(Tramite_Tipo tramite_tipo) {
        return tramite_tipoRepository.save(tramite_tipo);
    }

   @Override
    @Transactional
    public Optional<Tramite_Tipo> update(Tramite_Tipo tramite_tipo, Long id) {
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

