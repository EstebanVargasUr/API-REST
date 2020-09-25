package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.repositories.IParametroGeneralRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class ParametroGeneralServiceImplementation implements IParametroGeneralService{

    @Autowired
    private IParametroGeneralRepository parametroGeneralRepository;
    
    private Optional<List<ParametroGeneralDTO>> findList(List<ParametroGeneral> list) {
        if (list != null) {
            List<ParametroGeneralDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, ParametroGeneralDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<ParametroGeneralDTO>> findList(Optional<List<ParametroGeneral>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<ParametroGeneralDTO> oneToDto(Optional<ParametroGeneral> one) {
        if (one.isPresent()) {
            ParametroGeneralDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), ParametroGeneralDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }

    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findAll() {
       return findList(parametroGeneralRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroGeneralDTO> findById(Long id) {
       return oneToDto(parametroGeneralRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByNombreAproximateIgnoreCase(String nombre) {
         return findList(parametroGeneralRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(parametroGeneralRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    @Transactional
    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneralDTO) {
        ParametroGeneral parametroGeneral = MapperUtils.EntityFromDto(parametroGeneralDTO, ParametroGeneral.class);
        parametroGeneral = parametroGeneralRepository.save(parametroGeneral);
        return MapperUtils.DtoFromEntity(parametroGeneral, ParametroGeneralDTO.class);

    }

    @Override
    @Transactional
    public Optional<ParametroGeneralDTO> update(ParametroGeneralDTO parametroGeneralDTO, Long id) {
        if (parametroGeneralRepository.findById(id).isPresent()) {
            ParametroGeneral parametroGeneral = MapperUtils.EntityFromDto(parametroGeneralDTO, ParametroGeneral.class);
            parametroGeneral = parametroGeneralRepository.save(parametroGeneral);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(parametroGeneral, ParametroGeneralDTO.class));
        } else {
            return null;
        } 

    }

    @Override
    @Transactional
    public void delete(Long id) {
        parametroGeneralRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        parametroGeneralRepository.deleteAll();
    }

    
}
