package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;

public interface IUsuarioService {
    public Optional<List<UsuarioDTO>> findAll();

    public Optional<UsuarioDTO> findById(Long id);

    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula);

    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);
    
    public Optional<List<UsuarioDTO>>  findByDepartamentoId(Long id);

    @Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    public UsuarioDTO findJefeByDepartamento(Long id);

    public UsuarioDTO create(UsuarioDTO usuario);

    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id);

    public void delete(Long id);

    public void deleteAll();

    public String login(AuthenticationRequest authenticationRequest);
    
    public Optional <UsuarioDTO> findByCedula(String cedula);
    
   // public List<PermisoOtorgadoDTO> findPermisosOtorgadosByCedula(String cedula);
    
}
