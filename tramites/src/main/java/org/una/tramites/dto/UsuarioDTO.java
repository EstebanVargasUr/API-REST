package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Departamento;

/**
 *
 * @author adria
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class UsuarioDTO {
 
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private String passwordEncriptado;
    private boolean estado; 
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private boolean esJefe;
    private Departamento departamento;
     
}
