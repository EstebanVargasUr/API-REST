package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Cliente;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService{

    @Autowired
    private IDepartamentoRepository departamentoRepository;
    
     private Optional<List<DepartamentoDTO>> findList(List<Departamento> list) {
        if (list != null) {
            List<DepartamentoDTO> departamentoDTO = MapperUtils.DtoListFromEntityList(list, DepartamentoDTO.class);
            return Optional.ofNullable(departamentoDTO);
        } else {
            return null;
        }
    }
    private Optional<List<DepartamentoDTO>> findList(Optional<List<Departamento>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }
    private Optional<DepartamentoDTO> oneToDto(Optional<Departamento> one) {
        if (one.isPresent()) {
            DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(one.get(), DepartamentoDTO.class);
            return Optional.ofNullable(departamentoDTO);
        } else {
            return null;
        }
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        return findList(departamentoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
        return oneToDto(departamentoRepository.findById(id));
    }
    
    @Override
    public Optional<List<DepartamentoDTO>> findByEstado(boolean estado) {
        return findList(departamentoRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional
    public DepartamentoDTO create(DepartamentoDTO departamento) {
        Departamento _departamento= MapperUtils.EntityFromDto(departamento, Departamento.class);
        _departamento = departamentoRepository.save(_departamento);
        return MapperUtils.DtoFromEntity(_departamento, DepartamentoDTO.class);
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id) {
       if (departamentoRepository.findById(id).isPresent()) {
            Departamento _departamento = MapperUtils.EntityFromDto(departamento, Departamento.class);
            _departamento = departamentoRepository.save(_departamento);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(_departamento, DepartamentoDTO.class));
        } else {
            return null;
        } 

    }

    @Override
    @Transactional
    public void delete(Long id) {

        departamentoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        departamentoRepository.deleteAll();
    }
}
