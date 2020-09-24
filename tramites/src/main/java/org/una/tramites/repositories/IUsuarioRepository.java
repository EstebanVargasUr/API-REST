package org.una.tramites.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;

public interface IUsuarioRepository extends JpaRepository<UsuarioDTO, Long> {

    public UsuarioDTO findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);

    public List<UsuarioDTO> findByCedulaContaining(String cedula);

    public List<UsuarioDTO> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
    
    public Optional <UsuarioDTO> findByCedula(String cedula);
  
 // @Query("select u from Usuario u where UPPER(u.nombreCompleto) like CONCAT('%',UPPER(:nombreCompleto),'%')\"") 
//  public Usuario findNombreCompletoWithLikeSQL(@Param("nombreCompleto")String nombreCompleto);
    public List<UsuarioDTO> findByDepartamentoId(Long id);

    @Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    public UsuarioDTO findJefeByDepartamento(Long id);

     public List<PermisoOtorgadoDTO> findPermisosOtorgadosByCedula(String cedula);

}
