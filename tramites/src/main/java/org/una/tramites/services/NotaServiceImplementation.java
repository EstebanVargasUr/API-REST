package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.repositories.INotaRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class NotaServiceImplementation implements INotaService{

    @Autowired
    private INotaRepository notaRepository;
    
    private Optional<List<NotaDTO>> findList(List<Nota> list) {
        if (list != null) {
            List<NotaDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, NotaDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }
        private Optional<List<NotaDTO>> findList(Optional<List<Nota>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }
        private Optional<NotaDTO> oneToDto(Optional<Nota> one) {
        if (one.isPresent()) {
            NotaDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), NotaDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findAll() {
        return findList(notaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotaDTO> findById(Long id) {
        return oneToDto(notaRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByTituloAproximateIgnoreCase(String titulo) {
        return findList(notaRepository.findByTituloContainingIgnoreCase(titulo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByEstado(boolean estado){
         return findList(notaRepository.findByEstado(estado));
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByTipo(boolean tipo) {
        return findList(notaRepository.findByTipo(tipo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(notaRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findByTramiteRegistradoId(Long id) {
       return findList(notaRepository.findByTramiteRegistradoId(id));
    }
    
    @Override
    @Transactional
    public NotaDTO create(NotaDTO notaDTO) {
        Nota nota= MapperUtils.EntityFromDto(notaDTO, Nota.class);
        nota=notaRepository.save(nota);
        return MapperUtils.DtoFromEntity(nota, NotaDTO.class);
    }

    @Override
    @Transactional
    public Optional<NotaDTO> update(NotaDTO notaDTO, Long id) {
        if (notaRepository.findById(id).isPresent()) {
            Nota nota= MapperUtils.EntityFromDto(notaDTO,Nota.class);
            nota=notaRepository.save(nota);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(nota, NotaDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        notaRepository.deleteAll();
    }
    
}
