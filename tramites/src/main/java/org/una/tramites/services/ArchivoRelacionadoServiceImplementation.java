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
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class ArchivoRelacionadoServiceImplementation implements IArchivoRelacionadoService{

    @Autowired
    private IArchivoRelacionadoRepository archivoRelacionadoRepository;
     
    private Optional<List<ArchivoRelacionadoDTO>> findList(List<ArchivoRelacionado> list) {
        if (list != null) {
            List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(list, ArchivoRelacionadoDTO.class);
            return Optional.ofNullable(archivoRelacionadoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<ArchivoRelacionadoDTO>> findList(Optional<List<ArchivoRelacionado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }
    private Optional<ArchivoRelacionadoDTO> oneToDto(Optional<ArchivoRelacionado> one) {
        if (one.isPresent()) {
            ArchivoRelacionadoDTO archivoRelacionadoDTO = MapperUtils.DtoFromEntity(one.get(), ArchivoRelacionadoDTO.class);
            return Optional.ofNullable(archivoRelacionadoDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findAll() {
        
    return findList(archivoRelacionadoRepository.findAll());
     
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionadoDTO> findById(Long id) {
        return oneToDto(archivoRelacionadoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        return findList(archivoRelacionadoRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByEstado(boolean estado) {
        return findList(archivoRelacionadoRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTipo(boolean tipo) {
         return findList(archivoRelacionadoRepository.findByTipo(tipo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(archivoRelacionadoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistradoId(Long id) {
        return findList(archivoRelacionadoRepository.findByTramiteRegistradoId(id));
    }
    
    @Override
    @Transactional
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionadoDTO) {
        ArchivoRelacionado archivoRelacionado = MapperUtils.EntityFromDto(archivoRelacionadoDTO, ArchivoRelacionado.class);
        archivoRelacionado = archivoRelacionadoRepository.save(archivoRelacionado);
        return MapperUtils.DtoFromEntity(archivoRelacionado, ArchivoRelacionadoDTO.class);
    }


    @Override
    @Transactional
    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO archivoRelacionadoDTO, Long id) {
         if (archivoRelacionadoRepository.findById(id).isPresent()) {
            ArchivoRelacionado archivoRelacionado = MapperUtils.EntityFromDto(archivoRelacionadoDTO, ArchivoRelacionado.class);
            archivoRelacionado = archivoRelacionadoRepository.save(archivoRelacionado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(archivoRelacionado, ArchivoRelacionadoDTO.class));
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
