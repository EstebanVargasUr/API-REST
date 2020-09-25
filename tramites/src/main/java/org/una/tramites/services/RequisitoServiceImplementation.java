package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    @Autowired
    private IRequisitoRepository requisitoRepository;
    
    private Optional<List<RequisitoDTO>> findList(List<Requisito> list) {
        if (list != null) {
            List<RequisitoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, RequisitoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<RequisitoDTO>> findList(Optional<List<Requisito>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<RequisitoDTO> oneToDto(Optional<Requisito> one) {
        if (one.isPresent()) {
            RequisitoDTO requisitoDTO = MapperUtils.DtoFromEntity(one.get(), RequisitoDTO.class);
            return Optional.ofNullable(requisitoDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoDTO>> findAll() {
        return findList(requisitoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoDTO> findById(Long id) {
        return oneToDto(requisitoRepository.findById(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoDTO>> findByVariacionId(Long id) {
        return findList(requisitoRepository.findByVariacionId(id));
    }
    
    @Override
    public Optional<List<RequisitoDTO>> findByEstado(boolean estado) {
        return findList(requisitoRepository.findByEstado(estado));
    }
    
    @Override
    public Optional<List<RequisitoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(requisitoRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    @Transactional
    public RequisitoDTO create(RequisitoDTO requisitoDTO) {
        Requisito requisito = MapperUtils.EntityFromDto(requisitoDTO, Requisito.class);
        requisito = requisitoRepository.save(requisito);
        return MapperUtils.DtoFromEntity(requisito, RequisitoDTO.class);
    }

    @Override
    @Transactional
    public Optional<RequisitoDTO> update(RequisitoDTO requisitoDTO, Long id) {
        if (requisitoRepository.findById(id).isPresent()) {
            Requisito requisito = MapperUtils.EntityFromDto(requisitoDTO, Requisito.class);
            requisito = requisitoRepository.save(requisito);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(requisito, RequisitoDTO.class));
        } else {
            return null;
        } 
    }

    @Override
    @Transactional
    public void delete(Long id) {
        requisitoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        requisitoRepository.deleteAll();
    }
}
