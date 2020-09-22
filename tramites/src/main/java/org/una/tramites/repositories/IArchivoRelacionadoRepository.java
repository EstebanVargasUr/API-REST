package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author Esteban Vargas
 */
public interface IArchivoRelacionadoRepository extends JpaRepository<ArchivoRelacionado, Long>{
    
    public List<ArchivoRelacionadoDTO> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<ArchivoRelacionadoDTO>>  findByEstado(boolean estado);
    
    public Optional<List<ArchivoRelacionadoDTO>>  findByTipo(boolean tipo);
    
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<ArchivoRelacionadoDTO> findByTramiteRegistradoId(Long id);
}
