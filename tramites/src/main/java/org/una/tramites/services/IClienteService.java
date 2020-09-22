package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;

public interface IClienteService {
    public Optional<List<ClienteDTO>> findAll();

    public Optional<ClienteDTO> findById(Long id);

     public Optional<List<ClienteDTO>> findByCedulaAproximate(String cedula);

    public Optional<List<ClienteDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);
    
    public Optional<List<ClienteDTO>>  findByEstado(boolean estado);

     public Optional<List<ClienteDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
     
    public ClienteDTO create(ClienteDTO cliente);

    public Optional<ClienteDTO> update(ClienteDTO cliente, Long id);

    public void delete(Long id);

    public void deleteAll();
}
