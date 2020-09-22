package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> 
{
   @Query("SELECT u FROM Transaccion u LEFT JOIN Usuario d WHERE d.id=:usuarioId AND u.fechaRegistro=:startDate AND d.fechaRegistro=:endDate")
   public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate);
   
    @Query("SELECT u FROM Transaccion u LEFT JOIN PermisoOtorgado d WHERE d.id=:permisoId AND u.fechaRegistro=:startDate AND d.fechaRegistro=:endDate")
   public Optional<List<TransaccionDTO>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate);

    @Query("SELECT u FROM Transaccion u WHERE u.objeto=:objeto AND u.fechaRegistro=:startDate AND u.fechaRegistro=:endDate")
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate);

    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);


}

