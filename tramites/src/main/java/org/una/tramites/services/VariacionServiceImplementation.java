package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.IVariacionRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class VariacionServiceImplementation implements IVariacionService{
    
    @Autowired
    private IVariacionRepository variacionRepository;

    private Optional<List<VariacionDTO>> findList(List<Variacion> list) {
        if (list != null) {
            List<VariacionDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, VariacionDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<VariacionDTO>> findList(Optional<List<Variacion>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<VariacionDTO> oneToDto(Optional<Variacion> one) {
        if (one.isPresent()) {
            VariacionDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), VariacionDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findAll() {
        return findList(variacionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VariacionDTO> findById(Long id) {
        return oneToDto(variacionRepository.findById(id));
    }

    @Override
    public Optional<List<VariacionDTO>> findByEstado(boolean estado) {
        return findList(variacionRepository.findByEstado(estado));
    }
    
    @Override
    public Optional<List<VariacionDTO>> findByGrupo(boolean grupo) {
        return findList(variacionRepository.findByGrupo(grupo));
    }
    
    @Override
    public Optional<List<VariacionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(variacionRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    public Optional<List<VariacionDTO>> findByTramiteTipoId(Long id) {
       return findList(variacionRepository.findByTramiteTipoId(id));
    }
    
    @Override
    @Transactional
    public VariacionDTO create(VariacionDTO variacionDTO) {
        Variacion variacion = MapperUtils.EntityFromDto(variacionDTO, Variacion.class);
        variacionRepository.save(variacion);
        return MapperUtils.DtoFromEntity(variacion, VariacionDTO.class);
    }

    @Override
    @Transactional
    public Optional<VariacionDTO> update(VariacionDTO variacionDTO, Long id) {
        if (variacionRepository.findById(id).isPresent()) {
            Variacion variacion = MapperUtils.EntityFromDto(variacionDTO, Variacion.class);
            variacion = variacionRepository.save(variacion);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(variacion, VariacionDTO.class));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        variacionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        variacionRepository.deleteAll();
    }
}
