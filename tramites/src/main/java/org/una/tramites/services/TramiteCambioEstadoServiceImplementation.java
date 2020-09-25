package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.repositories.ITramiteCambioEstadoRepository;
import org.una.tramites.utils.MapperUtils;


@Service
public class TramiteCambioEstadoServiceImplementation implements ITramiteCambioEstadoService{

    @Autowired
    private ITramiteCambioEstadoRepository tramiteCambioEstadoRepository;

    private Optional<List<TramiteCambioEstadoDTO>> findList(List<TramiteCambioEstado> list) {
        if (list != null) {
            List<TramiteCambioEstadoDTO> tramiteCambioEstado = MapperUtils.DtoListFromEntityList(list, TramiteCambioEstadoDTO.class);
            return Optional.ofNullable(tramiteCambioEstado);
        } else {
            return null;
        }
    }

    private Optional<List<TramiteCambioEstadoDTO>> findList(Optional<List<TramiteCambioEstado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TramiteCambioEstadoDTO> oneToDto(Optional<TramiteCambioEstado> one) {
        if (one.isPresent()) {
            TramiteCambioEstadoDTO TramiteCambioEstadoDTO = MapperUtils.DtoFromEntity(one.get(), TramiteCambioEstadoDTO.class);
            return Optional.ofNullable(TramiteCambioEstadoDTO);
        } else {
            return null;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteCambioEstadoDTO>> findAll() {
        return findList(tramiteCambioEstadoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteCambioEstadoDTO> findById(Long id) {

        return oneToDto(tramiteCambioEstadoRepository.findById(id));
    }
    
    @Override
    @Transactional
    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO tramiteCambioEstadoDTO) {
        TramiteCambioEstado tramiteCambioEstado = MapperUtils.EntityFromDto(tramiteCambioEstadoDTO, TramiteCambioEstado.class);
        tramiteCambioEstado = tramiteCambioEstadoRepository.save(tramiteCambioEstado);
        return MapperUtils.DtoFromEntity(tramiteCambioEstado, TramiteCambioEstadoDTO.class);

    }
    
    @Override
    @Transactional
    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramiteCambioEstadoDTO, Long id) {
        if (tramiteCambioEstadoRepository.findById(id).isPresent()) {

            TramiteCambioEstado tramiteCambioEstado = MapperUtils.EntityFromDto(tramiteCambioEstadoDTO, TramiteCambioEstado.class);
            tramiteCambioEstado= tramiteCambioEstadoRepository.save(tramiteCambioEstado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tramiteCambioEstado, TramiteCambioEstadoDTO.class));
        } else {
            return null;
        }


    }

    @Override
    @Transactional
    public void delete(Long id) {

        tramiteCambioEstadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {

        tramiteCambioEstadoRepository.deleteAll();
    }

    @Override
    public Optional<List<TramiteCambioEstadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(tramiteCambioEstadoRepository.findByFechaRegistroBetween(startDate,endDate));
    }

  
}

