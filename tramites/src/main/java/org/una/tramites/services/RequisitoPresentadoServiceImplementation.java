package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.repositories.IRequisitoPresentadoRepository;
import org.una.tramites.utils.MapperUtils;


@Service
public class RequisitoPresentadoServiceImplementation implements IRequisitoPresentadoService{

    @Autowired
    private IRequisitoPresentadoRepository requisitoPresentadoRepository;
    
        private Optional<List<RequisitoPresentadoDTO>> findList(List<RequisitoPresentado> list) {
        if (list != null) {
            List<RequisitoPresentadoDTO> requisitoPresentadoDTO = MapperUtils.DtoListFromEntityList(list, RequisitoPresentadoDTO.class);
            return Optional.ofNullable(requisitoPresentadoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<RequisitoPresentadoDTO>> findList(Optional<List<RequisitoPresentado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<RequisitoPresentadoDTO> oneToDto(Optional<RequisitoPresentado> one) {
        if (one.isPresent()) {
            RequisitoPresentadoDTO requisitoPresentadoDTO = MapperUtils.DtoFromEntity(one.get(), RequisitoPresentadoDTO.class);
            return Optional.ofNullable(requisitoPresentadoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentadoDTO>> findAll() {
        return findList(requisitoPresentadoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoPresentadoDTO> findById(Long id) {
        return oneToDto(requisitoPresentadoRepository.findById(id));
    }
    
    @Override
    @Transactional
    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentadoDTO) {
        RequisitoPresentado requisitoPresentado = MapperUtils.EntityFromDto(requisitoPresentadoDTO, RequisitoPresentado.class);
        requisitoPresentado = requisitoPresentadoRepository.save(requisitoPresentado);
        return MapperUtils.DtoFromEntity(requisitoPresentado, RequisitoPresentadoDTO.class);
    }

    
    @Override
    @Transactional
    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentadoDTO, Long id) {
        if (requisitoPresentadoRepository.findById(id).isPresent()) {
            RequisitoPresentado requisitoPresentado = MapperUtils.EntityFromDto(requisitoPresentadoDTO, RequisitoPresentado.class);
            requisitoPresentado = requisitoPresentadoRepository.save(requisitoPresentado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(requisitoPresentado, RequisitoPresentadoDTO.class));
        } else {
            return null;
        } 
    }

    @Override
    public Optional<List<RequisitoPresentadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(requisitoPresentadoRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        requisitoPresentadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        requisitoPresentadoRepository.deleteAll();
    }

  
}


