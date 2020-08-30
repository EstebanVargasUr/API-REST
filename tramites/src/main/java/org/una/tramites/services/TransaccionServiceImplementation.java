package org.una.tramites.services;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.repositories.ITransaccionRepository;


@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaccion> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    @Override
    public Transaccion create(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate) {
       return Optional.ofNullable(transaccionRepository.findByPermisoIdAndFechaRegistroBetween(permisoId, startDate, endDate)); 
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate) {
       return Optional.ofNullable(transaccionRepository.findByUsuarioIdAndFechaRegistroBetween(usuarioId, startDate, endDate)); 
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate) {
        return Optional.ofNullable(transaccionRepository.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.ofNullable(transaccionRepository.findByFechaRegistroBetween(startDate, endDate));
    }
 
}

