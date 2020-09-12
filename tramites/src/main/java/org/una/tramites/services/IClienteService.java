package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Cliente;

public interface IClienteService {
    public Optional<List<Cliente>> findAll();

    public Optional<Cliente> findById(Long id);

     public Optional<List<Cliente>> findByCedulaAproximate(String cedula);

    public Optional<List<Cliente>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);
    
    public Optional<List<Cliente>>  findByEstado(boolean estado);

     public Optional<List<Cliente>> findByFechaRegistroBetween(Date startDate, Date endDate);
     
    public Cliente create(Cliente cliente);

    public Optional<Cliente> update(Cliente cliente, Long id);

    public void delete(Long id);

    public void deleteAll();
}
