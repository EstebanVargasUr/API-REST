package org.una.tramites.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteEstadoRepository;
import org.una.tramites.utils.MapperUtils;

@Service
public class TramiteEstadoServiceImplementation implements ITramiteEstadoService{

    @Autowired
    private ITramiteEstadoRepository tramiteEstadoRepository;
    
     private Optional<List<TramiteEstadoDTO>> findList(List<TramiteEstado> list) {
        if (list != null) {
            List<TramiteEstadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, TramiteEstadoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TramiteEstadoDTO>> findList(Optional<List<TramiteEstado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TramiteEstadoDTO> oneToDto(Optional<TramiteEstado> one) {
        if (one.isPresent()) {
            TramiteEstadoDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), TramiteEstadoDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }
        
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteEstadoDTO>> findAll() {
        return findList(tramiteEstadoRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteEstadoDTO> findById(Long id) {
        return oneToDto(tramiteEstadoRepository.findById(id));
    }
    
    @Override
    public Optional<List<TramiteEstadoDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return findList(tramiteEstadoRepository.findByNombreContainingIgnoreCase(nombre));
    }
    
    @Override
    @Transactional
    public TramiteEstadoDTO create(TramiteEstadoDTO tramiteEstadoDTO) {
        TramiteEstado usuario = MapperUtils.EntityFromDto(tramiteEstadoDTO, TramiteEstado.class);
        usuario = tramiteEstadoRepository.save(usuario);
        return MapperUtils.DtoFromEntity(usuario, TramiteEstadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramiteEstadoDTO, Long id) {
        if (tramiteEstadoRepository.findById(id).isPresent()) {
            TramiteEstado usuario = MapperUtils.EntityFromDto(tramiteEstadoDTO, TramiteEstado.class);
            usuario = tramiteEstadoRepository.save(usuario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, TramiteEstadoDTO.class));
        } else {
            return null;
        } 
    }
}
