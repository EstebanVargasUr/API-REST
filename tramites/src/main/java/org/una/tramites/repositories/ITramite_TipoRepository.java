package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Tramite_Tipo;

public interface ITramite_TipoRepository extends JpaRepository<Tramite_Tipo, Long> {

   Optional<List<Tramite_Tipo>> findByEstado(boolean estado);
    public Optional<List<Tramite_Tipo>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin);
//        @Query("select u from Usuario u where UPPER(u.nombreCompleto) like CONCAT('%',UPPER(:nombreCompleto),'%')\"") 
//        public Usuario findNombreCompletoWithLikeSQL(@Param("nombreCompleto")String nombreCompleto);
    public List<Tramite_Tipo> findByDepartamentoId(Long id);
}
