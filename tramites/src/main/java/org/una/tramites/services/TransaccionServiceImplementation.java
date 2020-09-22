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


@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    @Override
    public TransaccionDTO create(TransaccionDTO transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate) {
       return transaccionRepository.findByPermisoIdAndFechaRegistroBetween(permisoId, startDate, endDate); 
    }


 
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate) {
       return transaccionRepository.findByFechaRegistroBetween(startDate, endDate);
    }



    @Override
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate) {
      return transaccionRepository.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate);
    }

    @Override
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
       return transaccionRepository.findByFechaRegistroBetween(startDate, endDate);
    }
 
}

