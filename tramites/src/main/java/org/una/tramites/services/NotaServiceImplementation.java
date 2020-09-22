package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.repositories.INotaRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class NotaServiceImplementation implements INotaService{

    @Autowired
    private INotaRepository notaRepository;
     
    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findAll() {
        return Optional.ofNullable(notaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotaDTO> findById(Long id) {
        return notaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByTituloAproximateIgnoreCase(String titulo) {
        return Optional.ofNullable(notaRepository.findByTituloContainingIgnoreCase(titulo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByEstado(boolean estado) {
        return notaRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByTipo(boolean tipo) {
         return notaRepository.findByTipo(tipo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return notaRepository.findByFechaRegistroBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByTramiteRegistradoId(Long id) {
        return Optional.ofNullable(notaRepository.findByTramiteRegistradoId(id));
    }
    
    @Override
    @Transactional
    public NotaDTO create(NotaDTO nota) {
        return notaRepository.save(nota);
    }

    @Override
    @Transactional
    public Optional<NotaDTO> update(NotaDTO nota, Long id) {
        if (notaRepository.findById(id).isPresent()) {
            return Optional.ofNullable(notaRepository.save(nota));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        notaRepository.deleteAll();
    }
    
}
