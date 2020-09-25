package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.repositories.ITramiteRegistradoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class TramiteRegistradoServiceImplementation implements ITramiteRegistradoService{

    @Autowired
    private ITramiteRegistradoRepository tramiteRegistradoRepository;
    
    private Optional<List<TramiteRegistradoDTO>> findList(List<TramiteRegistrado> list) {
        if (list != null) {
            List<TramiteRegistradoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, TramiteRegistradoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TramiteRegistradoDTO>> findList(Optional<List<TramiteRegistrado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TramiteRegistradoDTO> oneToDto(Optional<TramiteRegistrado> one) {
        if (one.isPresent()) {
            TramiteRegistradoDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), TramiteRegistradoDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findAll() {
        return findList(tramiteRegistradoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteRegistradoDTO> findById(Long id) {
        return oneToDto(tramiteRegistradoRepository.findById(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findByClienteId(Long id) {
        return findList(tramiteRegistradoRepository.findByClienteId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findByTramiteTipoId(Long id) {
        return findList(tramiteRegistradoRepository.findByTramiteTipoId(id));
    }
    
    @Override
    @Transactional
    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramiteRegistradoDTO) {
        TramiteRegistrado tramiteRegistrado = MapperUtils.EntityFromDto(tramiteRegistradoDTO, TramiteRegistrado.class);
        tramiteRegistrado = tramiteRegistradoRepository.save(tramiteRegistrado);
        return MapperUtils.DtoFromEntity(tramiteRegistrado, TramiteRegistradoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramiteRegistradoDTO, Long id) {
        if (tramiteRegistradoRepository.findById(id).isPresent()) {
            TramiteRegistrado usuario = MapperUtils.EntityFromDto(tramiteRegistradoDTO, TramiteRegistrado.class);
            usuario = tramiteRegistradoRepository.save(usuario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, TramiteRegistradoDTO.class));
        } else {
            return null;
        } 
    }
}

