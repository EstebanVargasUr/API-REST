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
    
    public List<Nota> findByTituloContainingIgnoreCase(String titulo);
    
    public Optional<List<Nota>>  findByEstado(boolean estado);
    
    public Optional<List<Nota>>  findByTipo(boolean tipo);
    
    public Optional<List<Nota>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<Nota> findByTramiteRegistradoId(Long id);
}
