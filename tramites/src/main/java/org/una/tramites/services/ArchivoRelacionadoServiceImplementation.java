package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.repositories.IArchivoRelacionadoRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class ArchivoRelacionadoServiceImplementation implements IArchivoRelacionadoService{

    @Autowired
    private IArchivoRelacionadoRepository archivoRelacionadoRepository;
     
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findAll() {
        return Optional.ofNullable(archivoRelacionadoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionadoDTO> findById(Long id) {
        return archivoRelacionadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        return Optional.ofNullable(archivoRelacionadoRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByEstado(boolean estado) {
        return archivoRelacionadoRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTipo(boolean tipo) {
         return archivoRelacionadoRepository.findByTipo(tipo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return archivoRelacionadoRepository.findByFechaRegistroBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistradoId(Long id) {
        return Optional.ofNullable(archivoRelacionadoRepository.findByTramiteRegistradoId(id));
    }
    
    @Override
    @Transactional
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionado) {
        return archivoRelacionadoRepository.save(archivoRelacionado);
    }

    @Override
    @Transactional
    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO archivoRelacionado, Long id) {
        if (archivoRelacionadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(archivoRelacionadoRepository.save(archivoRelacionado));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        archivoRelacionadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        archivoRelacionadoRepository.deleteAll();
    }
    
}
