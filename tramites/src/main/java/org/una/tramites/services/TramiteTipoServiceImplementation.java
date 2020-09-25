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
import org.una.tramites.utils.MapperUtils;



@Service
public class TramiteTipoServiceImplementation implements ITramiteTipoService {
    @Autowired
    private ITramiteTipoRepository tramiteTipoRepository;

    private Optional<List<TramiteTipoDTO>> findList(List<TramiteTipo> list) {
        if (list != null) {
            List<TramiteTipoDTO> tamiteTipoDTO = MapperUtils.DtoListFromEntityList(list, TramiteTipoDTO.class);
            return Optional.ofNullable(tamiteTipoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TramiteTipoDTO>> findList(Optional<List<TramiteTipo>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TramiteTipoDTO> oneToDto(Optional<TramiteTipo> one) {
        if (one.isPresent()) {
            TramiteTipoDTO tamiteTipoDTO = MapperUtils.DtoFromEntity(one.get(), TramiteTipoDTO.class);
            return Optional.ofNullable(tamiteTipoDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findAll() {
        return findList(tramiteTipoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteTipoDTO> findById(Long id) {
        return oneToDto(tramiteTipoRepository.findById(id));
    }
    
    @Override
    @Transactional 
    public Optional<List<TramiteTipoDTO>> findByEstado(boolean estado) {
        return findList(tramiteTipoRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional 
    public Optional<List<TramiteTipoDTO>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin) {
        return findList(tramiteTipoRepository.findByFechaRegistroBetween(fechaRegitro,fechafin));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findByDepartamentoId(Long id) {
        return findList(tramiteTipoRepository.findByDepartamentoId(id));
    }

    @Override
    @Transactional
    public TramiteTipoDTO create(TramiteTipoDTO tramiteTipoDTO) {
        TramiteTipo tramiteTipo = MapperUtils.EntityFromDto(tramiteTipoDTO, TramiteTipo.class);
        tramiteTipo = tramiteTipoRepository.save(tramiteTipo);
        return MapperUtils.DtoFromEntity(tramiteTipo, TramiteTipoDTO.class);
    }

   @Override
    @Transactional
    public Optional<TramiteTipoDTO> update(TramiteTipoDTO tramiteTipoDTO, Long id) {
        if (tramiteTipoRepository.findById(id).isPresent()) {
            TramiteTipo tramiteTipo = MapperUtils.EntityFromDto(tramiteTipoDTO, TramiteTipo.class);
            tramiteTipo = tramiteTipoRepository.save(tramiteTipo);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tramiteTipo, TramiteTipoDTO.class));
        } else {
            return null;
        }

    }
}

