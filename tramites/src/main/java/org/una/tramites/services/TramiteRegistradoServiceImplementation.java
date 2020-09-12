package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.repositories.ITramiteRegistradoRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class TramiteRegistradoServiceImplementation implements ITramiteRegistradoService{

    @Autowired
    private ITramiteRegistradoRepository tramite_registradoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistrado>> findAll() {
        return Optional.ofNullable(tramite_registradoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteRegistrado> findById(Long id) {
        return tramite_registradoRepository.findById(id);
    }
    
  /*  @Override
    public Optional<List<TramiteRegistrado>> findByClienteId(Long id) {
        return tramite_registradoRepository.findByClienteId(id);
    }*/
    
   /* @Override
    public Optional<List<TramiteRegistrado>> findByTramiteTipoId(Long id) {
        return tramite_registradoRepository.findByTramiteTipoId(id);
    }*/
    
    @Override
    @Transactional
    public TramiteRegistrado create(TramiteRegistrado tramite_registrado) {
        return tramite_registradoRepository.save(tramite_registrado);
    }

    @Override
    @Transactional
    public Optional<TramiteRegistrado> update(TramiteRegistrado tramite_registrado, Long id) {
        if (tramite_registradoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramite_registradoRepository.save(tramite_registrado));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

        tramite_registradoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramite_registradoRepository.deleteAll();
    }
}

