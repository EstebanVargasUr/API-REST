package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.TramiteTipo;

public interface ITramiteTipoRepository extends JpaRepository<TramiteTipoDTO, Long> {

   Optional<List<TramiteTipoDTO>> findByEstado(boolean estado);
    public Optional<List<TramiteTipoDTO>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin);
//        @Query("select u from Usuario u where UPPER(u.nombreCompleto) like CONCAT('%',UPPER(:nombreCompleto),'%')\"") 
//        public Usuario findNombreCompletoWithLikeSQL(@Param("nombreCompleto")String nombreCompleto);
    public List<TramiteTipoDTO> findByDepartamentoId(Long id);
}
