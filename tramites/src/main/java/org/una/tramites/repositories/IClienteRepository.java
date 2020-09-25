package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    
    public List<Cliente> findByCedulaContaining(String cedula);

    public List<Cliente> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
    
    public Optional<List<Cliente>>  findByEstado(boolean estado);
    
    public Optional<List<Cliente>> findByFechaRegistroBetween(Date startDate, Date endDate);
 }

