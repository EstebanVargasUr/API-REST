package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteRegistradoDTO;
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
    public Optional<List<TramiteRegistradoDTO>> findAll() {
        return Optional.ofNullable(tramite_registradoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteRegistradoDTO> findById(Long id) {
        return tramite_registradoRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findByClienteId(Long id) {
        return Optional.ofNullable(tramite_registradoRepository.findByClienteId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findByTramiteTipoId(Long id) {
        return Optional.ofNullable(tramite_registradoRepository.findByTramiteTipoId(id));
    }
    
    @Override
    @Transactional
    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramite_registrado) {
        return tramite_registradoRepository.save(tramite_registrado);
    }

    @Override
    @Transactional
    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramite_registrado, Long id) {
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

