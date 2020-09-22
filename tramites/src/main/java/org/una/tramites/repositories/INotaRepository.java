package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;

/**
 *
 * @author Esteban Vargas
 */
public interface INotaRepository extends JpaRepository<Nota, Long> {
    
    public List<NotaDTO> findByTituloContainingIgnoreCase(String titulo);
    
    public Optional<List<NotaDTO>>  findByEstado(boolean estado);
    
    public Optional<List<NotaDTO>>  findByTipo(boolean tipo);
    
    public Optional<List<NotaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<NotaDTO> findByTramiteRegistradoId(Long id);
}
