package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Long> {
  
    
    public List<ClienteDTO> findByCedulaContaining(String cedula);

    public List<ClienteDTO> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
    
    public Optional<List<ClienteDTO>>  findByEstado(boolean estado);
    
    public Optional<List<ClienteDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
 }

