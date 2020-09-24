package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author Esteban Vargas
 */
public interface IArchivoRelacionadoRepository extends JpaRepository<ArchivoRelacionado, Long>{
    
    public List<ArchivoRelacionado> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<ArchivoRelacionado>>  findByEstado(boolean estado);
    
    public Optional<List<ArchivoRelacionado>>  findByTipo(boolean tipo);
    
    public Optional<List<ArchivoRelacionado>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<ArchivoRelacionado> findByTramiteRegistradoId(Long id);
}
