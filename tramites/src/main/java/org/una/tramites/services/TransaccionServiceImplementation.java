package org.una.tramites.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.repositories.ITransaccionRepository;
import org.una.tramites.utils.MapperUtils;


@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    private Optional<List<TransaccionDTO>> findList(List<Transaccion> list) {
        if (list != null) {
            List<TransaccionDTO> transaccionesDTO = MapperUtils.DtoListFromEntityList(list, TransaccionDTO.class);
            return Optional.ofNullable(transaccionesDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TransaccionDTO>> findList(Optional<List<Transaccion>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TransaccionDTO> oneToDto(Optional<Transaccion> one) {
        if (one.isPresent()) {
            TransaccionDTO transaccionesDTO = MapperUtils.DtoFromEntity(one.get(), TransaccionDTO.class);
            return Optional.ofNullable(transaccionesDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findAll() {
        return findList(transaccionRepository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return oneToDto(transaccionRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate) {
       return findList(transaccionRepository.findByPermisoIdAndFechaRegistroBetween(permisoId, startDate, endDate)); 
    }
 
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate) {
       return findList(transaccionRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate) {
      return findList(transaccionRepository.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate));
    }

    @Override
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
       return findList(transaccionRepository.findByFechaRegistroBetween(startDate, endDate));
    }
 
    @Override
    public TransaccionDTO create(TransaccionDTO transaccionDTO) {
        Transaccion usuario = MapperUtils.EntityFromDto(transaccionDTO, Transaccion.class);
        usuario = transaccionRepository.save(usuario);
        return MapperUtils.DtoFromEntity(usuario, TransaccionDTO.class);
    }
}

