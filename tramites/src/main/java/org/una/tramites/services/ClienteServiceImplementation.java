package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;
import org.una.tramites.repositories.IClienteRepository;

@Service
public class ClienteServiceImplementation implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private void encriptarPassword(ClienteDTO cliente) {
        String password = cliente.getPasswordEncriptado();
        if (!password.isBlank()) {
            cliente.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
    } 
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findAll() {
        return Optional.ofNullable(clienteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    @Transactional
    public ClienteDTO create(ClienteDTO cliente) {
        encriptarPassword(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> update(ClienteDTO cliente, Long id) {
        if (clienteRepository.findById(id).isPresent()) {
             encriptarPassword(cliente);
            return Optional.ofNullable(clienteRepository.save(cliente));
        } else {
            return null;
        }
    }
    
    @Override
    public Optional<List<ClienteDTO>> findByEstado(boolean estado) {
        return clienteRepository.findByEstado(estado);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        clienteRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return Optional.ofNullable(clienteRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByCedulaAproximate(String cedula) {
        return Optional.ofNullable(clienteRepository.findByCedulaContaining(cedula));
    }
    
     @Override
    public Optional<List<ClienteDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return clienteRepository.findByFechaRegistroBetween(startDate, endDate);
    }
}

