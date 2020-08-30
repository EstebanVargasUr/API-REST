package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.PermisoOtorgado;

public interface IPermisoOtorgadoRepository extends JpaRepository<PermisoOtorgado, Long> {

    @Query("SELECT u FROM PermisoOtorgado u LEFT JOIN Usuario d WHERE d.id=:usuarioId")
    public Optional<List<PermisoOtorgado>> findByUsuarioId(Long usuarioId);

    @Query("SELECT u FROM PermisoOtorgado u LEFT JOIN Permiso d WHERE d.id=:permisoId")
    public Optional<List<PermisoOtorgado>> findByPermisoId(Long permisoId);
    
    @Query("SELECT u FROM PermisoOtorgado u LEFT JOIN Usuario d WHERE d.id=:usuarioId AND u.estado=:estado")
    public Optional<List<PermisoOtorgado>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    @Query("SELECT u FROM PermisoOtorgado u LEFT JOIN Permiso d WHERE d.id=:permisoId AND u.estado=:estado")
    public Optional<List<PermisoOtorgado>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public Optional<List<PermisoOtorgado>> findByFechaRegistroBetween(Date startDate, Date endDate);

 
}

