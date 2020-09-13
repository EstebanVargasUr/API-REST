package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Cliente;
import org.una.tramites.entities.Usuario;
import org.una.tramites.jwt.JwtProvider;
import org.una.tramites.repositories.IClienteRepository;
import org.una.tramites.repositories.IUsuarioRepository;

@Service
public class ClienteServiceImplementation implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private void encriptarPassword(Cliente cliente) {
        String password = cliente.getContrasenaEncriptado();
        if (!password.isBlank()) {
            cliente.setContrasenaEncriptado(bCryptPasswordEncoder.encode(password));
        }
    } 
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Cliente>> findAll() {
        return Optional.ofNullable(clienteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    


    
    @Override
    @Transactional
    public Cliente create(Cliente cliente) {
        encriptarPassword(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Cliente> update(Cliente cliente, Long id) {
        if (clienteRepository.findById(id).isPresent()) {
             encriptarPassword(cliente);
            return Optional.ofNullable(clienteRepository.save(cliente));
        } else {
            return null;
        }

    }
    
    @Override
    public Optional<List<Cliente>> findByEstado(boolean estado) {
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
    public Optional<List<Cliente>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return Optional.ofNullable(clienteRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Cliente>> findByCedulaAproximate(String cedula) {
        return Optional.ofNullable(clienteRepository.findByCedulaContaining(cedula));
    }
    
     @Override
    public Optional<List<Cliente>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return clienteRepository.findByFechaRegistroBetween(startDate, endDate);
    }
   /* @Override
    @Transactional(readOnly = true)
    public Optional<List<Cliente>> findByCedulaAproximate(String cedula) {
        return clienteRepository.findByCedulaAproximate(cedula);
    }*/

    /*@Override
    public Optional<List<Cliente>> findByCedulaAproximate(String cedula) {
        return clienteRepository.findByCedulaAproximate(cedula);
    }*/
}

