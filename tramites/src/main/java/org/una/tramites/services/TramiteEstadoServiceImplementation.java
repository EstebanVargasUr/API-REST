package org.una.tramites.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteEstadoRepository;

@Service
public class TramiteEstadoServiceImplementation implements ITramiteEstadoService{

    @Autowired
    private ITramiteEstadoRepository tramiteEstadoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteEstadoDTO>> findAll() {
        return Optional.ofNullable(tramiteEstadoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteEstadoDTO> findById(Long id) {
        return tramiteEstadoRepository.findById(id);
    }
    
    @Override
    public Optional<List<TramiteEstadoDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return tramiteEstadoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    @Transactional
    public TramiteEstadoDTO create(TramiteEstadoDTO tramite_estado) {
        return tramiteEstadoRepository.save(tramite_estado);
    }

    @Override
    @Transactional
    public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramite_estado, Long id) {
        if ( tramiteEstadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable( tramiteEstadoRepository.save(tramite_estado));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {

         tramiteEstadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
         tramiteEstadoRepository.deleteAll();
    }
}
